package ar.fiuba.tecnicas.logging.formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.fiuba.tecnicas.logging.config.LogLevel;

public class LogFormatter implements ILogFormatter {

	private String format;

	public LogFormatter() {
		super();
		// formato por defecto
		this.format = "[%p] - %m";
	}

	public LogFormatter(String format) {
		super();
		this.format = format;
	}

	@Override
	public String format(String message, LogLevel level) {

		// TODO: Modificar mensaje de acuerdo al format
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

		// TODO: agarrar formato del format
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date();
		String date = dateFormat.format(d);

		String threadName = "";
		String separator = "";
		String lineNumber = "";
		String filename = "";
		String methodName = "";

		String formattedMessage = this.format;
		formattedMessage = formattedMessage.replace("%d", date);
		formattedMessage = formattedMessage.replace("%p", level.name());
		formattedMessage = formattedMessage.replace("%t", threadName);
		formattedMessage = formattedMessage.replace("%m", message);
		formattedMessage = formattedMessage.replace("%%", "%");
		formattedMessage = formattedMessage.replace("%n", separator);
		formattedMessage = formattedMessage.replace("%L", lineNumber);
		formattedMessage = formattedMessage.replace("%F", filename);
		formattedMessage = formattedMessage.replace("%M", methodName);
		// return Thread.currentThread().getStackTrace()[3].getLineNumber() +
		// " - " + message + "-" + level;

		return formattedMessage;
	}

}
