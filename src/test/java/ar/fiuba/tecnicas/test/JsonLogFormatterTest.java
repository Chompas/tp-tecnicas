package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.JsonLogFormatter;

public class JsonLogFormatterTest {
	private Date date;
	private String message = "processing...";
	private LogLevel level = LogLevel.DEBUG;
	private String loggerName = "myLogger";
	
	private JsonLogFormatter jsonFormatter = new JsonLogFormatter();
	
	@Before
	public void setUp() {
		this.date = new Date();
	}
	
	@Test
	public void shouldFormatLikeJsonWithoutLoggerName() {
		String expected = "{\"datetime\":\"" + this.date.toString() + "\"," +
							"\"level\":\"" + this.level + "\"," +
							"\"message\":\"" + this.message + "\"}";
		String actual = jsonFormatter.format(date, message, level);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldFormatLikeJsonWithLoggerName() {
		String expected = "{\"datetime\":\"" + this.date.toString() + "\"," +
							"\"level\":\"" + this.level + "\"," +
							"\"loggerName\":\"" + this.loggerName + "\"," + 
							"\"message\":\"" + this.message + "\"}";
		String actual = jsonFormatter.format(date, message, level, loggerName);
		
		assertEquals(expected, actual);
	}

}
