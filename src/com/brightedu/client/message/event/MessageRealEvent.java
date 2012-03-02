package com.brightedu.client.message.event;

import com.brightedu.model.edu.Messages;

import de.novanic.eventservice.client.event.Event;

public class MessageRealEvent implements Event {

	private static final long serialVersionUID = -3735042378131839820L;

	Messages message;

	public MessageRealEvent() {
	}

	public MessageRealEvent(Messages msg) {
		this.message = msg;
	}

	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}

}
