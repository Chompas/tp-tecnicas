package ar.fiuba.tecnicas.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import static org.junit.Assert.*;
import ar.fiuba.tecnicas.logging.config.*;

public class LoggerConfigTest {
	
	private LoggerConfig config;
	private static File configOriginal = new File("config.properties");
	private static File configCopy = new File("config2.properties");
	
	private void deleteConfigFile()
	{		
		try {
			Files.copy(configOriginal.toPath(), configCopy.toPath());
			Files.delete(configOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restoreConfigFile()
	{
		try {
			Files.copy(configCopy.toPath(), configOriginal.toPath());
			Files.delete(configCopy.toPath());
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void getLogLevelFromDefaultProperties() {
		this.deleteConfigFile();
		this.config = new LoggerConfig();
		
		IProperties defaultConfig = new DefaultProperties();
		
		String expected = defaultConfig.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);
		this.restoreConfigFile();
	}
	
	@Test
	public void getLogLevelFromJavaProperties() {		
		this.config = new LoggerConfig();
		
		IProperties javaProperties = new JavaProperties("config.properties");
		
		String expected = javaProperties.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);		
	}

}
