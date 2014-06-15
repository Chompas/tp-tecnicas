package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.LoggersWithNameFactory;
import ar.fiuba.tecnicas.logging.LoggerWithName;

public class LoggersWithNameFactoryTest {
	
	private LoggerWithName loggerWithName;
	private LoggersWithNameFactory loggerFactory;
	
	@Before
	public void init() {
		this.loggerWithName = new LoggerWithName(new Logger(), "myLogger");
		this.loggerFactory = LoggersWithNameFactory.getInstance();		
	}
	
	@Test
	public void addAndGetLoggerShouldReturnLogger() {
		this.loggerFactory.addLogger(this.loggerWithName);
		
		LoggerWithName expected = this.loggerWithName;		
		LoggerWithName actual = this.loggerFactory.getLogger("myLogger");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getNonExistentLoggerShouldReturnNull() {		
		LoggerWithName expected = null;
		LoggerWithName actual = this.loggerFactory.getLogger("inexistentLogger");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addDuplicateLoggerShouldReturnFalse() {		
		boolean expected = false;
		boolean actual = this.loggerFactory.addLogger(loggerWithName);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addNewLoggerShouldReturnTrue() {	
		boolean expected = true;
		boolean actual = this.loggerFactory.addLogger(new LoggerWithName(new Logger(), "aNewLogger"));
		
		assertEquals(expected, actual);
	}

}
