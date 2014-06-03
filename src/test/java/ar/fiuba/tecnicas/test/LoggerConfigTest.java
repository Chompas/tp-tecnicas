package ar.fiuba.tecnicas.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import static org.junit.Assert.*;
import ar.fiuba.tecnicas.logging.config.*;

public class LoggerConfigTest {
	
	private LoggerConfig config;
	private static File propertiesFileOriginal = new File("config.properties");
	private static File propertiesFileCopy = new File("config2.properties");
	private static File xmlFileOriginal = new File("config.xml");
	private static File xmlFileCopy = new File("config2.xml");
	
	private void deletePropertiesFile()
	{		
		try {
			Files.copy(propertiesFileOriginal.toPath(), propertiesFileCopy.toPath());
			Files.delete(propertiesFileOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void deleteXmlFile()
	{		
		try {
			Files.copy(xmlFileOriginal.toPath(), xmlFileCopy.toPath());
			Files.delete(xmlFileOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restorePropertiesFile()
	{
		try {
			Files.copy(propertiesFileCopy.toPath(), propertiesFileOriginal.toPath());
			Files.delete(propertiesFileCopy.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restoreXmlFile()
	{
		try {
			Files.copy(xmlFileCopy.toPath(), xmlFileOriginal.toPath());
			Files.delete(xmlFileCopy.toPath());
		} catch (IOException e) {
			
		}
	}
	
	@Test
	public void getLogLevelFromJavaProperties() {		
		this.config = new LoggerConfig();
		
		IProperties javaProperties = new JavaProperties("config.properties");
		
		String expected = javaProperties.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);		
	}
	
	@Test
	public void getLogLevelFromXmlProperties() {
		this.deletePropertiesFile();
		this.config = new LoggerConfig();
		
		IProperties xmlProperties = new XmlProperties("config.xml");
		
		String expected = xmlProperties.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);
		this.restorePropertiesFile();
	}
	
	@Test
	public void getLogLevelFromDefaultProperties() {
		this.deletePropertiesFile();
		this.deleteXmlFile();
		this.config = new LoggerConfig();
		
		IProperties defaultConfig = new DefaultProperties();
		
		String expected = defaultConfig.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);
		this.restorePropertiesFile();
		this.restoreXmlFile();
	}
	


}
