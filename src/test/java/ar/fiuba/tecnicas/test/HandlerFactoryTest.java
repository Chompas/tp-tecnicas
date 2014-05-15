package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;
import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class HandlerFactoryTest {
	
	private HandlerFactory factory;
	
	@Before
	public void initialize()
	{
		this.factory = new HandlerFactory();
	}
	
	@Test
	public void testConsoleHandlerIsBuilt()
	{		
		List<IHandler> handlers = this.factory.createHandlers("Console");
		
		assertEquals(1,handlers.size());
		assertEquals(ConsoleHandler.class,handlers.get(0).getClass());		
	}
	
	@Test
	public void testFileHandlerIsBuiltForValidFile()
	{		
		List<IHandler> handlers = this.factory.createHandlers("output.txt");
		
		assertEquals(1,handlers.size());
		assertEquals(FileHandler.class,handlers.get(0).getClass());		
	}

}
