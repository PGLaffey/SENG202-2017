package seng202.Model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import static seng202.Model.CurrentStorage.getUser;

public class DataFetcher {
    private Connection connect = null;
    private boolean hasImported = false;
    static int wifiOffset = 0;
    static int retailerOffset = 0;
    static int routeOffset = 0;
    static int poiOffset = 0;
    static int toiletOffset = 0;

	/**
	 * @return true if an object has already been imported to the database
	 */
	// TODO: Change docstring
    public boolean isHasImported() {
    	return hasImported;
	}

	/**
	 * Sets the value of hasImported to true if the object is already in the database
	 * @param value boolean value for whether an object has been imported previously
	 */
	// TODO: Change docstring
	public void setHasImported(boolean value) {
    	hasImported = value;
	}

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
    	PreparedStatement preparedStatement = null;
    	String stmt = "SELECT * FROM tblUser WHERE Username = ?";

    	try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, username);
			runQuery(preparedStatement);
		} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
		}
    }

	/**
	 * Changes the password of a user in the database
	 * @param username username of the user trying to change their password
	 * @param newPassword desired password
	 */
	public void updateUserPassword(String username, String newPassword) {
    	PreparedStatement preparedStatement = null;
    	String stmt = "UPDATE tblUser SET Password = ? WHERE Username = ?";

    	try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, username);
			runUpdate(preparedStatement);
		} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
		}

    }

	/**
	 * Fetches the users password to verify that they have the correct credentials
	 * @param username Username of user logging in
	 * @return The users password if the user exists, null otherwise
	 */
	public String fetchPassword(String username) {
    	PreparedStatement preparedStatement;
    	String stmt = "SELECT Password FROM tblUser WHERE Username = ?";

    	try {
    		preparedStatement = connect.prepareStatement(stmt);
    		preparedStatement.setString(1, username);
    		return runQuery(preparedStatement).get(0).get(0);
		} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
		}
		return null;
	}

	/**
	 *
	 * @param username the username entered for login
	 * @return returns whether this is a valid user in the database
	 */
	public boolean isUser(String username) {
		PreparedStatement preparedStatement;
		String stmt = "SELECT Username FROM tblUser WHERE Username = ?";

		try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, username);
			if (runQuery(preparedStatement).isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
		return false;
	}

	/**
	 *
	 * @param username the username of the user logging in
	 * @return the LName, FName and YearOfBirth of the user from the database
	 */
	public ArrayList<String> fetchUserInfo(String username) {
		PreparedStatement preparedStatement;
		String stmt = "SELECT FName, LNAME, YearOfBirth, NumRoutesCycled, DistanceCycled, HoursCycled FROM tblUser WHERE Username = ?";

		try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, username);
			return runQuery(preparedStatement).get(0);
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
		return null;
	}

	/**
	 * Deletes the user from the database, as well as all corresponding
	 * user locations and routes,
	 * @param username Username of the user
	 */
	public void deleteUser(String username) {
		PreparedStatement preparedStatement;
		int userID;
		String stmt = "SELECT UserID FROM tblUser WHERE Username = ?";

		try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, username);
			userID = Integer.parseInt(runQuery(preparedStatement).get(0).get(0));

			stmt = "UPDATE tblLocations SET User = NULL WHERE User = ?";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, userID);
			runUpdate(preparedStatement);

			stmt = "UPDATE tblRoutes SET User = NULL WHERE User = ?";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, userID);
			runUpdate(preparedStatement);

			stmt = "DELETE FROM tblUsersRoutes WHERE UserID = ?";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, userID);
			runUpdate(preparedStatement);

			stmt = "DELETE FROM tblUsersLocations WHERE UserID = ?";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, userID);
			runUpdate(preparedStatement);

			stmt = "DELETE FROM tblUser WHERE UserID = ?";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, userID);
			runUpdate(preparedStatement);

		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}

	}

	/**
	 * Stores the new retailers, toilets, wifi's, Poi's in to the database
	 */
	public void storeCurrentStorage() {
		storeNewRetailers();
		storeNewToilets();
		storeNewWifi();
		storeNewPoi();
		storeNewGeneral();
		storeUser();
	}

	/**
	 * Updates the user's information on the database
	 */

	private void storeUser() {
		User user = getUser();
		PreparedStatement preparedStatement;
		String username = user.getUsername();
		int routesCycled = user.getRoutesCycled();
		double hoursCycled = user.getHours();
		double distanceCycled = user.getDistance();
			String stmt = "UPDATE tblUser SET NumRoutesCycled = ?, HoursCycled = ?, DistanceCycled = ? WHERE Username = ?";
			try {
				preparedStatement = connect.prepareStatement(stmt);
				preparedStatement.setInt(1, routesCycled);
				preparedStatement.setDouble(2, hoursCycled);
				preparedStatement.setDouble(3, distanceCycled);
				preparedStatement.setString(4, username);
				runUpdate(preparedStatement);
			} catch (SQLException sqlException) {
				System.out.println(sqlException.getMessage());
		}
	}

	/**
	 * Stores the new retailers from CurrentStorage into the database
	 */
	private void storeNewRetailers() {
		int count = 0;
		ArrayList<Integer> newRetailers = CurrentStorage.getAddedRetailers();
		ArrayList<Retailer> retailers = CurrentStorage.getRetailerArray();
		while (count < newRetailers.size()) {
			addLocation(retailers.get(newRetailers.get(count)));
			count += 1;
		}
	}

	/**
	 * Stores the new toilets from CurrentStorage into the database
	 */
	private void storeNewToilets() {
		int count = 0;
		ArrayList<Integer> newToilets = CurrentStorage.getAddedToilets();
		ArrayList<Toilet> toilets = CurrentStorage.getToiletArray();
		while (count < newToilets.size()) {
			addLocation(toilets.get(newToilets.get(count)));
			count += 1;
		}
	}

	/**
	 * Stores the new Wifi's from CurrentStorage into the database
	 */
	private void storeNewWifi() {
		int count = 0;
		ArrayList<Integer> newWifi = CurrentStorage.getAddedWifi();
		ArrayList<Wifi> wifi = CurrentStorage.getWifiArray();
		while (count < newWifi.size()) {
			System.out.println(count + "\n" + newWifi.get(count));
			addLocation(wifi.get(newWifi.get(count)));
			count += 1;
		}
	}

	/**
	 * Stores the new Poi's from CurrentStorage into the database
	 */
	private void storeNewPoi() {
		int count = 0;
		ArrayList<Integer> newPoi = CurrentStorage.getAddedPoi();
		ArrayList<Poi> poi = CurrentStorage.getPoiArray();
		while (count < newPoi.size()) {
			addLocation(poi.get(newPoi.get(count)));
			count += 1;
		}
	}

	/**
	 * Stores the new General locations from CurrentStorage into the database
	 */
	private void storeNewGeneral() {
		int count = 0;
		ArrayList<Integer> newLocations = CurrentStorage.getAddedGeneral();
		ArrayList<Location> locations = CurrentStorage.getGeneralArray();
		while (count < newLocations.size()) {
			addLocation(locations.get(newLocations.get(count)));
			count += 1;
		}
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
    	//System.out.print(runQuery("SELECT Route.*, Start.*, End.* FROM tblRoutes as Route "
    	//		+ "inner join tblLocations as Start on Route.StartID = Start.LocationID "
    	//		+ "inner join tblLocations as End on Route.EndID = End.LocationID"));
    }


	/**
	 * Fetches all of the routes out of the database to be loaded in to the app
	 */
	public void loadAllRoutes() {
    	Route route = null;
    	try {
    		PreparedStatement qryLoadRoutes = connect.prepareStatement("SELECT * FROM tblRoutes LIMIT ?, 1000");
    		qryLoadRoutes.setInt(1, routeOffset);
			ResultSet output = qryLoadRoutes.executeQuery();
			routeOffset += output.getFetchSize();
			Location start = null;
			Location end = null;
			int startID;
			int endID;
			boolean secret = false;
			String name;
			String bikeID;
			String gender;
			User owner;
			
			double latitude;
			double longitude;
			int type;
			

			ResultSet locationOutput; 
			ResultSet endOutput; 
			while (output.next()) {
				startID = output.getInt(2);
				endID = output.getInt(3);

				PreparedStatement qryLocation = connect.prepareStatement("SELECT * FROM tblLocations" +
						" WHERE LocationID = ?");
				qryLocation.setInt(1, startID);

				locationOutput = qryLocation.executeQuery();


				locationOutput.next();
	    		start = loadLocation(locationOutput);

				qryLocation = connect.prepareStatement("SELECT * FROM tblLocations" +
						" WHERE LocationID = ?");
	    		qryLocation.setInt(1, endID);
				locationOutput = qryLocation.executeQuery();
				locationOutput.next();
				end = loadLocation(locationOutput);
				
				secret = output.getBoolean(7);
				name = output.getString(9);
				bikeID = output.getString(10);
				if (output.getBoolean(11)) {
					gender = "Male";
				}
				else {
					gender = "Female";
				}

				route = new Route(bikeID, start, end, name, gender);
				CurrentStorage.addRoute(route);
			}

			
    	}
    	catch (SQLException ex) {
    		printSqlError(ex);
    	}
    }

	/**
	 * Fetches all of the locations out of the database to be loaded in to the app
	 */
    private Location loadLocation(ResultSet output) throws SQLException {	
    	//Initialize all the variables for the different location types
    	String ssid;
    	String provider;
    	String description;
    	String product;
    	String wifi_type;
    	double cost;
    	boolean isDisabled;
    	boolean unisex;
    	int typeID;
    	
    	ResultSet typeOutput;
    	
		//Defines the data all location types share
		double latitude = output.getDouble(2);
		double longitude = output.getDouble(3);
		String name = output.getString(4);
		//user index 5
		boolean secret = output.getBoolean(6);
		int type = output.getInt(7);
		String borough = output.getString(12);
		int zip = output.getInt(13);
		String address = output.getString(14);
		Statement qryTypeData = connect.createStatement();
		//Switch to determine what type of location is being loaded
		switch (type) {
		case 0:
			//Defines the type specific data
			typeID = output.getInt(8);
			typeOutput = qryTypeData.executeQuery("SELECT * FROM tblToilets WHERE ToiletID = " + typeID + "");
			typeOutput.next();
			isDisabled = typeOutput.getBoolean(2);
			unisex = typeOutput.getBoolean(3);
			//Add the toilet to the current storage
			Toilet toilet = new Toilet(latitude, longitude, name, isDisabled, unisex);
			if (borough != null) {
				toilet.setBorough(borough);
			}
			if (zip != 0) {
				toilet.setZip(zip);
			}
			if (address != null) {
				toilet.setAddress(address);
			}
			if (!secret) {
				toilet.setSecret(true);
			}
			CurrentStorage.addToilet(toilet);
			return toilet;
		//Same as the case for toilet but with data for the other types
		case 1:
			typeID = output.getInt(9);
			typeOutput = qryTypeData.executeQuery("SELECT * FROM tblPOI WHERE PoiID = " + typeID + "");
			typeOutput.next();
			cost = typeOutput.getDouble(2);
			description = typeOutput.getString(3);
			Poi poi = new Poi(latitude, longitude, name, description, cost);
			if (borough != null) {
				poi.setBorough(borough);
			}
			if (zip != 0) {
				poi.setZip(zip);
			}
			if (address != null) {
				poi.setAddress(address);
			}
			if (!secret) {
				poi.setSecret(true);
			}
			CurrentStorage.addPoi(poi);
			return poi;
		case 2:
			typeID = output.getInt(10);
			typeOutput = qryTypeData.executeQuery("SELECT * FROM tblRetailers WHERE RetailerID = " + typeID + "");
			typeOutput.next();
			product = typeOutput.getString(2);
			description = typeOutput.getString(3);
			Retailer retailer = new Retailer(latitude, longitude, name, product, description);
			if (borough != null) {
				retailer.setBorough(borough);
			}
			if (zip != -1) {
				retailer.setZip(zip);
			}
			if (address != null) {
				retailer.setAddress(address);
			}
			if (!secret) {
				retailer.setSecret(true);
			}
			CurrentStorage.addRetailer(retailer);
			return retailer;
		case 3:
			typeID = output.getInt(11);
			typeOutput = qryTypeData.executeQuery("SELECT * FROM tblWifi WHERE WifiID = " + typeID + "");
			typeOutput.next();
			ssid = typeOutput.getString(5);
			provider = typeOutput.getString(6);
			wifi_type = typeOutput.getString(4);
			Wifi wifi = new Wifi(latitude, longitude, name, wifi_type,  provider, ssid);
			if (borough != null) {
				wifi.setBorough(borough);
			}
			if (zip != 0) {
				wifi.setZip(zip);
			}
			if (address != null) {
				wifi.setAddress(address);
			}
			if (!secret) {
				wifi.setSecret(true);
			}
			CurrentStorage.addWifi(wifi);
			return wifi;
		case 4:
			Location location = new Location(latitude, longitude, name, 4);
			if (borough != null) {
				location.setBorough(borough);
			}
			if (zip != 0) {
				location.setZip(zip);
			}
			if (address != null) {
				location.setAddress(address);
			}
			if (!secret) {
				location.setSecret(true);
			}
			CurrentStorage.addGeneral(location);
			return location;
		}
		return null;
    }
    
    /**
     * Loads all locations in the database into the programs current storage
     */
    public void loadNextLocations() {
    	try {
    		//Initialize the query to fetch all locations from the database and its result set
			String[] locationTypes = {"Retailer", "Toilet", "Poi", "Wifi"};
			for (String location : locationTypes) {
				PreparedStatement qryLoadLocations = connect.prepareStatement("SELECT * FROM tblLocations WHERE " + location + "ID IS NOT NULL LIMIT ?, 1000");
				switch(location) {
					case("Retailer"): qryLoadLocations.setInt(1, retailerOffset);
						break;
					case("Toilet"): qryLoadLocations.setInt(1, toiletOffset);
						break;
					case("Poi"): qryLoadLocations.setInt(1, poiOffset);
						break;
					case("Wifi"): qryLoadLocations.setInt(1, wifiOffset);
						break;
				}

				ResultSet output = qryLoadLocations.executeQuery();
				//Loop while there a another location to be loaded from the database
				while (output.next()) {
					loadLocation(output);
				}
				output.last();
				switch(location) {
					case("Retailer"): retailerOffset += output.getRow();
						break;
					case("Toilet"): toiletOffset += output.getRow();
						break;
					case("Poi"): poiOffset += output.getRow();
						break;
					case("Wifi"): wifiOffset += output.getRow();
						break;
				}
			}

		} 
    	//Prints the correct error statements if an SQLException occurs
    	catch (SQLException ex) {
			printSqlError(ex);
		}
    }
    
    /**
     * Runs an update query on the database. Update queries are used to add data to an existing table.
     * @param update The update query to run
     */
    private void runUpdate(PreparedStatement update) {

    	try {
			update.executeUpdate();
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
    private ArrayList<ArrayList<String>> runQuery(PreparedStatement query) {
    	//Creates an ArrayList that will contain each individual result as its own ArrayList
    	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    	//By using try/catch here the entire program will not crash if there is an error with a query. Instead it will return null.
		try {
			//Initializes the query statement
			//Initializes the set of results for the query
	    	ResultSet output = query.executeQuery();
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
    	PreparedStatement preparedStatement;
    	String username = user.getUsername();

    	try {
			preparedStatement = connect.prepareStatement("SELECT Username FROM tblUser WHERE Username = ?");
			preparedStatement.setString(1, username);
			if (runQuery(preparedStatement).isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sqlException){
    		System.out.println(sqlException.getMessage());
		}
		return false;
    }
    
    /**
     * Adds a user into the external database
     * @param user User to add into the external database
     */
    public void addUser(User user) {
    	PreparedStatement preparedStatement;
    	String firstName = user.getFirstName();
    	String lastName = user.getLastName();
    	String password = user.getPassword();
    	String dob = user.getDob();
    	String username = user.getUsername();
    	int routesCycled = user.getRoutesCycled();
    	double hoursCycled = user.getHours();
    	double distanceCycled = user.getDistance();
    	if (!userExists(user)) {
    		String stmt  = "INSERT INTO tblUser "
					+ "(FName, LName, Username, YearOfBirth, Password, NumRoutesCycled, HoursCycled, DistanceCycled) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?)";

    		try {
				preparedStatement = connect.prepareStatement(stmt);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, username);
				preparedStatement.setString(4, dob);
				preparedStatement.setString(5, password);
				preparedStatement.setInt(6, routesCycled);
				preparedStatement.setDouble(7, hoursCycled);
				preparedStatement.setDouble(8, distanceCycled);

				runUpdate(preparedStatement);
			} catch (SQLException sqlException) {
    			System.out.println(sqlException.getMessage());
			}

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
    	PreparedStatement preparedStatement;
    	String name = route.getName();
    	Location start = route.getStart();
    	Location end = route.getEnd();
    	
    	//Checks if the start and end locations exist in the database.
    	//If they do not it will create them.
    	int startID = findLocation(start);
    	int endID = findLocation(end);

    	System.out.println(start.getName() + " " + startID);
    	System.out.println(end.getName() + " " + endID);

    	if (startID == 0) {
    		addLocation(start);
    		startID = findLocation(start);
    	}

    	if (endID == 0) {
    		addLocation(end);
    		endID = findLocation(end);
    	}

    	String stmt = "INSERT INTO tblRoutes "
				+ "(StartID, EndID, Name, BikeID, Gender) VALUES "
				+ "(?, ?, ?, ?, ?)";
    	try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, startID);
			preparedStatement.setInt(2, endID);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, route.getBikeID());
			preparedStatement.setString(5, route.getGender());

			runUpdate(preparedStatement);
		} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
		}

    }

    /**
     * 
     * @param location The location to find in the database.
     * @return The ID of the location in the database, returns 0 if location not found.
     */
    private int findLocation(Location location) {
    	PreparedStatement preparedStatement;
    	String locationID = "0";
    	double[] coords = location.getCoords();
    	int type = location.getLocationType();
    	try {
    		preparedStatement = connect.prepareStatement("SELECT LocationID FROM tblLocations WHERE Latitude = ? AND Longitude = ? AND Type = ? AND Name = ?");
    		preparedStatement.setDouble(1, coords[0]);
    		preparedStatement.setDouble(2, coords[1]);
    		preparedStatement.setInt(3, type);
    		preparedStatement.setString(4, location.getName());
    		System.out.println(preparedStatement);
    		locationID = runQuery(preparedStatement).get(0).get(0);
    	} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
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
    	PreparedStatement preparedStatement;
    	String typeID;
    	String ssid = wifi.getSsid();
    	String provider = wifi.getProvider();
    	String type = wifi.getType();

    	try {
			String stmt = "INSERT INTO tblWifi "
					+ "(latitude, longitude, SSID, Provider, Type) VALUES "
					+ "(?, ?, ?, ?, ?)";
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setDouble(1, wifi.getLatitude());
			preparedStatement.setDouble(2, wifi.getLongitude());
			preparedStatement.setString(3, ssid);
			preparedStatement.setString(4, provider);
			preparedStatement.setString(5, type);

			runUpdate(preparedStatement);

			stmt = "SELECT LAST_INSERT_ID()";
			preparedStatement = connect.prepareStatement(stmt);

			typeID = runQuery(preparedStatement).get(0).get(0);
			insertLocation(wifi, typeID);
		} catch (SQLException sqlException){
    		System.out.println(sqlException.getMessage());
		}
    }
    
    /**
     * Adds a retailer to tblRetailers in the database
     * @param retailer The Retailer to be added to the database
     */
    private void addRetailer(Retailer retailer) {
    	PreparedStatement preparedStatement;
    	String typeID;
    	String product = retailer.getProduct();
    	String description = retailer.getDescription();

    	String stmt = "INSERT INTO tblRetailers "
				+ "(RetailerType, Description) VALUES "
				+ "(?, ?)";
    	try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setString(1, product);
			preparedStatement.setString(2, description);
			runUpdate(preparedStatement);

			stmt = "SELECT LAST_INSERT_ID()";

			preparedStatement = connect.prepareStatement(stmt);
			typeID = runQuery(preparedStatement).get(0).get(0);
			insertLocation(retailer, typeID);
		} catch (SQLException sqlException) {
    		System.out.println(sqlException.getMessage());
		}
    }
    
    /**
     * Adds a Poi to tblPOI in the database
     * @param poi The Poi to be added to the database
     */
    private void addPoi(Poi poi) {
    	PreparedStatement preparedStatement;
    	String typeID;
    	String description = poi.getDescription();
    	double cost = poi.getCost();

    	String stmt = "INSERT INTO tblPOI "
				+ "(Cost, Description) VALUES"
				+ "(?, ?)";
		try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setDouble(1, cost);
			preparedStatement.setString(2, description);
			runUpdate(preparedStatement);

			stmt = "SELECT LAST_INSERT_ID()";

			preparedStatement = connect.prepareStatement(stmt);
			typeID = runQuery(preparedStatement).get(0).get(0);
			insertLocation(poi, typeID);
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
    }
    
    /**
     * Adds a toilet to tblToilets in the database
     * @param toilet The Toilet to be added to the database
     */
    private void addToilet(Toilet toilet) {
    	PreparedStatement preparedStatement;
    	String typeID;
    	int disabled = 0;
    	int unisex = 0;
    	if (toilet.getForDisabled()) {
    		disabled = 1;
    	}
    	if (toilet.getUniSex()) {
    		unisex = 1;
    	}

		String stmt = "INSERT INTO tblToilets "
				+ "(IsDisabled, MixedGender) VALUES "
				+ "(?, ?)";
		try {
			preparedStatement = connect.prepareStatement(stmt);
			preparedStatement.setInt(1, disabled);
			preparedStatement.setInt(2, unisex);
			runUpdate(preparedStatement);

			stmt = "SELECT LAST_INSERT_ID()";

			preparedStatement = connect.prepareStatement(stmt);
			typeID = runQuery(preparedStatement).get(0).get(0);
			insertLocation(toilet, typeID);
		} catch (SQLException sqlException) {
			System.out.println(sqlException.getMessage());
		}
    }
    
    /**
     * Adds a location to tblLocations in the database
     * @param location The Location to be added to the database.
     */
    private void insertLocation(Location location, String typeID) {
		PreparedStatement preparedStatement;

    	String name = location.getName();
    	int local = 1;
    	if (location.getLocal()) {
    		local = 0;
    	}
    	String address = location.getAddress();
    	String zip = ((Integer) location.getZip()).toString();
		String borough = location.getBorough();
    	String owner = null;
    	if (location.getOwner() != null) {
			owner = location.getOwner().getUsername();
		}

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
    		String stmt = "INSERT INTO tblLocations "
					+ "(Latitude, Longitude, Name, User, Public, Type, Address) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)";
    		try {
				preparedStatement = connect.prepareStatement(stmt);
				preparedStatement.setDouble(1, latitude);
				preparedStatement.setDouble(2, longitude);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, owner);
				preparedStatement.setInt(5, local);
				preparedStatement.setInt(6, type);
				preparedStatement.setString(7, address);

				runUpdate(preparedStatement);
			} catch (SQLException sqlException) {
    			System.out.println(sqlException.getMessage());
			}
    	} else {

			String stmt = "INSERT INTO tblLocations "
					+ "(Latitude, Longitude, Name, User, Public, Type, " + typeName + "ID, Zip, Borough, Address) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try {
				preparedStatement = connect.prepareStatement(stmt);
				preparedStatement.setDouble(1, latitude);
				preparedStatement.setDouble(2, longitude);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, owner);
				preparedStatement.setInt(5, local);
				preparedStatement.setInt(6, type);
				preparedStatement.setString(7, typeID);
				preparedStatement.setString(8, zip);
				preparedStatement.setString(9, borough);
				preparedStatement.setString(10, address);

				runUpdate(preparedStatement);
			} catch (SQLException sqlException) {
				System.out.println(sqlException.getMessage());
			}
		}
    }
    
    /**
     * Provides a request to connect to the external database.
     * @throws ClassNotFoundException Occurs when the mysql driver class is not found
     * @throws IllegalAccessException Occurs when access to the database is refused due to username or password issues
     * @throws InstantiationException Occurs when there is an error creating an instance of the mysql driver class
     */
    public void connectDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Creates an instance of the driver to connect to the database
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		//Tells the driver manager of jdbc to create a connection to a database of type mysql with the ip 222.152.179.135, through port 3306, named cyclrr, using user 'monitor' and password 'Team5Pass'
    		//Following line the 192.168.1.70 needs to be 125.239.188.8 if outside of Patrick's network
    		connect = DriverManager.getConnection("jdbc:mysql://125.239.188.8:3306/cyclrr","monitor","Team5Pass");
    	}
    	catch (SQLException ex) {
    		printSqlError(ex);
    	}
    }
    
    /**
     * Closes the connection to the database if it exists
     */
    public void closeConnection() {
    	try {
			connect.close();
		} catch (SQLException ex) {
			printSqlError(ex);
		}
    }

    public void connectDbTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		System.out.println("Connecting...");
    		//Following line the 192.168.1.70 needs to be 222.152.179.135 if outside of Patrick's network
    		Connection connectTest = DriverManager.getConnection("jdbc:mysql://125.239.188.8:3306/cyclrr","monitor","Team5Pass");
    		testConnection(connectTest);
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

    public static void main(String[] argv) {
    	DataFetcher doot = new DataFetcher();
    	try {
			doot.connectDb();
			FileManager.routeRetriever(new File(seng202.Model.DataFetcher.class.getResource("/data_files/").getFile()).toString() + "/2014-01 - Citi Bike trip data.csv");
			for (Route route : CurrentStorage.getRouteArray()) {
				doot.addRoute(route);
			}
		} catch (Exception e) {
    		e.printStackTrace();
		}

	}

}
