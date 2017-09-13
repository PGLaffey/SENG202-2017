package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seng202.exceptions.NoDataException;
import seng202.exceptions.WrongFormatException;

import java.io.File;
import java.io.FileNotFoundException;


@Ignore public class FileManagerTest extends TestCase {
    private String TARGET = new File(this.getClass().getResource("/testdata/").getFile()).getAbsolutePath();

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
        //TARGET = new File("./testdata/route_data1.csv");
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 1 route.
     */
    @Test
    public void testRouteOneEntry()
    {
        FileManager.readFile(getClass().getResource(TARGET+"/route_data1.csv").getFile());
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "Expected start", 4),
                                        new Location(40.73221853, -73.98165557, "Expected end", 4),
                                        "Expected Route", "0");
        assertEquals(expected_route, CurrentStorage.getRouteArray().get(0));

    }

    /**
     * Test to ensure that duplicates are handled properly.
     */
    @Ignore @Test
    public void testRouteDuplicates()
    {
        //Read the same data twice, should only save it once.
        FileManager.readFile(TARGET+"/route_data1.csv");
        FileManager.readFile(TARGET+"/route_data1.csv");
        assertEquals(1, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 10 routes.
     */
    @Ignore @Test
    public void testRouteTenEntries() {
        FileManager.readFile(TARGET+"/route_data10.csv");
        //Only need to test whether there are 10 objects as it was previously tested whether routes are saved properly, and tested that duplicates are not saved.
        assertEquals(10, CurrentStorage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a WiFi location
     */
    @Ignore @Test
    public void testWifiOneEntry() {

        FileManager.readFile(TARGET+"/wifi_data1.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "LinkNYC Free Wi-Fi", "Manhattan","Free","LinkNYC - Citybridge");
        assertEquals(expected_wifi, CurrentStorage.getWifiArray().get(0));
    }

    /**
     * Test to ensure duplicate WiFi data is handled correctly.
     */
    @Ignore @Test
    public void testWifiDuplicate() {

        FileManager.readFile(TARGET+"/wifi_data1.csv");
        FileManager.readFile(TARGET+"/wifi_data1.csv");
        assertEquals(1, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 WiFi locations.
     */
    @Ignore @Test
    public void testWifiTenEntries() {

        FileManager.readFile(TARGET+"/wifi_data10.csv");
        assertEquals(10, CurrentStorage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a Retailer
     */
    @Ignore @Test
    public void testRetailerOneEntry() {

        FileManager.readFile(TARGET+"/retailer_data1.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse", 10004);
        assertEquals(expected_retailer, CurrentStorage.getRetailerArray().get(0));
    }

    /**
     * Test to check if duplicate retailer entries are handled properly.
     */
    @Ignore @Test
    public void testRetailerDuplicate() {

        FileManager.readFile(TARGET+"/retailer_data1.csv");
        FileManager.readFile(TARGET+"/retailer_data1.csv");
        assertEquals(1, CurrentStorage.getRetailerArray().size());
    }
    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 Retailers
     */
    @Ignore @Test
    public void testRetailerTenEntries() {

        FileManager.readFile(TARGET+"/retailer_data10.csv");
        assertEquals(10, CurrentStorage.getRetailerArray().size());
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Ignore @Test(expected = NoDataException.class)
    public void testEmpty() {
        thrown.expect(NoDataException.class);
        FileManager.readFile(TARGET+"/empty_file.csv");
    }

    /**
     * Test to test FileManager's ability to throw a FileNotFound exception
     */
    @Ignore @Test(expected = FileNotFoundException.class)
    public void testNonExistant()
    {
        thrown.expect(FileNotFoundException.class);
        FileManager.readFile(TARGET+"/gibbletyfook.csv");
    }

    /**
     * Test to test FileManager's ability to handle files of the wrong format.
     */
    @Ignore @Test(expected = WrongFormatException.class)
    public void testWrongFormat()
    {
        thrown.expect(WrongFormatException.class);
        FileManager.readFile(TARGET+"/terrible_format.csv");
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    public void testWriteRouteFile()
    {
        FileManager.readFile(TARGET+"/route_data1.csv");
        //TODO: confirm this and potentially require a user as well.
        //.writeFile("test_file.csv", CurrentStorage.getRouteArray());
        assertTrue(new File(TARGET+"/test_file.csv").exists());
        Route expected_route = new Route("16950", new Location(40.75323098, -73.97032517, "Expected start", 4),
                new Location(40.73221853, -73.98165557, "Expected end", 4),
                "Expected Route", "0");
        assertEquals(expected_route, CurrentStorage.getRouteArray().get(0));
    }
}
