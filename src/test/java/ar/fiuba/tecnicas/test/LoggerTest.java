package ar.fiuba.tecnicas.test;

import java.io.File;

import org.junit.Before;


import ar.fiuba.tecnicas.logging.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LoggerTest {
	
	private Logger logger;
	private String message;
	private String formattedMessage;
	private LogLevel level;
	
	@Before
	public void initialize() {
		this.logger = new Logger(new File("config.xml"));
		this.message = "simple message";
		
		this.level = LogLevel.DEBUG;
		this.formattedMessage = "[" + this.level + "] - " + this.message;
	}
}
