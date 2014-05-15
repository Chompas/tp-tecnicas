package ar.fiuba.tecnicas.logging.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogFormatter implements ILogFormatter {

	private String format;
	private String separator;
	
	private String dateRegex = Pattern.quote("%d{") + "(.*?)" + Pattern.quote("}");

	public LogFormatter(String format, String separator) {
		super();
		
		if(format.equals("")) {
			//default format
			this.format = "[%p] %n %m";
		} else {
			this.format = format;
		}
		
		if(separator.equals("")) {
			//default separator
			this.separator = "-";
		} else {
			this.separator = separator;
		}
	}

	@Override
	public String format(String message, LogLevel level) {

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
		 ******************************************/

		DateFormat dateFormat = new SimpleDateFormat(this.getDateFormat());
		Date d = new Date();
		String date = dateFormat.format(d);

		String threadName = Thread.currentThread().getName();
		String separator = this.separator;
		String lineNumber = Integer.toString(Thread.currentThread().getStackTrace()[3].getLineNumber());
		String filename = Thread.currentThread().getStackTrace()[3].getFileName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();

		String formattedMessage = this.format;
		formattedMessage = formattedMessage.replaceAll(this.dateRegex, date);
		formattedMessage = formattedMessage.replace("%p", level.name());
		formattedMessage = formattedMessage.replace("%t", threadName);
		formattedMessage = formattedMessage.replace("%m", message);
		formattedMessage = formattedMessage.replace("%%", "%");
		formattedMessage = formattedMessage.replace("%n", separator);
		formattedMessage = formattedMessage.replace("%L", lineNumber);
		formattedMessage = formattedMessage.replace("%F", filename);
		formattedMessage = formattedMessage.replace("%M", methodName);

		return formattedMessage;
	}
	
	private String getDateFormat() {
		Pattern pattern = Pattern.compile(this.dateRegex);
		Matcher matcher = pattern.matcher(this.format);

		String textInBetween = "";
		while (matcher.find()) {
		  textInBetween = matcher.group(1); 
		}	
		return textInBetween;
	
	}

}
