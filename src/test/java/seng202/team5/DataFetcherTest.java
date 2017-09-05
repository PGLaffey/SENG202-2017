package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DataFetcherTest extends TestCase {
    private DataFetcher fetcher;

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


}
