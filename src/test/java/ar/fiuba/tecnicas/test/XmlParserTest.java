package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.Logger;
import ar.fiuba.tecnicas.logging.config.XmlParser;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;
import ar.fiuba.tecnicas.logging.formatter.JsonLogFormatter;
import ar.fiuba.tecnicas.logging.formatter.TextLogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class XmlParserTest {
	
	private XmlParser parser;
	private String fileName;
	
	@Before
	public void setUp() {
		this.fileName = "config.xml";
		this.parser = new XmlParser();
	}
	
	@Test
	public void parserShouldNotCreateLoggersIfFileDoesntExist() {
		String nonExistantFile = "nonExistantConfig.xml";
		
		try {
			parser.load(nonExistantFile);
			fail();
		} catch (CouldNotReadConfigurationException e) {
		}
	}

	@Test
	public void parserShouldCreateTwoLoggers() {
		
		ArrayList<Logger> loggers = new ArrayList<>();
		try {
			loggers = parser.load(this.fileName);
		} catch (CouldNotReadConfigurationException e) {
			fail();
		}
		
		assertEquals(2, loggers.size());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithNames() {		
		ArrayList<Logger> loggers = new ArrayList<>();
		
		try {
			loggers = parser.load(this.fileName);
		} catch (CouldNotReadConfigurationException e) {
			fail();
		}
		
		assertEquals("Logger1", loggers.get(0).getName());
		assertEquals("Logger2", loggers.get(1).getName());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithGlobalLogLevels() {		
		ArrayList<Logger> loggers = new ArrayList<>();
		
		try {
			loggers = parser.load(this.fileName);
		} catch (CouldNotReadConfigurationException e) {
			fail();
		}
		
		assertEquals(LogLevel.INFO, loggers.get(0).getGlobalLogLevel());
		assertEquals(LogLevel.DEBUG, loggers.get(1).getGlobalLogLevel());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithFormat() {		
		ArrayList<Logger> loggers = new ArrayList<>();
		try {
			loggers = parser.load(this.fileName);
		} catch (CouldNotReadConfigurationException e) {
			fail();
		}
		
		assertEquals(JsonLogFormatter.class, loggers.get(0).getIlogFormatter().getClass());
		assertEquals(TextLogFormatter.class, loggers.get(1).getIlogFormatter().getClass());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithHandlers() {		
		ArrayList<Logger> loggers = new ArrayList<>();
		try {
			loggers = parser.load(this.fileName);
		} catch (CouldNotReadConfigurationException e) {
			fail();
		}
		
		assertEquals(2, loggers.get(0).getHandlers().size());
		assertEquals(FileHandler.class, loggers.get(0).getHandlers().get(0).getClass());		
		assertEquals(ConsoleHandler.class, loggers.get(0).getHandlers().get(1).getClass());
				
		assertEquals(1, loggers.get(1).getHandlers().size());
		assertEquals(ConsoleHandler.class, loggers.get(1).getHandlers().get(0).getClass());
	}
}
