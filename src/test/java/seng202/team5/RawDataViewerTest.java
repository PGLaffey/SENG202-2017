package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import seng202.Model.Poi;
import seng202.Model.RawDataViewer;
import seng202.Model.Retailer;

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
        ArrayList<Poi> found = RawDataViewer.searchPoi(pois, "z");
        assertEquals(3, found.size());
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

        ArrayList<Retailer> found = RawDataViewer.searchRetailer(retailers, "#");
        assertEquals(0, found.size());
    }

    /**
     * Test to check if the searchWifi is working.
     */
    public void testSearchWifi() {

    }

    /**
     * Test to check if the searchRetailer
     */
    public void testSearchRoute() {

    }

    /**
     * Test to check if the search
     */
}
