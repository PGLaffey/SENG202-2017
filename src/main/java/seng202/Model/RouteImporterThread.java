package seng202.Model;

import java.util.logging.FileHandler;

public class RouteImporterThread implements Runnable {
    private String path;

    public RouteImporterThread(String path) {
        this.path = path;
    }

    public void run() {
        FileManager.routeRetriever(path);
    }
}
