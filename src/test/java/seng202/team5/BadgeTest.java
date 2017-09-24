package seng202.team5;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Ignore;
import seng202.Model.Badge;
import seng202.exceptions.BadgeLevelException;
import seng202.exceptions.BadgeTypeException;

public class BadgeTest extends TestCase {
    private Badge distanceBadge;
    private Badge timeBadge;
    private Badge routesBadge;

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
    public static junit.framework.Test suite()
    {
        return new TestSuite(BadgeTest.class);
    }

    /**
     * Sets up a new dataFetcher before every test
     */
    public void setUp() throws BadgeLevelException, BadgeTypeException {
        distanceBadge = new Badge("Distance");
        timeBadge = new Badge("Time");
        routesBadge = new Badge("Routes");
    }



    /**
     * Group of tests for testing getStrRemaining on a distance badge
     */

    /**
     * Test to check the 0 boundary case (same test for all badge types)
     */
    @Test
    public void testGetStrRemaingDistanceZero() {
        distanceBadge.setValue(distanceBadge.getLevelCap());
        try {
            String strRemaining = distanceBadge.getStrRemaining();
            assertTrue(strRemaining == "8 kilometres");
        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
    }

    /**
     * Test to check kilometres only conditional
     */
    @Test
    public void testGetStrRemainingDistanceKilometres() {
        distanceBadge.setValue(130000);
        String strRemaining = "";
        try {
            distanceBadge.setLevel(4);
            strRemaining = distanceBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "70 kilometres");
    }

    /**
     * Test to check metres only conditional
     */
    @Test
    public void testGetStrRemainingDistanceMetres() {
        distanceBadge.setValue(9643);
        String strRemaining = "";
        try {
            distanceBadge.setLevel(1);
            strRemaining = distanceBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "357 metres");
    }

    /**
     * Test to check else conditional
     */
    @Test
    public void testGetStrRemainingDistanceElse() {
        distanceBadge.setValue(11742);
        String strRemaining = "";
        try {
            distanceBadge.setLevel(2);
            strRemaining = distanceBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "38 kilometres & 258 metres");
    }

    /**
     * Group of tests to check getStrRemaining with a time badge
     */

    /**
     * Test to check hours only conditional
     */
    @Test
    public void testGetStrRemainingTimeHours() {
        timeBadge.setValue(11520);
        String strRemaining = "";
        try {
            timeBadge.setLevel(4);
            strRemaining = timeBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "8 hours");
    }

    /**
     * Test to check minutes only conditional
     */
    @Test
    public void testGetStrRemainingTimeMinutes() {
        timeBadge.setValue(542);
        String strRemaining = "";
        try {
            timeBadge.setLevel(4);
            strRemaining = timeBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "58 minutes");
    }

    /**
     * Test to check else conditional
     */
    @Test
    public void testGetStrRemainingTimeElse() {
        timeBadge.setValue(670);
        String strRemaining = "";
        try {
            timeBadge.setLevel(2);
            strRemaining = timeBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "38 hours & 50 minutes");
    }


    /**
     * Test to check getStrRemaining with routes badge
     */
    @Test
    public void testGetStrRemainingRoutes() {
        routesBadge.setValue(158);
        String strRemaining = "";
        try {
            routesBadge.setLevel(5);
            strRemaining = routesBadge.getStrRemaining();

        } catch (BadgeLevelException exception) {
            fail("BadgeLevelException");
        }
        assertTrue(strRemaining == "42 trips");
    }





}
