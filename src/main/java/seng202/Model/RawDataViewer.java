package seng202.Model;

import java.util.ArrayList;

/**
 * The raw data viewer class of the program.
 */
public class RawDataViewer {
    ArrayList<Route> routeArrayList = new ArrayList<Route>();

    public RawDataViewer(CurrentStorage currentStorage) {
        this.routeArrayList = currentStorage.getRouteArray();
    }

}
