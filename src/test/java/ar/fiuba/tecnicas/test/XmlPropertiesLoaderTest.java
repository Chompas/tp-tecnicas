package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.XmlProperties;

public class XmlPropertiesLoaderTest {
	
	private XmlProperties loader;
	
	@Test
	public void valueReadCorrectlyFromConfigFile()
			throws FileNotFoundException {
		FileInputStream xmlFile = new FileInputStream("config.xml");
		this.loader = new XmlProperties(xmlFile);
		
		String actualSeparator = loader.getValue("Separator");
		
		String expectedSeparator = "-";
		assertEquals(expectedSeparator, actualSeparator);	
	}
	
	@Test
	public void emptyValueReturnedIfConfigFileNotFound() {

		this.loader = new XmlProperties(null);
		
		String actualSeparator = loader.getValue("Separator");
		
		String expectedSeparator = "";
		assertEquals(expectedSeparator, actualSeparator);		
	}

}
