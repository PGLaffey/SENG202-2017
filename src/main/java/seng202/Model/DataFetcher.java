package seng202.Model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import static seng202.Model.CurrentStorage.getUser;

public class DataFetcher {
    private Connection connect = null;
    private boolean hasImported = false;

    
	/**
	 * @return true if objects have already been imported from database
	 */
    public boolean isHasImported() {
    	return hasImported;
	}

    
	/**
	 * Sets the value of hasImported to true if the database has already been imported from
	 * @param value boolean value for whether an object has been imported previously
	 */
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
     * Updates a Location (oldLocation) in the database to the values of newLocation
     * @param oldLocation The old Location to be replaced
     * @param newLocation The new Location to replace the old Location
     */
    public void updateLocation(Location oldLocation, Location newLocation) {
    	double latitude = newLocation.getLatitude();
    	double longitude = newLocation.getLongitude();
    	String address = newLocation.getAddress();
    	String name = newLocation.getName();
    	int type = newLocation.getLocationType();
    	int secret = 0;
    	if (newLocation.getSecret()) {
    		secret = 1;
    	}
    	String borough = newLocation.getBorough();
    	int zip = newLocation.getZip();
    	int oldID = findLocation(oldLocation);

    	if (oldID != 0) {
	    	String stmt = "UPDATE tblLocations SET Latitude = ?, Longitude = ?, Name = ?, Public = ?, "
	    			+ "Borough = ?, Zip = ?, Address = ? WHERE LocationID = ?";
	    	ArrayList<String> params = new ArrayList<String>();
	    	Collections.addAll(params, String.valueOf(latitude), String.valueOf(longitude), 
				    				name, String.valueOf(secret), borough, String.valueOf(zip), address, String.valueOf(oldID));
			runUpdate(stmt, params);
			
			stmt = "SELECT * FROM tblLocations WHERE LocationID = ?";
			params = new ArrayList<String>();
			params.add(String.valueOf(oldID));
			ArrayList<String> location = runQuery(stmt, params).get(0);
			switch(type) {
			case 0:
				updateToilet((Toilet) newLocation, Integer.parseInt(location.get(7)));
				break;
			case 1:
				updatePoi((Poi) newLocation, Integer.parseInt(location.get(8)));
				break;
			case 2:
				updateRetailer((Retailer) newLocation, Integer.parseInt(location.get(9)));
				break;
			case 3:
				updateWifi((Wifi) newLocation, Integer.parseInt(location.get(10)));
				break;
			}
    	}
    	else {
    		System.out.println("Location does not exisit in the database. Did you mean to add it?");
    	}
    }
   
    
    /**
     * Updates a Wifi location in the database
     * @param wifi The new wifi location
     * @param typeID The ID in the database of the wifi location
     */
    private void updateWifi(Wifi wifi, int typeID) {
    	String ssid = wifi.getSsid();
    	String type = wifi.getType();
    	String provider = wifi.getProvider();
    	String stmt = "UPDATE tblWifi SET SSID = ?, Provider = ?, Type = ? WHERE WifiID = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, ssid, provider, type, String.valueOf(typeID));
		runUpdate(stmt, params);
    }
    
    
    /**
     * Updates a Poi location in the database
     * @param poi The new Poi location
     * @param typeID The ID in the database of the Poi location
     */
    private void updatePoi(Poi poi, int typeID) {
    	String description = poi.getDescription();
    	double cost = poi.getCost();
    	String stmt = "UPDATE tblPOI SET Cost = ?, Description = ? WHERE PoiID = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(cost), description, String.valueOf(typeID));
		runUpdate(stmt, params);
    }
    
    
    /**
     * Updates a toilet location in the database
     * @param toilet The new toilet location
     * @param typeID The ID in the database of the toilet location
     */
    private void updateToilet(Toilet toilet, int typeID) {
    	System.out.println(toilet.getForDisabled());
    	System.out.println(toilet.getUniSex());
    	int isDisabled = 0;
    	int unisex = 0;
    	if (toilet.getForDisabled()) {
    		isDisabled = 1;
    	}
    	if (toilet.getUniSex()) {
    		unisex = 1;
    	}
    	String stmt = "UPDATE tblToilets SET IsDisabled = ?, MixedGender = ? WHERE ToiletID = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(isDisabled), String.valueOf(unisex), String.valueOf(typeID));
		runUpdate(stmt, params);
    }
    
    
    /**
     * Updates a retailer location in the database
     * @param retailer The new retailer location
     * @param typeID The ID in the database of the retailer location
     */
    private void updateRetailer(Retailer retailer, int typeID) {
    	String retailerType = retailer.getProduct();
    	String description = retailer.getDescription();
    	String stmt = "UPDATE tblRetailers SET RetailerType = ?, Description = ? WHERE RetailerID = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, retailerType, description, String.valueOf(typeID));
		runUpdate(stmt, params);
    }
    
    
	/**
	 * Changes the password of a user in the database
	 * @param username username of the user trying to change their password
	 * @param newPassword desired password
	 */
	public void updateUserPassword(String username, String newPassword) {
    	String stmt = "UPDATE tblUser SET Password = ? WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, newPassword, username);
		runUpdate(stmt, params);
    }

	
	/**
	 * Fetches the users password to verify that they have the correct credentials
	 * @param username Username of user logging in
	 * @return The users password if the user exists, null otherwise
	 */
	public String fetchPassword(String username) {
    	String stmt = "SELECT Password FROM tblUser WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		params.add(username);
		if (runQuery(stmt, params).isEmpty()) {
			return null;
		}
		return runQuery(stmt, params).get(0).get(0);
	}

	
	/**
	 *
	 * @param username the username of the user logging in
	 * @return the LName, FName and YearOfBirth of the user from the database
	 */
	public ArrayList<String> fetchUserInfo(String username) {
		String stmt = "SELECT FName, LNAME, YearOfBirth, NumRoutesCycled, DistanceCycled, HoursCycled FROM tblUser WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, username);
		return runQuery(stmt, params).get(0);
	}

	
	/**
	 * Deletes the user from the database, as well as all corresponding
	 * user locations and routes,
	 * @param username Username of the user
	 */
	public void deleteUser(String username) {
		String stmt = "SELECT UserID FROM tblUser WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, username);
		Integer userID = Integer.parseInt(runQuery(stmt, params).get(0).get(0));
		if (userID != null) {
			stmt = "UPDATE tblLocations SET User = NULL WHERE User = ?";
			params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(userID));
			runUpdate(stmt, params);

			stmt = "UPDATE tblRoutes SET User = NULL WHERE User = ?";
			params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(userID));
			runUpdate(stmt, params);

