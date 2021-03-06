package com.brightedu.server.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LocationInfo;

public class Log {

	private static String FQCN = Log.class.getName();

	private static Map<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();

	static {
		String log4jConfigLocation = System.getProperty("log4j.configuration");

		if (log4jConfigLocation == null) {
			log4jConfigLocation = Log.class.getResource("/log4j.properties")
					.getFile();
		}
		if (log4jConfigLocation == null) {
			log4jConfigLocation = Log.class.getResource("/log4j.xml").getFile();
		}

		PropertyConfigurator.configureAndWatch(log4jConfigLocation, 5000);
	}

	public static boolean isDebugEnabled() {
		 
		return getLogger().isDebugEnabled();
	}

	public static boolean isInfoEnabled() {
		return getLogger().isInfoEnabled();
	}

	public static void d(String msg) {
		 getLogger().log(FQCN, Level.DEBUG, msg, null);
	}

	public static void d(String msg, Throwable e) {
		getLogger().log(FQCN, Level.DEBUG, msg, e);
	}

	public static void i(String msg) {
		getLogger().log(FQCN, Level.INFO, msg, null);
	}

	public static void i(String msg, Throwable e) {
		getLogger().log(FQCN, Level.INFO, msg, e);
	}

	public static void w(String msg) {
		getLogger().log(FQCN, Level.WARN, msg, null);
	}

	public static void w(String msg, Throwable e) {
		getLogger().log(FQCN, Level.WARN, msg, e);
	}

	public static void e(String msg) {
		getLogger().log(FQCN, Level.ERROR, msg, null);
	}

	public static void e(String msg, Throwable e) {
		getLogger().log(FQCN, Level.ERROR, msg, e);
	}

	public static void f(String msg) {
		getLogger().log(FQCN, Level.FATAL, msg, null);
	}

	public static void f(String msg, Throwable e) {
		getLogger().log(FQCN, Level.FATAL, msg, e);
	}
	
	public static Logger getLogger(){
		String fqcn = getCallerFQCN();
		Logger log = loggers.get(fqcn);
		if(log==null){
			log = Logger.getLogger(fqcn);
			loggers.put(fqcn, log);
		}
		return log;
	}

	public static String getCallerFQCN() {
		LocationInfo location = new LocationInfo(new Throwable(), Log.FQCN);
		return location.getClassName();
	}
}