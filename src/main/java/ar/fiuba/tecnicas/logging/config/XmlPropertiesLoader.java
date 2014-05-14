package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class XmlPropertiesLoader implements IPropertiesLoader {
	
	private FileInputStream xmlFile;
	
	public XmlPropertiesLoader(FileInputStream xmlFile)
	{
		this.xmlFile = xmlFile;
	}

	@Override
	public String getValue(String key) {
		Properties properties = new Properties();
		
		try {
			properties.loadFromXML(xmlFile);
			return properties.getProperty(key);
		} catch (NullPointerException | IOException e) {
			return "";
		}		
	}
}
