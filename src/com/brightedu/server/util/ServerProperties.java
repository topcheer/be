package com.brightedu.server.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ServerProperties {

	private static String dataLocation;

	private static int auditLevel = 1;

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
		dataLocation = p.getProperty("dataLocation");
		auditLevel = Integer.parseInt(p.getProperty("auditLevel"));
		Log.i("Data location: " + new File(dataLocation).getAbsolutePath());
		Log.i("Audit Level: " + auditLevel);
	}

	public static String getDataLocation() {
		return dataLocation;
	}

	public static int getAuditLevel() {
		return auditLevel;
	}

}
