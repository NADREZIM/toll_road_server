package task1.process.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import task1.process.service.dataAccess.entity.Way;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 30.09.2016.
 */
public class DOMParser {

    private String xmlFileName;

    public DOMParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * @author - Borisov Artem
     * @return - <Way> objects list. Each of them have full filled fields
     * @throws ParserConfigurationException, SAXException, IOException
     */
    public List<Way> parse() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(xmlFileName);

        int pointOfJourneyCount = document.getElementsByTagName("pointOfJourney").getLength();

        List<Way>wayList = new ArrayList<Way>();

        for (int i = 0; i < pointOfJourneyCount; i++) {
            Way way = new Way();

            Node journeyText = document.getElementsByTagName("pointOfJourney").item(i);
            String startString = findValueForTagName(journeyText, "st3:startPoint");
            String endString = findValueForTagName(journeyText, "st3:endPoint");
            String fareString = findValueForTagName(journeyText,"st3:fare");

            int startDigit = Integer.parseInt(startString);
            int endDigit = Integer.parseInt(endString);
            double fareDigit = Double.parseDouble(fareString);

            way.setStartPoint(startDigit);
            way.setEndPoint(endDigit);
            way.setLocalFare(fareDigit);

            wayList.add(way);
                }

        return wayList;
    }

    /**
     * @author - Borisov Artem
     * param nodeElement - required node in xml document
     * param tagName - mane of the tag in node element
     * @return - tag value in the document
     */
    private String findValueForTagName(Node nodeElement, String tagName) {
        String res = null;
        if (nodeElement != null) {
            Element element = (Element) nodeElement;
            Node node = element.getElementsByTagName(tagName).item(0);
            if (node != null) {
                res = node.getFirstChild().getNodeValue();
            }
        }
        return res;
    }


}
