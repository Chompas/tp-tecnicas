package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;
import org.mockito.*;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String formattedMessage;
	private String errorMessage;
	private LogLevel level;
	
	@Before
	public void init() {
		this.logger = new Logger();
		this.message = "simple message";
		this.errorMessage = "ERROR MESSAGE";
		
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
		
		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
		this.formattedMessage = now + " - " + this.level + " - " + this.message;
		Mockito.verify(mockedHandler).write(this.formattedMessage);
	}
	
	@Test
	public void logWithException() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(this.message, this.level, new Exception(this.errorMessage));
		
		String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
		this.formattedMessage = now + " - " + this.level + " - " + this.message + " Exception: "+this.errorMessage;
		Mockito.verify(mockedHandler).write(this.formattedMessage);
	}	
}
