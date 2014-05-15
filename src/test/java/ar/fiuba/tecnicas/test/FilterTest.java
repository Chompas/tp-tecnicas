package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Filter;
import ar.fiuba.tecnicas.logging.config.LogLevel;

public class FilterTest {
	
	private Filter filter;
	private static String message = "test";
	
	@Test
	public void returnsEmptyStringIfLevelIsLower()
	{	
		// arrange
		this.filter = new Filter(LogLevel.ERROR);
		
		// act
		String filteredMessage = this.filter.filter(message, LogLevel.DEBUG);
		
		// assert
		String expectedMessage = "";
		assertEquals(expectedMessage, filteredMessage);
	}
	
	@Test
	public void returnsStringIfLevelIsHigher()
	{	
		// arrange
		this.filter = new Filter(LogLevel.DEBUG);
		
		// act
		String filteredMessage = this.filter.filter(message, LogLevel.ERROR);
		
		// assert
		assertEquals(message, filteredMessage);
	}

}
