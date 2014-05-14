package ar.fiuba.tecnicas.logging.handlers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler implements IHandler {

	private String filename;

	@Override
	public void write(String message) {
		try {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
			logFile.println(message);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
