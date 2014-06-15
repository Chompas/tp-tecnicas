package ar.fiuba.tecnicas.logging.formatter;

import java.util.Date;
import java.util.HashMap;

public interface ILogMessage {
	
	public String getPlainMessage();
	
	public HashMap<String, String> getHashMessage();
	
	public void addException(Throwable e);

	public String getLineNumber();

	public Date getDate();

}
