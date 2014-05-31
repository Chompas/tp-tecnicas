package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.logging.config.JavaProperties;

public class JavaPropertiesTest {
	
	private JavaProperties loader;
	
	@Test
	public void valueReadCorrectlyFromConfigFile() {
		try {
			this.loader = new JavaProperties("config.properties");
			
			String actualSeparator = loader.getValue("Separator");
			
			String expectedSeparator = "-";
			assertEquals(expectedSeparator, actualSeparator);
		} catch (Exception e) {
			fail();
		}			
	}
	
	@Test
	public void emptyValueReturnedIfConfigFileNotFound() {

		try {
			this.loader = new JavaProperties("");
			
			String actualSeparator = loader.getValue("Separator");
			
			String expectedSeparator = "";
			assertEquals(expectedSeparator, actualSeparator);
		} catch (Exception e) {
			fail();
		}
	}

}