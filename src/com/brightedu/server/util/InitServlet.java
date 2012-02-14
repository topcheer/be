package com.brightedu.server.util;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {

	public void init() {
		ServerProperties.load();
		AuthManager.load();
		ConnectionManager.load();
		Log.i("初始化完成");
	}

	public void destroy() {

	}

}
