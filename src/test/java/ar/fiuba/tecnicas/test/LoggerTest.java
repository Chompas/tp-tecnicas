package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.*;

import java.io.File;

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
		logger = new Logger(new File("config.xml"));
		message = "simple message";
		formattedMessage = "simple message - DEBUG";
	}
	
	@Test
	public void logCallsWrite()
	{	
		// arrange
		ConsoleHandler mockedConsoleHandler = mock(ConsoleHandler.class);
		logger.addHandler(mockedConsoleHandler);
		
		// act
		logger.log(message, LogLevel.DEBUG);
		
		// assert
		Mockito.verify(mockedConsoleHandler).write(formattedMessage);
	}

}
