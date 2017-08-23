package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class LocationTest extends TestCase{
    /**
     * @param testName Name of the test
     */
    public LocationTest(String testName)
    {
        super(testName);
    }

    /**
     * @return The suite that the test belongs to.
     */
    public Test suite()
    {
        return new TestSuite(LocationTest.class);
    }
}
