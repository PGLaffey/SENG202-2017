package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import seng202.Model.*;

import java.util.ArrayList;

public class RawDataViewerTest extends TestCase {
    /**
     * @param testName The name of the test
     */
    public RawDataViewerTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the test suite that the test belongs to.
     */
    public static Test suite()
    {
        return new TestSuite(RawDataViewerTest.class);
    }

    /**
     * Test to check if the searchPoi method is working for a single object.
     */
    public void testSearchPoi() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        // Create new places of interest.
        Poi ESB = new Poi("350 5th Ave, New Tork, NY", "Empire State Building", "The Empire State Building", 0);
        Poi SoL = new Poi("45 Rockefeller Plaza, New York, NY", "Rockefeller Center", "Comprised of 19 buildings", 0);
        Poi MoMA = new Poi("11 W 53rd St, New York, NY", "Museum of Modern Art", "Has many artworks", 25);

        // Add the places of interest to an ArrayList
        pois.add(SoL);
        pois.add(ESB);
        pois.add(MoMA);

        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "Modern Art");
        assertTrue(found.get(0).equals(MoMA));
    }

    /**
     * Test to check if searchPoi can search for a specific address.
     */
    public void testSearchPoiAddr() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        // Create new place of interest.
        Poi ESB = new Poi("350 5th Ave, New Tork, NY", "Empire State Building", "The Empire State Building", 0);

        // Add the place of interest to an ArrayList
        pois.add(ESB);

        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "350 5th Ave, New York, NY");
        assertTrue(found.get(0).equals(ESB));
    }

    /**
     * Test to check if searchPoi can search for a specific name of Point of Interest.
     */
    public void testSearchPoiName() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        // Create new place of interest.
        Poi SoL = new Poi("45 Rockefeller Plaza, New York, NY", "Rockefeller Center", "Comprised of 19 buildings", 0);

        // Add the new place of interest to the arrayList
        pois.add(SoL);

        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "Rockefeller Center");
        assertTrue(found.get(0).equals(SoL));
    }

    /**
     * Test to check if searchPoi can search for a specific description of Point of Interest.
     */
    public void testSearchPoiDescription() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        // Create new place of interest
        Poi MoMA = new Poi("11 W 53rd St, New York, NY", "Museum of Modern Art", "Has many artworks", 25);

        // Add the new place of interest to the arrayList
        pois.add(MoMA);

        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "Has many artworks");
        assertTrue(found.get(0).equals(MoMA));
    }

    /**
     * Test to check if search Poi can search for a specific cost of Point of Interest
     */
    public void testSearchPoiCost() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        // Create new place of interest
        Poi MoMA = new Poi("11 W 53rd St, New York, NY", "Museum of Modern Art", "Has many artworks", 25);

        // Add the place of interest to an arrayList
        pois.add(MoMA);

        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "25");
        assertTrue(found.get(0).equals(MoMA));
    }

    /**
     * Test to ensure that the search function is capable of finding multiple instances that fit the condition.
     */
    public void testSearchPoiManyFound() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        //Create three new places of interest
        Poi ESB = new Poi("350 5th Ave, New Tork, NY", "Empire State Building", "The Empire State Building", 0);
        Poi SoL = new Poi("45 Rockefeller Plaza, New York, NY", "Rockefeller Center", "Comprised of 19 buildings", 0);
        Poi MoMA = new Poi("11 W 53rd St, New York, NY", "Museum of Modern Art", "Has many artworks", 25);

        //Add the places of interest to an ArrayList
        pois.add(ESB);
        pois.add(SoL);
        pois.add(MoMA);

        //Filter the 3 places with a condition such that they all fit the criteria
        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "s");
        assertEquals(3, found.size());
    }

    /**
     * Test to ensure that nothing is returned when no results are found.
     */
    public void testSearchPoiNoneFound() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        //Create new Places of interest
        Poi ESB = new Poi("350 5th Ave, New York, NY", "Empire State Building", "The Empire State Building", 0);
        Poi SoL = new Poi("45 Rockefeller Plaza, New York, NY", "Rockefeller Center", "Comprised of 19 buildings", 0);
        Poi MoMA = new Poi("11 W 53rd St, New York, NY", "Museum of Modern Art", "Has many artworks", 25);

        //Add the places of interest to an arrayList.
        pois.add(ESB);
        pois.add(SoL);
        pois.add(MoMA);

        //Make a condition such that no entries should be found.
        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "#");
        assertEquals(0, found.size());
    }

    /**
     * Test to ensure the search functions work with no data. Method should return an empty arraylist
     */
    public void testSearchPoisNoEntries() {
        ArrayList<Poi> pois = new ArrayList<Poi>();

        //Make a condition such that no entries should be found.
        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "z");
        assertEquals(0, found.size());
    }

    /**
     * Test to check if the searchRoute is working.
     */
    public void testSearchRetailer() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        //Create new retailers to test on.
        Retailer SBC = new Retailer("3 New York Plaza, New York, NY", "Starbucks Coffee",
                "Casual Eating and Takeout", "F-Coffeehouse", 10004);

        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        Retailer AJK = new Retailer("6 Stone Street, New York, NY", "A.J. Kelly's",
                "Full Service Dining", "F-American", 10004);

        //Add the retailers to the retailers ArrayList
        retailers.add(SBC);
        retailers.add(HRC);
        retailers.add(AJK);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "Starbucks Coffee");
        assertTrue(found.get(0).equals(SBC));
    }

    /**
     * Test to ensure that the search Retailer works with a condition that satisfies all retailers.
     * Should return an ArrayList of size 3.
     */
    public void testSearchRetailerManyFound() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        //Create new retailers to test on.
        Retailer SBC = new Retailer("3 New York Plaza, New York, NY", "Starbucks Coffee",
                "Casual Eating and Takeout", "F-Coffeehouse", 10004);

        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        Retailer AJK = new Retailer("6 Stone Street, New York, NY", "A.J. Kelly's",
                "Full Service Dining", "F-American", 10004);

        //Add the retailers to the retailers ArrayList
        retailers.add(SBC);
        retailers.add(HRC);
        retailers.add(AJK);

        RawDataViewer filterer = new RawDataViewer();
        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "NY");
        assertEquals(3, found.size());
    }

    /**
     * Test to ensure that the method returns an arrayList of size 0 if no retailers are provided.
     */
    public void testSearchRetailerNoneFound() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        //Create new retailers to test on.
        Retailer SBC = new Retailer("3 New York Plaza, New York, NY", "Starbucks Coffee",
                "Casual Eating and Takeout", "F-Coffeehouse", 10004);

        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        Retailer AJK = new Retailer("6 Stone Street, New York, NY", "A.J. Kelly's",
                "Full Service Dining", "F-American", 10004);

        //Add the retailers to the retailers ArrayList
        retailers.add(SBC);
        retailers.add(HRC);
        retailers.add(AJK);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "#");
        assertEquals(0, found.size());
    }

    /**
     * Test to ensure that the method returns an arrayList of size 0 if no retailers are provided.
     */
    public void testSearchRetailerNoEntries() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "NY");
        assertEquals(0, found.size());
    }

    /**
     * Test to ensure that searchRetailers can search via an address
     */
    public void testSearchRetailerAddr() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        //Create new retailers to test on.
        Retailer SBC = new Retailer("3 New York Plaza, New York, NY", "Starbucks Coffee",
                "Casual Eating and Takeout", "F-Coffeehouse", 10004);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "3 New York Plaza, New York, NY");
        assertTrue(found.get(0).equals(SBC));
    }

    /**
     * Test to ensure that searchRetailers can search via product
     */
    public void testSearchRetailerProduct() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        // Create new retailers to test on.
        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "Personal and Professional Services");
        assertTrue(found.get(0).equals(HRC));
    }

    /**
     * Test to ensure that searchRetailers can search via zip code
     */
    public void testSearchRetailerZip() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        // Create new retailers to test on.
        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "Personal and Professional Services");
        assertTrue(found.get(0).equals(HRC));
    }

    /**
     * Test to ensure that searchRetailers can search via a name
     */
    public void testSearchRetailerName() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        //Create new retailers to test on.
        Retailer AJK = new Retailer("6 Stone Street, New York, NY", "A.J. Kelly's",
                "Full Service Dining", "F-American", 10004);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "A.J. Kelly's");
        assertTrue(found.get(0).equals(AJK));
    }

    /**
     * Test to ensure that searchRetailers can search via a description
     */
    public void testSearchRetailerDescription() {
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();

        // Create new retailers to test on.
        Retailer HRC = new Retailer("39 Whitehall Street, New York, NY", "New York Health & Racquet Club",
                "Personal and Professional Services", "P-Athletic Clubs/Fitness", 10004);

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "P-Athletic Clubs/Fitness");
        assertTrue(found.get(0).equals(HRC));
    }

    /**
     * Test to check if the searchWifi is working when the condition is a name.
     */
    public void testSearchWifiName() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "WhyFly");
        assertTrue(found.get(0).equals(WF));
    }

    /**
     * Test to check if the searchWifiFunction works for searching by Borough.
     */
    public void testSearchWifiBorough() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "Brooklyn");
        assertTrue(found.get(0).equals(WF));
    }

    /**
     * Test is searchWifi can search by type.
     */
    public void testSearchWifiType() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "PAID");
        assertTrue(found.get(0).equals(HYW));
    }

    /**
     * Test if searchWifi can search by provider.
     */
    public void testSearchWifiProvider() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "Yeezy");
        assertTrue(found.get(0).equals(HYW));
    }

    /**
     * Test if searchWifi can search by latitude
     */
    public void testSearchWifiLat() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "40.745968");
        assertTrue(found.get(0).equals(LnK));
    }

    /**
     * Test if searchWifi can search by longitude
     */
    public void testSearchWifiLong() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "-71.51563");
        assertTrue(found.get(0).equals(WF));
    }

    /**
     * Test if searchWifi returns the correct amount of data.
     */
    public void testSearchWifiMany() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "W");
        assertEquals(3, found.size());
    }

    /**
     * Test if searchWifi returns the nothing if no results were found.
     */
    public void testSearchWifiNone() {
        ArrayList<Wifi> wifis = new ArrayList<Wifi>();

        // Create new Wifi hotspots.
        Wifi LnK = new Wifi(40.745968, -73.9940399, "LinkNYC Free Wi-Fi", "Manhattan", "FREE", "LinkNYC - Citybridge");
        Wifi WF = new Wifi(23.15644, -71.51563, "WhyFly", "Brooklyn", "FREE", "Spark");
        Wifi HYW = new Wifi(35.61213, -72.15961, "Hide yo kids, hide yo wifi", "Queens", "PAID", "Yeezy");

        // Add the wifis to an ArrayList
        wifis.add(LnK);
        wifis.add(WF);
        wifis.add(HYW);

        ArrayList<Wifi> found = RawDataViewer.searchWifi(wifis, "#");
        assertEquals(0, found.size());
    }

    /**
     * Test to check if the searchRetailer can search by Name
     */
    public void testSearchRoute() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "Home-Work");
        assertTrue(found.get(0).equals(HW));
    }

    /**
     * Test to check if searchRetailer can search for routes by starting location name
     */
    public void testSearchRouteLocStartName() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "TestPlace2Start");
        assertTrue(found.get(0).equals(TP));
    }

    /**
     * Test to check if searchRetailer can search for routes by ending location name.
     */
    public void testSearchRouteLocEndName() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "TestPlace3End");
        assertTrue(found.get(0).equals(WP));
    }

    /**
     * Test if the searchRoute can search by bikeID
     */
    public void testSearchRouteBikeID() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "12");
        assertEquals(2, found.size());
        assertTrue(found.get(0).equals(HW));
        assertTrue(found.get(1).equals(TP));
    }

    /**
     * Test to check if the searchRoute method can search by gender.
     */
    public void testSearchRouteGender() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "F");
        assertTrue(found.get(0).equals(TP));
    }

    /**
     * Test to check if the searchRoute method can return many routes.
     */
    public void testSearchRouteMany() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "-");
        assertEquals(3, found.size());
    }

    /**
     * Test to check if the searchRoute method will return no routes if it cannot find a match.
     */
    public void testSearchRouteNone() {
        ArrayList<Route> routes = new ArrayList<Route>();

        // Create new Wifi hotspots.
        Route HW = new Route("1238", new Location(24.156342, -42.123645, "TestPlace1Start", 4), new Location(25.156342, -43.123645, "TestPlace1End", 4), "Home-Work", "M");
        Route TP = new Route("12873", new Location(21.157382, -41.128660, "TestPlace2Start", 4), new Location(26.187342, -41.123625, "TestPlace2End", 4), "Test-Place", "F");
        Route WP = new Route("1414", new Location(-11.159542, 45.112345, "TestPlace3Start", 4), new Location(-16.158742, 33.654645, "TestPlace3End", 4), "Work-Party", "0");

        // Add the wifis to an ArrayList
        routes.add(HW);
        routes.add(TP);
        routes.add(WP);

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "#");
        assertEquals(0, found.size());
    }

    /**
     * Test to check if the searchRoute method will return no routes if there are no entries.
     */
    public void testSearchRouteNoEntry() {
        ArrayList<Route> routes = new ArrayList<Route>();

        ArrayList<Route> found = RawDataViewer.searchRoutes(routes, "Home-Work");
        assertEquals(0, found.size());
    }
}
