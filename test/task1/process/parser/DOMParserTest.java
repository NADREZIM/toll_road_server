package task1.process.parser;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import task1.process.service.dataAccess.entity.Way;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 04.10.2016.
 */
public class DOMParserTest {
    @Test
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        DOMParser domParser = new DOMParser("ways.xml");
        List<Way> rr = domParser.parse();
        Assert.assertNotNull(rr);
    }
}