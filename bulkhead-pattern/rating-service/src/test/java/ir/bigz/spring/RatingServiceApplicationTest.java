package ir.bigz.spring;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class RatingServiceApplicationTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RatingServiceApplicationTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RatingServiceApplicationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
