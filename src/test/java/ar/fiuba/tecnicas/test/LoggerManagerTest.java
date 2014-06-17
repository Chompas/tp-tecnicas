package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.LoggerManager;

public class LoggerManagerTest {
	
	private LoggerManager loggerManager;
	
	@Before
	public void setUp() {
		this.loggerManager = LoggerManager.getInstance();
	}
	
	@Test
	public void addAndGetLoggerShouldReturnLogger() {
		Logger logger = new Logger("firstLogger");
		
		this.loggerManager.addLogger(logger);
		
		assertEquals(logger, this.loggerManager.getLogger("firstLogger"));
	}
	
	@Test
	public void getNonExistentLoggerShouldReturnNull() {		
		assertNull(this.loggerManager.getLogger("secondLogger"));
	}
	
	@Test
	public void addDuplicateLoggerShouldReturnFalse() {
		this.loggerManager.addLogger(new Logger("thirdLogger"));
		assertFalse(this.loggerManager.addLogger(new Logger("thirdLogger")));
	}
	
	@Test
	public void addNewLoggerShouldReturnTrue() {
		assertTrue(this.loggerManager.addLogger(new Logger("fourthLogger")));
	}
	
	@Test
	public void deleteExistentLoggerShouldReturnTrue() {
		this.loggerManager.addLogger(new Logger("fifthLogger"));
		
		assertTrue(this.loggerManager.deleteLogger("fifthLogger"));
	}
	
	@Test
	public void deleteNonexistentLoggerShouldReturnFalse() {
		this.loggerManager.addLogger(new Logger("sixthLogger"));		
		this.loggerManager.deleteLogger("sixthLogger");
		
		assertFalse(this.loggerManager.deleteLogger("sixthLogger"));
	}
	
	@Test
	public void getEmptyLogger() {
		assertNull(this.loggerManager.getLogger(""));
	}

}
