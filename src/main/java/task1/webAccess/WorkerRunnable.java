package task1.webAccess;

import org.apache.log4j.Logger;
import task1.process.service.TripControllService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by User on 03.10.2016.
 */
public class WorkerRunnable implements Runnable{
    private static final Logger logger = Logger.getLogger(WorkerRunnable.class);
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            ObjectInputStream objIn = new ObjectInputStream(input);
            ClientData inData = (ClientData) objIn.readObject();
            if(inData != null){
                new TripControllService().addWayPoint(inData);
            }
            objIn.close();
            input.close();
        } catch (IOException e) {
           logger.error("IOException while run() processing ",e);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException while run() processing",e);
        }
    }
}