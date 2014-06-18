package ar.fiuba.tecnicas.logging.filters;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LogMessage;

public class Filter {

	private LogLevel globalLogLevel;

	public Filter(LogLevel globalLogLevel) {
		this.globalLogLevel = globalLogLevel;
	}

	public String filter(LogMessage logMessage, LogLevel level, String filterRegex, CustomFilter customFilter) {
		if (shouldShowMessage(level) 
				&& shouldShowMessage(logMessage.getPlainMessage(), filterRegex)
				&& shouldShowMessage(logMessage, customFilter)) {
			return logMessage.getPlainMessage();
		} else {
			return "";
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
	
	private boolean shouldShowMessage(LogMessage logMessage, CustomFilter customFilter) {
		boolean result = true;

		if (result && customFilter.fromDate != null && customFilter.fromDate.after(logMessage.getDate())) {
			result = false;
		}

		if (result && customFilter.toDate != null && customFilter.toDate.before(logMessage.getDate())) {
			result = false;
		}

		if (result && customFilter.lineNumber != null && customFilter.lineNumber.equals(logMessage.getLineNumber())) {
			result = false;
		}

		return result;
	}
}
