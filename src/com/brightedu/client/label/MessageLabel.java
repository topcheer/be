package com.brightedu.client.label;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.window.IMWindow;
import com.google.gwt.user.client.Timer;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class MessageLabel extends Label {
	MessTimer mt = new MessTimer();
	public static final String ID = "newmessagelabel_id";
	IMWindow win;

	public MessageLabel() {
		setID(ID);
		setContents("没有新短消息");

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (win == null) {
					win = new IMWindow(BrightEdu.getUser());
				}
				win.show();
			}
		});
	}

	public void showNewMessageTip() {
		if (win != null && win.isVisible()) {
			// 其实这个不怎么完美，由于Messages和MessageReal对象有用户名的差别，push
			// event传递过来的只是Message，只能重刷一次，以后再优化
			win.refreshMessagelist();
			win.redraw();
		}
		if (getOpacity() != 100) {
			setOpacity(100);
			setContents("<b><font color=red>你有新短消息</font></b>");
			mt.scheduleRepeating(1000);
		}
	}

	public void dismissNewMessageTip() {
		setOpacity(50);
		setContents("没有新短消息");
		setVisible(true);
		mt.cancel();
	}

	private class MessTimer extends Timer {

		@Override
		public void run() {

			if (isVisible()) {
				animateHide(AnimationEffect.FADE);
			} else {
				animateShow(AnimationEffect.FADE);
			}
			
		}

	}

}
