package seng202.team5;
//TODO fill in methods of the class

import java.sql.*;

public class DataFetcher {
    private User user;
    private String hostname;
    private String db_username;
    private String db_password;
    private String db_name;

    /**
     * Loads a user into the application
     * @param user User to add into the application
     */
    public void loadUser(User user) {

    }

    /**
     * Loads a route into the application
     * @param route Route to add into the application
     */
    public void loadRoute(Route route) {

    }

    /**
     * Loads a location into the application
     * @param location Location to add into the application
     */
    public void loadLocation(Location location) {

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
     */
    public void connectDb() {
    	Connection connect = DriverManager.getConnection("jdbc:mysql://222.152.179.135:3306/cyclrr","monitor","Team5Pass");
    	testConnection(connect);
    	connect.close();
    }
    
    private void testConnection(Connection connect) {
    	Statement qrytest = connect.createStatement();
    	ResultSet result = qrytest.executeQuery("show tables");
    	while (result.next()) {
    	system.out.println(result.getString());
    	}
    }
}
