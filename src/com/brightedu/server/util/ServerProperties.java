package com.brightedu.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.helpers.FileWatchdog;

public class ServerProperties {

	private static String dataLocation;

	static {
		FileWatchdog fwd = new FileWatchdog(ServerProperties.class.getResource(
				"/server.props").getPath()) {

			@Override
			protected void doOnChange() {
				load();
			}
		};
		fwd.setDelay(5000);
		fwd.setName("Server Properties Watcher");
		fwd.start();
	}

	public static void load() {
		Properties p = new Properties();
		InputStream in = ServerProperties.class
				.getResourceAsStream("/server.props");
		try {
			p.load(in);
		} catch (IOException e) {
			Log.e("failed to load server.props", e);
		}
		dataLocation = p.getProperty("dataLocation");
	}

	public static String getDataLocation() {
		return dataLocation;
	}

}
