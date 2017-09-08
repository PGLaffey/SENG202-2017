package seng202.team5;

import java.sql.*;
import java.util.ArrayList;

public class DataFetcher {
    private Connection connect = null;

	/**
	 * Getter for the Connection connect
	 */
	public Connection getConnect() {
		return connect;
	}

	/**
     * Loads a user into the application
     * @param user User to add into the application
     */
    public void loadUser(User user) {
    	String username = user.getUsername();
    	System.out.print(runQuery("SELECT * FROM tblUser WHERE "
    			+ "Username = '" + username +"'"));
    }

    /**
     * Loads a route into the application
     * @param route Route to add into the application
     */
    public void loadRoute(Route route) {
    	double[] startCoords = route.getStart().getCoords();
    	double[] endCoords = route.getEnd().getCoords();
    	Location[] vias = route.getVia();
    	if (vias[0] != null) {
    		double[] via1Coords = vias[0].getCoords();
    		if (vias[1] != null) {
    			double[] via2Coords = vias[1].getCoords();
    			if (vias[2] != null) {
    				double[] via3Coords = vias[2].getCoords();
    			}
    		}
    	}
    	System.out.print(runQuery("SELECT Route.*, Start.*, End.* FROM tblRoutes as Route "
    			+ "inner join tblLocations as Start on Route.StartID = Start.LocationID "
    			+ "inner join tblLocations as End on Route.EndID = End.LocationID"));
    }    	


    /**
     * Loads a location into the application
     * @param location Location to add into the application
     */
    public void loadLocation(Location location) {
    	double[] coords = location.getCoords();
    	int type = location.getLocationType();
    	System.out.print(runQuery("SELECT * FROM tblLocations "
    			+ "WHERE Latitude = '" + coords[0] + "' AND "
    					+ "Longitude = '" + coords[1] + "' AND "
    					+ "Type = '" + type + "'").toString());
    }

    /**
     * Loads all locations in the database into the program
     * @return An ArrayList of all locations
     */
    public ArrayList<Location> loadAllLocations() {
    	ArrayList<Location> locations = new ArrayList<Location>();
    	try {
    		//Idk what this does
    		Statement qryLoadLocations = connect.createStatement();
			ResultSet output = qryLoadLocations.executeQuery("SELECT * FROM tblLocations");
			Statement qryTypeData;
	    	ArrayList<String> typeData = new ArrayList<String>();
	    	ResultSet typeOutput;
	    	//intitilize some things
	    	double latitude;
	    	double longitude;
	    	String name;
	    	boolean local;
	    	int type;
	    	User owner;
	    	String ssid;
	    	String provider;
	    	String description;
	    	String product;
	    	double cost;
	    	boolean isDisabled;
	    	boolean unisex;
	    	int typeID;
	    	//Do something else. Idk what else
			while (output.next()) {
	    		latitude = output.getDouble(2);
	    		longitude = output.getDouble(3);
	    		name = output.getString(4);
	    		//user index 5
	    		local = output.getBoolean(6);
				type = output.getInt(7);
				qryTypeData = connect.createStatement();
				//have a switch cause why not
				switch (type) {
				case 0:
					typeID = output.getInt(8);
					typeOutput = qryTypeData.executeQuery("SELECT * FROM tblToilets WHERE ToiletID = '" + typeID + "'");
					typeOutput.next();
					isDisabled = typeOutput.getBoolean(2);
					unisex = typeOutput.getBoolean(3);
					//add things to another thing
					locations.add(new Toilet(latitude, longitude, name, isDisabled, unisex));
					break;
				// more things like case 0. whatever that is
				case 1:
					typeID = output.getInt(9);
					typeOutput = qryTypeData.executeQuery("SELECT * FROM tblPOI WHERE PoiID = '" + typeID + "'");
					typeOutput.next();
					cost = typeOutput.getDouble(2);
					description = typeOutput.getString(3);
					locations.add(new Poi(latitude, longitude, name, description, cost));
					break;
				case 2:
					typeID = output.getInt(10);
					typeOutput = qryTypeData.executeQuery("SELECT * FROM tblRetailers WHERE RetailerID = '" + typeID + "'");
					typeOutput.next();
					product = typeOutput.getString(2);
					description = typeOutput.getString(3);
					locations.add(new Retailer(latitude, longitude, name, product, description));
					break;
				case 3:
					typeID = output.getInt(11);
					typeOutput = qryTypeData.executeQuery("SELECT * FROM tblWifi WHERE WifiID = '" + typeID + "'");
					typeOutput.next();
					ssid = typeOutput.getString(2);
					provider = typeOutput.getString(3);
					locations.add(new Wifi(latitude, longitude, name, provider));					
					break;
				case 4:
					locations.add(new Location(latitude, longitude, name, 4));
					break;
				}	
	    	}
		} 
    	//Just encase all else fails
    	catch (SQLException ex) {
			printSqlError(ex);
		}
    	//return locations, which is an array list I think
    	return locations;
    }
    
