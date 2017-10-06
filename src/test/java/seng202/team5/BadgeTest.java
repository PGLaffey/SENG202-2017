package seng202.team5;


import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Ignore;
import seng202.Model.Badge;

import static java.lang.Integer.MAX_VALUE;


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
    public void setUp() {
        distanceBadge = new Badge("Distance");
        timeBadge = new Badge("Time");
        routesBadge = new Badge("Routes");
    }


    /**
     * Test to check the 0 boundary case (same test for all badge types)
     */
    @Test
    public void testGetStrRemaingDistanceZero() {
        distanceBadge.setValue(2000);
        String strRemaining = distanceBadge.getStrRemaining();
        assertEquals("8 kilometres", strRemaining);
    }


    /**
     * Test to check kilometres only conditional
     */
    @Test
    public void testGetStrRemainingDistanceKilometres() {
        distanceBadge.setValue(130000);
        distanceBadge.setLevel(4);
        String strRemaining = distanceBadge.getStrRemaining();
        assertEquals("70 kilometres", strRemaining);
    }


    /**
     * Test to check metres only conditional
     */
    @Test
    public void testGetStrRemainingDistanceMetres() {
        distanceBadge.setValue(9643);
        distanceBadge.setLevel(1);
        String strRemaining = distanceBadge.getStrRemaining();
        assertEquals("357 metres", strRemaining);
    }


    /**
     * Test to check else conditional
     */
    @Test
    public void testGetStrRemainingDistanceElse() {
        distanceBadge.setValue(11742);
        distanceBadge.setLevel(2);
        String strRemaining = distanceBadge.getStrRemaining();
        assertEquals("38 kilometres and 258 metres", strRemaining);
    }


    /**
     * Test to check hours only conditional
     */
    @Test
    public void testGetStrRemainingTimeHours() {
        timeBadge.setValue(11520);
        timeBadge.setLevel(4);
        String strRemaining = timeBadge.getStrRemaining();
        assertEquals("8 hours", strRemaining);
    }


    /**
     * Test to check minutes only conditional
     */
    @Test
    public void testGetStrRemainingTimeMinutes() {
        timeBadge.setValue(542);
        timeBadge.setLevel(1);
        String strRemaining = timeBadge.getStrRemaining();
        assertEquals("58 minutes", strRemaining);
    }


    /**
     * Test to check else conditional
     */
    @Test
    public void testGetStrRemainingTimeElse() {
        timeBadge.setValue(670);
        timeBadge.setLevel(2);
        String strRemaining = timeBadge.getStrRemaining();
        assertEquals("38 hours and 50 minutes", strRemaining);
    }


    /**
     * Test to check getStrRemaining with routes badge
     */
    @Test
    public void testGetStrRemainingRoutes() {
        routesBadge.setValue(158);
        routesBadge.setLevel(5);
        String strRemaining = routesBadge.getStrRemaining();
        assertEquals("42 trips", strRemaining);
    }


    /**
     * Test to check getStrRemaining with level 6 boundary case
     */
    @Test
    public void testGetStrRemainingBoundarySix() {
        routesBadge.setValue(200);
        routesBadge.setLevel(6);
        assertEquals("Max level achieved", routesBadge.getStrRemaining());
    }


    /**
     * Test to check updateName
     */
    @Test
    public void testUpdateName() {
        routesBadge.setLevel(5);
        routesBadge.updateName();
        assertTrue(routesBadge.getName().equals("Routes badge, level 5"));
    }


    /**
     * Test for updateRemaining for 0 border case
     */
    @Test
    public void testUpdateRemainingZeroBoundary() {
        distanceBadge.setValue(50000);
        distanceBadge.setLevel(2);
        distanceBadge.updateRemaining();
        assertTrue(distanceBadge.getRemaining() == 50000);
    }


    /**
     * Test for updateRemaining for beyond border case
     */
    @Test
    public void testUpdateRemainingNegative() {
        routesBadge.setValue(23);
        routesBadge.setLevel(1);
        routesBadge.updateRemaining();
        assertTrue(routesBadge.getRemaining() == 27);
    }


    /**
     * Test for updateRemaining within bounds
     */
    @Test
    public void testUpdateRemainingWithinBounds() {
        timeBadge.setValue(550);
        timeBadge.setLevel(1);
        timeBadge.updateRemaining();
        assertTrue(timeBadge.getRemaining() == 50);
    }


    /**
     * Test for updateLevel at 0
     */
    @Test
    public void testUpdateLevelZero() {
        distanceBadge.updateLevel();
        assertTrue(distanceBadge.getLevel() == 0);
    }


    /**
     * Test updateLevel on boundary
     */
    @Test
    public void testUpdateLevelBoundary() {
        routesBadge.setValue(200);
        routesBadge.updateLevel();
        assertTrue(routesBadge.getLevel() == 6);
    }


    /**
     * Test updateLevel on extreme value
     */
    @Test
    public void testUpdateLevelExtreme() {
        routesBadge.setValue(MAX_VALUE);
        routesBadge.updateLevel();
        assertTrue(routesBadge.getLevel() == 6);
    }


    /**
     * Test updateIcon
     */
    @Test
    public void testUpdateIcon() {
        timeBadge.setLevel(4);
        timeBadge.updateIcon();
        assertTrue(timeBadge.getIcon().equals("images/Badges/badgeTime4.png"));
    }


    /**
     * Test updateDescription on level 0
     */
    @Test
    public void testUpdateDescriptionZero() {
        routesBadge.updateDescription();
        String check = "You don't have a Routes badge yet.\nTake 2 trips to earn your first one!";
        assertEquals(check, routesBadge.getDescription());
    }


    /**
     * Test updateDescription on level between 0 and 6
     */
    @Test
    public void testUpdateDescriptionMidrange() {
        distanceBadge.setLevel(3);
        distanceBadge.setValue(74500);
        distanceBadge.updateBadge();
        String check = "This is your Level 3 Distance Badge." +
                "\nTravel 25 kilometres and 500 metres to earn the next one!";
        assertTrue(distanceBadge.getDescription().equals(check));
    }


    /**
     * Test updateDescription on level 6
     */
    @Test
    public void testUpdateDescriptionBoundary() {
        timeBadge.setLevel(6);
        timeBadge.setValue(30000);
        timeBadge.updateDescription();
        String check = "Nice Work! This is your Level 6 Time Badge." +
                "\nYou're at the top of your game, and we've run out of Time Badges to give you!";
        assertTrue(check.equals(timeBadge.getDescription()));
    }
}

