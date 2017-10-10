package seng202.Model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Runnable object to import toilets from a separate thread
 */
public class ToiletImporterThread implements Runnable {
	
    String path;

    
    /**
     * Constructor to pass the path of a .csv to import.
     * @param path - Path of the csv
     */
    public ToiletImporterThread(String path) {
        this.path = path;
    }

    
    /**
     * Run the separate thread.
     */
    @Override
    public void run() {
        try {
            FileManager.toiletRetriever(path);
        } catch (ArrayIndexOutOfBoundsException ex){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR, "File is not in the right format");
                alert.show();
            }
        });
    }}
}
