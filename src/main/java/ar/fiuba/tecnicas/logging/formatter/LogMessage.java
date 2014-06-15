package ar.fiuba.tecnicas.logging.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogMessage implements ILogMessage {
	
	private String plainMessage;
	private HashMap<String, String> hashMessage = new HashMap<>();
	private String dateRegex = Pattern.quote("%d{") + "(.*?)" + Pattern.quote("}");
	private Date date;
	private String dateString;
	private String threadName;
	private String lineNumber;
	private String filename;
	private String methodName;
	private LogLevel logLevel;
	private String loggerName;
	
	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel, String loggerName) {
		init(date, format, separator, message, logLevel);
		this.loggerName = loggerName;
	}
	
	public LogMessage(Date date, String format, String separator, String message, LogLevel logLevel) {
		init(date, format, separator, message, logLevel);
	}
	
	public boolean equals(Object obj) {
		if (obj.getClass() != LogMessage.class) {
			return false;
		}
		
		LogMessage logmessage = (LogMessage) obj;
		return this.plainMessage.equals(logmessage.getPlainMessage());
	}
	
	@Override
	public int hashCode() {
		return this.getClass().toString().hashCode();
	}
	
	public Date getDate() {
		return date;
	}

	public String getLineNumber() {
		return lineNumber;
	}
	
	public String getPlainMessage() {
		return this.plainMessage;
	}
	
	public HashMap<String, String> getHashMessage() {
		return this.hashMessage;
	}
	
	public void addException(Throwable e) {
		plainMessage += " Exception: " + e.getMessage();
		hashMessage.put("exception", e.getMessage());
	}
	
	private void init(Date date, String format, String separator,
			String message, LogLevel logLevel) {
		DateFormat dateFormat = new SimpleDateFormat(this.getDateFormat(format));
		this.date = date;
		this.dateString = dateFormat.format(date);
				
		this.logLevel = logLevel;
		this.loggerName = "";
		
		this.threadName = Thread.currentThread().getName();
		this.lineNumber = FormatterHelper.getCallingLineNumber();
		this.filename = FormatterHelper.getCallingFilename();
		this.methodName = FormatterHelper.getCallingMethod();
		
		this.setupPlainMessage(format, separator, message);
		this.setupHashMessage(format, separator, message);
	}
	
	private void setupHashMessage(String format, String separator, String message) {		
		hashMessage.put("level", this.logLevel.name());
		hashMessage.put("threadName", this.threadName);
		hashMessage.put("message", message);
		hashMessage.put("separator", separator);
		hashMessage.put("lineNumber", this.lineNumber);
		hashMessage.put("filename", this.filename);
		hashMessage.put("methodName", this.methodName);
		hashMessage.put("loggerName", this.loggerName);
	}
	
	private void setupPlainMessage(String format, String separator, String message) {
		
		/****************
		 * FORMATOS *****************
		 * %d{xxxxxxx} debería aceptar cualquier formato
		 *  válido de SimpleDateFormat. 
		 * %p debería mostrar el Nivel del mensaje. 
		 * %t deberia mostrar el nombre del thread actual. 
		 * %m debería mostrar el contenido del mensaje logueado por el usuario. 
		 * %% debería mostrar un % (es el escape de %). 
		 * %n debería mostrar el “separador
		 * ” con el que el usuario configuró la herramienta
		 *  o un default a elección.  %L line number.  %F filename. 
		 * %M method name. 
		 * %g logger name
		 ******************************************/

		String formattedMessage = format;
		formattedMessage = formattedMessage.replaceAll(this.dateRegex, this.dateString);
		formattedMessage = formattedMessage.replace("%p", this.logLevel.name());
		formattedMessage = formattedMessage.replace("%t", this.threadName);
		formattedMessage = formattedMessage.replace("%m", message);
		formattedMessage = formattedMessage.replace("%%", "%");
		formattedMessage = formattedMessage.replace("%n", separator);
		formattedMessage = formattedMessage.replace("%L", this.lineNumber);
		formattedMessage = formattedMessage.replace("%F", this.filename);
		formattedMessage = formattedMessage.replace("%M", this.methodName);
		formattedMessage = formattedMessage.replace("%g", this.loggerName);

		this.plainMessage = formattedMessage;
	}
	
	private String getDateFormat(String format) {
		Pattern pattern = Pattern.compile(this.dateRegex);
		Matcher matcher = pattern.matcher(format);

		String textInBetween = "";
		while (matcher.find()) {
		  textInBetween = matcher.group(1); 
		}	
		return textInBetween;	
	}
}
