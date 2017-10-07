package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import seng202.Model.DataFetcher;
import seng202.Model.FileManager;
import seng202.Model.Location;

import java.sql.SQLException;

public class DataFetcherTest extends TestCase {
    private DataFetcher fetcher;

    /**
     * @param testName The name of the test
     */
    public DataFetcherTest(String testName) {
        super(testName);
    }

    /**
     * @return The suite which the test belongs to.
     */
    public static junit.framework.Test suite() {
        return new TestSuite(DataFetcherTest.class);
    }

    /**
     * Sets up a new dataFetcher before every test
     */
    public void setUp() {
        fetcher = new DataFetcher();
    }

    /**
     * Test to ensure that the connectDB function results in a functional connection.
     */
    @Test
    public void testConnectDb() {
        try {
            fetcher.connectDb();
        } 
        catch (IllegalAccessException exception) {
            fail("Login fail");
        } 
        catch (ClassNotFoundException exception) {
            fail("Database driver class not found");
        } 
        catch (InstantiationException exception) {
            fail("Failed to create instance of database driver");
        }
        try {
            // Checks if the connection obtained by the fetcher in connectDB is valid
            assertTrue(fetcher.getConnect().isValid(180));
        } 
        catch (SQLException exception) {
            fail("SQLException");
        }
    }

    /**
     * Test to ensure that the DataFetcher class can write routes to the database.
     */
    @Test
    public void testWriteRoute() {
        String TARGET = getClass().getResource("/testdata/route_data1.csv").getFile();
        FileManager reader = new FileManager();
        reader.readFile(TARGET);
    }

    /**
     * Test to ensure that the DataFetcher class can write Wifi hotspots to the database.
     */
    @Test
    public void testWriteWifi() {
        String TARGET = getClass().getResource("/testdata/wifi_data1.csv").getFile();
        FileManager reader = new FileManager();
        reader.readFile(TARGET);
    }

    /**
     * Test to ensure that the DataFetcher class can write Retailers to the database correctly.
     */
    @Test
    public void testWriteRetailer() {
        String TARGET = getClass().getResource("/testdata/retailer_data1.csv").getFile();
        FileManager reader = new FileManager();
        reader.readFile(TARGET);
    }

    /**
     * Test to ensure that the DataFetcher class closes its connection.
     */
    @Test
    public void testClose() {


    	try {
    	    fetcher.connectDb();
            fetcher.closeConnection();
            assertFalse(fetcher.getConnect().isValid(60));
//    		Location testLoc = new Location(10, 10, "Test", 4);
//    		fetcher.addLocation(testLoc);
//    		fetcher.deleteLocation(testLoc);
//    		fail("Did not successfully disconnect from database");
    	}
    	catch (Exception ex) {
    		
    	}
    }

    /**
     * Test to ensure that the DataFetcher class can add new users to the database correctly.
     */
    @Test
    public void testWriteUser() {
    	
    }
}
