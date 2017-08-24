package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Ignore;

public class BadgeTest extends TestCase {

    /**
     * @param testName The name of the test
     */
    public BadgeTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the test suite that the test belongs to.
     */
    public static Test suite()
    {
        return new TestSuite(BadgeTest.class);
    }

    /**
     * Skeleton test
     */
    //TODO: Add real tests
    @Ignore
    public void testBadge()
    {
        assertTrue(true);
    }
}
