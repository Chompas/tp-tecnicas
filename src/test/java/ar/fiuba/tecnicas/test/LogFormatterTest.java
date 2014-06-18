package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LogMessage;
import ar.fiuba.tecnicas.logging.formatter.TextLogFormatter;

public class LogFormatterTest {
	
	private TextLogFormatter formatter;
	private String message;
	private LogLevel level;
	private String separator;
	private Date date;
	private LogMessage logMessage;

	@Before
	public void initialize() {
		separator = "-";
		formatter = new TextLogFormatter("", separator);
		message = "simple message";
		level = LogLevel.ERROR;
		date = new Date();
		this.logMessage = new LogMessage(date, formatter, message, level);		
	}
	
	@Test
	public void defaultFormatterReturningMessageWithDefaultFormatCorrectly() {
		String formattedMessage = formatter.format(this.logMessage);
		
		assertEquals(formattedMessage, "[" + level.name() + "] - " + message);	
	}
	
	@Test
	public void formatterReturningMessageWithSimpleFormatPassedByParameterCorrectly() {		
		TextLogFormatter formatter = new TextLogFormatter("%p + %p + %m%p%% %m", "");
		
		String formattedMessage = formatter.format(this.logMessage);
		
		assertEquals(formattedMessage, level.name() + " + " + level.name() + " + " + message + level.name() + "% " + message);	
	}
	
	@Test
	public void formatterReturningSimpleDateFormatCorrectly() {		
		String pattern = "yyyy.MM.dd";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		TextLogFormatter formatter = new TextLogFormatter("%d{" + pattern + "} %n %m %n %p", separator);
		String formattedMessage = formatter.format(this.logMessage);
		
		assertEquals(formattedMessage, dateString + " " + separator + " " + message + " " + separator + " " + level.name());	
	}
	
	@Test
	public void formatterReturningAnotherSimpleDateFormatCorrectly() {		
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		TextLogFormatter formatter = new TextLogFormatter("%d{" + pattern + "} %n %m %n %p", separator);
		String formattedMessage = formatter.format(this.logMessage);
		
		assertEquals(formattedMessage, dateString + " " + separator + " " + message + " " + separator + " " + level.name());	
	}

}
