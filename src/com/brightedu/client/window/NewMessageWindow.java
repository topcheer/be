package com.brightedu.client.window;


import java.util.ArrayList;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.model.edu.Messages;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewMessageWindow extends Window {

	NewMessageWindow _self;
	RichTextEditor x = new RichTextEditor();
	IButton sendBtn = new IButton("发送");
	Messages newMessage;
	public NewMessageWindow()
	{
		_self = this;

		VLayout l = new VLayout();
		l.setMargin(5);
		l.setPadding(5);
		this.setAutoSize(true);

		x.setWidth(550);
		x.setHeight(200);
		x.setShowEdges(true);
		x.setEdgeOpacity(50);
		l.addMember(x);
		l.addMember(sendBtn);
		
		
		addItem(l);

		sendBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
						
				newMessage.setMessage(x.getValue());
				
				ArrayList<Messages> list = new ArrayList<Messages>();
				list.add(newMessage);
				
				BrightEdu.createDataBaseRPC().sendMessage(list, new CommonAsyncCall<Boolean>(){

					@Override
					public void onSuccess(Boolean result) {
						
						SC.say("发送成功");
						_self.hide();
					}});
				
			}});
		
		
	}
	public void setMessage(Messages mess)
	{
		this.newMessage = mess;
	}
}
