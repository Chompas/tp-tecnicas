package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class XmlProperties implements IProperties {
	
	private Properties properties;
	
	public XmlProperties(FileInputStream xmlFile)
	{
		this.properties = new Properties();
		try {
			this.properties.loadFromXML(xmlFile);
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getValue(String key) {
		try {
			return properties.getProperty(key);
		} catch (NullPointerException e) {
			return "";
		}			
	}
}
