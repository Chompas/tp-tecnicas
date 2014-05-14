package ar.fiuba.tecnicas.test;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerConfig;
import ar.fiuba.tecnicas.logging.config.XmlProperties;

public class LoggerConfigTest {
	
	@Test
	public void testGetGlobalLogLevel()
	{
		// arrange
		XmlProperties mockedProperties = mock(XmlProperties.class);
		when(mockedProperties.getValue("LogLevel")).thenReturn("INFO");
		
		LoggerConfig config = new LoggerConfig(mockedProperties);
		
		// act
		config.setGlobalLogLevel();
		
		// assert
		Mockito.verify(mockedProperties).getValue("LogLevel");
		assertEquals(LogLevel.INFO, config.getGlobalLogLevel());
	}
}
