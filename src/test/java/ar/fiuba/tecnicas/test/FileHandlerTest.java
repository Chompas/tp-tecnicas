package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.After;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class FileHandlerTest {
	
	private static String filename = "outputFile.txt";
	private static String message = "test";
	
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
			fileHandler.write(message);
			
			Scanner fileReader = new Scanner(new FileReader(filename));
					
			assertEquals(message, fileReader.nextLine());
			
			fileReader.close();			
		} catch (Exception e) {
			fail();
		}
	}	
}
