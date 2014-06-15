package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.CustomFilter;
import ar.fiuba.tecnicas.logging.Filter;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.ILogMessage;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.formatter.NullLogMessage;

public class FilterTest {
	
	private Filter filter;
	private static String message = "test";
	private CustomFilter customFilter = new CustomFilter();
	
	@Test
	public void returnsEmptyMessageIfLevelIsLower() {
		this.filter = new Filter(LogLevel.ERROR);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.DEBUG);
		ILogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.DEBUG,"", customFilter);
		
		assertEquals(filteredMessage.getClass(),NullLogMessage.class);
	}
	
	@Test
	public void returnsStringIfLevelIsHigher() {	
		this.filter = new Filter(LogLevel.DEBUG);
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.ERROR);
		ILogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(message, filteredMessage.getPlainMessage());
	}
	
	@Test
	public void customFilterWithFutureFromDate() {
		this.filter = new Filter(LogLevel.DEBUG);
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		this.customFilter.fromDate = tomorrow;
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.ERROR);
		ILogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(filteredMessage.getClass(),NullLogMessage.class);
	}
	
	@Test
	public void customFilterWithPastFromDate() {
		this.filter = new Filter(LogLevel.DEBUG);
		Date today = new Date();
		Date yesterday = new Date(today.getTime() - (1000 * 60 * 60 * 24));
		this.customFilter.fromDate = yesterday;
		
		LogMessage logMessage = new LogMessage(new Date(), "%m", "", message, LogLevel.ERROR);
		ILogMessage filteredMessage = this.filter.filter(logMessage, LogLevel.ERROR,"", customFilter);
		
		assertEquals(message, filteredMessage.getPlainMessage());
	}

}
