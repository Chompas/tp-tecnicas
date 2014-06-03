package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.handlers.JsonHandler;

import com.google.gson.stream.JsonReader;

public class JsonHandlerTest {
	
	private static String filename = "outputFile.json";
	private static String message = "test";
	/*
	@After
	public void tearDown()
	{
		File file = new File(filename);
		file.delete();
	}
	*/
	@Test
	public void shouldWrite() {
		try {
			JsonHandler jsonHandler = new JsonHandler(filename);			
			jsonHandler.write(message);
			
			JsonReader jsonReader = new JsonReader(new FileReader(filename));
			jsonReader.beginObject();
					
			assertEquals(message, jsonReader.nextName());
			
			jsonReader.endObject();
			jsonReader.close();			
		} catch (Exception e) {
			fail();
		}
	}

}
