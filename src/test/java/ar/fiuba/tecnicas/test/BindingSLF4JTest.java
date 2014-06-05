package ar.fiuba.tecnicas.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.After;
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
    
    @After
	public void tearDown()
	{
		File file = new File("log.txt");
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
		}
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
