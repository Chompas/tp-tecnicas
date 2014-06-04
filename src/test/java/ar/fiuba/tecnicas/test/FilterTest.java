package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Filter;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public class FilterTest {
	
	private Filter filter;
	private static String message = "test";
	
	@Test
	public void returnsEmptyStringIfLevelIsLower() {
		this.filter = new Filter(LogLevel.ERROR);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.DEBUG);
		LogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.DEBUG,"");
		
		assertEquals(null, filteredMessage);
	}
	
	@Test
	public void returnsStringIfLevelIsHigher() {	
		this.filter = new Filter(LogLevel.DEBUG);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.ERROR);
		LogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"");
		
		assertEquals(message, filteredMessage.getPlainMessage());
	}

}
