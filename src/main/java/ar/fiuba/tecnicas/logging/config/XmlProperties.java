package ar.fiuba.tecnicas.logging.config;

import java.io.FileInputStream;
import java.util.Properties;

public class XmlProperties implements IProperties {

	private Properties properties;

	public XmlProperties(String fileName) {
		this.properties = new Properties();
		try {
			this.properties.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
		}
	}

	@Override
	public String getValue(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			value = "";
		} 			
		return value;
	}
}
