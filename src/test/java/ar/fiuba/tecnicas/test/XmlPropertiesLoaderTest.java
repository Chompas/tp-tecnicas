package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.XmlPropertiesLoader;

public class XmlPropertiesLoaderTest {
	
	private XmlPropertiesLoader loader;
	
	@Test
	public void testValueReadCorrectlyFromConfigFile() throws FileNotFoundException
	{
		// arrange
		FileInputStream xmlFile = new FileInputStream("config.xml");
		this.loader = new XmlPropertiesLoader(xmlFile);
		
		// act
		String actualSeparator = loader.getValue("Separator");
		
		// assert
		String expectedSeparator = "-";
		assertEquals(expectedSeparator, actualSeparator);	
	}
	
	@Test
	public void testEmptyValueReturnedIfConfigFileNotFound()
	{
		// arrange
		this.loader = new XmlPropertiesLoader(null);
		
		// act
		String actualSeparator = loader.getValue("Separator");
		
		// assert
		String expectedSeparator = "";
		assertEquals(expectedSeparator, actualSeparator);		
	}

}
