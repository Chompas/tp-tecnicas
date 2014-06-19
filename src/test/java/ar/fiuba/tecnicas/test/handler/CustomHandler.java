package ar.fiuba.tecnicas.test.handler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class CustomHandler implements IHandler {

	private String filename;
	
	public CustomHandler(String filename) {
		this.filename = filename;
	}
	
	@Override
	public void write(String message) {
		try {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
			logFile.println(message);
			logFile.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
