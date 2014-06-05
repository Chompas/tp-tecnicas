package ar.fiuba.tecnicas.test;

import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class LoggerWithNameTest {
	
	private Logger logger;
	private String message;
	private LogLevel level;
	private LogMessage logMessage;
	private String loggerName;
	private static Date now = new Date();
	
	@Before
	public void init() {
		this.logger = new Logger();
		this.message = "simple message";	
		this.level = LogLevel.DEBUG;
		this.loggerName = "SuperLogger";
		this.logMessage = new LogMessage(now, "%d{HH:mm:ss} %n %p %n %m %g", "-", this.message, this.level, this.loggerName);	
	}
	
	
	@Test
	public void log() {
		ConsoleHandler mockedHandler = mock(ConsoleHandler.class);
		this.logger.addHandler(mockedHandler);
		
		this.logger.log(now, this.message, this.level, this.loggerName);
		
		Mockito.verify(mockedHandler).write(this.logMessage);
	}

}
