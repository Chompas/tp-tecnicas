package ar.fiuba.tecnicas.logging.handlers;

import java.io.BufferedWriter;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ar.fiuba.tecnicas.logging.exceptions.InvalidFileNameException;

public class FileHandler implements IHandler {

	private String filename;
	
	public FileHandler(String filename)
	{
		this.filename = filename;
	}
	
	@Override
	public void write(String message) {
		try {
			PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filename, true)));
			logFile.println(message);
			logFile.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
