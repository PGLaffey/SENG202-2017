package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Ignore;

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
    public static Test suite()
    {
        return new TestSuite(FileManagerTest.class);
    }

    /**
     * Skeleton test
     */
    //TODO: Add real tests
    @Ignore
    public void testFile()
    {
        assertTrue(true);
    }

}
