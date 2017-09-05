package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.sql.Connection;
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

    @Before
    public void setUp() {
        DataFetcher fetcher = new DataFetcher();
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
            assertTrue(fetcher.getConnect().isValid(60));
        } catch(SQLException e) {
            fail("Encountered an sql exception.");
        }
    }

    }
}
