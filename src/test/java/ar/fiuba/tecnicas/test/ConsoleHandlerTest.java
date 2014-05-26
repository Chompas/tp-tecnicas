package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class ConsoleHandlerTest {
	
	private PrintStream originalSysOut;
    private ByteArrayOutputStream mockOut;

    @Before
    public void setSysOut() {
        originalSysOut = System.out;
        mockOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOut));
    }

    @After
    public void restoreSysOut() {
        System.setOut(originalSysOut);
    }

    @Test
    public void outputIsCorrect() {
    	ConsoleHandler consoleHandler = new ConsoleHandler();
    	
    	consoleHandler.write("hello");

        assertEquals("hello" + System.lineSeparator(), mockOut.toString());
    }
}
