package ar.fiuba.tecnicas.logging;

abstract class LoggerDecorator implements ILogger {
	
	protected ILogger logger;
	
	public LoggerDecorator (ILogger loggerToBeDecorated) {
		this.logger = loggerToBeDecorated;
	}

}
