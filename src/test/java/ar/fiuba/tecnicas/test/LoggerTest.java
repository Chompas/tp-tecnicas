package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String formattedMessage;
	
	@Before
	public void iniatilize()
	{
		logger = new Logger(null);
		message = "simple message";
		formattedMessage = "simple message - ERROR";
	}
	
	@Test
	public void simpleLog()
	{	
		// arrange
		ConsoleHandler mockedConsoleHandler = mock(ConsoleHandler.class);		
		logger.addHandler(mockedConsoleHandler);
		
		// act
		logger.log(message, LogLevel.ERROR);
		
		// assert
		Mockito.verify(mockedConsoleHandler).write(message);
	}
	
	@Test
	public void formattedLog()
	{
		// arrange		
		LogFormatter mockedFormatter = mock (LogFormatter.class);		
		when(mockedFormatter.format(message, LogLevel.ERROR)).thenReturn(formattedMessage);
		
		ConsoleHandler mockedConsoleHandler = mock(ConsoleHandler.class);		
		logger.addHandler(mockedConsoleHandler);
		
		// act
		logger.log(message, LogLevel.ERROR, mockedFormatter);
		
		// assert
		Mockito.verify(mockedConsoleHandler).write(formattedMessage);
	}

}
