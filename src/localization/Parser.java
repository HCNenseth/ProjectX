package localization;

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
import java.util.Set;

/**
 * Created by alex on 4/15/15.
 */
class Parser
{
    /****************************
     * Proof of concept only!!! *
     ****************************/
    private Map<String, Map<String, String>> languages;
    private DocumentBuilderFactory dbf;
    private final String path = "languages";

    public Parser()
    {
        languages = new HashMap<>();
        dbf = DocumentBuilderFactory.newInstance();
        readFiles();
    }

    private void readFiles()
    {
        File[] files = new File(path).listFiles();

        if (files == null)
            throw new IllegalStateException(
                    "Could not locate languages directory!");

        for (File f : files) {
            if (f.canRead() && f.isFile()) {
                injectFile(f.getAbsolutePath());
            }
        }
    }

    private void injectFile(String filename)
    {
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            parseDocument(db.parse(filename));
        } catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch(SAXException se) {
            se.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseDocument(Document dom)
    {
        Element docEle = dom.getDocumentElement();
        NodeList nl = docEle.getElementsByTagName("string");

        Map<String, String> tmp = new HashMap<>();
        String lang = docEle.getAttribute("name");

        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element el = (Element)nl.item(i);
                String key = el.getAttribute("name");
                String value = el.getTextContent();
                tmp.put(key, value);
            }
        }
        languages.put(lang, tmp);
    }

    public String get(String lang, String key)
    {
        return languages.get(lang).get(key);
    }

    public Set<String> getLanguages()
    {
        return languages.keySet();
    }
}
