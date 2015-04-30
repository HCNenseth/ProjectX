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
    private final String mainFile = "preferences/main.xml";
    private final String rootLevel = "preferences";
    private final String childLevel = "string";
    private final String childAttributeName = "name";

    private Map<String, String> preferences;
    private DocumentBuilderFactory dbf;
    private Document dom;

    public XMLParser()
    {
        preferences = new HashMap<>();
        dbf = DocumentBuilderFactory.newInstance();
        readFile();
    }

    /**
     * Try to read from file if possible.
     */
    private void readFile()
    {
        File file = new File(mainFile);

        if (!file.isFile()) {
            System.out.println("File: " + mainFile + " not found");
            return;
        }

        if (file.canRead()) {
            parseFile(file.getAbsolutePath());
        } else {
            System.out.println("File: " + mainFile + " read error");
        }
    }

    /**
     * Try to parse file if possible
     * @param filename - absolute filename path
     */
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

    /**
     * Parse file document
     */
    private void parseDocument()
    {
        Element root = dom.getDocumentElement();
        NodeList nl = root.getElementsByTagName(childLevel);

        for (int i = 0; i < nl.getLength(); i++) {
            Element el = (Element)nl.item(i);
            String key = el.getAttribute(childAttributeName);
            String value = el.getTextContent();
            preferences.put(key, value);
        }
    }
    /**
     * Public getter method for accessing values in the hashmap.
     * @param key - key to search for.
     * @return
     */
    public String get(String key)
    {
        return preferences.get(key);
    }

    /**
     * Put a key value pair into the hashmap. Immediately after save the
     * whole hashmap to file (overwrite)
     * @param key - key value to store.
     * @param value - value value to store.
     */
    public void put(String key, String value)
    {
        preferences.put(key, value);
        save();
    }

    /**
     * Save the complete hashmap to file.
     */
    private void save()
    {
        Document dom;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            Element rootEle = dom.createElement(rootLevel);

            // loop through the hashmap and add all the elements.
            Iterator iterator = preferences.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                String key = (String) pair.getKey();
                String value = (String) pair.getValue();

                Element e = dom.createElement(childLevel);
                e.setAttribute(childAttributeName, key);
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
            pce.printStackTrace();
        }
    }
}
