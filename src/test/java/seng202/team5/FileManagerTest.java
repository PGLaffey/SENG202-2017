package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seng202.Model.*;
import seng202.exceptions.WrongFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class FileManagerTest extends TestCase {
    private String TARGET = new File(this.getClass().getResource("/testdata/").getFile()).getAbsolutePath();
    private String WRITE_TARGET = new File(seng202.Model.FileManager.class.getResource("/data_files/").getFile()).toString();
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
        System.out.println(WRITE_TARGET);
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
     * Test to test FileManager's readFile functions as expected for a .csv with 1 route.
     */
    @Test
    public void testRouteOneEntry()
    {
        FileManager.routeRetriever(TARGET+"/route_data1.csv");

        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4, 4),
                                        new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4, 4),
                                        "NA", "0");

        assertEquals(expected_route, CurrentStorage.getRouteArray().get(0));
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
     * Test to test FileManager's readFile functions as expected for a .csv with 10 routes.
     */
    @Test
    public void testRouteTenEntries() {
        //Only need to test whether there are 10 objects as it was previously tested whether routes are saved properly, and tested that duplicates are not saved.
        FileManager.routeRetriever(TARGET+"/route_data10.csv");
        assertEquals(10, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a WiFi location
     */
    @Test
    public void testWifiOneEntry() {
        FileManager.wifiRetriever(TARGET+"/wifi_data1.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "mn-05-123662", "Manhattan","Free","LinkNYC - Citybridge");
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
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 WiFi locations.
     */
    @Test
    public void testWifiTenEntries() {
        FileManager.wifiRetriever(TARGET+"/wifi_data10.csv");
        assertEquals(10, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a Retailer
     */
    @Test
    public void testRetailerOneEntry() {
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse", 10004);
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
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 Retailers
     */
    @Test
    public void testRetailerTenEntries() {
        FileManager.retailerRetriever(TARGET+"/retailer_data10.csv");
        assertEquals(10, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Test
    public void testEmpty() {
        FileManager.retailerRetriever(TARGET+"/empty_file.csv");
        FileManager.wifiRetriever(TARGET+"/empty_file.csv");
        FileManager.routeRetriever(TARGET+"/empty_file.csv");
        assertEquals(0, CurrentStorage.getRetailerArray().size());
        assertEquals(0, CurrentStorage.getRouteArray().size());
        assertEquals(0, CurrentStorage.getWifiArray().size());
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
        //TODO: confirm this and potentially require a user as well.
        FileManager.routeWriter("test_file.csv");
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4, 4),
                new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4, 4),
                "Expected Route", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }




    /**
     * Test to test the FileManager's ability to write a file with a filename that contains spaces.
     */
    @Test
    public void testWriteRouteFileSpaces()
    {
        FileManager.routeRetriever(TARGET+"/route_data1.csv");
        FileManager.routeWriter("test file.csv");
        assertTrue(new File(WRITE_TARGET+"/test file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "E 47 St & 2 Ave", 4, 4),
                new Location(40.73221853, -73.98165557, "1 Ave & E 15 St", 4, 4),
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
        FileManager.wifiWriter("test_file", CurrentStorage.getWifiArray());
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "mn-05-123662", "Manhattan","Free","LinkNYC - Citybridge");
        assertTrue(CurrentStorage.getWifiArray().get(0).equals(expected_wifi));
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteRetailerFile()
    {
        FileManager.retailerRetriever(TARGET+"/retailer_data1.csv");
        FileManager.retailerWriter("test_file");
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse", 10004);
        assertTrue(CurrentStorage.getRetailerArray().get(0).equals(expected_retailer));
    }

}
