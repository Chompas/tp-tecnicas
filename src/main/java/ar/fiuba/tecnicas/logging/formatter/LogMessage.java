package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;
import java.util.TreeMap;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogMessage {

	private String plainMessage;
	private String formattedMessage;
	private String threadName;
	private String lineNumber;
	private String filename;
	private String methodName;
	private String loggerName;
	private Date date;
	private LogLevel logLevel;
	private LogFormatter formatter;
	
	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel, String loggerName) {
		init(date, format, separator, message, logLevel);
		this.loggerName = loggerName;
		this.threadName = Thread.currentThread().getName();
		this.lineNumber = FormatterHelper.getCallingLineNumber();
		this.filename = FormatterHelper.getCallingFilename();
		this.methodName = FormatterHelper.getCallingMethod();
	}

	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel) {
		init(date, format, separator, message, logLevel);
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
		return this.formattedMessage.equals(logmessage.getFormattedMessage());
	}

	@Override
	public int hashCode() {
		return this.getClass().toString().hashCode();
	}

	public String getPlainMessage() {
		return this.plainMessage;
	}
	
	public String getFormattedMessage() {
		return this.formattedMessage;
	}
	
	public TreeMap<String, String> getAttributes() {
		TreeMap<String, String> attributes = new TreeMap<>();
		attributes.put("datetime", this.date.toString());
		attributes.put("level", this.logLevel.name());
		if (this.loggerName != "") {
			attributes.put("loggerName", this.loggerName);
		}
		attributes.put("message", this.plainMessage);
		
		return attributes;		
	}

	public void addException(Throwable e) {
		this.plainMessage += " Exception: " + e.getMessage();
		this.formattedMessage = this.formatter.format(this.date, this.plainMessage, this.logLevel);
	}

	private void init(Date date, String format, String separator,
			String message, LogLevel logLevel) {
		this.date = date;
		this.logLevel = logLevel;
		this.loggerName = "";
		this.formatter = new LogFormatter(format, separator);
		this.setupPlainMessage(format, separator, message);
	}

	private void setupPlainMessage(String format, String separator, String message) {
		this.plainMessage = message;
		this.formattedMessage = this.formatter.format(this.date, message, this.logLevel);
	}

	public Date getDate() {
		return this.date;
	}

	public Object getLineNumber() {
		return this.lineNumber;
	}

}
