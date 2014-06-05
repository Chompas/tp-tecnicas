package ar.fiuba.tecnicas.logging.formatter;

public class FormatterHelper {
	
	public static String getLogLevelCallingMethod() {
		return Thread.currentThread().getStackTrace()[4].getMethodName();
	}
	
	public static String getCallingMethod() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	public static String getCallingFilename() {
		return Thread.currentThread().getStackTrace()[2].getFileName();
	}
	
	public static String getCallingLineNumber() {
		return Integer.toString(Thread.currentThread().getStackTrace()[2].getLineNumber());
	}

}
