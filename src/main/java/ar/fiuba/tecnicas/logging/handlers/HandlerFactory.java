package ar.fiuba.tecnicas.logging.handlers;

import java.util.ArrayList;
import java.util.List;

public class HandlerFactory {
	
	public List<IHandler> createHandlers(String outputs) {
		List <IHandler> handlers = new ArrayList<>();
		
		String[] outputsSplitted = outputs.split(",");
		
		for (String output : outputsSplitted) {
			if (output.equals("Console")) {
				handlers.add(new ConsoleHandler());
			}
			else {
				String filename = output;
				handlers.add(new FileHandler(filename));
			}
		}
		
		return handlers;		
	}
}
