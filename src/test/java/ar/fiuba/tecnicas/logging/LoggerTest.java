package ar.fiuba.tecnicas.logging;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class LoggerTest {

	@Test
	public void integrationTest() {
		Logger logger = new Logger(null);
		logger.addHandler(new ConsoleHandler());
		
		FileHandler fileHandler = new FileHandler();
		fileHandler.setFilename("test.log");
		logger.addHandler(fileHandler);
		
		logger.log("Test", LogLevel.WARN);
	}

}
