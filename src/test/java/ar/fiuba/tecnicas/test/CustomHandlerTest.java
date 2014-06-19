package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.LoggerManager;
import ar.fiuba.tecnicas.logging.config.ILogger;
import ar.fiuba.tecnicas.logging.config.LogLevel;

public class CustomHandlerTest {

	private LoggerManager loggerManager;
	private String filename = "customHandler.log";
	private String message = "custom handler message";
	private static Date now = new Date();
	private LogLevel level = LogLevel.DEBUG;
	
	@Test
	public void logInCustomHandlerLoggerFromXmlProperties() {
		try {
			this.deleteJavaProperties();
			
			this.loggerManager = LoggerManager.getInstance();
			this.loggerManager.loadConfiguration();
			ILogger logger1 = this.loggerManager.getLogger("Logger2");
			
			logger1.log(now, this.message, this.level);
			
			this.restoreJavaProperties();
			
			assertShouldWrite();
		
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	private void assertShouldWrite() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileReader(filename));
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String expected = dateFormat.format(now) +" - "+this.level+" - "+message;
		String actual = fileReader.nextLine();

		assertEquals(expected, actual);

		fileReader.close();
		
		File file = new File(filename);
		file.delete();
	}	
	
	private static File javaPropertiesOriginal = new File("config.properties");
	private static File javaPropertiesCopy = new File("config2.properties");
	
	private void deleteJavaProperties() {		
		try {
			Files.copy(javaPropertiesOriginal.toPath(), javaPropertiesCopy.toPath());
			Files.delete(javaPropertiesOriginal.toPath());
		} catch (IOException e) {
			
		}
	}
	
	private void restoreJavaProperties() {
		try {
			Files.copy(javaPropertiesCopy.toPath(), javaPropertiesOriginal.toPath());
			Files.delete(javaPropertiesCopy.toPath());
		} catch (IOException e) {
			
		}
	}

}
