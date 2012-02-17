package com.brightedu.server.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerProperties {

	private static String dataLocation;

	private static int auditLevel = 1;

	private static String localEncoding = "UTF-8";
	private static String servletEncoding = "ISO8859-1";

	static {
		ConfigurationFileWatcher.watchFile(
				ServerProperties.class.getResource("/server.props").getPath(),
				new ConfigurationChangeListener() {

					@Override
					public void configurationChanged() {
						Log.i("Reload ServerProperties");
						load();
					}
				});
	}

	public static void load() {
		Properties p = new Properties();
		InputStream in = ServerProperties.class
				.getResourceAsStream("/server.props");
		try {
			p.load(in);
			in.close();
		} catch (IOException e) {
			Log.e("failed to load server.props", e);
		}
		dataLocation = p.getProperty("dataLocation","data");
		auditLevel = Integer.parseInt(p.getProperty("auditLevel","4"));
		localEncoding = p.getProperty("localEncoding","GBK");
		servletEncoding = p.getProperty("servletEncoding","ISO8859-1");
		Log.i("Data location: " + new File(dataLocation).getAbsolutePath());
		Log.i("Audit Level: " + auditLevel);
		Log.i("Local Encoding: " + localEncoding);
		Log.i("Servlet Encoding: " + servletEncoding);
	}

	public static String getDataLocation() {
		return dataLocation;
	}

	public static int getAuditLevel() {
		return auditLevel;
	}
	
	public static String getLocalEncoding(){
		return localEncoding;
	}
	
	public static String getServletEncoding(){
		return servletEncoding;
	}

}
