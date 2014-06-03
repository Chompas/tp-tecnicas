package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.JsonHandler;

import com.google.gson.stream.JsonReader;

public class JsonHandlerTest {
	
	private static String filename = "outputFile.json";
	private static String message = "testtest";
	
	@After
	public void tearDown()
	{
		File file = new File(filename);
		file.delete();
	}
	
	@Test
	public void shouldWrite() {
		try {
			LogMessage logMessage = new LogMessage("[%p] %n %m", "-", message, LogLevel.INFO);
			
			JsonHandler jsonHandler = new JsonHandler(filename);			
			jsonHandler.write(logMessage);
			
			JsonReader jsonReader = new JsonReader(new FileReader(filename));
			jsonReader.beginObject();
			
			while (jsonReader.hasNext()) {
				String name = jsonReader.nextName();
				if (name.equals("message")) {
					String messageFromJson = jsonReader.nextString();
					assertEquals(message, messageFromJson);
				}
				break;
			}
							
			jsonReader.endObject();
			jsonReader.close();			
		} catch (Exception e) {
			fail();
		}
	}

}
