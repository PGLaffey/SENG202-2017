package seng202.Model;

import java.util.ArrayList;

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
