package task1.process.storage;

import org.junit.Assert;
import org.junit.Test;
import task1.process.service.dataAccess.entity.Way;

/**
 * Created by User on 04.10.2016.
 */
public class WayStorageTest {
    @Test
    public void createWayStorage() {
        Way way = new Way(1, 2);
        WayStorage wayStorage = new WayStorage("ways.xml");
        Way way1 = WayStorage.getResource(way);
        Assert.assertNotNull(way1.getLocalFare());
    }
}
