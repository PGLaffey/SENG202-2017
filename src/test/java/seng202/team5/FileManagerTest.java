package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FileManagerTest extends TestCase {
    /**
     * @param testName The name of the test.
     */
    public FileManagerTest(String testName)
    {
        super(testName);
    }

    /**
     * @return The suite that the test belongs to.
     */
    public Test suite()
    {
        return new TestSuite(FileManagerTest.class);
    }

}
