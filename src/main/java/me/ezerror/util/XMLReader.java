package me.ezerror.util;


import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

/**
 * @author ：师源
 * @date ：Created in 2020/11/12 15:45
 * @description：
 * @modified By：
 * @version:
 */
public class XMLReader {

    private Element root;

    public XMLReader(String xmlStr) throws DocumentException {
        readXml(xmlStr);
    }

    public XMLReader(String xmlStr, Map<String, String> map) throws DocumentException {
        readXml(xmlStr, map);
    }

    public void readXml(String xmlStr) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(xmlStr.getBytes()));
        root = document.getRootElement();
    }

    public void readXml(String xmlStr, Map<String, String> map) throws DocumentException {
        SAXReader reader = new SAXReader(new DocumentFactory());
        reader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document document = reader.read(new ByteArrayInputStream(xmlStr.getBytes()));
        root = document.getRootElement();
    }

    public List<Node> getNodeByTagName(String tagName) {
        if (root != null && !"".equals(tagName) && tagName != null) {
            return root.selectNodes(tagName);
        }
        return null;
    }

    public Node getSingleNodeByTagName(String tagName) {
        if (root != null && !"".equals(tagName) && tagName != null) {
            return root.selectSingleNode(tagName);
        }
        return null;
    }

    public String getSingleNodeTextByTagName(String tagName) {
        if (root != null && !"".equals(tagName) && tagName != null) {
            Node node = root.selectSingleNode(tagName);
            if (node != null) {
                return node.getText().trim();
            }
        }
        return null;
    }

}
