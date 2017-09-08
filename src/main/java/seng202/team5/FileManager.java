package seng202.team5;

import java.io.*;
import java.util.ArrayList;

/**
 * File Manager class reads and writes files to and from .csv files, and stores the information in the apps current storage.
 * The File Manager class also serializes and deserializes user objects.
 * @author Rebecka Cox
 */
public class FileManager {

    /**
     * Serializes an instance of the User class (exporting out of the program).
     * @param user The User object to be stored in a file
     */
    public void userSerialize(User user){
        try {
            String filePath = "SENG202-Team5-Cyclr/src/main/resources/data_files" + user.getName() + ".ser";
            FileOutputStream userOutFile = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(userOutFile);
            out.writeObject(user);
            out.close();
            userOutFile.close();

        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
    }


    /**
     * Deserializes an instance of the User class (importing into the program).
     * @param nameOfUser The name of the user to be deserialized.
     * @return The User object stored in the named file.
     */
    public User userDeserialize(String nameOfUser) {
        User user = null;
        try {
            String filePath = "SENG202-Team5-Cyclr/src/main/resources/data_files" + nameOfUser + ".ser";
            FileInputStream userInFile = new FileInputStream(filePath);
            ObjectInputStream userObjectStream = new ObjectInputStream(userInFile);
            try {
                user = (User) userObjectStream.readObject();
                userObjectStream.close();
                userInFile.close();
                return user;
            } catch (ClassNotFoundException classException) {
                System.out.println("User class not found");
                classException.printStackTrace();
            }
        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
        return user;
    }


    /**
     * Method to read a .csv file from a chosen folder.
     * @param fileName The name of the .csv file to be read.
     * @return An arrayList of the data from the .csv file.
     */
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> dataList = new ArrayList<String>();
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader buffReader = new BufferedReader(file);
            String data;
            while ((data = buffReader.readLine()) != null) {
                dataList.add(data);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return dataList;
    }


    /**
     * Function to write a .csv file to import to other instances of the app.
     * @param fileName The name of the file for the .csv to
     * @param content The content for the .csv file.
     */
    public void writeFile(String fileName, ArrayList<String> content) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(fileName));
            StringBuilder stringBuilder = new StringBuilder();
            for (String line : content) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Retrieves a list of routes from the readFile function and then converts them indevidually to Route objects stored
     * in the current storage class for that instance of the app.
     * @param currentStorage The instance of the CurrentStorage class for the open instance of the app.
     */
    public void routeRetriever(CurrentStorage currentStorage) {
        ArrayList<String> routes = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/2014-01 - Citi Bike trip data.csv");
        routes.remove(0);
        for (String route : routes) {
            String[] information = route.split(",");
            String startName = information[4];
            String endName = information[8];
            double startLatitude = Double.parseDouble(information[5]);
            double startLongitude = Double.parseDouble(information[6]);
            double endLatitude = Double.parseDouble(information[9]);
            double endLongitude = Double.parseDouble(information[10]);
            Location startLocation = new Location(startLatitude, startLongitude, startName);
            Location endLocation = new Location(endLatitude, endLongitude, endName);
            Route newRoute = new Route(startLocation, endLocation);
            currentStorage.addRoute(newRoute);
        }
    }

    /**
     * Stores the route data stored in the current storage class.
     * @param currentStorage The current instance of the CurrentStorage class of the application.
     */
    public void routeWriter(CurrentStorage currentStorage) {
        ArrayList<Route> routes = currentStorage.getRouteArray();
        ArrayList<String> strRoutes = new ArrayList<String>();
        for (Route route : routes) {
            String strRoute = "";
            String startName = route.getStart().getName();
            String endName = route.getEnd().getName();
            String startLatitude = Double.toString(route.getStart().getCoords()[0]);
            String startLongitude = Double.toString(route.getStart().getCoords()[1]);
            String endLatitude = Double.toString(route.getEnd().getCoords()[0]);
            String endLongitude = Double.toString(route.getEnd().getCoords()[1]);
            strRoute = startName + "," + startLatitude + "," + startLongitude + "," + endName + "," + endLatitude + "," + endLongitude;
            strRoutes.add(strRoute);
        }
        writeFile("SENG202-Team5-Cyclr/src/main/resources/data_files/route_data.csv", strRoutes);
    }


    /**
     * Retrieves the list of the given retailers and converts each of these to being a new instance of Retailer stored in currentStorage.
     * @param currentStorage The instance of the current storage object that the list of retailers will be added to.
     */
    public void retailerRetriever(CurrentStorage currentStorage) {
        ArrayList<String> retailers = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/Lower_Manhattan_Retailers.csv");
        retailers.remove(0);
        retailers.remove(0);
        for (String retailer : retailers) {
            String[] information = retailer.split(",");
            String retailerAddress = information[1] + information[2] + information[3] + information[4] + information[5] + information[6];
            Retailer newRetailer = new Retailer(retailerAddress, information[0], information[7], information[8]);
            currentStorage.addRetailer(newRetailer);
        }
    }


    /**
     * Retrieves the list of the given wifiHotspots and converts each item into a wifi object list in the currentStorage class.
     * @param currentStorage The current storage of the application which the Wifi hotspots will be added to.
     */
    public void wifiRetriever(CurrentStorage currentStorage) {
        ArrayList<String> wifiHotspots = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/NYC_Free_Public_WiFi_03292017.csv");
        wifiHotspots.remove(0);
        for (String wifiHotspot: wifiHotspots) {
            String[] information = wifiHotspot.split(",");
            Double wifiLatitude = new Double(information[7]);
            Double wifiLongitude = new Double(information[8]);
            String wifiName = information[0];
            String wifiProvider = information[4];
            Wifi newHotspot = new Wifi(wifiLatitude, wifiLongitude, wifiName, wifiProvider);
        }
    }
}
