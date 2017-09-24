package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.omg.CORBA.Current;
import seng202.Model.*;
import seng202.exceptions.WrongFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileManagerTest extends TestCase {
    private String TARGET = "./src/test/resources/testdata/";
    private String WRITE_TARGET = "./src/main/resources/data_files/";
    private ArrayList<String> result;

    @Rule
    private ExpectedException thrown = ExpectedException.none();

    /**
     * Constructor for FileManagerTest
     * @param testName The name of the test.
     */
    public FileManagerTest(String testName)
    {
        super(testName);
    }

    /**
     * Gets the suite to which the test belongs
     * @return The suite that the test belongs to.
     */
    public static junit.framework.Test suite()
    {
        return new TestSuite(FileManagerTest.class);
    }

    /**
     * Set up a new FileManager object and create a new instance of the storage of the app.
     */
    @Before
    public void setUp(){
        result = new ArrayList<String>();
        // Flushes the current storage to ensure empty lists each time.
        CurrentStorage.flush();
    }

    /**
     * Test to ensure that the readFile method can read a text file.
     */
    @Test
    public void testReadFile() {
        result = FileManager.readFile(TARGET+"/someText.txt");
        assertEquals("this is a string", result.get(0));
    }

    /**
     * Test to read multiple lines
     */
    @Test
    public void testReadMultiLine() {
        result = FileManager.readFile(TARGET+"/moreText.txt");
        assertEquals(11, result.size());
    }


    /**
     * Test to test FileManager's readFile functions work as expected for a .csv with 1 route.
     */
    @Test
    public void testRouteOneEntry()
    {
        FileManager.routeRetriever(TARGET+"/route_data1.csv");

        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4),
                                        new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4),
                                        "NA", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }

    /**
     * Test to ensure that duplicates are handled properly.
     */
    @Test
    public void testRouteDuplicates()
    {
        //Read the same data twice, should only save it once.
        FileManager.routeRetriever(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(TARGET+"/route_data1.csv");
        assertEquals(1, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test FileManager's readFile functions work as expected for a .csv with 10 routes.
     */
    @Test
    public void testRouteTenEntries() {
        //Only need to test whether there are 10 objects as it was previously tested whether routes are saved properly, and tested that duplicates are not saved.
        FileManager.routeRetriever(TARGET+"/route_data10.csv");
        assertEquals(10, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions work as expected for a .csv with a WiFi location
     */
    @Test
    public void testWifiOneEntry() {
        FileManager.wifiRetriever(TARGET+"/wifi_data1.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "mn-05-123662", "LinkNYC Free Wi-Fi","Free","LinkNYC - Citybridge");
        expected_wifi.setBorough("Manhatten");
        assertTrue(CurrentStorage.getWifiArray().get(0).equals(expected_wifi));
    }

    /**
     * Test to ensure duplicate WiFi data is handled correctly.
     */
    @Test
    public void testWifiDuplicate() {
        FileManager.wifiRetriever(TARGET+"/wifi_data1.csv");
        FileManager.wifiRetriever(TARGET+"/wifi_data1.csv");
        assertEquals(1, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions work as expected for a .csv with 10 WiFi locations.
     */
    @Test
    public void testWifiTenEntries() {
        FileManager.wifiRetriever(TARGET+"/wifi_data10.csv");
        assertEquals(10, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions work as expected for a .csv with a Retailer
     */
    @Test
    public void testRetailerOneEntry() {
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse");
        assertTrue(CurrentStorage.getRetailerArray().get(0).equals(expected_retailer));
    }

    /**
     * Test to check if duplicate retailer entries are handled properly.
     */
    @Test
    public void testRetailerDuplicate() {
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        assertEquals(1, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions works as expected for a .csv with 10 Retailers
     */
    @Test
    public void testRetailerTenEntries() {
        FileManager.retailerRetriever(TARGET+"/retailer_data10.csv");
        assertEquals(10, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Tests the FileManager's toiletRetriever function works as expected for a .csv file with 1 Toilet
     */
    @Test
    public void testToiletOneEntry() {
        FileManager.toiletRetriever(TARGET+"/toilet_data1.csv");
        Toilet toilet = new Toilet(40.745968,-73.994039,"Not a Real Toilet",true,false);
        assertTrue(CurrentStorage.getToiletArray().get(0).equals(toilet));
    }

    /**
     * Tests the FileManager's toiletRetriever function does not save multiple instances of a Toilet.
     */
    @Test
    public void testToiletDuplicates() {
        FileManager.toiletRetriever(TARGET+"/toilet_data1.csv");
        FileManager.toiletRetriever(TARGET+"/toilet_data1.csv");
        assertEquals(1, CurrentStorage.getToiletArray().size());
    }

    /**
     * Tests the FileManager's toiletRetriever works as expected for a .csv file with 10 Toilets
     */
    @Test
    public void testToiletTenEntries() {
        FileManager.toiletRetriever(TARGET+"/toilet_data10.csv");
        assertEquals(10, CurrentStorage.getToiletArray().size());
    }

    /**
     * Tests the FileManager's poiRetriever works as expected for a .csv with 1 Poi.
     */
    @Test
    public void testPoiOneEntry() {
        FileManager.poiRetriever(TARGET+"/poi_data1.csv");
        Poi poi = new Poi(40.745968,-73.994039,"siteOne","sherwood forest entrance",0);
        assertTrue(CurrentStorage.getPoiArray().get(0).equals(poi));
    }

    /**
     * Tests the poiRetriever in the FileManager class does not add duplicates to the CurrentStorage.
     */
    @Test
    public void testPoiDuplicates() {
        FileManager.poiRetriever(TARGET+"/poi_data1.csv");
        FileManager.poiRetriever(TARGET+"/poi_data1.csv");
        assertEquals(1, CurrentStorage.getPoiArray().size());
    }

    /**
     * Tests the FileManager's poiRetriever for the expected result of a .csv with 10 Poi.
     */
    @Test
    public void testPoiTenEnteries() {
        FileManager.poiRetriever(TARGET+"/poi_data10.csv");
        assertEquals(10, CurrentStorage.getPoiArray().size());
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Test
    public void testEmpty() {
        FileManager.retailerRetriever(TARGET+"/empty_file.csv");
        FileManager.wifiRetriever(TARGET+"/empty_file.csv");
        FileManager.routeRetriever(TARGET+"/empty_file.csv");
        FileManager.toiletRetriever(TARGET+"/empty_file.csv");
        FileManager.poiRetriever(TARGET+"/empty_file.csv");
        assertEquals(0, CurrentStorage.getRetailerArray().size());
        assertEquals(0, CurrentStorage.getRouteArray().size());
        assertEquals(0, CurrentStorage.getWifiArray().size());
        assertEquals(0, CurrentStorage.getToiletArray().size());
        assertEquals(0, CurrentStorage.getPoiArray().size());
    }

    /**
     * Test to test FileManager's ability to throw a FileNotFound exception
     */
    @Test(expected = FileNotFoundException.class)
    public void testNonExistant()
    {
        thrown.expect(FileNotFoundException.class);
        FileManager.readFile(TARGET+"/gibbletyfook.csv");
    }

    /**
     * Test to test FileManager's ability to handle files of the wrong format.
     */
    @Test(expected = WrongFormatException.class)
    public void testWrongFormat()
    {
        thrown.expect(WrongFormatException.class);
        FileManager.readFile(TARGET+"/terrible_format.csv");
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteRouteFile()
    {
        FileManager.routeRetriever(TARGET+"/route_data1.csv");
        FileManager.routeWriter(WRITE_TARGET+"/route_test_file.csv", CurrentStorage.getRouteArray());
        assertTrue(new File(WRITE_TARGET+"/route_test_file.csv").exists());
        CurrentStorage.flush();
        FileManager.routeRetriever(WRITE_TARGET+"/route_test_file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4),
                new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4),
                "Expected Route", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }

    @Test
    public void testWriteRouteTenFile() {
        FileManager.routeRetriever(TARGET+"/route_data10.csv");
        FileManager.routeWriter(WRITE_TARGET+"/route_test_10_file.csv", CurrentStorage.getRouteArray());
        assertTrue(new File(WRITE_TARGET+"/route_test_10_file.csv").exists());
        CurrentStorage.flush();
        FileManager.routeRetriever(WRITE_TARGET+"/route_test_10_file.csv");
        assertEquals(10, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's ability to write a file with a filename that contains spaces.
     */
    @Test
    public void testWriteRouteFileSpaces()
    {
        FileManager.routeRetriever(TARGET+"/route_data1.csv");
        FileManager.routeWriter(WRITE_TARGET+"/test file.csv", CurrentStorage.getRouteArray());
        assertTrue(new File(WRITE_TARGET+"/test file.csv").exists());
        CurrentStorage.flush();
        FileManager.routeRetriever(WRITE_TARGET+"/test file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4),
                new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4),
                "Expected Route", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteWifiFile()
    {
        FileManager.wifiRetriever(TARGET+"/wifi_data1.csv");
        FileManager.wifiWriter(WRITE_TARGET+"/test_file.csv", CurrentStorage.getWifiArray());
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        CurrentStorage.flush();
        FileManager.wifiRetriever(WRITE_TARGET+"/test_file.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "mn-05-123662", "Manhattan","Free","LinkNYC - Citybridge");
        assertTrue(CurrentStorage.getWifiArray().get(0).equals(expected_wifi));
    }

    /**
     * Test the ability of wifiWriter to write a file with 10 wifis.
     */
    @Test
    public void testWriteTenWifiFile(){
        FileManager.wifiRetriever(TARGET+"/wifi_data10.csv");
        FileManager.wifiWriter(WRITE_TARGET+"/test_wifi_10.csv", CurrentStorage.getWifiArray());
        assertTrue(new File(WRITE_TARGET+"/test_wifi_10.csv").exists());
        CurrentStorage.flush();
        FileManager.wifiRetriever(WRITE_TARGET+"/test_wifi_10.csv");
        assertEquals(10, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteRetailerFile()
    {
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        FileManager.retailerWriter(WRITE_TARGET+"retailer_test_file.csv", CurrentStorage.getRetailerArray());
        assertTrue(new File(WRITE_TARGET+"/retailer_test_file.csv").exists());
        CurrentStorage.flush();
        FileManager.retailerRetriever(WRITE_TARGET+"/retailer_test_file.csv");
        Retailer expectedRetailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse");
        assertTrue(CurrentStorage.getRetailerArray().get(0).equals(expectedRetailer));
    }

    @Test
    public void testWriteRetailerTenFile() {
        FileManager.retailerRetriever(TARGET+"/retailer_data10.csv");
        FileManager.retailerWriter(WRITE_TARGET+"retailer_test_10_file.csv", CurrentStorage.getRetailerArray());
        assertTrue(new File(WRITE_TARGET+"/retailer_test_10_file.csv").exists());
        CurrentStorage.flush();
        FileManager.retailerRetriever(WRITE_TARGET+"/retailer_test_10_file.csv");
        assertEquals(10, CurrentStorage.getRetailerArray().size());
    }

    @Test
    public void testWriteToiletFile() {
        FileManager.toiletRetriever(TARGET+"/toilet_data1.csv");
        FileManager.toiletWriter(WRITE_TARGET+"/toilet_test_file.csv", CurrentStorage.getToiletArray());
        assertTrue(new File(WRITE_TARGET+"/toilet_test_file.csv").exists());
        CurrentStorage.flush();
        FileManager.toiletRetriever(WRITE_TARGET+"/toilet_test_file.csv");
        Toilet expectedToilet = new Toilet(40.745968,-73.994039,"Not a Real Toilet",true,false);
        assertTrue(CurrentStorage.getToiletArray().get(0).equals(expectedToilet));
    }

    @Test
    public void testWriteToiletTenFile() {
        FileManager.toiletRetriever(TARGET+"/toilet_data10.csv");
        FileManager.toiletWriter(WRITE_TARGET+"/toilet_test_10_file.csv", CurrentStorage.getToiletArray());
        assertTrue(new File(WRITE_TARGET+"/toilet_test_10_file.csv").exists());
        CurrentStorage.flush();
        FileManager.toiletRetriever(WRITE_TARGET+"/toilet_test_10_file.csv");
        assertEquals(10, CurrentStorage.getToiletArray().size());
    }

    @Test
    public void testWritePoiFile() {
        FileManager.poiRetriever(TARGET+"/poi_data1.csv");
        FileManager.poiWriter(WRITE_TARGET+"/poi_test_file.csv", CurrentStorage.getPoiArray());
        assertTrue(new File(WRITE_TARGET+"/poi_test_file.csv").exists());
        Poi expectedPoi = new Poi(40.745968,-73.994039,"siteOne","sherwood forest entrance",0);
        assertTrue(CurrentStorage.getPoiArray().get(0).equals(expectedPoi));
    }

    @Test
    public void testWritePoiTenFile() {
        FileManager.poiRetriever(TARGET+"/poi_data10.csv");
        FileManager.poiWriter(WRITE_TARGET+"/poi_test10_file.csv", CurrentStorage.getPoiArray());
        assertTrue(new File(WRITE_TARGET+"/poi_test10_file.csv").exists());
        FileManager.poiRetriever(WRITE_TARGET+"/poi_test10_file.csv");
        assertEquals(10, CurrentStorage.getPoiArray().size());
    }
}
