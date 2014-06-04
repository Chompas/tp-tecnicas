package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.CustomFilter;
import ar.fiuba.tecnicas.logging.Filter;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public class FilterTest {
	
	private Filter filter;
	private static String message = "test";
	private CustomFilter customFilter = new CustomFilter();
	
	@Test
	public void returnsEmptyStringIfLevelIsLower() {
		this.filter = new Filter(LogLevel.ERROR);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.DEBUG);
		LogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.DEBUG,"", customFilter);
		
		assertEquals(null, filteredMessage);
	}
	
	@Test
	public void returnsStringIfLevelIsHigher() {	
		this.filter = new Filter(LogLevel.DEBUG);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.ERROR);
		LogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(message, filteredMessage.getPlainMessage());
	}

}
