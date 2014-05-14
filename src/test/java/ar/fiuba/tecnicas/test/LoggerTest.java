package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.logging.Level;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class LoggerTest {

	@Test
	public void integrationTest() {
		Logger logger = new Logger(null);
		logger.addHandler(new ConsoleHandler());
		
		FileHandler fileHandler = new FileHandler();
		fileHandler.setFilename("test.log");
		logger.addHandler(fileHandler);
		
		logger.log("Test", LogLevel.WARN);
	}
	
	@Test
	public void simpleLog()
	{
		Logger logger = new Logger(null);
		
		ConsoleHandler mockedConsoleHandler = mock(ConsoleHandler.class);
		
		logger.addHandler(mockedConsoleHandler);
		
		// act
		logger.log("simple message", LogLevel.ERROR);
		
		// assert
		Mockito.verify(mockedConsoleHandler).write("simple message");
	}

}
