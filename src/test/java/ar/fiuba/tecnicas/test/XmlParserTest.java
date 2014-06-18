package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.ConcreteLogger;
import ar.fiuba.tecnicas.logging.LogLevel;
import ar.fiuba.tecnicas.logging.config.XmlParser;
import ar.fiuba.tecnicas.logging.formatter.JsonLogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class XmlParserTest {
	
	private XmlParser parser;
	
	@Before
	public void setUp() {
		this.parser = new XmlParser();
	}
	
	@Test
	public void parserShouldNotCreateLoggersIfFileDoesntExist() {
		String nonExistantFile = "nonExistantConfig.xml";
		
		ArrayList<ConcreteLogger> loggers = parser.load(nonExistantFile);
		
		assertEquals(0, loggers.size());
	}

	@Test
	public void parserShouldCreateTwoLoggers() {		
		String configFile = "configTest.xml";
		
		ArrayList<ConcreteLogger> loggers = parser.load(configFile);
		
		assertEquals(2, loggers.size());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithNames() {		
		ArrayList<ConcreteLogger> loggers = parser.load("configTest.xml");
		
		assertEquals("Logger1", loggers.get(0).getName());
		assertEquals("Logger2", loggers.get(1).getName());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithGlobalLogLevels() {		
		ArrayList<ConcreteLogger> loggers = parser.load("configTest.xml");
		
		assertEquals(LogLevel.INFO, loggers.get(0).getGlobalLogLevel());
		assertEquals(LogLevel.DEBUG, loggers.get(1).getGlobalLogLevel());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithFormat() {		
		ArrayList<ConcreteLogger> loggers = parser.load("configTest.xml");
		
		assertEquals(JsonLogFormatter.class, loggers.get(0).getIlogFormatter());
		assertEquals(LogFormatter.class, loggers.get(1).getIlogFormatter());
	}
	
	@Test
	public void parserShouldCreateTwoLoggersWithHandlers() {		
		ArrayList<ConcreteLogger> loggers = parser.load("configTest.xml");
		
		assertEquals(2, loggers.get(0).getHandlers().size());
		assertEquals(FileHandler.class, loggers.get(0).getHandlers().get(0));		
		assertEquals(ConsoleHandler.class, loggers.get(0).getHandlers().get(1));
				
		assertEquals(1, loggers.get(1).getHandlers().size());
		assertEquals(ConsoleHandler.class, loggers.get(1).getHandlers().get(0));
	}
}
