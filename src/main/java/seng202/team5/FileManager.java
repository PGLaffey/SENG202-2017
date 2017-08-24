package seng202.team5;

import java.io.FileReader;

public class FileManager {
    private FileReader reader;

    /**
     * Loads a route to into the program.
     * @param route
     */
    public void loadRoute(Route route) {

    }

    /**
     * Method to load a location and turn it into a route object.
     * @param location
     */
    public void loadLocation(Location location) {}

    /**
     * Method to read a .csv file.
     * TODO: add what the direct parameter means, and a way of determining what type of data the file contains.
     * @param direct
     * @return
     */
    // Do we want to add a type param that just tells what type of data is in the file?
    public String readFile(String direct) {
        return direct;
    }

    /**
     * Function to write a .csv file to import to other instances of the app.
     * @param direct
     * @param contents
     */
    public void writeFile(String direct, String contents) {

    }
}
