package ar.fiuba.tecnicas.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import static org.junit.Assert.*;
import ar.fiuba.tecnicas.logging.config.*;

public class LoggerConfigTest {
	
	private LoggerConfig config;
	private static File javaPropertiesOriginal = new File("config.properties");
	private static File javaPropertiesCopy = new File("config2.properties");
	private static File xmlPropertiesOriginal = new File("config.xml");
	private static File xmlPropertiesCopy = new File("config2.xml");
	
	private void deleteJavaProperties() {		
		try {
			Files.copy(javaPropertiesOriginal.toPath(), javaPropertiesCopy.toPath());
			Files.delete(javaPropertiesOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void deleteXmlProperties() {
		try {
			Files.copy(xmlPropertiesOriginal.toPath(), xmlPropertiesCopy.toPath());
			Files.delete(xmlPropertiesOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restoreJavaProperties() {
		try {
			Files.copy(javaPropertiesCopy.toPath(), javaPropertiesOriginal.toPath());
			Files.delete(javaPropertiesCopy.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restoreXmlProperties() {
		try {
			Files.copy(xmlPropertiesCopy.toPath(), xmlPropertiesOriginal.toPath());
			Files.delete(xmlPropertiesCopy.toPath());
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
		this.deleteJavaProperties();
		this.config = new LoggerConfig();
		
		IProperties xmlProperties = new XmlProperties("config.xml");
		
		String expected = xmlProperties.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);
		this.restoreJavaProperties();
	}
	
	@Test
	public void getLogLevelFromDefaultProperties() {
		this.deleteJavaProperties();
		this.deleteXmlProperties();
		this.config = new LoggerConfig();
		
		IProperties defaultConfig = new DefaultProperties();
		
		String expected = defaultConfig.getValue("LogLevel");
		String actual = config.getGlobalLogLevel().toString();
				
		assertEquals(expected, actual);
		this.restoreJavaProperties();
		this.restoreXmlProperties();
	}
}
