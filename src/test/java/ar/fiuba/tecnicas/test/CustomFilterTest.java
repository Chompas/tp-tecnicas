package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LogMessage;
import ar.fiuba.tecnicas.logging.filters.CustomFilter;
import ar.fiuba.tecnicas.logging.filters.Filter;
import ar.fiuba.tecnicas.logging.formatter.TextLogFormatter;

public class CustomFilterTest {
	
	private Filter filter;
	private static String message = "test";
	private CustomFilter customFilter = new CustomFilter();
	private TextLogFormatter formatter;
	
	@Test
	public void customFilterWithFutureFromDate() {
		this.filter = new Filter(LogLevel.DEBUG);
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		this.customFilter.fromDate = tomorrow;
		
		LogMessage logMessage = new LogMessage(new Date(), formatter, message, LogLevel.ERROR);
		String filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(filteredMessage, "");
	}
	
	@Test
	public void customFilterWithPastFromDate() {
		this.filter = new Filter(LogLevel.DEBUG);
		Date today = new Date();
		Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
		this.customFilter.fromDate = yesterday;
		
		LogMessage logMessage = new LogMessage(new Date(), formatter, message, LogLevel.ERROR);
		String filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(message, filteredMessage);
	}

}
