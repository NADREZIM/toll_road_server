package task1.process.service.dataAccess.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import org.apache.log4j.Logger;
import org.bson.Document;
import task1.process.service.dataAccess.DBWrapper;
import task1.process.service.dataAccess.entity.Trip;
import task1.process.service.dataAccess.entity.User;
import task1.process.service.dataAccess.entity.Way;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by User on 30.09.2016.
 */
public class MongoDBWrapper implements DBWrapper {
    private static final Logger logger = Logger.getLogger(MongoDBWrapper.class);
    @Override
    /**
     * @author - Borisov Artem
     * @param ordinalNumber - user ordinal number in data base
     * @return - <User> object which is fulfilled with  fields from the data base
     */
    public User getUser(int ordinalNumber) {
        BasicDBObject query = new BasicDBObject();
        query.put("ordinalNumber", ordinalNumber);
        FindIterable<Document> docElement = DBInitializer.getDataBase().getCollection("user").find(query);
        User user = null;
        Trip trip = null;
        for (Document doc : docElement) {
            user = new User();
            user.setOrdinalNumber(doc.getInteger("ordinalNumber"));
            user.setEmail(doc.getString("email"));
            if(doc.containsKey("trip")){
                Document innerDoc = (Document) doc.get("trip");
                trip = new Trip();
                {
                    String entryTime = innerDoc.getString("entryTime");
                    if (entryTime != null && entryTime.length() > 0) {
                        SimpleDateFormat original = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);
                        try {
                            trip.setEntryTime(original.parse(entryTime));
                        } catch (ParseException e) {
                            logger.error("Error while parsing entryTime ",e);
                        }
                    }
                }
                {
                    String exitTime = innerDoc.getString("exitTime");
                    if (exitTime != null && exitTime.length() > 0) {
                        SimpleDateFormat original = new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH);
                        try {
                            trip.setExitTime(original.parse(exitTime));
                        } catch (ParseException e) {
                            logger.error("Error while parsing exitTime ",e);
                        }
                    }
                }
                if(innerDoc.containsKey("ways")){
                    List<Document> waysDoc = (List<Document>) innerDoc.get("ways");
                    for (Document item : waysDoc) {
                        trip.getWays().add(new Way(
                                    item.getInteger("startPoint"),
                                    item.getInteger("endPoint"),
                                    item.getDouble("localFare")));
                    }
                }
            }
            user.setTrip(trip);
        }
       logger.info("necessary user is: "+ user);
        return user;
    }
    /**
     * @author - Borisov Artem
     * @param ordinalNumber - user ordinal number in data base
     * @param way - object with a startPoint and endPoint parameters
     */
    @Override
    public void updateUser(final int ordinalNumber, Way way) {
        DBInitializer.getDataBase().getCollection("user").updateOne(
                new Document("$and",
                        new ArrayList<BasicDBObject>(){{
                         add( new BasicDBObject("ordinalNumber", ordinalNumber));
                         add( new BasicDBObject("trip.entryTime",
                                 new BasicDBObject("$eq", "")));
                            }
                        }
                        ),
                new Document("$set",
                        new Document("trip.entryTime",
                                new SimpleDateFormat("MMM d, yyyy HH:mm:ss a", Locale.ENGLISH).format(
                                        new Date(System.currentTimeMillis())
                                )))
        );
        DBInitializer.getDataBase().getCollection("user").updateOne(new Document("ordinalNumber", ordinalNumber),
                new Document("$push",new Document("trip.ways", new Document()
                            .append("startPoint",way.getStartPoint())
                            .append("endPoint",way.getEndPoint())
                            .append("localFare",way.getLocalFare()))));
    }

    @Override
    public void removeData(int ordinalNumber) {
        DBInitializer.getDataBase().getCollection("user").updateOne(new Document("ordinalNumber", ordinalNumber),
                new Document("$set",new Document("trip.entryTime", "")
                                .append("trip.exitTime","")
                                .append("trip.ways", new ArrayList<DBObject>())
                                .append("trip.finalFare",0)
                ));
    }
}