			stmt = "DELETE FROM tblUsersRoutes WHERE UserID = ?";
			params = new ArrayList<String>();
			Collections.addAll(params,String.valueOf(userID));
			runUpdate(stmt, params);

			stmt = "DELETE FROM tblUsersLocations WHERE UserID = ?";
			params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(userID));
			runUpdate(stmt, params);

			stmt = "DELETE FROM tblUser WHERE UserID = ?";
			params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(userID));
			runUpdate(stmt, params);
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
		storeNewRoutes();
		checkSavedRoutes(CurrentStorage.getSavedRoutes(), 1);
		checkSavedRoutes(CurrentStorage.getFavRoutes(), 2);
	}

	private void checkSavedRoutes(ArrayList<Integer> savedList, int listNo) {
		int count = 0;
		ArrayList<Route> routes = CurrentStorage.getRouteArray();
		boolean found = false;
		int routeID = 0;
		int checkCount = 0;
		int userID = findUser(CurrentStorage.getUser());
    	String stmt = "SELECT RouteID FROM tblUsersRoutes WHERE UserID = ? AND ListID = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(userID), String.valueOf(listNo));
		ArrayList<ArrayList<String>> saved = runQuery(stmt, params);
		while (count < savedList.size()) {
			checkCount = 0;
			found = false;
			routeID = findRoute(routes.get(savedList.get(count)));
			if (routeID != 0) {
				while (!found && checkCount < saved.size()) {
					if (routeID == Integer.parseInt(saved.get(checkCount).get(0))) {
						found = true;
					}
					checkCount += 1;
				}
				if (!found) {
					addSavedRoute(userID, routeID, listNo);
				}
			}
		}
	}
	
	
	private void addSavedRoute(int userID, int routeID, int listID) {
    	String stmt = "INSERT INTO tblUsersRoutes (UserID, RouteID, ListID) VALUES (?, ?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(userID), String.valueOf(routeID), String.valueOf(listID));
		runUpdate(stmt, params);
	}
	
	private void storeNewRoutes() {
		int count = 0;
		ArrayList<Integer> newRoutes = CurrentStorage.getNewRoutes();
		ArrayList<Route> routes = CurrentStorage.getRouteArray();
		while (count < newRoutes.size()) {
			addRoute(routes.get(newRoutes.get(count)));
			count += 1;
		}
	}
	
	
	/**
	 * Updates the user's information on the database
	 */
	private void storeUser() {
		User user = CurrentStorage.getUser();
		if (user != null) {
			String username = user.getUsername();
			int routesCycled = user.getRoutesCycled();
			double hoursCycled = user.getHours();
			double distanceCycled = user.getDistance();
			String stmt = "UPDATE tblUser SET NumRoutesCycled = ?, HoursCycled = ?, DistanceCycled = ? WHERE Username = ?";
			ArrayList<String> params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(routesCycled), String.valueOf(hoursCycled),
					String.valueOf(distanceCycled), username);
			runUpdate(stmt, params);
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
	 * Loads a single Route from the database and returns a Route object
	 * @param output The ResultSet of the Route to be loaded from the database
	 * @return A Route object made of the data from the database
	 * @throws SQLException If there is an error reading data from the database
	 */
	private Route loadRoute(ResultSet output) throws SQLException {
		int startID = output.getInt(2);
		int endID = output.getInt(3);
		Location start = loadRouteLocation(startID);
		Location end = loadRouteLocation(endID);
		boolean secret = output.getBoolean(7);
		String name = output.getString(9);;
		String bikeID = output.getString(10);;
		String gender;
		if (output.getBoolean(11)) {
			gender = "Male";
		}
		else {
			gender = "Female";
		}
		double distance = output.getDouble(12);
		Route route = new Route(bikeID, start, end, name, gender);
		route.setSecret(secret);
		route.setDistance(distance);
		return route;
	}
	
	
	/**
	 * Loads a location along a route.EG Start or End
	 * @param locationID The database ID of the location to be loaded
	 * @return A location object made of the data from the database
	 * @throws SQLException If there is an error reading data from the database
	 */
	private Location loadRouteLocation(int locationID) throws SQLException {
		PreparedStatement qryLocation = connect.prepareStatement("SELECT * FROM tblLocations" +
				" WHERE LocationID = ?");
		qryLocation.setInt(1, locationID);
		ResultSet locationOutput = qryLocation.executeQuery();
		locationOutput.next();
		return loadLocation(locationOutput);
	}
	
	
	/**
	 * Adds the Route to the current Users saved routes or fav routes if it is set to
	 * @param output ResultSet of information about the currently loading Route
	 * @param index The index of the currently loading Route in current storage
	 * @throws SQLException If there is an error reading data from the database
	 */
	private void addRouteToList(ResultSet output, int index) throws SQLException {
		int routeID = output.getInt(1);
		int currUser = findUser(CurrentStorage.getUser());
		int userList = 0;
		PreparedStatement qryBelongsTo = connect.prepareStatement("SELECT * FROM tblUsersRoutes" +
				" WHERE UserID = ? AND RouteID = ?");
		qryBelongsTo.setInt(1, currUser);
		qryBelongsTo.setInt(2, routeID);
		ResultSet belongsToOutput = qryBelongsTo.executeQuery();
		while (belongsToOutput.next()) {
			userList = belongsToOutput.getInt(3);
			if (userList == 1) {
				CurrentStorage.addSavedRoute(index);
			}
			else if (userList == 2) {
				CurrentStorage.addFavRoute(index);
			}
		}
	}
	
	
	/**
	 * Fetches all of the routes out of the database to be loaded in to the app
	 */
	public void loadAllRoutes() {
    	try {
    		//Initializes the result set of all Routes from the database
    		PreparedStatement qryLoadRoutes = connect.prepareStatement("SELECT * FROM tblRoutes");
			ResultSet output = qryLoadRoutes.executeQuery();
			//Loops while there is another Route to be loaded
			while (output.next()) {
				boolean secret = output.getBoolean(7);
				//Checks if the Route is set to be secret
				if (secret) {
					int ownerID = output.getInt(8);
					//Checks if the Route belongs to a User
					if (ownerID != 0) {
						String currUser = CurrentStorage.getUser().getUsername();
						PreparedStatement qryOwner = connect.prepareStatement("SELECT Username FROM tblUser WHERE UserID = ?");
						qryOwner.setInt(1, ownerID);
						ResultSet ownerOutput = qryOwner.executeQuery();
						String owner = ownerOutput.getString(1);
						//Checks if the currently loaded User is the Owner of the Route
						//If so add the Route to current storage
						if (currUser.equals(owner)) {
							CurrentStorage.addRoute(loadRoute(output));
							int addedIndex = CurrentStorage.getRouteArray().size() - 1;
							addRouteToList(output, addedIndex);
						}
					}
					//If the Route does not belong to a User, don't add it because it is secret
				}
				//If the Route is not secret add it to current storage
				else {
					CurrentStorage.addRoute(loadRoute(output));
					int addedIndex = CurrentStorage.getRouteArray().size() - 1;
					addRouteToList(output, addedIndex);
				}
			}
    	}
    	//Prints the correct error statements if an SQLException occurs
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
			ssid = typeOutput.getString(2);
			provider = typeOutput.getString(3);
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
    public void loadAllLocations() {
    	try {
    		//Initialize the query to fetch all Locations from the database and its result set
    		PreparedStatement qryLoadLocations = connect.prepareStatement("SELECT * FROM tblLocations");
			ResultSet output = qryLoadLocations.executeQuery();
			boolean secret = false;
			int ownerID = 0;
	    	//Loop while there a another Location to be loaded from the database
			while (output.next()) {
				secret = output.getBoolean(6);
				//Checks if the Location is set to be secret
				if (secret) {
					ownerID = output.getInt(5);
					//Checks if the Location belongs to a User
					if (ownerID != 0) {
						String currUser = CurrentStorage.getUser().getUsername();
						PreparedStatement qryOwner = connect.prepareStatement("SELECT Username FROM tblUser WHERE UserID = ?");
						qryOwner.setInt(1, ownerID);
						ResultSet ownerOutput = qryOwner.executeQuery();
						String owner = ownerOutput.getString(1);
						//Checks if the currently loaded User is the Owner of the Location
						//If so continue loading the Location
						if (currUser.equals(owner)) {
							loadLocation(output);
						}
					}
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
    private void runUpdate(String query, ArrayList<String> parameters) {
    	try {
    		PreparedStatement update = connect.prepareStatement(query);
    		int count = 0;
    		while (count < parameters.size()) {
    			update.setString(count + 1, parameters.get(count));
    			count += 1;
    		}
			update.executeUpdate();
		} 
    	catch (SQLException ex) {
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
    private ArrayList<ArrayList<String>> runQuery(String stmt, ArrayList<String> parameters) {
    	//Creates an ArrayList that will contain each individual result as its own ArrayList
    	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    	//By using try/catch here the entire program will not crash if there is an error with a query. Instead it will return null.
		try {
    		PreparedStatement query = connect.prepareStatement(stmt);
    		int count = 0;
    		while (count < parameters.size()) {
    			query.setString(count + 1, parameters.get(count));
    			count += 1;
    		}
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
		} 
		catch (SQLException ex) {
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
    	String stmt = "SELECT Username FROM tblUser WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, username);
		if (runQuery(stmt, params).isEmpty()) {
			return false;
		}
		return true;
    }
    
    
    /**
     * Adds a user into the external database
     * @param user User to add into the external database
     */
    public void addUser(User user) {
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
    		ArrayList<String> params = new ArrayList<String>();
    		Collections.addAll(params, firstName, lastName, username, dob, password, String.valueOf(routesCycled),
    				String.valueOf(hoursCycled), String.valueOf(distanceCycled));
    		runUpdate(stmt, params);
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
    	int routeID = findRoute(route);
    	if (routeID == 0) {
	    	String name = route.getName();
	    	Location start = route.getStart();
	    	Location end = route.getEnd();
	    	int secret = 0;
	    	double distance = route.getDistance();
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
	    	if (route.getSecret()) {
	    		secret = 1;
	    	}
	    	String owner = null;
	    	if (route.getOwner() != null) {
				owner = String.valueOf(findUser(route.getOwner()));
				if (owner == "0") {
					owner = null;
				}
			}
	    	String stmt = "INSERT INTO tblRoutes "
					+ "(StartID, EndID, Public, User, Name, BikeID, Gender, Distance) VALUES "
					+ "(?, ?, ?, ?, ?, ?)";
			ArrayList<String> params = new ArrayList<String>();
			Collections.addAll(params, String.valueOf(startID), String.valueOf(endID), String.valueOf(secret),
					owner, name, route.getBikeID(), route.getGender().substring(0, 1), String.valueOf(distance));
			runUpdate(stmt, params);
    	}
    	else {
    		System.out.println("Route already exisits in the database. Did you mean to update it?");
    	}
    }

    
    private int findRoute(Route route) {
    	int startID = findLocation(route.getStart());
    	if (startID == 0) {
    		return 0;
    	}
    	int endID = findLocation(route.getEnd());
    	if (endID == 0) {
    		return 0;
    	}
    	String name = route.getName();
    	String stmt = "SELECT RouteID FROM tblLocations WHERE StartID = ? AND EndID = ? AND Name = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(startID), String.valueOf(endID), name);
		if (runQuery(stmt, params).isEmpty()) {
			return 0;
		}
    	return Integer.parseInt(runQuery(stmt, params).get(0).get(0));
    }
    
    
    /**
     * 
     * @param location The location to find in the database.
     * @return The ID of the location in the database, returns 0 if location not found.
     */
    private int findLocation(Location location) {
    	double[] coords = location.getCoords();
    	int type = location.getLocationType();
    	String stmt = "SELECT LocationID FROM tblLocations WHERE Latitude = ? AND Longitude = ? AND Type = ? AND Name = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(coords[0]), String.valueOf(coords[1]),
				String.valueOf(type), location.getName());
		if (runQuery(stmt, params).isEmpty()) {
			return 0;
		}
    	return Integer.parseInt(runQuery(stmt, params).get(0).get(0));
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
    	String ssid = wifi.getSsid();
    	String provider = wifi.getProvider();
    	String type = wifi.getType();
		String stmt = "INSERT INTO tblWifi "
				+ "(SSID, Provider, Type) VALUES "
				+ "(?, ?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, ssid, provider, type);
		runUpdate(stmt, params);

		stmt = "SELECT LAST_INSERT_ID()";
		params = new ArrayList<String>();

		typeID = runQuery(stmt, params).get(0).get(0);
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
    	String stmt = "INSERT INTO tblRetailers "
				+ "(RetailerType, Description) VALUES "
				+ "(?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, product, description);
		runUpdate(stmt, params);
		
		stmt = "SELECT LAST_INSERT_ID()";

		params = new ArrayList<String>();
		typeID = runQuery(stmt, params).get(0).get(0);
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
    	String stmt = "INSERT INTO tblPOI "
				+ "(Cost, Description) VALUES"
				+ "(?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(cost), description);
		runUpdate(stmt, params);

		stmt = "SELECT LAST_INSERT_ID()";
		params = new ArrayList<String>();
		typeID = runQuery(stmt, params).get(0).get(0);
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
		String stmt = "INSERT INTO tblToilets "
				+ "(IsDisabled, MixedGender) VALUES "
				+ "(?, ?)";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, String.valueOf(disabled), String.valueOf(unisex));
		runUpdate(stmt, params);

		stmt = "SELECT LAST_INSERT_ID()";
		params = new ArrayList<String>();
		typeID = runQuery(stmt, params).get(0).get(0);
		insertLocation(toilet, typeID);
    }
    
    
    /**
     * Adds a location to tblLocations in the database
     * @param location The Location to be added to the database.
     */
    private void insertLocation(Location location, String typeID) {
    	String name = location.getName();
    	int secret = 0;
    	if (location.getSecret()) {
    		secret = 1;
    	}
    	String address = location.getAddress();
    	String zip = ((Integer) location.getZip()).toString();
		String borough = location.getBorough();
    	String owner = null;
    	if (location.getOwner() != null) {
			owner = String.valueOf(findUser(location.getOwner()));
			if (owner == "0") {
				owner = null;
			}
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
    		ArrayList<String> params = new ArrayList<String>();
    		Collections.addAll(params, String.valueOf(latitude), String.valueOf(longitude), name, owner,
    				String.valueOf(secret), String.valueOf(type), address);
    		runUpdate(stmt, params);
    	} 
    	else {
			String stmt = "INSERT INTO tblLocations "
					+ "(Latitude, Longitude, Name, User, Public, Type, " + typeName + "ID, Zip, Borough, Address) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		ArrayList<String> params = new ArrayList<String>();
    		Collections.addAll(params, String.valueOf(latitude), String.valueOf(longitude), name,
    				owner, String.valueOf(secret), String.valueOf(type), typeID, zip, borough, address);
    		runUpdate(stmt, params);
		}
    }
    
    
    /**
     * Finds the input User in the database and returns its ID within the database
     * @param user The user to find
     * @return ID in the database as an int
     */
    private int findUser(User user) {
    	int userID = 0;
    	String username = user.getUsername();
    	String stmt = "SELECT UserID FROM tblUser WHERE Username = ?";
		ArrayList<String> params = new ArrayList<String>();
		Collections.addAll(params, username);
		userID = Integer.parseInt(runQuery(stmt, params).get(0).get(0));
    	return userID;
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
    		Connection connectTest = DriverManager.getConnection("jdbc:mysql://192.168.1.70:3306/cyclrr","monitor","Team5Pass");
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
//    	DataFetcher doot = new DataFetcher();
//    	try {
//			doot.connectDb();
//			FileManager.routeRetriever(new File(seng202.Model.DataFetcher.class.getResource("/data_files/").getFile()).toString() + "/2014-01 - Citi Bike trip data.csv");
//			for (Route route : CurrentStorage.getRouteArray()) {
//				doot.addRoute(route);
//			}
//		} catch (Exception e) {
//    		e.printStackTrace();
//		}
    	DataFetcher ba = new DataFetcher();
    	try {
			ba.connectDb();
			System.out.println(ba.fetchPassword("adsfds"));
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}

}
