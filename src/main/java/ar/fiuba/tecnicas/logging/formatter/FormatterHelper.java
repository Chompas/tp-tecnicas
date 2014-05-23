package ar.fiuba.tecnicas.logging.formatter;

public class FormatterHelper {
	
	public static String getCallingMethod() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
	
	public static String getCallingFilename() {
		return Thread.currentThread().getStackTrace()[3].getFileName();
	}
	
	public static String getCallingLineNumber() {
		return Integer.toString(Thread.currentThread().getStackTrace()[3].getLineNumber());
	}

}
