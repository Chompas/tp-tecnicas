package ar.fiuba.tecnicas.logging.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ar.fiuba.tecnicas.logging.formatter.ILogMessage;

public class FileHandler implements IHandler {

	private String filename;
	
	public FileHandler(String filename) { 
		this.filename = filename;
	}
	
	@Override
	public void write(ILogMessage message) {
		try {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
			logFile.println(message.getPlainMessage());
			logFile.close();
		} catch (IOException e) {
		}
	}
}
