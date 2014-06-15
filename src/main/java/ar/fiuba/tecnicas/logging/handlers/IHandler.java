package ar.fiuba.tecnicas.logging.handlers;

import ar.fiuba.tecnicas.logging.formatter.ILogMessage;

public interface IHandler {
	
	public void write(ILogMessage message);
}
