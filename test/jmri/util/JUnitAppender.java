package jmri.util;

import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;

import junit.framework.Assert;

/**
 * Log4J Appender that just publishes what it sees
 *
 * @author	Bob Jacobsen - Copyright 2007
 * @version	$Revision: 1.1 $
 */
 
public class JUnitAppender extends org.apache.log4j.ConsoleAppender {

    static java.util.ArrayList list = new java.util.ArrayList();
    
    /**
     * Called for each logging event.
     */
    public synchronized void append( LoggingEvent event ) {
        if (hold) 
            list.add(event);
        else
            super.append(event);
    }
    
    /**
     * Called once options are set.
     *
     * Currently just reflects back to super-class.
     */
    public void activateOptions() {
        if (JUnitAppender.instance != null)
            System.err.println("JUnitAppender initialized more than once");
        else
            JUnitAppender.instance = this;
        super.activateOptions();
    }

    /**
     * Do clean-up at end.
     *
     * Currently just reflects back to super-class.
     */
    public synchronized void close() {
        super.close();
    }
    
    static boolean hold = false;
        
    static private JUnitAppender instance = null;
    
    /**
     * Tell appender that a JUnit test is starting.
     * <P>
     * This causes log messages to be held for examination.
     */
    public static void start() {
        hold = true;
    }
    
    /**
     * Tell appender that the JUnit test is ended.
     * <P>
     * Any queued messages at this point will be passed
     * through to the actual log.
     */
    public static void end() {
        hold = false;
        while (!list.isEmpty()){
            instance().superappend((LoggingEvent)list.remove(0));
        }
    }
    
    void superappend(LoggingEvent l) {
        super.append(l);
    }
    
    /**
     * Check that the next queued message was of Error 
     * severity, and has a specific message.
     *<P>
     * Invokes a JUnit Assert if the message doesn't match
     */
    public static void assertErrorMessage(String msg) {
        if (list.isEmpty()) {
            Assert.fail("No message present: "+msg);
            return;
        }
        LoggingEvent evt = (LoggingEvent) list.remove(0);
        if ( (evt.priority!=Priority.ERROR) || !evt.getMessage().equals(msg) )
            Assert.fail("Unexpected message: "+evt.getMessage());
    }
    
    /**
     * Check that the next queued message was of Warn 
     * severity, and has a specific message.
     *<P>
     * Invokes a JUnit Assert if the message doesn't match
     */
    public static void assertWarnMessage(String msg) {
        if (list.isEmpty()) {
            Assert.fail("No message present: "+msg);
            return;
        }
        LoggingEvent evt = (LoggingEvent) list.remove(0);
        if ( (evt.priority!=Priority.WARN) || !evt.getMessage().equals(msg) )
            Assert.fail("Unexpected message: "+evt.getMessage());
    }
    
    public static JUnitAppender instance() {
        return JUnitAppender.instance;
    }
}
