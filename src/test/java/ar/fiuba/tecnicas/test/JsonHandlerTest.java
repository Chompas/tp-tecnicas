package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import org.junit.After;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.JsonHandler;

import com.google.gson.stream.JsonReader;

public class JsonHandlerTest {
	
	private static String message = "testtest";
	private static File jsonFile = new File("outputFile.json");
	private static Date now = new Date();
	
	@After
	public void tearDown() {
		try {
			Path path = jsonFile.toPath();
			Files.delete(path);
		} catch (IOException e) {
		}
	}

	@Test
	public void shouldWrite() {
		LogMessage logMessage = new LogMessage(now, "[%p] %n %m", "-", message, LogLevel.INFO);
		JsonHandler jsonHandler = new JsonHandler(jsonFile.getName());
		
		jsonHandler.write(logMessage);
		
		assertShouldWrite();
	}

	private void assertShouldWrite() {
		try {
			JsonReader jsonReader = new JsonReader(new FileReader(jsonFile.getAbsolutePath()));
			jsonReader.beginObject();
			
			while (jsonReader.hasNext()) {
				String name = jsonReader.nextName();
				if (name.equals("message")) {
					String messageFromJson = jsonReader.nextString();
					assertEquals(message, messageFromJson);
				} else {
					jsonReader.skipValue();
				}
			}
							
			jsonReader.endObject();
			jsonReader.close();
		} catch (Exception e) {
			fail("Couldn't read the jSON file");
		}
	}

}
