package com.vipapp.appmark2.xml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLObject {

    private Document source;
    private Node parsed;

    XMLObject(Node node, Document source){
        parsed = node;
        this.source = source;
    }
    public XMLObject(String xml){
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));

            // Получаем корневой элемент

            source = document;
            parsed = document.getDocumentElement();

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isArray(){
        return parsed.getChildNodes().getLength() == 1;
    }
    public XMLArray getChildren(){
        return new XMLArray(parsed.getChildNodes(), parsed, source);
    }

    public ArrayList<XMLAttribute> getAttributes(){
        ArrayList<XMLAttribute> attributes = new ArrayList<>();
        NamedNodeMap map = parsed.getAttributes();
        for(int i = 0; i < map.getLength(); i++){
            Node attr = map.item(i);
            attributes.add(new XMLAttribute(attr.getNodeName(), attr.getNodeValue()));
        }
        return attributes;
    }

    public void setAttribute(String attr_name, String attr_value){
        ((Element)parsed).setAttribute(attr_name, attr_value);
    }
    public void setName(String name){
        source.renameNode(parsed, null, name);
    }

    public String getName(){
        return parsed.getNodeName();
    }
    @NonNull
    public XMLAttribute getNamedAttribute(String name){
        Node node = getAttributeNode(name);
        if(node == null){
            return new XMLAttribute(name, null);
        }
        return new XMLAttribute(Objects.requireNonNull(node).getNodeName(), node.getNodeValue());
    }

    @Nullable
    private Node getAttributeNode(String name){
        NamedNodeMap attrs = parsed.getAttributes();
        if(hasAttribute(name)) {
            return attrs.getNamedItem(name);
        } else {
            return null;
        }
    }

    public XMLAttribute getXMLAttribute(int id){
        Node node = parsed.getAttributes().item(id);
        return new XMLAttribute(node.getNodeName(), node.getNodeValue());
    }
    public boolean hasAttribute(String name){
        NamedNodeMap attrs = parsed.getAttributes();
        for(int i = 0; i < attrs.getLength(); i++){
            if(attrs.item(i).getNodeName().equals(name)) return true;
        }
        return false;
    }
    @Nullable
    public XMLObject getNamedXMLObject(String name){
        if(parsed != null) {
            NodeList list = parsed.getChildNodes();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() != Node.TEXT_NODE && node.getNodeName().equals(name))
                    return new XMLObject(node, source);
                builder.append(node.getNodeName()).append("\n");
            }
        }
        return null;
    }
    public XMLObject getXMLObject(int pos){
        return new XMLObject(parsed.getChildNodes().item(pos), source);
    }
    public XMLArray getNamedXMLArray(String tagName){
        return new XMLArray(((Element)parsed).getElementsByTagName(tagName), parsed, source);
    }
    public String getValue(){
        return parsed.getTextContent();
    }
    public void setValue(String value){
        parsed.setTextContent(value);
    }
    public void addInside(XMLObject object){
        parsed.appendChild(source.importNode(object.toNode(), true));
    }

    public void removeAttribute(String name){
        Node toRemove = getAttributeNode(name);
        if(toRemove != null)
            ((Attr)toRemove).getOwnerElement().removeAttribute(name);
    }

    @Nullable
    public static XMLObject parse(String xml){
        try{
            XMLObject object = new XMLObject(xml);
            return object.toNode() == null? null: object;
        } catch (Exception ignored){
            return null;
        }
    }

    @NonNull
    @Override
    public String toString() {
        try {
            return nodeToString(parsed);//.replaceAll(Const.PRETTY_XML_NEWLINE, "\n");
        } catch (Exception e) {
            return "";
        }
    }
    public Node toNode(){
        return parsed;
    }

    public XMLObject copy(){
        return new XMLObject(parsed, source);
    }

    private static String nodeToString(Node node) throws Exception{
        StringWriter sw = new StringWriter();

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }
}
