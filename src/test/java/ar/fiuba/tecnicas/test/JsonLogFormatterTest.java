package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.LogLevel;
import ar.fiuba.tecnicas.logging.LogMessage;
import ar.fiuba.tecnicas.logging.formatter.JsonLogFormatter;

public class JsonLogFormatterTest {
	
	private Date date = new Date();
	private String message = "processing...";
	private LogLevel level = LogLevel.DEBUG;
	private String loggerName = "myLogger";	
	private JsonLogFormatter jsonFormatter = new JsonLogFormatter();	
	
	@Test
	public void shouldFormatLikeJsonWithoutLoggerName() {
		LogMessage logMessage = new LogMessage(date, "", "", message, level);
		String expected = "{\"datetime\":\"" + this.date.toString() + "\"," +
							"\"level\":\"" + this.level + "\"," +
							"\"message\":\"" + this.message + "\"}";
		String actual = jsonFormatter.format(logMessage);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldFormatLikeJsonWithLoggerName() {
		LogMessage logMessage = new LogMessage(date, "", "", message, level, loggerName);
		String expected = "{\"datetime\":\"" + this.date.toString() + "\"," +
							"\"level\":\"" + this.level + "\"," +
							"\"loggerName\":\"" + this.loggerName + "\"," + 
							"\"message\":\"" + this.message + "\"}";
		String actual = jsonFormatter.format(logMessage);
		
		assertEquals(expected, actual);
	}

}
