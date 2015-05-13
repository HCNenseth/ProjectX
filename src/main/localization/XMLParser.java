package main.localization;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * XML parser created for scanning and importing main.localization
 * string from defined system path.
 * This parser expects XML files in the following format:
 *
 * ---
 * <language name="english">
 *     <string name="key1">value1</string>
 *     <string name="key2">value2</string>
 * </language>
 * ---
 *
 * This class is not accessible from outside this package.
 *
 * @date 2015-04-15
 * @filename XMLParser.java
 */
class XMLParser
{
    private Map<String, Map<String, String>> languages;
    private DocumentBuilderFactory dbf;
    private final static String path = "languages";

    public XMLParser()
    {
        languages = new HashMap<>();
        dbf = DocumentBuilderFactory.newInstance();
        readFiles();
    }

    /**
     * Read through all files in the given directory.
     * This process is a bit fragile against files not being
     * XML files.
     */
    private void readFiles()
    {
        File[] files = new File(path).listFiles();

        if (files == null) {
            throw new IllegalStateException(
                    "Could not locate languages directory!");
        }

        for (File f : files) {
            if (f.canRead() && f.isFile() && f.getName().contains(".xml")) {
                parseFile(f.getAbsolutePath());
            }
        }
    }

    /**
     * Inject XML file data into storage
     * @param filename - filename (absoulute) on system.
     */
    private void parseFile(final String filename)
    {
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            parseDocument(db.parse(filename));
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Parse DOM data into storage
     * @param dom
     */
    private void parseDocument(final Document dom)
    {
        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getElementsByTagName("string");

        String lang = docEle.getAttribute("name");

        if (nl != null && lang != null && nl.getLength() > 0) {
            Map<String, String> tmp = new HashMap<>();
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element)nl.item(i);
                String key = el.getAttribute("name");
                String value = el.getTextContent();
                tmp.put(key, value);
            }
            languages.put(lang, tmp);
        }
    }

    /**
     * Public method for returning value based on lang and key.
     * Throws IllegalStateException if lang does not exists.
     * @param lang
     * @param key
     * @return
     */
    public String get(String lang, String key)
    {
        if (!languages.containsKey(lang)) {
            throw new IllegalStateException("Unknown language!");
        }

        /** Since the XML files are prone to errors and missing data,
         *  return back the key if it does not exists.
         */

        if (languages.get(lang).containsKey(key)) {
            return languages.get(lang).get(key);
        } else {
            return "*" + key;
        }
    }

    /**
     * Returns a set of all available languages.
     * @return
     */
    public Set<String> getLanguages() { return languages.keySet(); }
}
