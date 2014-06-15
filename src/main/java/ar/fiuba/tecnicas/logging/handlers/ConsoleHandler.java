package ar.fiuba.tecnicas.logging.handlers;

import ar.fiuba.tecnicas.logging.formatter.ILogMessage;

public class ConsoleHandler implements IHandler {

	@Override
	public void write(ILogMessage message) {
		System.out.println(message.getPlainMessage());
	}

}
