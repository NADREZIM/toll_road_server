package task1.process.storage;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import task1.process.parser.DOMParser;
import task1.process.service.dataAccess.entity.Way;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by User on 01.10.2016.
 */
public class WayStorage {
    private static final Logger logger = Logger.getLogger(WayStorage.class);
    private static List<Way> resource;

    public WayStorage(){

    }

    public WayStorage(String xmlFileName) {
        DOMParser domParser = new DOMParser(xmlFileName);
        try {
            List<Way> wayList = domParser.parse();
            resource = Collections.unmodifiableList(wayList);
        } catch (ParserConfigurationException e) {
            logger.error("Error parse configuration in WayStorage const ",e);
        } catch (SAXException e) {
            logger.error("Error SAXException in WayStorage const ",e);
        } catch (IOException e) {
            logger.error("Error IOException in WayStorage const ",e);
        }
    }

    public static Way getResource(Way way) {
        int index = resource.indexOf(way);
        if(index >= 0){
            return resource.get(index);
        }
        return null;
    }
}
