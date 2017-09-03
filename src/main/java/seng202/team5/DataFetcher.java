package seng202.team5;

import java.sql.*;
import java.util.ArrayList;

public class DataFetcher {
    private Connection connect = null;

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
     * Runs any MySQL query given to it on the currently connected database.
     * Array List return format is result[Row[Column]] or: 
     * result = [[(0,0), (0,1), (0,2)],
     *           [(1,0), (1,1), (1,2)],
     *           [(2,0), (2,1), (2,2)]]
     * @param query The MySQL syntax query to run
     * @return An Array List that contains another Array List, representing the rows of the table, that contains Strings, representing the column of the data
     */
    private ArrayList<ArrayList<String>> runQuery(String query) {
    	ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    	Statement qryQuery;
		try {
			qryQuery = connect.createStatement();
	    	ResultSet output = qryQuery.executeQuery(query);
	    	int totalColumns = output.getMetaData().getColumnCount();
	    	int currentColumn = 1;
	    	int currentRow = 0;
	    	while (output.next()) {
	    		results.add(new ArrayList<String>());
	    		while (currentColumn <= totalColumns) {
	    			results.get(currentRow).add(output.getString(currentColumn));
	    			currentColumn += 1;
	    		}
	    		currentRow += 1;
	    		currentColumn = 1;
	    	}
	    	return results;
		} catch (SQLException ex) {
			printSqlError(ex);
			return null;
		}

    }
    
    private void printSqlError(SQLException ex) {
		System.out.println("Exception: " + ex.getMessage());
		System.out.println("State: " + ex.getSQLState());
		System.out.println("Error: " + ex.getErrorCode());
    }
    
    /**
     * Adds a user into the external database
     * @param user User to add into the external database
     */
    public void addUser(User user) {

    }

    /**
     * Adds a route into the external database
     * @param route Route to add into the external database.
     */
    public void addRoute(Route route) {

    }

    /**
     * Provides a request to connect to the external database.
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public void connectDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		//Following line the 192.168.1.70 needs to be 222.152.179.135 if outside of Patrick's network
    		connect = DriverManager.getConnection("jdbc:mysql://192.168.1.70:3306/cyclrr","monitor","Team5Pass");
    	}
    	catch (SQLException ex) {
    		System.out.println("Exception: " + ex.getMessage());
    		System.out.println("State: " + ex.getSQLState());
    		System.out.println("Error: " + ex.getErrorCode());
    	}
    }
    

    public void connectDbTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
    		System.out.println("Connecting...");
    		//Following line the 192.168.1.70 needs to be 222.152.179.135 if outside of Patrick's network
    		Connection connectTest = DriverManager.getConnection("jdbc:mysql://192.168.1.70:3306/cyclrr","monitor","Team5Pass");
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
    	//test.connectDbTest();
    	test.connectDb();
    	Location testLoc1 = new Location(47.2134400, 172.1232100, "Telecom Hotspot", 3);
    	//test.loadLocation(testLoc1);
    	Location testLoc2 = new Location(159.1547895, 0.3256984, "NULL", 4);
    	Route testRoute = new Route(testLoc1, testLoc2, "Test");
    	test.loadRoute(testRoute);
    }
}
