package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;

public class FormatterTest {
	
	private LogFormatter defaultFormatter;
	private String message;
	private LogLevel level;
	private String separator;
	private Date date;

	@Before
	public void initialize()
	{
		defaultFormatter = new LogFormatter("","");
		message = "simple message";
		level = LogLevel.ERROR;
		separator = "-";
		date = new Date();
	}
	
	@Test
	public void testDefaultFormatterReturningMessageWithDefaultFormatCorrectly() {
		String formattedMessage = defaultFormatter.format(message, level);
		
		assertEquals(formattedMessage, "[" + level.name() + "] - " + message);	
	}
	
	@Test
	public void testFormatterReturningMessageWithSimpleFormatPassedByParameterCorrectly() {
		
		LogFormatter formatter = new LogFormatter("%p + %p + %m%p%% %m","");
		
		String formattedMessage = formatter.format(message, level);
		
		assertEquals(formattedMessage, level.name()+ " + " + level.name() + " + " + message + level.name() + "% " + message);	
	}
	
	@Test
	public void testFormatterReturningSimpleDateFormatCorrectly() {
		
		String pattern = "yyyy.MM.dd";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		LogFormatter formatter = new LogFormatter("%d{" + pattern + "} %n %m %n %p",separator);
		String formattedMessage = formatter.format(message, level);
		
		assertEquals(formattedMessage, dateString + " " + separator + " " + message + " " + separator + " " + level.name());	
	}
	
	@Test
	public void testFormatterReturningAnotherSimpleDateFormatCorrectly() {
		
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		LogFormatter formatter = new LogFormatter("%d{" + pattern + "} %n %m %n %p",separator);
		String formattedMessage = formatter.format(message, level);
		
		assertEquals(formattedMessage, dateString + " " + separator + " " + message + " " + separator + " " + level.name());	
	}
	
	@Test
	public void testFormatterReturningFormatWithAllPosibilitiesCorrectly() {
		
		String pattern = "HH:mm dd-MM-yyyy";
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		String dateString = dateFormat.format(date);
		
		LogFormatter formatter = new LogFormatter("%d{" + pattern + "} ­%n %p %n­ %t %n­ %m %%", "*");
		String formattedMessage = formatter.format(message, level);
		
		String expectedMessage = dateString + " * " + level.name() + " * " + "main" + " * " + message + " %";

		System.out.println(formattedMessage);
		System.out.println(expectedMessage);
		
		assertEquals(formattedMessage, expectedMessage);	
	}

}
