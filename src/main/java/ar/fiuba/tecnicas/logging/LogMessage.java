package ar.fiuba.tecnicas.logging;

import java.util.Date;
import java.util.TreeMap;

import ar.fiuba.tecnicas.logging.formatter.FormatterHelper;
import ar.fiuba.tecnicas.logging.formatter.LogFormatter;

public class LogMessage {

	private String message;
	private String threadName;
	private String lineNumber;
	private String filename;
	private String methodName;
	private String loggerName;
	private Date date;
	private LogLevel logLevel;
	private LogFormatter formatter;
	
	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel) {
		this.threadName = Thread.currentThread().getName();
		this.lineNumber = FormatterHelper.getCallingLineNumber();
		this.filename = FormatterHelper.getCallingFilename();
		this.methodName = FormatterHelper.getCallingMethod();
		this.date = date;
		this.logLevel = logLevel;
		this.loggerName = "";
		this.formatter = new LogFormatter(format, separator);
		this.message = message;
	}
	
	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel, String loggerName) {
		this(date, format, separator, message, logLevel);
		this.loggerName = loggerName;
		
		// Override last values
		this.threadName = Thread.currentThread().getName();
		this.lineNumber = FormatterHelper.getCallingLineNumber();
		this.filename = FormatterHelper.getCallingFilename();
		this.methodName = FormatterHelper.getCallingMethod();		
	}

	public boolean equals(Object obj) {
		if (obj.getClass() != LogMessage.class) {
			return false;
		}

		LogMessage logmessage = (LogMessage) obj;
		return getFormattedMessage().equals(logmessage.getFormattedMessage());
	}

	@Override
	public int hashCode() {
		return this.getClass().toString().hashCode();
	}
	
	public TreeMap<String, String> getAttributes() {
		TreeMap<String, String> attributes = new TreeMap<>();
		attributes.put("datetime", this.date.toString());
		attributes.put("level", this.logLevel.name());
		if (this.loggerName != "") {
			attributes.put("loggerName", this.loggerName);
		}
		attributes.put("message", this.message);
		
		return attributes;		
	}

	public void addException(Throwable e) {
		this.message += " Exception: " + e.getMessage();
	}
	
	public String getPlainMessage() {
		return this.message;
	}
	
	public Date getDate() {
		return this.date;
	}

	public String getLineNumber() {
		return this.lineNumber;
	}

	public LogLevel getLogLevel() {
		return this.logLevel;
	}

	public String getFileName() {
		return this.filename;
	}

	public String getMethod() {
		return this.methodName;
	}

	public String getLoggerName() {
		return this.loggerName;
	}

	public String getThread() {
		return this.threadName;
	}
	
	public String getFormattedMessage() {
		return this.formatter.format(this);
	}

}
