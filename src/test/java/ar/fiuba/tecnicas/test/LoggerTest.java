package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.config.XmlProperties;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String formattedMessage;
	private LogLevel level;
	
	@Before
	public void iniatilize()
	{
		this.logger = new Logger(new File("config.xml"));
		this.message = "simple message";
		
		this.level = LogLevel.DEBUG;
		this.formattedMessage = "[" + this.level + "] - " + this.message;
	}
	
	@Test
	public void testlogCallsWrite()
	{	
		// arrange
		ConsoleHandler mockedConsoleHandler = mock(ConsoleHandler.class);
		logger.addHandler(mockedConsoleHandler);
		
		// act
		logger.log(message, level);
		
		// assert
		Mockito.verify(mockedConsoleHandler).write(formattedMessage);
	}
}
