package task1.process.service.dataAccess;

import task1.process.service.dataAccess.entity.User;
import task1.process.service.dataAccess.entity.Way;

/**
 * Created by User on 30.09.2016.
 */
public interface DBWrapper {
    public User getUser(int ordinalNumber);
    public void updateUser(int ordinalNumber,Way way);
    public void removeData(int ordinalNumber);
}
