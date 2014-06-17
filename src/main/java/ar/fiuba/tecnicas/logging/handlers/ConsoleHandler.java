package ar.fiuba.tecnicas.logging.handlers;

public class ConsoleHandler implements IHandler {

	@Override
	public void write(String message) {
		System.out.println(message);
	}

}
