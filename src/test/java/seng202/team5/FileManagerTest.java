package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seng202.Model.*;
import seng202.exceptions.WrongFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


@Ignore public class FileManagerTest extends TestCase {
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
        result = FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(result);

        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "Expected start", 4),
                                        new Location(40.73221853, -73.98165557, "Expected end", 4),
                                        "Expected Route", "0");

        assertEquals(expected_route, CurrentStorage.getRouteArray().get(0));
    }

    /**
     * Test to ensure that duplicates are handled properly.
     */
    @Test
    public void testRouteDuplicates()
    {
        //Read the same data twice, should only save it once.
        result = FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(result);
        result = FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(result);
        assertEquals(1, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 10 routes.
     */
    @Test
    public void testRouteTenEntries() {
        result = FileManager.readFile(TARGET+"/route_data10.csv");
        //Only need to test whether there are 10 objects as it was previously tested whether routes are saved properly, and tested that duplicates are not saved.
        FileManager.routeRetriever(result);
        assertEquals(10, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a WiFi location
     */
    @Test
    public void testWifiOneEntry() {

        result = FileManager.readFile(TARGET+"/wifi_data1.csv");
        FileManager.wifiRetriever(result);
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "LinkNYC Free Wi-Fi", "Manhattan","Free","LinkNYC - Citybridge");
        assertTrue(CurrentStorage.getWifiArray().get(0).equals(expected_wifi));
    }

    /**
     * Test to ensure duplicate WiFi data is handled correctly.
     */
    @Test
    public void testWifiDuplicate() {
        result = FileManager.readFile(TARGET+"/wifi_data1.csv");
        FileManager.wifiRetriever(result);
        result = FileManager.readFile(TARGET+"/wifi_data1.csv");
        FileManager.wifiRetriever(result);
        assertEquals(1, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 WiFi locations.
     */
    @Test
    public void testWifiTenEntries() {
        result = FileManager.readFile(TARGET+"/wifi_data10.csv");
        FileManager.wifiRetriever(result);
        assertEquals(10, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a Retailer
     */
    @Test
    public void testRetailerOneEntry() {

        result = FileManager.readFile(TARGET+"/retailer_data1.csv");
        FileManager.retailerRetriever(result);
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse", 10004);
        assertTrue(CurrentStorage.getRetailerArray().get(0).equals(expected_retailer));
    }

    /**
     * Test to check if duplicate retailer entries are handled properly.
     */
    @Test
    public void testRetailerDuplicate() {

        result = FileManager.readFile(TARGET+"/retailer_data1.csv");
        FileManager.retailerRetriever(result);
        result = FileManager.readFile(TARGET+"/retailer_data1.csv");
        FileManager.retailerRetriever(result);
        assertEquals(1, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 Retailers
     */
    @Test
    public void testRetailerTenEntries() {
        result = FileManager.readFile(TARGET+"/retailer_data10.csv");
        FileManager.retailerRetriever(result);
        assertEquals(10, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Test
    public void testEmpty() {
        result = FileManager.readFile(TARGET+"/empty_file.csv");
        FileManager.retailerRetriever(result);
        FileManager.wifiRetriever(result);
        FileManager.routeRetriever(result);
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
        result = FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(result);
        //TODO: confirm this and potentially require a user as well.
        FileManager.routeWriter("test_file.csv");
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "Expected start", 4),
                new Location(40.73221853, -73.98165557, "Expected end", 4),
                "Expected Route", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }

    /**
     * Test to test the FileManager's ability to write a file with a filename that contains spaces.
     */
    @Test
    public void testWriteRouteFileSpaces()
    {
        result = FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.routeRetriever(result);
        //TODO: confirm this and potentially require a user as well.
        FileManager.routeWriter("test file.csv");
        assertTrue(new File(WRITE_TARGET+"/test file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test file.csv");
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "Expected start", 4),
                new Location(40.73221853, -73.98165557, "Expected end", 4),
                "Expected Route", "0");

        assertTrue(CurrentStorage.getRouteArray().get(0).equals(expected_route));
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteWifiFile()
    {
        result = FileManager.readFile(TARGET+"/wifi_data1.csv");
        FileManager.wifiRetriever(result);
        //TODO: confirm this and potentially require a user as well.
        FileManager.wifiWriter("test_file");
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "LinkNYC Free Wi-Fi", "Manhattan","Free","LinkNYC - Citybridge");
        assertTrue(CurrentStorage.getWifiArray().get(0).equals(expected_wifi));
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Test
    public void testWriteRetailerFile()
    {
        result = FileManager.readFile(TARGET+"/retailer_data1.csv");
        FileManager.retailerRetriever(result);
        //TODO: confirm this and potentially require a user as well.
        FileManager.retailerWriter("test_file");
        assertTrue(new File(WRITE_TARGET+"/test_file.csv").exists());
        result = FileManager.readFile(WRITE_TARGET+"/test_file.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse", 10004);
        assertTrue(CurrentStorage.getRetailerArray().get(0).equals(expected_retailer));
    }

}
