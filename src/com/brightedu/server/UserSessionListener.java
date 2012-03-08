package com.brightedu.server;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.brightedu.model.edu.User;

public class UserSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent paramHttpSessionEvent) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent paramHttpSessionEvent) {
		HttpSession session = paramHttpSessionEvent.getSession();
		User user =(User) session.getAttribute("user");
		if(user!=null){ // == null 表示已经认为logout
			GreetingServiceImpl.onlineUsers.decrementAndGet();
		}
	}

}
