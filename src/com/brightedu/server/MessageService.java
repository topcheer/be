package com.brightedu.server;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletException;

import com.brightedu.client.MessageServiceRPC;
import com.brightedu.client.message.event.MessageRealEvent;
import com.brightedu.model.edu.Messages;
import com.brightedu.server.util.Log;

import de.novanic.eventservice.client.event.domain.DomainFactory;
import de.novanic.eventservice.service.RemoteEventServiceServlet;

public class MessageService extends RemoteEventServiceServlet implements
		MessageServiceRPC {

	private static final long serialVersionUID = -8772904178666059460L;

	boolean flag = true;

	private static LinkedBlockingQueue<Messages> cachedMsgReal = new LinkedBlockingQueue<Messages>();

	public void startMessageSession() {
		// Do nothing, just init the session
		System.out.println(getThreadLocalRequest().getSession().getAttribute("user"));
	}

	public void init() {

		try {
			super.init();
		} catch (ServletException e) {
			Log.e("", e);
		}
		new MessageDetector().start();
	}

	public void destroy() {
		super.destroy();
		flag = false;
		cachedMsgReal.add(new Messages());
	}

	public void enqueue(Messages msg) {
		cachedMsgReal.add(msg);
	}

	public static void enqueue(List<Messages> msgs) {
		cachedMsgReal.addAll(msgs);
	}

	private class MessageDetector extends Thread {
		public MessageDetector() {
			setName("MessageDetector");
		}

		public void run() {
			while (flag) {
				try {
					Messages msg = cachedMsgReal.take();
					if (msg.getTo_user() != null) {
						addEvent(
								DomainFactory.getDomain("Message_User_"
										+ msg.getTo_user()),
								new MessageRealEvent(msg));
					}
				} catch (InterruptedException e) {
					Log.e("", e);
				}
			}
		}
	}

}
