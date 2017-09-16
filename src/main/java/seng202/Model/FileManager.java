package seng202.Model;

import java.io.*;
import java.util.ArrayList;

/**
 * File Manager class reads and writes files to and from .csv files, and stores the information in the apps current storage.
 * The File Manager class also serializes and deserializes user objects.
 * @author Rebecka Cox
 */
public class FileManager {
    private static final String DEST_TARGET = new File(seng202.Model.FileManager.class.getResource("/data_files/").getFile()).toString();

    /**
     * Serializes an instance of the User class (exporting out of the program).
     * @param user The User object to be stored in a file
     */
    public static void userSerialize(User user){
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
    public static User userDeserialize(String nameOfUser) {
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
    public static ArrayList<String> readFile(String fileName) {
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
    public static void writeFile(String destination, String fileName, ArrayList<String> content) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(destination+"/"+fileName));
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
     */
    public static void routeRetriever(ArrayList<String> routes) {
        //ArrayList<String> routes = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/2014-01 - Citi Bike trip data.csv");

        //TODO: Replace this line with one that reads the csv files more dynamically.
        routes.remove(0);

        for (String route : routes) {
            String[] information = route.split(",");

            //Obtain the relevant information from the csv.
            String bikeID = information[11];
            String startName = information[4];
            String endName = information[8];
            double startLatitude = Double.parseDouble(information[5]);
            double startLongitude = Double.parseDouble(information[6]);
            double endLatitude = Double.parseDouble(information[9]);
            double endLongitude = Double.parseDouble(information[10]);

            //Convert the relevant data into the associated classes
            Location startLocation = new Location(startLatitude, startLongitude, startName, 4);
            Location endLocation = new Location(endLatitude, endLongitude, endName, 4);
            Route newRoute = new Route(bikeID, startLocation, endLocation);

            //Log the new object into the storage class.
            CurrentStorage.addRoute(newRoute);
        }
    }

    /**
     * Stores the route data stored in the current storage class.
     */
    public static void routeWriter(String filename) {
        ArrayList<Route> routes = CurrentStorage.getRouteArray();
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
        writeFile(DEST_TARGET,filename+".csv", strRoutes);
    }


    /**
     * Retrieves the list of the given retailers and converts each of these to being a new instance of Retailer stored in currentStorage.
     */
    public static void retailerRetriever(ArrayList<String> retailers) {
        //ArrayList<String> retailers = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/Lower_Manhattan_Retailers.csv");
        retailers.remove(0);
        retailers.remove(0);

        for (String retailer : retailers) {
            String[] information = retailer.split(",");

            //Obtain relevant fields
            String retailerAddress = information[1] + information[2] + information[3] + information[4];
            int zip = Integer.parseInt(information[5]);

            //Creates a new instance of retailer.
            Retailer newRetailer = new Retailer(retailerAddress, information[0], information[7], information[8], zip);

            //Add the retailer to the storage class.
            CurrentStorage.addRetailer(newRetailer);
        }
    }

    public static void retailerWriter(String filename) {
        ArrayList<Retailer> retailers = CurrentStorage.getRetailerArray();
        ArrayList<String> strRetailers = new ArrayList<String>();
        for (Retailer retailer : retailers) {

            //Obtain relevant information to write.
            String address = retailer.getAddress();
            String product = retailer.getProduct();
            String description = retailer.getDescription();
            int zip = retailer.getZip();
            double latitude = retailer.getLatitude();
            double longitude = retailer.getLongitude();

            String strRetailer = address + "," + zip + "," + product + "," + description + "," + latitude + "," + longitude;
            strRetailers.add(strRetailer);
        }
        writeFile(DEST_TARGET, filename+".csv", strRetailers);
    }


    /**
     * Retrieves the list of the given wifiHotspots and converts each item into a wifi object list in the currentStorage class.
     */
    public static void wifiRetriever(ArrayList<String> wifiHotspots) {
        //ArrayList<String> wifiHotspots = readFile("SENG202-Team5-Cyclr/src/main/resources/data_files/NYC_Free_Public_WiFi_03292017.csv");
        wifiHotspots.remove(0);
        for (String wifiHotspot: wifiHotspots) {
            String[] information = wifiHotspot.split(",");

            //Obtains the relevant information
            Double wifiLatitude = new Double(information[7]);
            Double wifiLongitude = new Double(information[8]);
            String wifiName = information[15];
            String wifiProvider = information[4];
            String borough = information[19];
            String type = information[3];

            //Creates a new Wifi object.
            Wifi newHotspot = new Wifi(wifiLatitude, wifiLongitude, wifiName, borough, type, wifiProvider);

            CurrentStorage.addWifi(newHotspot);
        }
    }

    public static void wifiWriter(String filename) {
        ArrayList<Wifi> wifis = CurrentStorage.getWifiArray();
        ArrayList<String> strWifis = new ArrayList<String>();
        for (Wifi  wifi : wifis) {
            String strWifi = "";
            String SSID = wifi.getName();
            String wifiLatitude = Double.toString(wifi.getCoords()[0]);
            String wifiLongitude = Double.toString(wifi.getCoords()[1]);
            String wifiType = wifi.getType();
            String wifiBorough = wifi.getBorough();
            String wifiProvider = wifi.getProvider();
            //TODO: Change the order to suit the csv.
            strWifi = wifiLatitude + "," + wifiLongitude + "," + SSID + "," + wifiBorough + "," + wifiType + "," + wifiProvider;
            strWifis.add(strWifi);
        }
        writeFile(DEST_TARGET,filename+".csv", strWifis);
    }
}
