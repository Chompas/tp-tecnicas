package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;
import com.google.gson.*;
import ar.fiuba.tecnicas.logging.config.LogLevel;


public class JsonLogFormatter {
	
	public String format(Date date, String message, LogLevel level) {
		JsonObject js = new JsonObject();
		js.addProperty("datetime", date.toString());
		js.addProperty("level", level.toString());
		js.addProperty("message", message);
		
		String jsonMessage = js.toString();
		
		return jsonMessage;
	}

	public String format(Date date, String message, LogLevel level, String loggerName) {
		JsonObject js = new JsonObject();
		js.addProperty("datetime", date.toString());
		js.addProperty("level", level.toString());
		js.addProperty("loggerName", loggerName);
		js.addProperty("message", message);
		
		String jsonMessage = js.toString();
		return jsonMessage;
	}
}
