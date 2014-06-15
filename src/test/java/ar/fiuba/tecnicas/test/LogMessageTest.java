package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public class LogMessageTest {
	
	@Test
	public void equalsShouldReturnFalseForTwoDifferentClasses() {
		LogMessage logMessage = new LogMessage(new Date(), "%d{HH:mm:ss} %n %p %n %m", "-", "test", LogLevel.DEBUG);
		Logger logger = new Logger();
		
		assertNotEquals(logMessage, logger);
	}

}
