package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import org.junit.*;

import org.mockito.*;

import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String formattedMessage;
	private LogLevel level;
	
	@Before
	public void init() {
		this.logger = new Logger();
		this.message = "simple message";
		
		this.level = LogLevel.DEBUG;
		this.formattedMessage = this.level + " - " + this.message;
	}
	
	@Test
	public void log() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(this.message, this.level);
		
		Mockito.verify(mockedHandler).write(this.formattedMessage);
	}
}
