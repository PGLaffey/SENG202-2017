package seng202.Model;

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
        FileManager.routeRetriever(path);
    }
}
