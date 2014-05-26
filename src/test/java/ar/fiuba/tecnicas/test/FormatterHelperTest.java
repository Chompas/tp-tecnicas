package ar.fiuba.tecnicas.test;

import org.junit.Test;

import static org.junit.Assert.*;
import ar.fiuba.tecnicas.logging.formatter.FormatterHelper;

public class FormatterHelperTest {
	
	@Test
	public void testGetCallingMethod() {
		String expected = "testGetCallingMethod";
		String actual = FormatterHelper.getCallingMethod();
		
		assertEquals(expected, actual);		
	}
	
	@Test
	public void getCallingFilename() {
		String expected = "FormatterHelperTest.java";
		String actual = FormatterHelper.getCallingFilename();
		
		assertEquals(expected, actual);		
	}
	
	@Test
	public void getCallingLineNumber() {
		String expected = "29";
		String actual = FormatterHelper.getCallingLineNumber();
		
		assertEquals(expected, actual);		
	}

}
