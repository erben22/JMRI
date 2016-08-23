package jmri.jmrix.dcc4pc.configurexml;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Dcc4PcReporterManagerXmlTest.java
 *
 * Description: tests for the Dcc4PcReporterManagerXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class Dcc4PcReporterManagerXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("Dcc4PcReporterManagerXml constructor",new Dcc4PcReporterManagerXml());
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    @After
    public void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }

}
