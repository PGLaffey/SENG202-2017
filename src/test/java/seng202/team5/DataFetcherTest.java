package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
<<<<<<< HEAD

public class DataFetcherTest extends TestCase {
    private DataFetcher fetcher;
=======

import java.sql.SQLException;

public class DataFetcherTest extends TestCase {
    private DataFetcher fetcher;
    private static final String TARGET = "/testdata/";
>>>>>>> 27e06ee0d248745398f34c7f042e16ab4b5214d6

    /**
     * @param testName The name of the test
     */
    public DataFetcherTest(String testName)
    {
        super(testName);
    }

    /**
     * @return The suite which the test belongs to.
     */
    public static junit.framework.Test suite()
    {
        return new TestSuite(DataFetcherTest.class);
    }

    @Before @Override
    public void setUp() {
        fetcher = new DataFetcher();
    }

    /**
<<<<<<< HEAD
     * Test the code that allows us to connect to the database.
     */
    @Ignore @Test
    public void testConnectDB()
    {
        try {
            fetcher.connectDb();
        } catch(InstantiationException e) {
            fail("Instantiation Error");
        } catch(IllegalAccessException e) {
            fail("Illegal Access");
        } catch(ClassNotFoundException e) {
            fail("Class not found");
        }

        try {
            // Ensures that the connection is not closed. If it is closed, then there is no connection to the database, and hence the test fails.
            assertTrue(!fetcher.getConnect().isClosed());
        } catch(SQLException e) {
            fail("Encountered an sql exception.");
        }
    }

=======
     * Sets up a new dataFetcher before every test
     */
    public void setUp()
    {
        fetcher = new DataFetcher();
    }
    /**
     * Test to ensure that the connectDB function results in a functional connection.
     */
    @Test
    public void testConnectDB()
    {
        try {
            fetcher.connectDb();
        } catch (IllegalAccessException exception) {
            fail("Call to access illegal area.");
        } catch (ClassNotFoundException exception) {
            fail("Class not found");
        } catch (InstantiationException exception) {
            fail("Failed to instantiate a connection");
        }
        try {
            // Checks if the connection obtained by the fetcher in connectDB is valid
            assertTrue(fetcher.getConnect().isValid(60));
        } catch (SQLException exception) {
            fail("SQLException");
        }
    }

    /**
     * Test to ensure the DataFetcher class can be sent SQL statements and return correct data.
     */
    @Test
    public void testSQL()
    {

    }

    /**
     * Test to ensure that the DataFetcher class can write routes to the database.
     */
    @Test
    public void testWriteRoute()
    {
        FileManager reader = new FileManager();
        reader.readFile(TARGET+"route_data1.csv");
    }

    /**
     * Test to ensure that the DataFetcher class can write Wifi hotspots to the database.
     */
    @Test
    public void testWriteWifi()
    {

    }

    /**
     * Test to ensure that the DataFetcher class can write Retailers to the database correctly.
     */
    @Test
    public void testWriteRetailer()
    {

    }

    /**
     * Test to ensure that the DataFetcher class closes its connection.
     */
    @Test
    public void testClose()
    {

    }

    /**
     * Test to ensure that the DataFetcher class can add new users to the database correctly.
     */
>>>>>>> 27e06ee0d248745398f34c7f042e16ab4b5214d6

}
