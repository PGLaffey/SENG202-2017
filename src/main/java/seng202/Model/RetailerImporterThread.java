package seng202.Model;

/**
 * Runnable object to import retailers from a separate thread
 */
public class RetailerImporterThread implements Runnable {
    private String path;


    public RetailerImporterThread(String path) {
        this.path = path;
    }

    
    @Override
    public void run() {
        FileManager.retailerRetriever(path);
    }
}
