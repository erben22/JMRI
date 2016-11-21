package jmri.jmrix.ecos.utilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * RosterToEcosTest.java
 *
 * Description: tests for the RosterToEcos class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class RosterToEcosTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("RosterToEcos constructor",new RosterToEcos());
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
