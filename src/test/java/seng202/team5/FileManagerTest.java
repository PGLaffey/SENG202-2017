package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import seng202.exceptions.NoDataException;
import seng202.exceptions.WrongFormatException;

import java.io.FileNotFoundException;
import java.util.ArrayList;


@Ignore public class FileManagerTest extends TestCase {

    private FileManager reader;
    private CurrentStorage storage;
    private static final String TARGET = "/testdata/";
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
        reader = new FileManager();
        storage = new CurrentStorage();
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 1 route.
     */
    @Ignore @Test
    public void testRouteOneEntry()
    {
        reader.readFile(TARGET+"route_data1.csv");
        Route expected_route = new Route(new Location(40.75323098, -73.97032517, "Expected start", 4),
                                        new Location(40.73221853, -73.98165557, "Expected end", 4),
                                        "Expected Route");
        assertEquals(expected_route, storage.getRouteArray().get(0));

    }

    /**
     * Test to ensure that duplicates are handled properly.
     */
    @Ignore @Test
    public void testRouteDuplicates()
    {
        //Read the same data twice, should only save it once.
        reader.readFile(TARGET+"route_data1.csv");
        reader.readFile(TARGET+"route_data1.csv");
        assertEquals(1, storage.getRouteArray().size());
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 10 routes.
     */
    @Ignore @Test
    public void testRouteTenEntries() {
        reader.readFile(TARGET+"route_data10.csv");
        //Only need to test whether there are 10 objects as it was previously tested whether routes are saved properly, and tested that duplicates are not saved.
        assertEquals(10, storage.getRouteArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a WiFi location
     */
    @Ignore @Test
    public void testWifiOneEntry() {
        reader.readFile(TARGET+"wifi_data1.csv");
        Wifi expected_wifi = new Wifi(40.745968, -73.994039, "LinkNYC Free Wi-Fi", "LinkNYC - Citybridge");
        assertEquals(expected_wifi, storage.getWifiArray().get(0));
    }

    /**
     * Test to ensure duplicate WiFi data is handled correctly.
     */
    @Ignore @Test
    public void testWifiDuplicate() {
        reader.readFile(TARGET+"wifi_data1.csv");
        reader.readFile(TARGET+"wifi_data1.csv");
        assertEquals(1, storage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 WiFi locations.
     */
    @Ignore @Test
    public void testWifiTenEntries() {
        reader.readFile(TARGET+"wifi_data10.csv");
        assertEquals(10, storage.getWifiArray().size());
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a Retailer
     */
    @Ignore @Test
    public void testRetailerOneEntry() {
        reader.readFile(TARGET+"retailer_data1.csv");
        Retailer expected_retailer = new Retailer("3 New York Plaza", "Starbucks Coffee", "Casual Eating & Takeout", "F-Coffeehouse");
        assertEquals(expected_retailer, storage.getRetailerArray().get(0));
    }

    /**
     * Test to check if duplicate retailer entries are handled properly.
     */
    @Ignore @Test
    public void testRetailerDuplicate() {
        reader.readFile(TARGET+"retailer_data1.csv");
        reader.readFile(TARGET+"retailer_data1.csv");
        assertEquals(1, storage.getRetailerArray().size());
    }
    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 Retailers
     */
    @Ignore @Test
    public void testRetailerTenEntries() {
        reader.readFile(TARGET+"retailer_data10.csv");
        assertEquals(10, storage.getRetailerArray().size());
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Ignore @Test(expected = NoDataException.class)
    public void testEmpty() {
        reader.readFile(TARGET+"empty_file.csv");
    }

    /**
     * Test to test FileManager's ability to throw a FileNotFound exception
     */
    @Ignore @Test(expected = FileNotFoundException.class)
    public void testNonExistant()
    {
        reader.readFile(TARGET+"gibbletyfook.csv");
    }

    /**
     * Test to test FileManager's ability to handle files of the wrong format.
     */
    @Ignore @Test(expected = WrongFormatException.class)
    public void testWrongFormat()
    {
        reader.readFile(TARGET+"terrible_format.csv");
    }

    /**
     * Test to test the FileManager's ability to write a file.
     */
    @Ignore @Test
    public void testWriteRouteFile() {
        reader.readFile(TARGET+"route_data1.csv");
        //TODO: confirm this and potentially require a user as well.
<<<<<<< HEAD
        ArrayList<String> content = new ArrayList<String>();
        content.add("This");
        content.add("Is");
        content.add("A");
        content.add("String");
        //TODO: Make real test data to use with the writeFile function.
        reader.writeFile("test_file.csv", content/* storage.getRouteArray() */);
=======
        //reader.writeFile("test_file.csv", storage.getRouteArray());
>>>>>>> 27e06ee0d248745398f34c7f042e16ab4b5214d6
        reader.readFile("test_file.csv");
        Route expected_route = new Route(new Location(40.75323098, -73.97032517, "Expected start", 4),
                new Location(40.73221853, -73.98165557, "Expected end", 4),
                "Expected Route");
        assertEquals(expected_route, storage.getRouteArray().get(0));
    }
}
