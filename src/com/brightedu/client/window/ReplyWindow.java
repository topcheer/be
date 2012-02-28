package com.brightedu.client.window;

import java.util.ArrayList;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.model.edu.MessageReal;
import com.brightedu.model.edu.Messages;
import com.brightedu.model.edu.User;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ReplyWindow extends Window {

	ReplyWindow _self;
	MessageReal oldMessage;
	HTMLFlow displayPane;
	RichTextEditor x = new RichTextEditor();
	IButton sendBtn = new IButton("发送");
	public ReplyWindow()
	{
		_self = this;

//		this.setTitle("发送给" + oldMessage.getFrom_user_name());
		VLayout l = new VLayout();
		l.setMargin(5);
		l.setPadding(5);
		this.setAutoSize(true);
		
		displayPane = new HTMLFlow();
		
		displayPane.setHeight(50);
		displayPane.setWidth100();
		
		addItem(displayPane);
		
		
		x.setWidth(200);
		x.setHeight(300);
		x.setValue("回复...");
		x.setShowEdges(true);
		x.setEdgeOpacity(50);
		
		l.addMember(displayPane);
		l.addMember(x);
		l.addMember(sendBtn);
		
		
		addItem(l);

		sendBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
			
				Messages newMessage = new Messages();
				newMessage.setFrom_user(oldMessage.getTo_user());
				newMessage.setTo_user(oldMessage.getFrom_user());
				newMessage.setMessage(x.getValue());
				
				ArrayList<Messages> list = new ArrayList<Messages>();
				list.add(newMessage);
				
				BrightEdu.createDataBaseRPC().sendMessage(list, new CommonAsyncCall<Boolean>(){

					@Override
					public void onSuccess(Boolean result) {
						
						_self.hide();
						
					}});
				
			}});

		
	}
	public void setMessage(MessageReal oldMessage)
	{
		this.oldMessage = oldMessage;
		this.setTitle("发送给" + oldMessage.getFrom_user_name());
		if(oldMessage.getMessage() == null || oldMessage.getMessage().trim().equalsIgnoreCase(""))
		{
			displayPane.setVisible(false);
		}
		else
		{
			displayPane.setVisible(true);
			displayPane.setContents("<b>原始信息:</b><br><br>" + oldMessage.getMessage());
		}
		
		x.setValue("");
	}
}
