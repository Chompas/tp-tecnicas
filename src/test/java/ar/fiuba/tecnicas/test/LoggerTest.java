package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String anotherMessage;
	private String errorMessage;
	private String filterRegex;
	private LogLevel level;
	private LogMessage logMessage;
	private LogMessage logEmptyMessage;
	private static Date now = new Date();
	
	@Before
	public void init() {
		this.logger = new Logger();
		this.message = "simple message";
		this.anotherMessage = "another message";
		this.errorMessage = "ERROR MESSAGE";
		this.filterRegex = "^((?!simple).)*$";		
		this.level = LogLevel.DEBUG;
		this.logMessage = new LogMessage(now, "%d{HH:mm:ss} %n %p %n %m", "-", this.message, this.level);
		this.logEmptyMessage = new LogMessage(now, "%d{HH:mm:ss} %n %p %n %m", "-", "", this.level);	
	}
	
	@After
	public void tearDown() {
		File file = new File("log.txt");
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
		}
	}
	
	@Test
	public void log() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(now, this.message, this.level);
		
		Mockito.verify(mockedHandler).write(this.logMessage);
	}
	
	@Test
	public void logWithException() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(now, this.message, this.level, new Exception(this.errorMessage));
		
		LogMessage logErrorMessage = new LogMessage(now, "%d{HH:mm:ss} %n %p %n %m", "-", this.message + " Exception: " + this.errorMessage, this.level);
				
		Mockito.verify(mockedHandler).write(logErrorMessage);
	}
	
	@Test
	public void logWithExceptionWithFilterRegexNoExpectedMessage() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.message, this.level, new Exception(this.errorMessage));
		
		Mockito.verify(mockedHandler, Mockito.never()).write(this.logEmptyMessage);
	}	
	
	@Test
	public void logWithFilterRegexNoExpectedMessage() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.message, this.level);
		
		Mockito.verify(mockedHandler, Mockito.never()).write(this.logEmptyMessage);
	}	
	
	@Test
	public void logWithFilterRegex() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.anotherMessage, this.level);
				
		this.logMessage = new LogMessage(now, "%d{HH:mm:ss} %n %p %n %m", "-", this.anotherMessage, this.level);
		
		Mockito.verify(mockedHandler).write(this.logMessage);
	}	
}