    /**
     * Runs an update query on the database. Update queries are used to add data to an existing table.
     * @param update The update query to run
     */
    private void runUpdate(String update) {
    	Statement qryUpdate;

    	try {
        	qryUpdate = connect.createStatement();
			qryUpdate.executeUpdate(update);
		} catch (SQLException ex) {
			printSqlError(ex);
		}
    }
    
    /**
     * Runs any MySQL query given to it on the currently connected database.
     * Array List return format is result[Row[Column]] or: 
     * result = [[(0,0), (0,1), (0,2)],
     *           [(1,0), (1,1), (1,2)],
     *           [(2,0), (2,1), (2,2)]]
     * @param query The MySQL syntax query to run
     * @return An Array List that contains another Array List, representing the rows of the table, that contains Strings, representing the column of the data
     */
    private ArrayList<ArrayList<String>> runQuery(String query) {
    	//Creates an ArrayList that will contain each individual result as its own ArrayList
    	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    	Statement qryQuery;
    	//By using try/catch here the entire program will not crash if there is an error with a query. Instead it will return null.
		try {
			//Initializes the query statement
			qryQuery = connect.createStatement();
			//Initializes the set of results for the query
	    	ResultSet output = qryQuery.executeQuery(query);
	    	//Checks how many columns are in the result table
	    	int totalColumns = output.getMetaData().getColumnCount();
	    	int currentColumn = 1;
	    	int currentRow = 0;
	    	//Loop while there is another result (Row)
	    	while (output.next()) {
	    		//Add an empty ArrayList to allow space for the result
	    		results.add(new ArrayList<String>());
	    		//Loop while there are more columns with data for the current result
	    		while (currentColumn <= totalColumns) {
	    			//Add the data from the current result and current column to the respective position in the ArrayList
	    			results.get(currentRow).add(output.getString(currentColumn));
	    			currentColumn += 1;
	    		}
	    		currentRow += 1;
	    		currentColumn = 1;
	    	}
	    	return results;
		} catch (SQLException ex) {
			//If there is an error, print it and return null to keep the program running
			printSqlError(ex);
			return null;
		}

    }
    
    /**
     * Prints the contents of the SQL exception. This is needed for the standard exception print
     * does not give much information while this will give all the information needed to debug 
     * @param ex The SQL exception
     */
    private void printSqlError(SQLException ex) {
		System.out.println("Exception: " + ex.getMessage());
		System.out.println("State: " + ex.getSQLState());
		System.out.println("Error: " + ex.getErrorCode());
    }
    
    /**
     * Finds if a user exists in the database based off their unique username
     * @param user The user to check 
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(User user) {
    	String username = user.getUsername();
    	if (runQuery("SELECT Username FROM tblUser WHERE Username = '" + username + "'").get(0).isEmpty()) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    /**
     * Adds a user into the external database
     * @param user User to add into the external database
     */
    public void addUser(User user) {
    	String name = user.getName();
    	String firstName = "";
    	String lastName = "";
    	String password = user.getPassword();
    	String dob = user.getDob();
    	if (name.contains(" ")) {
    		firstName = name.substring(0, name.indexOf(" "));
    		lastName = name.substring(name.indexOf(" ") + 1);
    	}
    	else {
    		firstName = name;
    	}
    	String username = user.getUsername();
    	int routesCycled = user.getRoutesCycled();
    	double hoursCycled = user.getHours();
    	double distanceCycled = user.getDistance();
    	if (!userExists(user)) {
    		runUpdate("INSERT INTO tblUser "
    				+ "(FName, LName, Username, YearOfBirth, NumRoutesCycled, HoursCycled, DistanceCycled) VALUES "
    				+ "('" + firstName + "', '" + lastName + "', '" + username + "', '" + dob + "', '" + routesCycled + "', '" + hoursCycled + "', '" + distanceCycled + "')");
    	}
    	else {
    		System.out.println("Error: User already exists, cannot create new user with same username");
    	}
    }

