package seng202.Model;

/**
 * Runnable object to import POIs from a separate thread
 */
public class PoiImporterThread implements Runnable {
    String path;

    /**
     * Constructor to pass the path of a .csv to import
     * @param path - path of the csv.
     */
    public PoiImporterThread(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        FileManager.poiRetriever(path);
    }
}

