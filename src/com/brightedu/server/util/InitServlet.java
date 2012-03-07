package com.brightedu.server.util;

import javax.servlet.http.HttpServlet;


public class InitServlet extends HttpServlet {

	public void init() {
		new ConfigurationFileWatcher().start();
		ServerProperties.load();
		AuthManager.load();
		ConnectionManager.load();
		Log.i("USER.DIR="+System.getProperty("user.dir"));
		Log.i("初始化完成");
	}

	public void destroy() {
		ConfigurationFileWatcher.clear();
	}

}