    /**
     * Adds a route into the external database
     * @param route Route to add into the external database.
     */
    public void addRoute(Route route) {
    	String name = route.getName();
    	Location start = route.getStart();
    	Location end = route.getEnd();
    	
    	//Checks if the start and end locations exist in the database.
    	//If they do not it will create them.
    	int startID = findLocation(start);
    	int endID = findLocation(end);

    	if (startID == 0) {
    		addLocation(start);
    		startID = findLocation(start);
    	}
    	if (endID == 0) {
    		addLocation(end);
    		endID = findLocation(end);
    	}
    	
    	runUpdate("INSERT INTO tblRoutes "
    			+ "(StartID, EndID, Name) VALUES "
    			+ "('" + startID + "', '" + endID + "', '" + name + "')");
    }

    /**
     * 
     * @param location The location to find in the database.
     * @return The ID of the location in the database, returns 0 if location not found.
     */
    private int findLocation(Location location) {
    	String locationID = "0";
    	double[] coords = location.getCoords();
    	int type = location.getLocationType();
    	try {
    		locationID = runQuery("SELECT LocationID FROM tblLocations "
    				+ "WHERE Latitude = '" + coords[0] + "' AND "
    				+ "Longitude = '" + coords[1] + "' AND "
    				+ "Type = '" + type + "'").get(0).get(0);
    	}
    	catch (IndexOutOfBoundsException e) {
    		locationID = "0";
    	}
    	return Integer.parseInt(locationID);
    }
    
    /**
     * Adds a Location to the database based on what type of location it is
     * @param location The Location to be added to the database
     */
    public void addLocation(Location location) {
    	int locationID = findLocation(location);
    	if (locationID == 0) {
	    	if (location.getClass() == Toilet.class) {
	    		addToilet((Toilet) location);
	    	}
	    	else if (location.getClass() == Poi.class) {
	    		addPoi((Poi) location);
	    	}
	    	else if (location.getClass() == Retailer.class) {
	    		addRetailer((Retailer) location);
	    	}
	    	else if (location.getClass() == Wifi.class) {
	    		addWifi((Wifi) location);
	    	}
	    	else {
	    		insertLocation(location, null);
	    	}
    	}
    	else {
    		System.out.println("Error: The location is already in the database. Did you mean to update it?");
    	}
    }
    
    /**
     * Adds a Wifi to tblWifi in the database
     * @param wifi The Wifi to be added to the database
     */
    private void addWifi(Wifi wifi) {
    	String typeID;
    	String ssid = null; //Are we going to include SSID? Cause it is in the data we get
    	String provider = wifi.getProvider();
    	runUpdate("INSERT INTO tblWifi "
    			+ "(SSID, Provider) VALUES "
    			+ "('" + ssid + "', '" + provider + "')");
    	typeID = runQuery("SELECT LAST_INSERT_ID()").get(0).get(0);
    	insertLocation(wifi, typeID);
    }
    
    /**
     * Adds a retailer to tblRetailers in the database
     * @param retailer The Retailer to be added to the database
     */
    private void addRetailer(Retailer retailer) {
    	String typeID;
    	String product = retailer.getProduct();
    	String description = retailer.getDescription();
    	runUpdate("INSERT INTO tblRetailers "
    			+ "(RetailerType, Description) VALUES "
    			+ "('" + product + "', '" + description + "')");
    	typeID = runQuery("SELECT LAST_INSERT_ID()").get(0).get(0);
    	insertLocation(retailer, typeID);
    }
    
    /**
     * Adds a Poi to tblPOI in the database
     * @param poi The Poi to be added to the database
     */
    private void addPoi(Poi poi) {
    	String typeID;
    	String description = poi.getDescription();
    	double cost = poi.getCost();
    	runUpdate("INSERT INTO tblPOI "
    			+ "(Cost, Description) VALUES"
    			+ "('" + cost + "', '" + description + "')");
    	typeID = runQuery("SELECT LAST_INSERT_ID()").get(0).get(0);
    	insertLocation(poi, typeID);
    }
    
