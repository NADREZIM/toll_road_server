package task1.webAccess;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 03.10.2016.
 */
public class MultiThreadedServer implements Runnable {
    private static final Logger logger = Logger.getLogger(MultiThreadedServer.class);

    protected int          serverPort   = 6666;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;

    public MultiThreadedServer(int port){
        this.serverPort = port;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                System.out.println("Waiting for connection.");
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                   logger.info("Server Stopped.");
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);

            }
            new Thread(
                    new WorkerRunnable(
                            clientSocket, "Multithreaded Server")
            ).start();
        }
        logger.info("Server Stopped.");
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 6666", e);
        }
    }
}
