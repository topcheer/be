package com.brightedu.server.util;

import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet{
	
	public void init(){
		Log.i("初始化完成");
	}

}
