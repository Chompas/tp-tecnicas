package ar.fiuba.tecnicas.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindingSLF4JTest {

	private Logger logger;
	private String message;
	
    @Before
    public void setUp() {
        logger = LoggerFactory.getLogger(BindingSLF4JTest.class);
        message = "simple message";
    }
    
    @Test
    public void debug() {
    	logger.debug(message);
    }
    
    @Test
    public void info() {
    	logger.info(message);
    }
    
    @Test
    public void error() {
    	logger.error(message, new Exception("error"));
    }
    
    @Test
    public void warn() {
    	logger.warn(message);
    }
}
