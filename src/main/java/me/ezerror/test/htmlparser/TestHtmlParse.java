package me.ezerror.test.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHtmlParse {

    public static void main(String[] args) {
        HashMap<String, HashMap<Element, Elements>> map = new HashMap<>();
        String html = getHtml();
        Document doc = Jsoup.parse(html, "UTF-8");
        Elements table = doc.select("table[style='width:1000px; border:2px solid lightgrey; border-radius:8px;']");
        // 取到每一个classes
        for (Element element : table) {
            Elements subTable = element.select("td >table");
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
                    HashMap<Element, Elements> sourceToTargets = map.get(javaPath);

                    if (sourceToTargets == null) {
                        sourceToTargets = new HashMap<>();
                    }
                    for (Element sourceElement : sourceElements) {
                        sourceToTargets.put(sourceElement, targets);
                    }
                    map.put(javaPath, sourceToTargets);

                }


            }
        }


        System.out.println(map.size());
        for (Map.Entry<String, HashMap<Element, Elements>> stringListEntry : map.entrySet()) {

            System.out.println("=============================");
            System.out.println(stringListEntry.getKey());
            HashMap<Element, Elements> value = stringListEntry.getValue();
            int i = 1;
            for (Element element1 : value.keySet()) {
                System.out.println("片段"+(i++));
                System.out.println(element1);
                System.out.println("_____________________________________");
            }

            System.out.println("=============================");
        }

    }

    private static String getHtml() {
        InputStream resourceAsStream = TestHtmlParse.class.getResourceAsStream("/test2.html");

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(resourceAsStream);
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
}
