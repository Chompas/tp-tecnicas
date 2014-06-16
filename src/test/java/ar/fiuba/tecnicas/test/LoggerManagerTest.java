package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.LoggerManager;

public class LoggerManagerTest {
	
	private LoggerManager loggerFactory;
	
	@Before
	public void setUp() {
		this.loggerFactory = LoggerManager.getInstance();
	}
	
	@Test
	public void addAndGetLoggerShouldReturnLogger() {
		Logger logger = new Logger("firstLogger");
		
		assertTrue(this.loggerFactory.addLogger(logger));
		
		assertEquals(logger, this.loggerFactory.getLogger("firstLogger"));
	}
	
	@Test
	public void getNonExistentLoggerShouldReturnNull() {		
		assertNull(this.loggerFactory.getLogger("secondLogger"));
	}
	
	@Test
	public void addDuplicateLoggerShouldReturnFalse() {
		this.loggerFactory.addLogger(new Logger("thirdLogger"));
		assertFalse(this.loggerFactory.addLogger(new Logger("thirdLogger")));
	}
	
	@Test
	public void addNewLoggerShouldReturnTrue() {
		assertTrue(this.loggerFactory.addLogger(new Logger("fourthLogger")));
	}
	
	@Test
	public void deleteExistentLoggerShouldReturnTrue() {
		this.loggerFactory.addLogger(new Logger("fifthLogger"));
		
		assertTrue(this.loggerFactory.deleteLogger("fifthLogger"));
	}
	
	@Test
	public void deleteNonexistentLoggerShouldReturnFalse() {
		this.loggerFactory.addLogger(new Logger("sixthLogger"));		
		this.loggerFactory.deleteLogger("sixthLogger");
		
		assertFalse(this.loggerFactory.deleteLogger("sixthLogger"));
	}
	
	@Test
	public void getEmptyLogger() {
		assertNull(this.loggerFactory.getLogger(""));
	}

}
