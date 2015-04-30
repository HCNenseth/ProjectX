package main.preference;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by alex on 4/30/15.
 */
class XMLParser
{
    private Map<String, String> preferences;
    private DocumentBuilderFactory dbf;
    private final String mainFile = "preferences/main.xml";
    private final String rootLevel = "preferences";

    Document dom;

    public XMLParser()
    {
        preferences = new HashMap<>();
        dbf = DocumentBuilderFactory.newInstance();
        readFile();
    }

    private void readFile()
    {
        File file = new File(mainFile);

        if (file == null) {
            System.out.println("main.xml file not found");
            return;
        }

        if (file.canRead() && file.isFile()) {
            parseFile(file.getAbsolutePath());
        }
    }

    private void parseFile(final String filename)
    {
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(filename);
            parseDocument();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void parseDocument()
    {
        Element root = dom.getDocumentElement();
        NodeList nl = root.getElementsByTagName("string");

        for (int i = 0; i < nl.getLength(); i++) {
            Element el = (Element)nl.item(i);
            String key = el.getAttribute("name");
            String value = el.getTextContent();
            preferences.put(key, value);
        }
    }

    public String get(String key)
    {
        return preferences.get(key);
    }

    public void put(String key, String value)
    {
        preferences.put(key, value);
        save();
    }

    private void save()
    {
        Document dom;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            Element rootEle = dom.createElement(rootLevel);

            Iterator iterator = preferences.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                String key = (String) pair.getKey();
                String value = (String) pair.getValue();
                Element e = dom.createElement("string");
                e.setAttribute("name", key);
                e.appendChild(dom.createTextNode(value));
                rootEle.appendChild(e);
            }

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(mainFile)));

            } catch (TransformerException | IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }
}
