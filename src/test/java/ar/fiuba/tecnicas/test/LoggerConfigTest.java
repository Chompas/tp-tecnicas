package ar.fiuba.tecnicas.test;

import org.junit.Test;

import static org.junit.Assert.*;
import ar.fiuba.tecnicas.logging.config.*;

public class LoggerConfigTest {
	
	private LoggerConfig config;
	
	@Test
	public void getGlobalLogLevel() {
		this.config = new LoggerConfig();
		
		IProperties defaultConfig = new DefaultProperties();
		
		String expected = defaultConfig.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);		
	}

}
