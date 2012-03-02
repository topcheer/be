package com.brightedu.client.message.event;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.listener.RemoteEventListener;

public abstract class MessageEventListener implements RemoteEventListener {

	public abstract void onMessage(MessageRealEvent event);

	@Override
	public void apply(Event anEvent) {
		onMessage((MessageRealEvent) anEvent);
	}

}
