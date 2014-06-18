package ar.fiuba.tecnicas.logging.handlers;

import java.util.ArrayList;

public class HandlerFactory {
	
	public ArrayList<IHandler> createHandlers(String outputs) {
		ArrayList<IHandler> handlers = new ArrayList<>();
		
		String[] outputsSplitted = outputs.split(",");
		
		for (String output : outputsSplitted) {
			switch(output) {
				case("Console"):
					handlers.add(new ConsoleHandler());
					break;
				default:
					String filename = output;
					handlers.add(new FileHandler(filename));
			}
		}
		
		return handlers;
	}
}
