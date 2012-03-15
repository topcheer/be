package com.brightedu.server.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;

import com.brightedu.dao.edu.CurrentBatchMapper;
import com.brightedu.model.edu.CurrentBatch;
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
		initCurrentBatch();
		scheduler.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				initCurrentBatch();
			}
		}, 3600000, 3600000);
		Log.i("初始化完成");
	}

	private void initCurrentBatch() {
		SqlSession session = ConnectionManager.sessionFactory.openSession();
		try {
			CurrentBatchMapper map = session
					.getMapper(CurrentBatchMapper.class);
			List<CurrentBatch> result = map.selectByExample(null);
			if (result == null || result.size() == 0) {
				Log.w("Current patch is not set!");
			} else if (result.size() > 1) {
				Log.w("Multiple current patch is set!");
			} else {
				CurrentBatch cb = result.get(0);
				ServerProperties.currentBatch = cb.getCurrent_batch_id();
				Log.i("Current patch: " + ServerProperties.currentBatch);
			}
		} finally {
			session.close();
		}
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
