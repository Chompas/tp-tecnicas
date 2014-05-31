package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Filter;
import ar.fiuba.tecnicas.logging.config.LogLevel;

public class FilterTest {
	
	private Filter filter;
	private static String message = "test";
	
	@Test
	public void returnsEmptyStringIfLevelIsLower() {
		this.filter = new Filter(LogLevel.ERROR);
		
		String filteredMessage = this.filter.filter(message, LogLevel.DEBUG,"");
		
		String expectedMessage = "";
		assertEquals(expectedMessage, filteredMessage);
	}
	
	@Test
	public void returnsStringIfLevelIsHigher() {	
		this.filter = new Filter(LogLevel.DEBUG);
		
		String filteredMessage = this.filter.filter(message, LogLevel.ERROR,"");
		
		assertEquals(message, filteredMessage);
	}

}
