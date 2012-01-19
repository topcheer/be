package com.brightedu.server.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

	private static String FQCN = Log.class.getName();
	private static Logger logger = Logger.getLogger(Log.class);

	static {
		String log4jConfigLocation = System.getProperty("log4j.configuration");
		if (log4jConfigLocation == null) {
			log4jConfigLocation = Log.class.getResource(
					"/log4j.properties").getFile();
		}
		PropertyConfigurator.configureAndWatch(log4jConfigLocation, 3000);
	}

	public static void d(String msg) {
		logger.log(FQCN, Level.DEBUG, msg, null);
	}

	public static void d(String msg, Throwable e) {
		logger.log(FQCN, Level.DEBUG, msg, e);
	}
	
	public static void i(String msg) {
		logger.log(FQCN, Level.INFO, msg, null);
	}

	public static void i(String msg, Throwable e) {
		logger.log(FQCN, Level.INFO, msg, e);
	}
	
	public static void w(String msg) {
		logger.log(FQCN, Level.WARN, msg, null);
	}

	public static void w(String msg, Throwable e) {
		logger.log(FQCN, Level.WARN, msg, e);
	}
	
	public static void e(String msg) {
		logger.log(FQCN, Level.ERROR, msg, null);
	}

	public static void e(String msg, Throwable e) {
		logger.log(FQCN, Level.ERROR, msg, e);
	}
	
	public static void f(String msg) {
		logger.log(FQCN, Level.FATAL, msg, null);
	}

	public static void f(String msg, Throwable e) {
		logger.log(FQCN, Level.FATAL, msg, e);
	}

	public static Logger getLogger() {
		return logger;
	}
}