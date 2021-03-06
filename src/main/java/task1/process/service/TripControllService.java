package task1.process.service;

import org.apache.log4j.Logger;
import task1.process.service.dataAccess.DBWrapper;
import task1.process.service.dataAccess.entity.User;
import task1.process.service.dataAccess.entity.Way;
import task1.process.service.dataAccess.mongo.MongoDBWrapper;
import task1.process.service.mail.MailService;
import task1.process.storage.WayStorage;
import task1.webAccess.ClientData;

import java.util.Date;


public class TripControllService {
    private static final Logger logger = Logger.getLogger(TripControllService.class);

    /**
     * @author - Borisov Artem
     * @param data - information about the start and end points which some user want to overcome.
     * this info is comes from the client side.

     */
    public void addWayPoint(ClientData data){
        DBWrapper wrapper = new MongoDBWrapper();
        Way way = WayStorage.getResource(new Way(data.getStartPoint(), data.getEndPoint()));
        if(way != null){
            wrapper.updateUser(data.getOrdinalNumber(),way);
        } else {

            User user = wrapper.getUser(data.getOrdinalNumber());
            user.getTrip().setExitTime(new Date(System.currentTimeMillis()));

           for(Way wayPoint: user.getTrip().getWays()){
                user.getTrip().setFare(user.getTrip().getFare() + wayPoint.getLocalFare());
            }

            new MailService().sendEmailMessage(user);
            logger.info("Mail sent");
            wrapper.removeData(user.getOrdinalNumber());
        }
    }
}
