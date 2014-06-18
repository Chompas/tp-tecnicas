package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.LoggerManager;

public class LoggerManagerTest {
	
	private LoggerManager loggerManager;
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
		this.loggerManager = LoggerManager.getInstance();
		
		assertNotNull(this.loggerManager.getLogger("MyLogger"));
	}
	
	@Test
	public void getLogLevelFromXmlProperties() {
		this.deleteJavaProperties();
		
		this.loggerManager = LoggerManager.getInstance();
		
		assertNotNull(this.loggerManager.getLogger("Logger1"));
		assertNotNull(this.loggerManager.getLogger("Logger2"));
		
		this.restoreJavaProperties();
	}
	
	@Test
	public void getLogLevelFromDefaultProperties() {
		this.deleteJavaProperties();
		this.deleteXmlProperties();
		this.loggerManager = LoggerManager.getInstance();
		
		assertNotNull(this.loggerManager.getLogger("Default"));
		
		this.restoreJavaProperties();
		this.restoreXmlProperties();
	}

}
