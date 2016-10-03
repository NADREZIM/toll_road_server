package task1.process.service.dataAccess.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by User on 28.09.2016.
 */
public class DBInitializer {
    private static MongoClient client;
    private static MongoDatabase dataBase;

    private static void init() {
        client = new MongoClient();
        dataBase = client.getDatabase("testDB");
    }

    public static MongoDatabase getDataBase(){
        if(dataBase == null){
            init();
        }
        return dataBase;
    }
}
