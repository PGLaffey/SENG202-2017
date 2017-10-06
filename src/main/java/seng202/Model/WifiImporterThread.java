package seng202.Model;

/**
 * Thread to import Wifi locations in a separate thread.
 * Created by eli26 on 22/09/17.
 */
public class WifiImporterThread implements Runnable {
    private String path;

    
    public WifiImporterThread(String path) {
        this.path = path;
    }

    
    public void run(){
        FileManager.wifiRetriever(path);
    }
}
