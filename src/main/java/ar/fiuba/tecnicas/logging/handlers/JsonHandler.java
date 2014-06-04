package ar.fiuba.tecnicas.logging.handlers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import ar.fiuba.tecnicas.logging.formatter.LogMessage;

import com.google.gson.stream.JsonWriter;

public class JsonHandler implements IHandler {
	
	private String filename;
	
	public JsonHandler(String filename) { 
		this.filename = filename;
	}

	@Override
	public void write(LogMessage message) {
		try {
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(this.filename, true));
			jsonWriter.beginObject(); 
			
			HashMap<String, String> hashMessage = message.getHashMessage(); 

			for (String key : hashMessage.keySet()) {
				jsonWriter.name(key).value(hashMessage.get(key));
			}
		 
			jsonWriter.endObject();
			jsonWriter.close();
			
		} catch (IOException e) {
		}

	}

}
