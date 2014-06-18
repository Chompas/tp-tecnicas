package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import ar.fiuba.tecnicas.logging.Logger;
import ar.fiuba.tecnicas.logging.exceptions.CouldNotReadConfigurationException;

public class PropertiesParser {

	public ArrayList<Logger> load(String fileName) throws CouldNotReadConfigurationException {
		Properties properties = new Properties();
	
		try {
			InputStream file = new FileInputStream(fileName);
			properties.load(file);
			file.close();
			
			
			
			return null;
		} catch (IOException e) {
			throw new CouldNotReadConfigurationException();
		}
	}

}
