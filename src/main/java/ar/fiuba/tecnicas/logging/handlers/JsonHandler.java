package ar.fiuba.tecnicas.logging.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.stream.JsonWriter;

public class JsonHandler implements IHandler {
	
	private String filename;
	
	public JsonHandler(String filename) { 
		this.filename = filename;
	}

	@Override
	public void write(String message) {
		try {			
			JsonWriter jsonWriter = new JsonWriter(new FileWriter(this.filename, true));
			jsonWriter.beginObject(); // {
			jsonWriter.name("name").value("mkyong"); // "name" : "mkyong"
			jsonWriter.name("age").value(29); // "age" : 29
		 
			jsonWriter.name("messages"); // "messages" : 
			jsonWriter.beginArray(); // [
			jsonWriter.value("msg 1"); // "msg 1"
			jsonWriter.value("msg 2"); // "msg 2"
			jsonWriter.value("msg 3"); // "msg 3"
			jsonWriter.endArray(); // ]
		 
			jsonWriter.endObject(); // }
			jsonWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
