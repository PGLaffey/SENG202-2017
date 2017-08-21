package seng202.team5;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RouteTest extends TestCase{
    public RouteTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(RouteTest.class);
    }
}
