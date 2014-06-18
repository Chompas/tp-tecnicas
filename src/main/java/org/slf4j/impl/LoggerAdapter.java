package org.slf4j.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.Marker;

import ar.fiuba.tecnicas.logging.config.LogLevel;
import ar.fiuba.tecnicas.logging.config.LoggerDefault;
import ar.fiuba.tecnicas.logging.formatter.FormatterHelper;

public class LoggerAdapter implements Logger {
	
	private LoggerDefault logger;
	private String name;
	
	public LoggerAdapter() {
		
		logger = new LoggerDefault();
	}
	
	public LoggerAdapter(String name) {
		this.name = name;
	}
	
	private LogLevel generateLogLevel() {
		String callingMethod = FormatterHelper.getLogLevelCallingMethod();
		switch (callingMethod) {
			case "debug":
				return LogLevel.DEBUG;
			case "trace":
				return LogLevel.TRACE;
			case "error":
				return LogLevel.ERROR;
			case "info":
				return LogLevel.INFO;
			case "warn":
			    return LogLevel.WARN;
			default:
				return LogLevel.DEBUG;
		}
	}
	
	private void log(String msg) {
		logger.log(new Date(), msg, this.generateLogLevel());
	}
	
	private void log(String msg, Throwable e) {
		logger.log(new Date(), msg, this.generateLogLevel(), e);
	}

	@Override
	public void debug(String arg0) {
		this.log(arg0);
	}

	@Override
	public void debug(String arg0, Object arg1) {
		this.log(arg0);
	}

	@Override
	public void debug(String arg0, Object... arg1) {
		this.log(arg0);	
	}

	@Override
	public void debug(String arg0, Throwable arg1) {
		this.log(arg0,arg1);
		
	}

	@Override
	public void debug(Marker arg0, String arg1) {
		this.log(arg1);
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) {
		this.log(arg0);
		
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) {
		this.log(arg1);		
	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2) {
		this.log(arg1);
		
	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		this.log(arg1,arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		this.log(arg1);
	}

	@Override
	public void error(String arg0) {
		this.log(arg0);
	}

	@Override
	public void error(String arg0, Object arg1) {
		this.log(arg0);
	}

	@Override
	public void error(String arg0, Object... arg1) {
		this.log(arg0);	
	}

	@Override
	public void error(String arg0, Throwable arg1) {
		this.log(arg0,arg1);
	}

	@Override
	public void error(Marker arg0, String arg1) {
		this.log(arg1);
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) {
		this.log(arg0);	
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2) {
		this.log(arg1);
	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) {
		this.log(arg1);	
	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		this.log(arg1,arg2);	
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		this.log(arg1);	
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void info(String arg0) {
		this.log(arg0);
	}

	@Override
	public void info(String arg0, Object arg1) {
		this.log(arg0);	
	}

	@Override
	public void info(String arg0, Object... arg1) {
		this.log(arg0);
	}

	@Override
	public void info(String arg0, Throwable arg1) {
		this.log(arg0,arg1);
	}

	@Override
	public void info(Marker arg0, String arg1) {
		this.log(arg1);
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) {
		this.log(arg0);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) {
		this.log(arg1);		
	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) {
		this.log(arg1);		
	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		this.log(arg1,arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		this.log(arg1);		
	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		return true;
	}

	@Override
	public boolean isErrorEnabled() {
		return true;
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		return true;
	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		return true;
	}

	@Override
	public boolean isWarnEnabled() {
		return true;
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		return true;
	}

	@Override
	public void trace(String arg0) {
		this.log(arg0);
	}

	@Override
	public void trace(String arg0, Object arg1) {
		this.log(arg0);	
	}

	@Override
	public void trace(String arg0, Object... arg1) {
		this.log(arg0);
	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		this.log(arg0,arg1);
	}

	@Override
	public void trace(Marker arg0, String arg1) {
		this.log(arg1);
	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2) {
		this.log(arg0);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {
		this.log(arg1);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2) {
		this.log(arg1);
	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		this.log(arg1,arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		this.log(arg1);
	}

	@Override
	public void warn(String arg0) {
		this.log(arg0);
	}

	@Override
	public void warn(String arg0, Object arg1) {
		this.log(arg0);
	}

	@Override
	public void warn(String arg0, Object... arg1) {
		this.log(arg0);
	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		this.log(arg0,arg1);
	}

	@Override
	public void warn(Marker arg0, String arg1) {
		this.log(arg1);
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		this.log(arg0);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		this.log(arg1);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		this.log(arg1);
	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		this.log(arg1,arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		this.log(arg1);
	}

}
