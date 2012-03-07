package com.brightedu.server.util;

import java.io.File;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {

	public void init() {
		String deployPath = getServletContext().getRealPath("/");
		if (!deployPath.endsWith(String.valueOf(File.separatorChar)))
			deployPath += File.separatorChar;
		ServerProperties.dataLocation = deployPath;
		new ConfigurationFileWatcher().start();
		ServerProperties.load();
		AuthManager.load();
		ConnectionManager.load();
		Log.i("USER.DIR=" + System.getProperty("user.dir"));
		Log.i("初始化完成");

	}

	public void destroy() {
		ConfigurationFileWatcher.clear();
	}

}
