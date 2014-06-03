package ar.fiuba.tecnicas.logging.handlers;

import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public class ConsoleHandler implements IHandler {

	@Override
	public void write(LogMessage message) {
		System.out.println(message.getPlainMessage());
	}

}
