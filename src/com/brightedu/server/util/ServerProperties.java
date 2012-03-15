package com.brightedu.server.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerProperties {

	public static String dataLocation;

	public static String dataConfig;

	public static String agreementSubDir;
	public static String studentPicDir;
	public static String tempFileRelative = "/" + "tmp" + "/";
	public static String tempFileDir;

	private static int auditLevel = 1;

	public static String deployPath;
	
	public static int currentBatch = 0;

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
		dataConfig = p.getProperty("dataLocation", "data");
		dataLocation = deployPath + dataConfig;
		agreementSubDir = new File(ServerProperties.getDataLocation())
				.getAbsolutePath() + "/" + "agreement" + "/";
		Log.i("agreementSubDir: " + agreementSubDir);
		File agreementsDir = new File(agreementSubDir);
		if (!agreementsDir.exists()) {
			agreementsDir.mkdirs();
		}
		studentPicDir = new File(ServerProperties.getDataLocation())
				.getAbsolutePath() + "/student_pics/";
		Log.i("studentPicDir: " + studentPicDir);
		File sdutentPicFile = new File(studentPicDir);
		if (!sdutentPicFile.exists()) {
			sdutentPicFile.mkdirs();
		}
		tempFileDir = new File(ServerProperties.dataLocation + "/"
				+ tempFileRelative).getAbsolutePath()
				+ "/";
		File tempFile = new File(tempFileDir);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		Log.i("tempFileDir: " + tempFileDir);
		auditLevel = Integer.parseInt(p.getProperty("auditLevel", "4"));
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