    /**
     * Adds a toilet to tblToilets in the database
     * @param toilet The Toilet to be added to the database
     */
    private void addToilet(Toilet toilet) {
    	String typeID;
    	int disabled = 0;
    	int unisex = 0;
    	if (toilet.getForDisabled()) {
    		disabled = 1;
    	}
    	if (toilet.getUniSex()) {
    		unisex = 1;
    	}
    	runUpdate("INSERT INTO tblToilets "
    			+ "(IsDisabled, MixedGender) VALUES "
    			+ "('" + disabled + "', '" + unisex + "')");
    	typeID = runQuery("SELECT LAST_INSERT_ID()").get(0).get(0);
    	insertLocation(toilet, typeID);
    }
    
    /**
     * Adds a location to tblLocations in the database
     * @param location The Location to be added to the database.
     */
    private void insertLocation(Location location, String typeID) {
    	String name = location.getName();
    	int local = 1;
    	if (location.getLocal()) {
    		local = 0;
    	}
    	User owner = location.getOwner();
    	int type = location.getLocationType();
    	double[] coords = location.getCoords();
    	double longitude = coords[1];
    	double latitude = coords[0];
    	String typeName = "";
    	switch(type) {
    	case 0:
    		typeName = "Toilet";
    		break;
    	case 1:
    		typeName = "Poi";
    		break;
    	case 2:
    		typeName = "Retailer";
    		break;
    	case 3:
    		typeName = "Wifi";
    		break;
    	}
    	if (type == 4 || typeID == null) {
        	runUpdate("INSERT INTO tblLocations "
        			+ "(Latitude, Longitude, Name, User, Public, Type) VALUES "
        			+ "('" + latitude + "', '" + longitude + "', '" + name + "', '" + owner + "', '" + local + "', '" + type + "')");

    	}
    	runUpdate("INSERT INTO tblLocations "
    			+ "(Latitude, Longitude, Name, User, Public, Type, " + typeName + "ID) VALUES "
    			+ "('" + latitude + "', '" + longitude + "', '" + name + "', '" + owner + "', '" + local + "', '" + type + "', '" + typeID + "')");
    }
    
    /**
     * Provides a request to connect to the external database.
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public void connectDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Creates an instance of the driver to connect to the database
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		//Tells the driver manager of jdbc to create a connection to a database of type mysql with the ip 222.152.179.135, through port 3306, named cyclrr, using user 'monitor' and password 'Team5Pass'
    		//Following line the 192.168.1.70 needs to be 222.152.179.135 if outside of Patrick's network
    		connect = DriverManager.getConnection("jdbc:mysql://222.152.179.135:3306/cyclrr","monitor","Team5Pass");
    	}
    	catch (SQLException ex) {
    		printSqlError(ex);
    	}
    }
    

    public void connectDbTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		System.out.println("Connecting...");
    		//Following line the 192.168.1.70 needs to be 222.152.179.135 if outside of Patrick's network
    		Connection connectTest = DriverManager.getConnection("jdbc:mysql://222.152.179.135:3306/cyclrr","monitor","Team5Pass");
    		testConnection(connectTest);
    		connectTest.close();
    	}
    	catch (SQLException ex) {
    		printSqlError(ex);
    	}
    }
    
    private void testConnection(Connection connectTest) throws SQLException {
    	System.out.println("Connected");
    	Statement qrytest = connectTest.createStatement();
    	ResultSet result = qrytest.executeQuery("SHOW TABLES");
    	while (result.next()) {
    		System.out.println(result.getString(1));
    	}
    }
    
    
    //Purely for testing, will be removed later
    public static void main(String[] argv) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	DataFetcher test = new DataFetcher();
    	//test.connectDbTest();				//Tests the connection to the database
    	test.connectDb();
    	Location testLoc1 = new Location(47.2134400, 172.1232100, "Telecom Hotspot", 3);
    	//test.loadLocation(testLoc1);		//Tests loading a valid location
    	Location testLoc2 = new Location(159.1547895, 0.3256984, "NULL", 4);
    	Route testRoute = new Route(testLoc1, testLoc2, "Test");
    	//test.loadRoute(testRoute);		//Tests loading a valid route
    	Location invalidLoc = new Location(25.1258469, 56.1658468, "NULL", 4);
    	//test.loadLocation(invalidLoc);	//Tests loading a invalid location
    }
}
