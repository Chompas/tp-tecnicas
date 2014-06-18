package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.LogLevel;
import ar.fiuba.tecnicas.logging.LogMessage;
import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.formatter.ILogFormatter;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

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
	private ArrayList<IHandler> handlers;
	private ILogFormatter formatter;
	private ConsoleHandler mockedHandler;
	private String format = "%d{HH:mm:ss} %n %p %n %m";
	private String separator = "-";
	
	@Before
	public void init() {
		this.mockedHandler = mock(ConsoleHandler.class);
		this.formatter = new LogFormatter(format, separator);
		this.handlers = new ArrayList<>();
		this.handlers.add(this.mockedHandler);
		this.level = LogLevel.DEBUG;
		this.logger = new Logger(level, formatter, handlers);
		this.message = "simple message";
		this.anotherMessage = "another message";
		this.errorMessage = "ERROR MESSAGE";
		this.filterRegex = "^((?!simple).)*$";		
		
		this.logMessage = new LogMessage(now, formatter, this.message, this.level);
		this.logEmptyMessage = new LogMessage(now, formatter, this.message, this.level, "");	
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
		
		
		this.logger.log(now, this.message, this.level);
		
		Mockito.verify(mockedHandler).write(this.logMessage.getFormattedMessage());
	}
	
	@Test
	public void logWithException() {		
		this.logger.log(now, this.message, this.level, new Exception(this.errorMessage));
		
		LogMessage logErrorMessage = new LogMessage(now,formatter , this.message + " Exception: " + this.errorMessage, this.level);
				
		Mockito.verify(mockedHandler).write(logErrorMessage.getFormattedMessage());
	}
	
	@Test
	public void logWithExceptionWithFilterRegexNoExpectedMessage() {
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.message, this.level, new Exception(this.errorMessage));
		
		Mockito.verify(mockedHandler, Mockito.never()).write(this.logEmptyMessage.getFormattedMessage());
	}	
	
	@Test
	public void logWithFilterRegexNoExpectedMessage() {
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.message, this.level);
		
		Mockito.verify(mockedHandler, Mockito.never()).write(this.logEmptyMessage.getFormattedMessage());
	}	
	
	@Test
	public void logWithFilterRegex() {
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(now, this.anotherMessage, this.level);
				
		this.logMessage = new LogMessage(now, formatter, this.anotherMessage, this.level);
		
		Mockito.verify(mockedHandler).write(this.logMessage.getFormattedMessage());
	}	
}
