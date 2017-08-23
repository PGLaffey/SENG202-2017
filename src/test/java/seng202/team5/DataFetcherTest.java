package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DataFetcherTest extends TestCase {
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
    public Test suite()
    {
        return new TestSuite(DataFetcherTest.class);
    }
}
