package seng202.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        ArrayList<String> dataList = new ArrayList<>();
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
    public static void routeRetriever(String filename) {
        ArrayList<String> routes = readFile(filename);

        List header = Arrays.asList(routes.get(0).split(","));

        //Get the index of each of the key fields for the route class from the header of the csv.
        int startNameIndex = header.indexOf("start station name");
        int startLatIndex = header.indexOf("start station latitude");
        int startLongIndex = header.indexOf("start station longitude");
        int endNameIndex = header.indexOf("end station name");
        int endLatIndex = header.indexOf("end station latitude");
        int endLongIndex = header.indexOf("end station longitude");
        int bikeIdIndex = header.indexOf("bikeid");

        routes.remove(0);

        for (String route : routes) {
            String[] information = route.split(",");

            //Obtain the relevant information from the csv.
            String bikeID = information[bikeIdIndex];
            String startName = information[startNameIndex];
            String endName = information[endNameIndex];
            double startLatitude = Double.parseDouble(information[startLatIndex]);
            double startLongitude = Double.parseDouble(information[startLongIndex]);
            double endLatitude = Double.parseDouble(information[endLatIndex]);
            double endLongitude = Double.parseDouble(information[endLongIndex]);

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
        ArrayList<String> strRoutes = new ArrayList<>();
        for (Route route : routes) {
            String startName = route.getStart().getName();
            String endName = route.getEnd().getName();
            String startLatitude = Double.toString(route.getStart().getCoords()[0]);
            String startLongitude = Double.toString(route.getStart().getCoords()[1]);
            String endLatitude = Double.toString(route.getEnd().getCoords()[0]);
            String endLongitude = Double.toString(route.getEnd().getCoords()[1]);
            String strRoute = startName + "," + startLatitude + "," + startLongitude + "," + endName + "," + endLatitude + "," + endLongitude;
            strRoutes.add(strRoute);
        }
        writeFile(DEST_TARGET,filename+".csv", strRoutes);
    }


    /**
     * Retrieves the list of the given retailers and converts each of these to being a new instance of Retailer stored in currentStorage.
     */
    public static void retailerRetriever(String filename) {
        ArrayList<String> retailers = readFile(filename);

        List header = Arrays.asList(retailers.get(0).split(","));

        int retailerName = header.indexOf("CnBio_Org_Name");
        int addrLine1Index = header.indexOf("CnAdrPrf_Addrline1");
        int retailerZip = header.indexOf("CnAdrPrf_ZIP");
        int retailerPrimary = header.indexOf("Primary");
        int retailerSecondary = header.indexOf("Secondary");

        retailers.remove(0);

        if (retailers.get(0).split(",")[0] == "CnBio_Org_Name") {
            retailers.remove(0);
        }

        for (String retailer : retailers) {
            String[] information = retailer.split(",");

            //Obtain relevant fields
            int zip = Integer.parseInt(information[retailerZip]);

            //Creates a new instance of retailer.
            Retailer newRetailer = new Retailer(information[addrLine1Index], information[retailerName], information[retailerPrimary], information[retailerSecondary], zip);

            //Add the retailer to the storage class.
            CurrentStorage.addRetailer(newRetailer);
        }
    }

    public static void retailerWriter(String filename) {
        ArrayList<Retailer> retailers = CurrentStorage.getRetailerArray();
        ArrayList<String> strRetailers = new ArrayList<>();
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
    public static void wifiRetriever(String filename) {
        ArrayList<String> wifiHotspots = readFile(filename);
        List header = Arrays.asList(wifiHotspots.get(0).split(","));

        int wifiLatIndex = header.indexOf("LAT");
        int wifiLongIndex = header.indexOf("LON");
        int wifiNameIndex = header.indexOf("NAME");
        int wifiProviderIndex = header.indexOf("PROVIDER");
        int wifiBoroughIndex = header.indexOf("BORONAME");
        int wifiTypeIndex = header.indexOf("TYPE");

        wifiHotspots.remove(0);
        for (String wifiHotspot: wifiHotspots) {
            String[] information = wifiHotspot.split(",");

            //Obtains the relevant information
            Double wifiLatitude = new Double(information[wifiLatIndex]);
            Double wifiLongitude = new Double(information[wifiLongIndex]);
            String wifiName = information[wifiNameIndex];
            String wifiProvider = information[wifiProviderIndex];
            String borough = information[wifiBoroughIndex];
            String type = information[wifiTypeIndex];

            //Creates a new Wifi object.
            Wifi newHotspot = new Wifi(wifiLatitude, wifiLongitude, wifiName, borough, type, wifiProvider);

            CurrentStorage.addWifi(newHotspot);
        }
    }

    /**
     * Writes a csv file of wifi hotspots to a specified file.
     * @param filename The filename for the list of wifi objects to be written to.
     * @param wifis The list of wifi objects to be converted to a csv file.
     */
    public static void wifiWriter(String filename, ArrayList<Wifi> wifis) {
        ArrayList<String> strWifis = new ArrayList<>();
        for (Wifi  wifi : wifis) {
            String SSID = wifi.getName();
            String wifiLatitude = Double.toString(wifi.getCoords()[0]);
            String wifiLongitude = Double.toString(wifi.getCoords()[1]);
            String wifiType = wifi.getType();
            String wifiBorough = wifi.getBorough();
            String wifiProvider = wifi.getProvider();
            //TODO: Change the order to suit the csv.
            String strWifi = wifiLatitude + "," + wifiLongitude + "," + SSID + "," + wifiBorough + "," + wifiType + "," + wifiProvider;
            strWifis.add(strWifi);
        }
        writeFile(DEST_TARGET,filename+".csv", strWifis);
    }


    public static void toiletRetriever(String filename) {
        ArrayList<String> toilets = readFile(filename);
        List header = Arrays.asList(toilets.get(0).split(","));

        int nameIndex = header.indexOf("name");
        int disabledAccessIndex = header.indexOf("disabled access");
        int toiletLatIndex = header.indexOf("latitude");
        int toiletLonIndex = header.indexOf("longitude");
        int unisexIndex = header.indexOf("unisex");

        toilets.remove(0);
        for (String toilet: toilets) {
            String[] information = toilet.split(",");

            //Obtains the relevant information
            String toiletName = information[nameIndex];
            boolean disabledAccess = Boolean.parseBoolean(information[disabledAccessIndex]);
            Double toiletLatitude = new Double(information[toiletLatIndex]);
            Double toiletLongitude = new Double(information[toiletLonIndex]);
            boolean unisex = Boolean.parseBoolean(information[unisexIndex]);

            //Creates a new toilet object.
            Toilet newToilet = new Toilet(toiletLatitude, toiletLongitude, toiletName, disabledAccess, unisex);

            CurrentStorage.addToilet(newToilet);
        }

    }


    public static void toiletWriter(String fileName, ArrayList<Toilet> toilets) {
        // TODO write the function here
    }


    public static void poiReader(String filename) {
        // TODO Finish writing this function
        ArrayList<String> pois = readFile(filename);
        List header = Arrays.asList(pois.get(0).split(","));

        int PoiNameIndex = header.indexOf("name");
        int PoiLatIndex = header.indexOf("latitude");
        int PoiLonIndex = header.indexOf("longitude");
        int PoiDescriptionIndex = header.indexOf("description");
        int PoiCostIndex = header.indexOf("cost");

        for (String poi : pois) {
            String[] information = poi.split(",");
            String PoiName = information[PoiNameIndex];
            Double PoiLatitude = Double.parseDouble(information[PoiLatIndex]);
            Double PoiLongitude = Double.parseDouble(information[PoiLonIndex]);
            String PoiDescription = information[PoiDescriptionIndex];

        }
    }
}
