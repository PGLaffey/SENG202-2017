package seng202.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File Manager class reads and writes files to and from .csv files, and stores the information in the apps current storage.
 * The File Manager class also serializes and deserializes user objects.
 */
public class FileManager {

    /**
     * Serializes an instance of the User class (exporting out of the program).
     * @param user The User object to be stored in a file
     */
    public static void userSerialize(User user, String filePath){
        try {
            File newFile = new File(filePath + "/" + user.getUsername() + ".ser");
            FileOutputStream userOutFile = new FileOutputStream(newFile);
            ObjectOutputStream out = new ObjectOutputStream(userOutFile);
            out.writeObject(user);
            out.flush();
            out.close();
            userOutFile.close();

        } catch (IOException ioExcept) {
            ioExcept.printStackTrace();
        }
    }


    /**
     * Deserializes an instance of the User class (importing into the program).
     * @return The User object stored in the named file.
     */
    public static User userDeserialize(String filePath) {
        User user = null;
        try {
            FileInputStream userInFile = new FileInputStream(filePath);
            ObjectInputStream userObjectStream = new ObjectInputStream(userInFile);
            try {
                user = (User) userObjectStream.readObject();

                userObjectStream.close();
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


    private static int indexer(int index, List header) {
        if (index < 0) {
            index += header.size();
        }
        return index;
    }


    /**
     * Retrieves a list of routes from the readFile function and then converts them individually to Route objects stored
     * in the current storage class for that instance of the app.
     */
    public static void routeRetriever(String filename) {
        ArrayList<String> routes = readFile(filename);

        if (!(routes.isEmpty())) {
            List header = Arrays.asList(routes.get(0).split("\",\""));

            //Get the index of each of the key fields for the route class from the header of the csv.
            int startNameIndex = indexer(header.indexOf("start station name"),header);
            int startLatIndex = indexer(header.indexOf("start station latitude"), header);
            int startLongIndex = indexer(header.indexOf("start station longitude"), header);
            int endNameIndex = indexer(header.indexOf("end station name"), header);
            int endLatIndex = indexer(header.indexOf("end station latitude"), header);
            int endLongIndex = indexer(header.indexOf("end station longitude"), header);
            int bikeIdIndex = indexer(header.indexOf("bikeid"), header);
            int genderIndex = indexer(header.indexOf("gender"), header);

            routes.remove(0);

            for (String route : routes) {
                ArrayList<String> information = new ArrayList<String>(Arrays.asList(route.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));

                //Obtain the relevant information from the csv.
                String bikeID = information.get(bikeIdIndex).substring(1, information.get(bikeIdIndex).lastIndexOf("\""));
                String startName = information.get(startNameIndex).substring(1, information.get(startNameIndex).lastIndexOf("\""));
                String endName = information.get(endNameIndex).substring(1, information.get(endNameIndex).lastIndexOf("\""));
                double startLatitude = Double.parseDouble(information.get(startLatIndex).substring(1, information.get(startLatIndex).lastIndexOf("\"")));
                double startLongitude = Double.parseDouble(information.get(startLongIndex).substring(1, information.get(startLongIndex).lastIndexOf("\"")));
                double endLatitude = Double.parseDouble(information.get(endLatIndex).substring(1, information.get(endLatIndex).lastIndexOf("\"")));
                double endLongitude = Double.parseDouble(information.get(endLongIndex).substring(1, information.get(endLongIndex).lastIndexOf("\"")));
                String gender = information.get(genderIndex).substring(1, information.get(genderIndex).lastIndexOf("\""));

                //Convert the relevant data into the associated classes
                Location startLocation = new Location(startLatitude, startLongitude, startName, 4);
                Location endLocation = new Location(endLatitude, endLongitude, endName, 4);
                Route newRoute = new Route(bikeID, startLocation, endLocation, "NA", gender);

                //Log the new object into the storage class.
                CurrentStorage.addNewRoute(newRoute);
            }
        }
    }


    /**
     * Stores the route data stored in the current storage class.
     * @param filename The filename where the csv file is to be stored.
     */
    public static void routeWriter(String filename, ArrayList<Route> routeArray) {
        File file = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("\"start station name\",\"start station latitude\",\"start station longitude\"," +
                    "\"end station name\",\"end station latitude\",\"end station longitude\",\"bikeid\",\"gender\"\n");
            for (Route route : routeArray) {
                String startName = route.getStart().getName();
                String endName = route.getEnd().getName();
                String startLatitude = Double.toString(route.getStart().getCoords()[0]);
                String startLongitude = Double.toString(route.getStart().getCoords()[1]);
                String endLatitude = Double.toString(route.getEnd().getCoords()[0]);
                String endLongitude = Double.toString(route.getEnd().getCoords()[1]);
                String bikeID = route.getBikeID();
                String gender = route.getGender();

                String strRoute = "\"" + startName + "\",\"" + startLatitude + "\",\"" + startLongitude + "\",\"" + endName + "\",\"" + endLatitude + "\",\"" + endLongitude + "\",\"" + bikeID + "\",\"" + gender + "\"\n";
                bufferedWriter.write(strRoute);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves the list of the given retailers and converts each of these to being a new instance of Retailer stored in currentStorage.
     * @param filename The filename where the csv file is stored.
     */
    public static void retailerRetriever(String filename) {
        ArrayList<String> retailers = readFile(filename);

        if (!(retailers.isEmpty())) {
            List header = Arrays.asList(retailers.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));

            int retailerName = header.indexOf("CnBio_Org_Name");
            int addrLine1Index = header.indexOf("CnAdrPrf_Addrline1");
            int retailerZip = header.indexOf("CnAdrPrf_ZIP");
            int retailerPrimary = header.indexOf("Primary");
            int retailerSecondary = header.indexOf("Secondary");

            retailers.remove(0);

            if (retailers.get(0).split(",")[0].equals("CnBio_Org_Name")) {
                retailers.remove(0);
            }

            for (String retailer : retailers) {
                String[] information = retailer.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Creates a new instance of retailer.
                Retailer newRetailer = new Retailer(information[addrLine1Index], information[retailerName], information[retailerPrimary], information[retailerSecondary]);

                //Checks if the zip of the retailer needs to be changed
                if (!information[retailerZip].equals("")) {
                    newRetailer.setZip(Integer.parseInt(information[retailerZip]));
                }

                //Add the retailer to the storage class.
                CurrentStorage.addNewRetailer(newRetailer);
            }
        }
    }


    /**
     * Converts an arrayList of Retailers to a csv file.
     * @param filename The file where the csv of retailers is to be written.
     * @param retailers The arrayList of retailers to be stored as a csv file.
     */
    public static void retailerWriter(String filename, ArrayList<Retailer> retailers) {
        File newFile = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            if (!retailers.isEmpty()) {
                bufferedWriter.write("CnBio_Org_Name,CnAdrPrf_Addrline1,CnAdrPrf_ZIP,Primary,Secondary\n");
                for (Retailer retailer : retailers) {
                    //Obtain relevant information to write.
                    String name = retailer.getName();
                    String address = retailer.getAddress();
                    String zip = Integer.toString(retailer.getZip());
                    String product = retailer.getProduct();
                    String description = retailer.getDescription();
                    String strRetailer = name + "," + address + "," + zip + "," + product + "," + description + "\n";
                    bufferedWriter.write(strRetailer);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retrieves the list of the given wifiHotspots and converts each item into a wifi object list in the currentStorage class.
     * @param filename The file that the csv to be read is in.
     */
    public static void wifiRetriever(String filename) {
        ArrayList<String> wifiHotspots = readFile(filename);

        if (!(wifiHotspots.isEmpty())) {
            List header = Arrays.asList(wifiHotspots.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));

            int wifiLatIndex = header.indexOf("LAT");
            int wifiLongIndex = header.indexOf("LON");
            int wifiNameIndex = header.indexOf("NAME");
            int wifiSSIDIndex = header.indexOf("SSID");
            int wifiProviderIndex = header.indexOf("PROVIDER");
            int wifiBoroughIndex = header.indexOf("BORONAME");
            int wifiTypeIndex = header.indexOf("TYPE");

            wifiHotspots.remove(0);
            for (String wifiHotspot: wifiHotspots) {
                String[] information = wifiHotspot.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                //Obtains the relevant information
                Double wifiLatitude = new Double(information[wifiLatIndex]);
                Double wifiLongitude = new Double(information[wifiLongIndex]);
                String wifiName = information[wifiNameIndex];
                String wifiProvider = information[wifiProviderIndex];
                String borough = information[wifiBoroughIndex];
                String type = information[wifiTypeIndex];
                String ssid = information[wifiSSIDIndex];

                //Creates a new Wifi object.
                Wifi newHotspot = new Wifi(wifiLatitude, wifiLongitude, wifiName, ssid, type, wifiProvider);
                newHotspot.setBorough(borough);
                CurrentStorage.addNewWifi(newHotspot);
            }
        }
    }


    /**
     * Writes a csv file of wifi hotspots to a specified file.
     * @param filename The filename for the list of wifi objects to be written to.
     * @param wifis The list of wifi objects to be converted to a csv file.
     */
    public static void wifiWriter(String filename, ArrayList<Wifi> wifis) {
        //filename = filenameConverter(filename);
        File newFile = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            if (!(wifis.isEmpty())) {
                String header = "LAT,LON,NAME,PROVIDER,SSID,BORONAME,TYPE\n";
                bufferedWriter.write(header);
                for (Wifi  wifi : wifis) {
                    String SSID = wifi.getSsid();
                    String name = wifi.getName();
                    String wifiLatitude = Double.toString(wifi.getCoords()[0]);
                    String wifiLongitude = Double.toString(wifi.getCoords()[1]);
                    String wifiType = wifi.getType();
                    String wifiBorough = wifi.getBorough();
                    String wifiProvider = wifi.getProvider();
                    String strWifi = wifiLatitude + "," + wifiLongitude + "," + name + "," + wifiProvider + "," + SSID + "," + wifiBorough + "," + wifiType + "\n";
                    bufferedWriter.write(strWifi);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Retrieves a list of toilet location from a csv file and stores them in the current storage.
     * @param filename The file where the list of toilets is found.
     */
    public static void toiletRetriever(String filename) {
        ArrayList<String> toilets = readFile(filename);

        if (!(toilets.isEmpty())) {
            List header = Arrays.asList(toilets.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));

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

                CurrentStorage.addNewToilet(newToilet);
            }
        }
    }


    /**
     * Writes an arrayList of toilet objects to a string.
     * @param filename The file where the csv will be placed.
     * @param toilets The arrayList of toilet objects to be stored in the csv.
     */
    public static void toiletWriter(String filename, ArrayList<Toilet> toilets) {
        File newFile = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            if (!(toilets.isEmpty())) {
                String header = "name,latitude,longitude,disabled access,unisex\n";
                bufferedWriter.write(header);
                for (Toilet toilet : toilets) {
                    String toiletName = toilet.getName();
                    String toiletLat = Double.toString(toilet.getLatitude());
                    String toiletLon = Double.toString(toilet.getLongitude());
                    String accessable = Boolean.toString(toilet.getForDisabled());
                    String unisex = Boolean.toString(toilet.getUniSex());
                    String strToilet = toiletName + "," + toiletLat + "," + toiletLon + "," + accessable + "," + unisex + "\n";
                    bufferedWriter.write(strToilet);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads Points Of Interest from a csv file and stores them in the current storage class.
     * @param filename The file where the new points of interest csv is.
     */
    public static void poiRetriever(String filename) {
        ArrayList<String> pois = readFile(filename);

        if (!(pois.isEmpty())) {
            List header = Arrays.asList(pois.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));

            //Finds locations of information in header.
            int poiNameIndex = header.indexOf("name");
            int poiLatIndex = header.indexOf("latitude");
            int poiLonIndex = header.indexOf("longitude");
            int poiDescriptionIndex = header.indexOf("description");
            int poiCostIndex = header.indexOf("cost");

            pois.remove(0);

            for (String poi : pois) {
                String[] information = poi.split(",");

                // Obtains relevant information
                String poiName = information[poiNameIndex];
                Double poiLatitude = Double.parseDouble(information[poiLatIndex]);
                Double poiLongitude = Double.parseDouble(information[poiLonIndex]);
                String poiDescription = information[poiDescriptionIndex];
                double poiCost = Double.parseDouble(information[poiCostIndex]);

                // Creates new Poi object
                Poi newPoi = new Poi(poiLatitude, poiLongitude, poiName, poiDescription, poiCost);

                // Stores new object in the current storage class
                CurrentStorage.addNewPoi(newPoi);
            }
        }
    }


    /**
     * Writes a csv file of a supplied arrayList of Poi objects.
     * @param filename The filename for the csv to be saved to.
     * @param pois The list of points of interest to be saved as a csv.
     */
    public static void poiWriter(String filename, ArrayList<Poi> pois) {
        File newFile = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            if (!(pois.isEmpty())) {
                String header = "name,latitude,longitude,description,cost\n";
                bufferedWriter.write(header);
                for (Poi poi : pois) {
                    String poiName = poi.getName();
                    String poiLat = Double.toString(poi.getLatitude());
                    String poiLon = Double.toString(poi.getLongitude());
                    String poiDescription = poi.getDescription();
                    String poiCost = Double.toString(poi.getCost());

                    String strPoi = poiName + "," + poiLat + "," + poiLon + "," + poiDescription + "," + poiCost + "\n";
                    bufferedWriter.write(strPoi);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generalRetriever(String filename) {
        ArrayList<String> locations = readFile(filename);
        if (!locations.isEmpty()) {
            List header = Arrays.asList(locations.get(0).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));

            int nameIndex = header.indexOf("name");
            int latIndex = header.indexOf("latitude");
            int lonIndex = header.indexOf("longitude");

            locations.remove(0);

            for (String location : locations) {
                String[] information = location.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String name = information[nameIndex];
                Double latitude = Double.parseDouble(information[latIndex]);
                Double longitude = Double.parseDouble(information[lonIndex]);

                Location newLoc = new Location(latitude, longitude, name, 4);
                CurrentStorage.addNewGeneral(newLoc);
            }
        }
    }

    public static void generalWriter(String filename, ArrayList<Location> locations) {
        File newFile = new File(filename);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));
            if (!locations.isEmpty()) {
                String header = "name, latitude, longitude";
                bufferedWriter.write(header);
                for (Location location : locations) {
                    String name = location.getName();
                    String latitude = Double.toString(location.getLatitude());
                    String longitude = Double.toString(location.getLongitude());

                    String strLocation = name + "," + latitude + "," + longitude + "\n";
                    bufferedWriter.write(strLocation);
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
