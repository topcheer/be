package com.brightedu.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;

public class UiUtils {

	public static Label getLink(String message, ClickHandler handler) {
		Label link = new Label();
		link = new Label(message);
		link.addStyleName("linkable");
		link.setHeight(20);
		link.setAlign(Alignment.CENTER);
		// Set the width to the length of the text.
		// link.setWidth(message.length() * 6);
		if (handler != null) {
			link.addClickHandler(handler);
		}
		return link;

	}

}
