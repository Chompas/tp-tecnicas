package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.XmlProperties;

public class XmlPropertiesTest {
	private XmlProperties loader;

	@Test
	public void valueReadCorrectlyFromConfigFile() {
		this.loader = new XmlProperties("config.xml");

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
	
	@Test
	public void emptyValueReturnedIfKeyNotFound() {

		this.loader = new XmlProperties(null);

		String actualSeparator = loader.getValue("ThisIsAnInvalidKey");

		String expectedSeparator = "";
		assertEquals(expectedSeparator, actualSeparator);
	}

}
