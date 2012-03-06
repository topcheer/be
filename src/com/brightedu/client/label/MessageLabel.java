package com.brightedu.client.label;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.window.IMWindow;
import com.brightedu.model.edu.Messages;
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
		setStyleName("messagelabel");
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

	public void showNewMessageTip(Messages msg) {
		if (win != null && win.isVisible()) {
			if (msg != null) {
				win.addMessage(msg);
			}
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
