package ar.fiuba.tecnicas.logging.handlers;

import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public interface IHandler {
	
	public void write(LogMessage message);
}
