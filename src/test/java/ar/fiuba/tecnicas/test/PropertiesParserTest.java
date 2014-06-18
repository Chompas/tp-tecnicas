package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.Logger;
import ar.fiuba.tecnicas.logging.config.PropertiesParser;
import ar.fiuba.tecnicas.logging.formatter.TextLogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class PropertiesParserTest {
	
	private PropertiesParser parser;
	
	@Before
	public void setUp() {
		this.parser = new PropertiesParser();
	}
	
	@Test
	public void loggerIsCreated() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertNotNull(loggers.get(0));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void onlyOneloggerIsCreated() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals(1,loggers.size());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void logLevelIsCorrect() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals(LogLevel.DEBUG,loggers.get(0).getGlobalLogLevel());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void nameIsCorrect() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals("MyLogger",loggers.get(0).getName());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void formatterIsCorrect() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals(TextLogFormatter.class,loggers.get(0).getIlogFormatter().getClass());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void twoHandlersAreCreated() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals(2,loggers.get(0).getHandlers().size());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void correctHandlersAreCreated() {
		try {
			ArrayList<Logger> loggers = this.parser.load("config.properties");
			assertEquals(ConsoleHandler.class,loggers.get(0).getHandlers().get(0).getClass());
			assertEquals(FileHandler.class,loggers.get(0).getHandlers().get(1).getClass());
		} catch (Exception e) {
			fail();
		}
	}

}
