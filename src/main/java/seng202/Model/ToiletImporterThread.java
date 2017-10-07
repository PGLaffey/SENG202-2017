package seng202.Model;

/**
 * Runnable object to import toilets from a separate thread
 */
public class ToiletImporterThread implements Runnable {
    String path;

    /**
     * Constructor to pass the path of a .csv to import
     * @param path - path of the csv.
     */
    public ToiletImporterThread(String path) {
        this.path = path;
    }

    @Override
    public void run() { FileManager.toiletRetriever(path);}
}
