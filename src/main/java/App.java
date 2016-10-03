import task1.process.storage.WayStorage;
import task1.webAccess.MultiThreadedServer;

import java.text.ParseException;

/**
 * Created by User on 28.09.2016.
 */
public class App {
    public static void main(String[] args) throws ParseException {

        new WayStorage("ways.xml");
        MultiThreadedServer server = new MultiThreadedServer(6666);
        new Thread(server).start();

    }
}
