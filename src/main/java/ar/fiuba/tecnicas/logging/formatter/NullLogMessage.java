package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;
import java.util.HashMap;

public class NullLogMessage implements ILogMessage {
	
	private static String NO_LINE_NUMBER = "-1";
	private static String EMPTY_MESSAGE = "";
	private Date date;
	
	public NullLogMessage(Date date) {
		this.date = date;
	}
	
	public void addException(Throwable e) {
		return;
	}
	
	public String getPlainMessage() {
		return EMPTY_MESSAGE;
	}
	
	public HashMap<String, String> getHashMessage() {
		return new HashMap<>();
	}

	public String getLineNumber() {
		return NO_LINE_NUMBER;
	}

	public Date getDate() {
		return this.date;
	}
	
}
