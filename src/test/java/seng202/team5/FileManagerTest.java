package seng202.team5;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import seng202.exceptions.NoDataException;
import seng202.exceptions.WrongFormatException;

import java.io.FileNotFoundException;


public class FileManagerTest extends TestCase {

    private FileManager reader;
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
     * Set up a new FileManager object.
     */
    @Before
    public void setUp(){
        reader = new FileManager();
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 1 route.
     * TODO: Create the test files
     * TODO: Write expected output.
     */
    @Ignore
    @Test
    public void testRouteOneEntry()
    {
        reader.readFile(TARGET+"route_data1.csv");
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 10 routes.
     * TODO: Create the test files
     * TODO: Write expected output.
     */
    @Ignore
    @Test
    public void testRouteTenEntries() {
        reader.readFile(TARGET+"route_data10.csv");
    }


    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a WiFi location
     * TODO: Create the test file.
     * TODO: Write expected output.
     */
    public void testWifiOneEntry() {
        reader.readFile(TARGET+"wifi_data1.csv");
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 WiFi locations.
     * TODO: Create the test file.
     * TODO: Write expected output.
     */
    public void testWifiTenEntries() {
        reader.readFile(TARGET+"wifi_data10.csv");
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with a Retailer
     * TODO: Create the test file.
     * TODO: Write expected output.
     */
    public void testRetailerOneEntry() {
        reader.readFile(TARGET+"retailer_data1.csv");
    }

    /**
     * Test to test the FileManager's readFile functions as expected for a .csv with 10 Retailers
     * TODO: Create the test file.
     * TODO: Write expected output.
     */
    public void testRetailerTenEntries() {
        reader.readFile(TARGET+"retailer_data10.csv");
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     * TODO: Create the test files
     * TODO: Write expected output.
     */
    @Ignore
    @Test(expected = NoDataException.class)
    public void testEmpty() {
        reader.readFile(TARGET+"empty_file.csv");
    }

    /**
     * Test to test FileManager's ability to throw a FileNotFound exception
     * TODO: Create the test files
     * TODO: Write expected output.
     */
    @Ignore
    @Test(expected = FileNotFoundException.class)
    public void testNonExistant()
    {
        reader.readFile(TARGET+"gibbletyfook.csv");
    }

    /**
     * Test to test FileManager's ability to handle files of the wrong format.
     * TODO: Create the test files
     * TODO: Write expected output.
     */
    @Ignore
    @Test(expected = WrongFormatException.class)
    public void testWrongFormat()
    {
        reader.readFile(TARGET+"terribleFormat.csv");
    }

}
