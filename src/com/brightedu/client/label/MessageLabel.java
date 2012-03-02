package com.brightedu.client.label;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.Label;

public class MessageLabel extends Label {
	MessTimer mt = new MessTimer();
	public static final String ID = "newmessagelabel_id";

	public MessageLabel() {
		setID(ID);
		setContents("没有新短消息");
	}

	public void showNewMessageTip() {
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
