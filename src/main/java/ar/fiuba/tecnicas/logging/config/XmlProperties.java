package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class XmlProperties implements IProperties {
	
	private Properties properties;
	
	public XmlProperties(FileInputStream xmlFile) {
		this.properties = new Properties();
		try {
			this.properties.loadFromXML(xmlFile);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue(String key) {
		String value;
		try {
			value = properties.getProperty(key);
			if(value == null) {
				value = "";
			} 
		} catch (NullPointerException e) {
			value = "";
		}			
		return value;
	}
}
