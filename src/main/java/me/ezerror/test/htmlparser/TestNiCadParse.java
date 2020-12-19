package me.ezerror.test.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestNiCadParse {

    private final static String header = "<table style='width:1000px; border:2px solid lightgrey; border-radius:8px;'>\n";
    private final static String model =
            "    <tr>\n" +
                    "        <td style='background-color:white'>\n" +
                    "            <p style='font-size:14pt'><b>Class ##no##:</b> &nbsp; ##desc##</p>\n" +
                    "            <table cellpadding=4 border=2>\n" +
                    "##tablehtml##" +
                    "            </table>\n" +
                    "        </td>\n" +
                    "    </tr>\n";

    private final static String footer = "</table>\n<br>";

    public static void main(String[] args) throws IOException {
        // 汉化
        convertHtmlToCn("test.html", "test_cn.html");
        // 格式化
        formatByJavaPath("test_cn.html", "formattest_cn.html");
    }

    private static void formatByJavaPath(String fileName, String outputFileName) throws IOException {
        Document document = Jsoup.parse(getHtml("template.html"));
        Element table = document.select("#table").first();
        HashMap<String, List<sourceToTargetsRelation>> domMap = getSourceToTargetsRelationMap(fileName);
        for (Map.Entry<String, List<sourceToTargetsRelation>> entry : domMap.entrySet()) {
            List<sourceToTargetsRelation> value = entry.getValue();
            int i = 1;
            StringBuilder html = new StringBuilder(header);
            String desc = "\n" +
                    "        <tr>\n" +
                    "            <td style=\"background-color:white\">\n" +
                    "                <em style=\"font-size:14pt;color:red\">代码全路径" + entry.getKey() + "</em>\n" +
                    "            </td>\n" +
                    "        </tr>";
            html.append(desc);
            for (sourceToTargetsRelation element : value) {
                String sourceModel = model.replaceFirst("##no##", String.valueOf(i++)).replaceFirst("##desc##", element.getDesc());
                StringBuilder tableHtml = new StringBuilder(element.getSource().outerHtml());
                for (Element target : element.getTargets()) {
                    // 重新生成target id
                    Element a = target.selectFirst("a");
                    String targetId = "frag" + UUID.randomUUID().toString();
                    String onclick = a.attr("onclick").replaceAll("'.*'", "'" + targetId + "'");
                    a.attr("onclick", onclick);

                    // class=‘mid’ id绑定生成的target id
                    Element mid = target.selectFirst(".mid");
                    mid.attr("id", targetId);
                    tableHtml.append(target);
                }
                sourceModel = sourceModel.replaceFirst("##tablehtml##", tableHtml.toString());
                html.append(sourceModel);
            }

            generateFragments(entry, html);

            table.append(html.toString());
        }
        createHtml(outputFileName, document);

        // 生成list页面
        generateFragmentList(domMap);
    }

    private static void generateFragmentList(HashMap<String, List<sourceToTargetsRelation>> domMap) throws IOException {
        Document fragmentsListDoc = Jsoup.parse(getHtml("fragmentlistemplate.html"));
        Element listTable = fragmentsListDoc.select("#table").first();
        int no = 1;
        String trTpl = "<tr>\n" +
                "         <td>##no##</td>\n" +
                "         <td><a href=\"##fragmentfilename##.html\">##classname##</a></td>\n" +
                "         <td>##javapath##\n" +
                "      </tr>";
        for (String javaPath : domMap.keySet()) {
            String tr = trTpl.replaceAll("##no##", Integer.toString(no++))
                    .replaceAll("##fragmentfilename##", "fragments/" + javaPath.replace("/", "_"))
                    .replaceAll("##classname##", javaPath.substring(javaPath.lastIndexOf('/') + 1))
                    .replaceAll("##javapath##", javaPath);
            listTable.append(tr);
        }
        createHtml("fragmentlist.html", fragmentsListDoc);
    }

    private static void generateFragments(Map.Entry<String, List<sourceToTargetsRelation>> entry, StringBuilder html) throws IOException {
        Document fragmentTemp = Jsoup.parse(getHtml("fragmenttemplate.html"));
        Element fragmentTable = fragmentTemp.select("#table").first();
        fragmentTable.append(html.toString());
        String fragmentFileName = "fragments/" + entry.getKey().replace("/", "_") + ".html";
        createHtml(fragmentFileName, fragmentTemp);
    }

    private static void createHtml(String outputFileName, Document document) throws IOException {
        String rootPath = TestNiCadParse.class.getResource("/").getFile();
        String path = rootPath + outputFileName;
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(path, false);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        osw.write(document.html());
        osw.close();
    }

    private static HashMap<String, List<sourceToTargetsRelation>> getSourceToTargetsRelationMap(String fileName) throws FileNotFoundException {
        HashMap<String, List<sourceToTargetsRelation>> map = new HashMap<>();
        String html = getHtml(fileName);
        Document doc = Jsoup.parse(html);
        Elements table = doc.select("table[style='width:1000px; border:2px solid lightgrey; border-radius:8px;']");
        // 取到每一个classes
        for (Element element : table) {
            Elements subTable = element.select("td >table");
            Element p = element.select("td > p").first();
            String desc = p.text().substring(p.text().indexOf(',') + 2);
            // 遍历每一个pairs
            for (Element subTableElement : subTable) {
                Elements pairs = subTableElement.select("td");
                HashMap<String, Elements> sources = new HashMap<>();
                Elements targets = new Elements();
                for (Element pair : pairs) {
                    Elements a = pair.select("a");
                    String javaFullPath = a.text();
                    javaFullPath = javaFullPath.substring(0, javaFullPath.indexOf(':'));
                    if (javaFullPath.startsWith("source")) {
                        Elements sourceElements = sources.get(javaFullPath);
                        if (sourceElements == null) {
                            Elements elements = new Elements(pair);
                            sources.put(javaFullPath, elements);
                        }
                        else {
                            sourceElements.add(pair);
                            sources.put(javaFullPath, sourceElements);
                        }
                    }
                    else if (javaFullPath.startsWith("target")) {
                        targets.add(pair);
                    }
                }

                for (Map.Entry<String, Elements> source : sources.entrySet()) {
                    String javaPath = source.getKey();
                    Elements sourceElements = source.getValue();

                    List<sourceToTargetsRelation> sourceToTargetsRelations = map.get(javaPath);

                    if (sourceToTargetsRelations == null) {
                        sourceToTargetsRelations = new ArrayList<>();
                    }
                    for (Element sourceElement : sourceElements) {
                        sourceToTargetsRelation sourceToTargetsRelation = new sourceToTargetsRelation();
                        sourceToTargetsRelation.setDesc(desc);
                        sourceToTargetsRelation.setSource(sourceElement);
                        sourceToTargetsRelation.setTargets(targets);
                        sourceToTargetsRelations.add(sourceToTargetsRelation);
                    }
                    map.put(javaPath, sourceToTargetsRelations);
                }
            }
        }
        return map;
    }

    private static String getHtml(String filename) throws FileNotFoundException {
        String rootPath = TestNiCadParse.class.getResource("/").getFile();
        String path = rootPath + filename;
        FileInputStream is = new FileInputStream(new File(path));
        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line).append("\n");
            }
            return s.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void convertHtmlToCn(String fileName, String outFileName) throws IOException {
        String html = getHtml(fileName);
        Document doc = Jsoup.parse(html, "UTF-8");
        Element h2 = doc.selectFirst("h2");
        h2.text("相似度检测报告");
        doc.selectFirst("title").text("相似度检测报告");

        Element tbody = doc.selectFirst("tbody");
        Elements tds = tbody.select("td");
        HashMap<String, String> cnMap = new HashMap<>();
        cnMap.put("System 1", "产品代码");
        cnMap.put("System 2", "项目代码");
        cnMap.put("Clone pairs", "相似代码对个数");
        cnMap.put("Clone classes", "相似代码片段个数");
        cnMap.put("Max diff threshold", "最大差异阈值");
        cnMap.put("Clone size", "代码检测长度范围");
        cnMap.put("lines", "行");
        cnMap.put("Total functions-blind", "总检测代码片段量");
        cnMap.put("Total functions", "总检测代码片段量");
        containAndReplace(tds, cnMap);
        for (Element td : tds) {
            if (td.text().contains("Clone type")) {
                td.remove();
            }
            else if (td.text().contains("Granularity")) {
                td.remove();
            }
        }

        Elements tbodys = doc.select("table[style='width:1000px; border:2px solid lightgrey; border-radius:8px;'] >tbody");

        for (Element element : tbodys) {
            Elements ps = element.select("p");
            ps.forEach(p -> {
                containAndReplace(p, "Class", "代码片段");
                containAndReplace(p, "\\d+ fragments,", "");
                containAndReplace(p, "nominal size", "相似代码片段");
                containAndReplace(p, "lines", "行");
                containAndReplace(p, "similarity ", "相似度");
            });
        }
        createHtml(outFileName, doc);
    }

    public static void containAndReplace(Element element, String t, String r) {
        element.text(element.text().replaceAll(t, r));
    }

    public static void containAndReplace(Elements elements, HashMap<String, String> map) {
        for (Element element : elements) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                element.text(element.text().replaceAll(entry.getKey(), entry.getValue()));
            }
        }
    }

}

class sourceToTargetsRelation {

    private String desc;
    private Element source;
    private Elements targets;

    public Element getSource() {
        return source;
    }

    public void setSource(Element source) {
        this.source = source;
    }

    public Elements getTargets() {
        return targets;
    }

    public void setTargets(Elements targets) {
        this.targets = targets;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
