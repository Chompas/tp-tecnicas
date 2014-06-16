package ar.fiuba.tecnicas.logging.handlers;

import ar.fiuba.tecnicas.logging.LogMessage;

public interface IHandler {
	
	public void write(LogMessage message);
}
