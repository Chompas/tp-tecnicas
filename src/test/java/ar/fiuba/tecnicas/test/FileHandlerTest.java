package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import ar.fiuba.tecnicas.logging.handlers.FileHandler;

public class FileHandlerTest {
	
	private static String filename = "outputFile.txt";
	private static String message = "test";
	
	@After
	public void tearDown() {
		File file = new File(filename);
		file.delete();
	}
	
	@Test
	public void shouldWrite() {
		try {
			FileHandler fileHandler = new FileHandler(filename);			
			fileHandler.write(message);

			assertShouldWrite();
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	@Test
	public void shouldThrowExceptionIfFileIsFolder() {
		FileHandler mockedFileHandler = Mockito.mock(FileHandler.class);
		
		try {
			Mockito.doThrow(new RuntimeException()).when(mockedFileHandler).write(message);
			
			mockedFileHandler.write(message);
			
			fail("RuntimeException was not thrown");
		} catch (RuntimeException e) {
		}
			
	}

	private void assertShouldWrite() throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileReader(filename));
		
		String expected = message;
		String actual = fileReader.nextLine();

		assertEquals(expected, actual);

		fileReader.close();
	}	
}
