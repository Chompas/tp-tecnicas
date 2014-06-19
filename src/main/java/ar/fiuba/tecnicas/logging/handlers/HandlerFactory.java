package ar.fiuba.tecnicas.logging.handlers;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {
	
	public ArrayList<IHandler> createHandlers(String outputs) {
		ArrayList<IHandler> handlers = new ArrayList<>();
		
		String[] outputsSplitted = outputs.split(",");
		
		for (String output : outputsSplitted) {
			switch(output.toLowerCase()) {
				case("console"):
					handlers.add(new ConsoleHandler());
					break;
				default:
					String filename = output;
					handlers.add(new FileHandler(filename));
			}
		}
		
		return handlers;
	}
	
	public ArrayList<IHandler> createCustomHandlers(HashMap<String,String> customOutputs) {
		ArrayList<IHandler> handlers = new ArrayList<>();
		
		for (Map.Entry<String,String> output : customOutputs.entrySet()) {
		
		    Object[] customOutputParameters = output.getValue().split(",");
		
			Class<?> clazz = null;
			try {
				clazz = Class.forName(output.getKey());
				Constructor<?> constructor = null;
				constructor = clazz.getConstructor(String.class);
				IHandler customHandler  = (IHandler)constructor.newInstance(customOutputParameters);
				
				handlers.add(customHandler);
			} catch (Exception e) {
				return handlers;
			}
		}
		return handlers;
	}
}
