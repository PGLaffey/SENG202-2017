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
     * Test to test a blue sky scenario, i.e. File with actual data in correct format.
     */
    @Ignore
    @Test
    public void testOneEntry()
    {
        reader.readFile("real_data1.csv");
    }

    /**
     * Test to test FileManager's readFile functions as expected for a .csv with 10 entries.
     */
    @Ignore
    @Test
    public void testTenEntries() {
        reader.readFile("real_data10.csv");
    }

    /**
     * Test to test FileManager's readfile with an empty .csv file
     */
    @Ignore
    @Test(expected = NoDataException.class)
    public void testEmpty() {
        reader.readFile("empty_file.csv");
    }

    /**
     * Test to test FileManager's ability to throw a FileNotFound exception
     */
    @Ignore
    @Test(expected = FileNotFoundException.class)
    public void testNonExistant()
    {
        reader.readFile("gibbletyfook.csv");
    }

    /**
     * Test to test FileManager's ability to handle files of the wrong format.
     */
    @Ignore
    @Test(expected = WrongFormatException.class)
    public void testWrongFormat()
    {
        reader.readFile("terribleFormat.csv");
    }

}
