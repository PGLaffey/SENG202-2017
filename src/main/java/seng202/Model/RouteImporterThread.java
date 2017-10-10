package seng202.Model;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Runnable object to import routes from a separate thread
 */
public class RouteImporterThread implements Runnable {
	
    private String path;

    
    /**
     * Constructor to pass the path of a .csv to import
     * @param path - Path of the csv
     */
    public RouteImporterThread(String path) {
        this.path = path;
    }

    
    /**
     * Run the separate thread.
     */
    @Override
    public void run() {
        try {
            FileManager.routeRetriever(path);
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
