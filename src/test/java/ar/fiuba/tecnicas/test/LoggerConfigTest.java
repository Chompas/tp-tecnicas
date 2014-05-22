package ar.fiuba.tecnicas.test;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.config.JavaProperties;

public class LoggerConfigTest {
	
	private LoggerConfig config;
	
	@Test
	public void getGlobalLogLevel() {		
		JavaProperties mockedProperties = mock(JavaProperties.class);
		when(mockedProperties.getValue("LogLevel")).thenReturn("INFO");
		
		config = new LoggerConfig(mockedProperties);
		
		config.getGlobalLogLevel();
		
		Mockito.verify(mockedProperties).getValue("LogLevel");
		assertEquals(LogLevel.INFO, config.getGlobalLogLevel());		
	}
	
	@Test
	public void getHandlers() {		
		JavaProperties mockedProperties = mock(JavaProperties.class);
		when(mockedProperties.getValue("LogLevel")).thenReturn("INFO");
		
		config = new LoggerConfig(mockedProperties);
		
		config.getGlobalLogLevel();
		
		Mockito.verify(mockedProperties).getValue("LogLevel");
		assertEquals(LogLevel.INFO, config.getGlobalLogLevel());		
	}
}
