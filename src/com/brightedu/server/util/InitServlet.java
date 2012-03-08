package com.brightedu.server.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import com.brightedu.server.GreetingServiceImpl;

public class InitServlet extends HttpServlet {

	Timer scheduler = new Timer();

	public void init() {
		String deployPath = getServletContext().getRealPath("/");
		if (!deployPath.endsWith(String.valueOf(File.separatorChar)))
			deployPath += File.separatorChar;
		ServerProperties.deployPath = deployPath;
		new ConfigurationFileWatcher().start();
		ServerProperties.load();
		AuthManager.load();
		ConnectionManager.load();
		clearTempFiles();
		scheduler.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (GreetingServiceImpl.onlineUsers.intValue() == 0) {
					Log.d("clearing tmp files in "
							+ ServerProperties.tempFileDir);
					clearTempFiles();
				}
			}
		}, nextMidnight(), 24 * 60 * 60 * 1000);
		Log.i("初始化完成");
	}

	private static Date nextMidnight() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR, 4);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date midnight = c.getTime();
		return midnight;
	}

	private void clearTempFiles() {
		File[] tmpFiles = new File(ServerProperties.tempFileDir).listFiles();
		for (File f : tmpFiles) {
			f.delete();
		}
	}

	public void destroy() {
		ConfigurationFileWatcher.clear();
		scheduler.cancel();
	}
}
