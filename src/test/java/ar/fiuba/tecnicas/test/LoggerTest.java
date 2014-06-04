package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
	
	@Before
	public void init() {
		this.logger = new Logger();
		this.message = "simple message";
		this.anotherMessage = "another message";
		this.errorMessage = "ERROR MESSAGE";
		this.filterRegex = "^((?!simple).)*$";
		
		this.level = LogLevel.DEBUG;
	}
	
	@After
	public void tearDown()
	{
		File file = new File("log.txt");
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void log() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(this.message, this.level);
		
//		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
		//this.formattedMessage = now + " - " + this.level + " - " + this.message;
		this.logMessage = new LogMessage("HH:mm:ss", "-", this.message, this.level);
		//Mockito.verify(mockedHandler).write(this.formattedMessage);
		Mockito.verify(mockedHandler).write(this.logMessage);
	}
	
	@Test
	public void logWithException() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(this.message, this.level, new Exception(this.errorMessage));
		
//		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
		//this.formattedMessage = now + " - " + this.level + " - " + this.message + " Exception: "+this.errorMessage;
		//Mockito.verify(mockedHandler).write(this.formattedMessage);
		this.logMessage = new LogMessage("HH:mm:ss", "-", this.message, this.level);
		Mockito.verify(mockedHandler).write(this.logMessage);
	}	
	
	@Test
	public void logWithFilterRegexNoExpectedMessage() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(this.message, this.level);
		
//		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
//		String expected = now + " - " + this.level + " - ";
		
		this.logMessage = new LogMessage("HH:mm:ss", "-", "", this.level);
		
//		Mockito.verify(mockedHandler).write(expected);
		Mockito.verify(mockedHandler).write(this.logMessage);
	}	
	
	@Test
	public void logWithFilterRegex() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		this.logger.addFilterRegex(this.filterRegex);
		
		this.logger.log(this.anotherMessage, this.level);
		
//		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
//		String expected = now + " - " + this.level + " - "+ this.anotherMessage ;
		
		this.logMessage = new LogMessage("HH:mm:ss", "-", this.anotherMessage, this.level);
		
//		Mockito.verify(mockedHandler).write(expected);
		Mockito.verify(mockedHandler).write(this.logMessage);
	}	
}
