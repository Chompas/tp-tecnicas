package ar.fiuba.tecnicas.logging.formatter;

import java.util.Set;

import ar.fiuba.tecnicas.logging.config.LogMessage;

import com.google.gson.*;

public class JsonLogFormatter implements ILogFormatter {
	
	public String format(LogMessage logMessage) {
		JsonObject js = new JsonObject();

		Set<String> keys = logMessage.getAttributes().keySet();
	    for (String key : keys) {
	    	js.addProperty(key, logMessage.getAttributes().get(key));
	    }
		
		String jsonMessage = js.toString();
		
		return jsonMessage;
	}

}
