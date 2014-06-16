package ar.fiuba.tecnicas.logging.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.fiuba.tecnicas.logging.config.DefaultProperties;

public class LogFormatter implements ILogFormatter {

	private String format;
	private String separator;
	private String dateRegex = Pattern.quote("%d{") + "(.*?)" + Pattern.quote("}");

	public LogFormatter(String format, String separator) {
		if (format.equals("")) {
			this.format = (new DefaultProperties()).getValue("Format");
		} else {
			this.format = format;
		}
		
		if (separator.equals("")) {
			this.separator = (new DefaultProperties()).getValue("Separator");
		} else {
			this.separator = separator;
		}
	}
	
	@Override
	public String format(LogMessage logMessage) {
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
		DateFormat dateFormat = new SimpleDateFormat(this.getDateFormat(format));
		
		String formattedMessage = format;
		formattedMessage = formattedMessage.replaceAll(this.dateRegex, dateFormat.format(logMessage.getDate()));
		formattedMessage = formattedMessage.replace("%p", logMessage.getLogLevel().toString());		
		formattedMessage = formattedMessage.replace("%m", logMessage.getPlainMessage());
		formattedMessage = formattedMessage.replace("%%", "%");
		formattedMessage = formattedMessage.replace("%n", separator);
		formattedMessage = formattedMessage.replace("%t", logMessage.getThread());
		formattedMessage = formattedMessage.replace("%L", logMessage.getLineNumber());
		formattedMessage = formattedMessage.replace("%F", logMessage.getFileName());
		formattedMessage = formattedMessage.replace("%M", logMessage.getMethod());
		formattedMessage = formattedMessage.replace("%g", logMessage.getLoggerName());
		
		return formattedMessage;
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
