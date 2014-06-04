package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.After;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class FileHandlerTest {
	
	private static String filename = "outputFile.txt";
	private static String message = "test";
	private LogMessage logMessage = new LogMessage("", "", message, LogLevel.DEBUG);
	
	@After
	public void tearDown()
	{
		File file = new File(filename);
		file.delete();
	}
	
	@Test
	public void shouldWrite() {
		try {
			FileHandler fileHandler = new FileHandler(filename);			
			fileHandler.write(logMessage);
			
			assertShouldWrite();			
		} catch (Exception e) {
			fail();
		}
	}

	private void assertShouldWrite() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileReader(filename));
				
		assertEquals(logMessage.getPlainMessage(), fileReader.nextLine());
		
		fileReader.close();
	}	
}
