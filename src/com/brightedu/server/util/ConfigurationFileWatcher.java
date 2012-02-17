package com.brightedu.server.util;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurationFileWatcher extends Thread {

	private static Map<String, ConfigurationChangeListener> listenedFiles = new ConcurrentHashMap<String, ConfigurationChangeListener>();
	private static boolean flag = true;

	private static final int inerval = 5000;

	public ConfigurationFileWatcher() {
		setName("ConfigurationFileWatcher");
	}

	public void run() {

		while (flag) {
			Set<String> files = listenedFiles.keySet();
			for (String file : files) {
				ConfigurationChangeListener ccl = listenedFiles.get(file);
				try {
					checkAndConfigure(file, ccl);
				} catch (Exception e) {
					Log.e("checkAndConfigure failed for file: " + file, e);
				}
			}
			try {
				sleep(inerval);
			} catch (InterruptedException e) {
			}
		}
	}

	public static void watchFile(String file, ConfigurationChangeListener run) {
		if (!flag) {
			flag = true;
		}
		File f = new File(file);
		run.setLastModified(f.lastModified());
		listenedFiles.put(file, run);
	}

	public static void clear() {
		flag = false;
		listenedFiles.clear();
	}

	protected void checkAndConfigure(String f, ConfigurationChangeListener ccl) {
		boolean fileExists;
		File file = new File(f);
		try {
			fileExists = file.exists();
		} catch (SecurityException e) {
			Log.w(file.getAbsolutePath()
					+ " does not exist, removed from watching list");
			listenedFiles.remove(f);
			return;
		}

		if (fileExists) {
			long l = file.lastModified();
			if (l > ccl.getLastModified()) {
				ccl.setLastModified(l);
				ccl.configurationChanged();
			}
		}
	}

}
