package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;

public class FormatterTest {
	
	private LogFormatter defaultFormatter;
	private String message;
	private LogLevel level;

	@Before
	public void initialize()
	{
		defaultFormatter = new LogFormatter();
		message = "simple message";
		level = LogLevel.ERROR;
	}
	
	@Test
	public void testDefaultFormatterReturningMessageWithDefaultFormatCorrectly() {
		String formattedMessage = defaultFormatter.format(message, level);
		
		assertEquals(formattedMessage, "[" + level.name() + "] - " + message);	
	}
	
	@Test
	public void testFormatterReturningMessageWithSimpleFormatPassedByParameterCorrectly() {
		
		LogFormatter formatter = new LogFormatter("%p + %p + %m%p%% %m");
		
		String formattedMessage = formatter.format(message, level);
		
		assertEquals(formattedMessage, level.name()+ " + " + level.name() + " + " + message + level.name() + "% " + message);	
	}

}
