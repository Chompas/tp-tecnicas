package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.LogLevel;
import ar.fiuba.tecnicas.logging.LogMessage;
import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;

public class ConsoleHandlerTest {
	
	private PrintStream originalSysOut;
    private ByteArrayOutputStream mockOut;
    private LogMessage logMessage;
    private Date now;

    @Before
    public void setSysOut() {
        originalSysOut = System.out;
        mockOut = new ByteArrayOutputStream();
        now = new Date();
        logMessage = new LogMessage(now, "", "", "hello", LogLevel.DEBUG);
        System.setOut(new PrintStream(mockOut));
    }

    @After
    public void restoreSysOut() {
        System.setOut(originalSysOut);
    }

    @Test
    public void outputIsCorrect() {
    	ConsoleHandler consoleHandler = new ConsoleHandler();
    	
    	consoleHandler.write(logMessage);

        assertEquals(logMessage.getPlainMessage() + System.lineSeparator(), mockOut.toString());
    }
}
