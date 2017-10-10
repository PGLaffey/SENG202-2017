package seng202.Model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Runnable object to import Wifi locations in a separate thread.
 */
public class WifiImporterThread implements Runnable {
	
    private String path;

    
    /**
     * Constructor for the thread
     * @param path - path of file to import
     */
    public WifiImporterThread(String path) {
        this.path = path;
    }

    
    /**
     * The runnable's run function, which is executed when a thread using this class is started.
     */
    @Override
    public void run(){
        try {
            FileManager.wifiRetriever(path);
        } catch (ArrayIndexOutOfBoundsException ex){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "File is not in the right format");
                    alert.show();
                }
            });
        }
    }
}
