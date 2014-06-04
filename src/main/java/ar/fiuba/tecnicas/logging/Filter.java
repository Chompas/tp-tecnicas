package ar.fiuba.tecnicas.logging;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.formatter.LogMessage;

public class Filter {

	private LogLevel globalLogLevel;

	public Filter(LogLevel globalLogLevel) {
		this.globalLogLevel = globalLogLevel;
	}

	public LogMessage filter(LogMessage logMessage, LogLevel level, String filterRegex) {
		if (shouldShowMessage(level) && shouldShowMessage(logMessage.getPlainMessage(), filterRegex)) {
			return logMessage;
		} else {
			return null;
		}
	}

	private boolean shouldShowMessage(LogLevel level) {
		return level.getCode() <= globalLogLevel.getCode();
	}

	private boolean shouldShowMessage(String message, String filterRegex) {
		boolean result = true;
		if (!filterRegex.equals("")) {
			result = message.matches(filterRegex);
		}
		return result;
	}

}
