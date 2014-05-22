package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaProperties implements IProperties {
	
	private Properties properties = new Properties();
	private InputStream file = null;
	
	public JavaProperties(String fileName) {
		try {
			file = new FileInputStream(fileName);
			properties.load(file);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue(String key) {
		String value;
		value = properties.getProperty(key);
		if (value == null) {
			value = "";
		}		
		return value;
	}
}
