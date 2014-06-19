package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.fiuba.tecnicas.logging.handlers.ConsoleHandler;
import ar.fiuba.tecnicas.logging.handlers.FileHandler;
import ar.fiuba.tecnicas.logging.handlers.HandlerFactory;
import ar.fiuba.tecnicas.logging.handlers.IHandler;

public class HandlerFactoryTest {
	
	private HandlerFactory factory;
	private HashMap<String,String> customOutputs;
	
	@Before
	public void initialize() {
		this.factory = new HandlerFactory();
		this.customOutputs = new HashMap<>();
		this.customOutputs.put("ar.fiuba.tecnicas.test.handler.CustomHandler", "customHandler.log");
	}
	
	@Test
	public void consoleHandlerIsBuilt() {		
		List<IHandler> handlers = this.factory.createHandlers("Console");
		
		assertEquals(1,handlers.size());
		assertEquals(ConsoleHandler.class,handlers.get(0).getClass());
	}
	
	@Test
	public void fileHandlerIsBuiltForValidFile() {		
		List<IHandler> handlers = this.factory.createHandlers("output.txt");
		
		assertEquals(1,handlers.size());
		assertEquals(FileHandler.class,handlers.get(0).getClass());
	}

	@Test
	public void customHandlerIsBuiltForValidFile() {		
		List<IHandler> handlers = null;
		try {
			handlers = this.factory.createCustomHandlers(customOutputs);
		} catch (Exception e) {
			fail();
		}
		
		assertEquals(1,handlers.size());
		assertEquals(ar.fiuba.tecnicas.test.handler.CustomHandler.class,handlers.get(0).getClass());
	}
	
}
