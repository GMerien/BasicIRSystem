package com.gmerien.app;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlOpener {
    protected DocumentBuilderFactory factory;
    protected DocumentBuilder builder;
    protected Document document; 

    public XmlOpener(String path) {
        factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File(path));
        }
        catch (final ParserConfigurationException e) {

            e.printStackTrace();
        }
        catch (final SAXException e) {

            e.printStackTrace();
        }
        catch (final IOException e) {

            e.printStackTrace();
        }
    }

    public FileText getFileText(){
        FileText fileText = new FileText();
        DocText docText;

        final Element root = document.getDocumentElement();
        final NodeList rootNodes = root.getChildNodes();

        NodeList docNodes = null;
        Node current = null;

        for (int i = 0; i < rootNodes.getLength(); i ++){
            if(rootNodes.item(i).getNodeName() == "DOC"){
                docText = new DocText();

                if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE){
                    docNodes = rootNodes.item(i).getChildNodes();

                    for (int j = 0; j < docNodes.getLength(); j ++){
                        current = docNodes.item(j);

                        if (current.getNodeType() == Node.ELEMENT_NODE && current.getTextContent() != ""){
                            if (current.getNodeName() == "DOCNO"){
                                docText.setId(current.getTextContent());
                            }
                            docText.add(current.getTextContent());
                        }
                    } 
                }

                fileText.add(docText);
            }
        }

        return fileText;
    }

    public Queries getQueries(String lem){
        Queries queries = new Queries();

        Query currentQuery;

        final Element root = document.getDocumentElement();
        final NodeList rootNodes = root.getChildNodes();

        NodeList topNodes = null;
        Node current = null;

        String currentNum;
        String currentString;

        for (int i = 0; i < rootNodes.getLength(); i ++){
            if(rootNodes.item(i).getNodeName() == "top"){
                currentNum = "";
                currentString = "";

                topNodes = rootNodes.item(i).getChildNodes();

                for (int j = 0; j < topNodes.getLength(); j ++){
                    current = topNodes.item(j);

                    if (current.getNodeType() == Node.ELEMENT_NODE && current.getNodeName() == "num"){
                        currentNum = current.getTextContent();
                    }
                    else if (current.getNodeType() == Node.ELEMENT_NODE && current.getNodeName() == "title"){
                        currentString = current.getTextContent();
                    }
                }
                currentQuery = new Query(currentString, lem);
                queries.addQuery(currentNum, currentQuery);
            }
        }

        return queries;
    }

    

}