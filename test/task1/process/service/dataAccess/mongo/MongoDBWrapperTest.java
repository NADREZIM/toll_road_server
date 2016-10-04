package task1.process.service.dataAccess.mongo;

import org.junit.Assert;
import org.junit.Test;
import task1.process.service.dataAccess.entity.User;
import task1.process.service.dataAccess.entity.Way;

/**
 * Created by User on 04.10.2016.
 */
public class MongoDBWrapperTest {
    static int ordinalNumber = 1234;
    static Way way = new Way(1, 2);

    @Test
    public void getUserTest() {
        MongoDBWrapper mongoDBWrapper = new MongoDBWrapper();
        User user = mongoDBWrapper.getUser(ordinalNumber);
        Assert.assertNotNull(user);
    }

    @Test
    public void updateUserTest() {
        MongoDBWrapper mongoDBWrapper = new MongoDBWrapper();
        mongoDBWrapper.updateUser(ordinalNumber, way);
        User user = mongoDBWrapper.getUser(ordinalNumber);
        Assert.assertNotNull(user.getTrip().getWays());
    }

    @Test
    public void removeDataTest(){
        MongoDBWrapper mongoDBWrapper = new MongoDBWrapper();
        mongoDBWrapper.removeData(ordinalNumber);
        User user = mongoDBWrapper.getUser(ordinalNumber);
        Assert.assertEquals(user.getTrip().getWays().size(), 0);
    }

    @Test
    public void runner()
    {
        getUserTest();
        updateUserTest();
        removeDataTest();
    }


}
