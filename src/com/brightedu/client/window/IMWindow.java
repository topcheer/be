package com.brightedu.client.window;


import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.canvas.BrightCanvas;
import com.brightedu.client.label.MessageLabel;
import com.brightedu.model.edu.MessageReal;
import com.brightedu.model.edu.Messages;
import com.brightedu.model.edu.User;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class IMWindow extends Window {

	IMWindow _self;
	ReplyWindow cw;
	NewMessageWindow nw;
	ListGrid messages = new ListGrid();
	public IMWindow(final User user)
	{
		_self = this;
		this.setTitle("未读消息列表");
		//this.setAutoCenter(true);
		this.setTop(200);
		this.setLeft(200);
		
		this.setIsModal(true);
		this.setAutoSize(true);
		this.setShowModalMask(true);
		 
		ListGridField from_userField = new ListGridField("from_user","来自");
		ListGridField receiveTstpField = new ListGridField("receiveTstp","时间");
		ListGridField detailsField = new ListGridField("detailsField","内容");
		ListGridField objectField = new ListGridField("obj","完整对象");
		
		objectField.setHidden(true);
		from_userField.setWidth(80);
		receiveTstpField.setWidth(100);
		messages.setWrapCells(true);
		messages.setCellHeight(56);
		messages.setLongTextEditorThreshold(100);
		messages.setWidth(300);
		messages.setHeight(300);
		messages.setShowHeaderContextMenu(false);
		messages.setEmptyMessage("你没有未读短信");
		messages.setFields(from_userField,receiveTstpField,detailsField,objectField);
		messages.setSelectionType(SelectionStyle.SINGLE);
		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(25);
		buttonLayout.setPadding(5);
		IButton newBtn = new IButton("新短消息");
		newBtn.setHeight(25);
		DynamicForm form = new DynamicForm();
		form.setHeight(25);
		final SelectItem userItem = new SelectItem("users","选择用户");
		userItem.setShowTitle(false);
		form.setItems(userItem);
		final LinkedHashMap<String,String> users = new LinkedHashMap<String,String>();
		BrightEdu.createDataBaseRPC().getUserList(-1, -1, false, new CommonAsyncCall<List<User>>(){
			
			@Override
			public void onSuccess(List<User> result) {
				for(User user: result)
				{
					users.put(user.getUser_id()+"", user.getUser_name());
				}
				userItem.setValueMap(users);
			}});
	
		buttonLayout.addMember(newBtn);
		buttonLayout.addMember(form);
		
		addItem(messages);
		addItem(buttonLayout);
		
		userItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				if(userItem.getSelectedRecord() == null)
				{
					SC.say("请选择要发送的对象");
					return;
				}
//				final MessageReal mr = new MessageReal();
//				mr.setFrom_user(new Integer(userItem.getValueAsString()));
//				mr.setFrom_user_name(users.get(userItem.getValue()));
//				mr.setTo_user(user.getUser_id());
//				mr.setTo_user_name(user.getUser_name());
				if(cw!=null)cw.hide();
				if(nw == null ) nw = new NewMessageWindow();
				
				Messages mess = new Messages();
				mess.setFrom_user(user.getUser_id());
				mess.setTo_user(new Integer(userItem.getValueAsString()));
				
				nw.setMessage(mess);
				
				nw.setTitle("发送给" + users.get(userItem.getValue()));
				nw.setTop(_self.getTop());
				nw.setLeft(_self.getLeft() + _self.getWidth());
				nw.x.setValue("");
				nw.show();	
				
			}});
		
		
		newBtn.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				if(userItem.getSelectedRecord() == null)
				{
					SC.say("请选择要发送的对象");
					return;
				}
//				final MessageReal mr = new MessageReal();
//				mr.setFrom_user(new Integer(userItem.getValueAsString()));
//				mr.setFrom_user_name(users.get(userItem.getValue()));
//				mr.setTo_user(user.getUser_id());
//				mr.setTo_user_name(user.getUser_name());
				if(cw!=null)cw.hide();
				if(nw == null ) nw = new NewMessageWindow();
				
				Messages mess = new Messages();
				mess.setFrom_user(user.getUser_id());
				mess.setTo_user(new Integer(userItem.getValueAsString()));
				
				nw.setMessage(mess);
				
				nw.setTitle("发送给" + users.get(userItem.getValue()));
				nw.setTop(_self.getTop());
				nw.setLeft(_self.getLeft() + _self.getWidth());
				nw.x.setValue("");
				nw.show();				
				
			}});
		
		
		BrightEdu.createDataBaseRPC().readMessage(user, new CommonAsyncCall<List<MessageReal>>(){

			@SuppressWarnings("deprecation")
			@Override
			public void onSuccess(List<MessageReal> result) {
				RecordList list = new RecordList();
				for(MessageReal message : result)
				{
					ListGridRecord record = new ListGridRecord();
					record.setAttribute("from_user", message.getFrom_user_name());
					record.setAttribute("receiveTstp", message.getReceive_tstp().toLocaleString());
					record.setAttribute("detailsField", message.getMessage());
					record.setAttribute("obj", message);
					
					list.add(record);
				}
				messages.setData(list);
			}});
		
		messages.setAnimateRemoveRecord(true);
		messages.setAnimateRemoveSpeed(800);
		messages.addRecordClickHandler(new RecordClickHandler(){
			
			@Override
			public void onRecordClick(RecordClickEvent event) {
				
				final MessageReal message = (MessageReal)event.getRecord().getAttributeAsObject("obj");
				if(cw == null ) cw = new ReplyWindow();
				

				
				cw.setMessage(message);
				cw.setTop(_self.getTop());
				cw.setLeft(_self.getLeft() + _self.getWidth());
				cw.show();
				
			}}
		);
		messages.setTitle("双击记录标记已读并且从列表中移去");
		messages.addRecordDoubleClickHandler(new RecordDoubleClickHandler(){

			@Override
			public void onRecordDoubleClick(final RecordDoubleClickEvent event) {
				
				cw.hide();
				
				BrightEdu.createDataBaseRPC().markAsRead((MessageReal)event.getRecord().getAttributeAsObject("obj"), new CommonAsyncCall<Boolean>(){

					@Override
					public void onSuccess(Boolean result) {
						messages.removeData(event.getRecord());
						if(messages.getRecords() == null || messages.getRecords().length==0){
							MessageLabel ca = (MessageLabel) MessageLabel.getById(MessageLabel.ID);
							ca.dismissNewMessageTip();
						}
					}});
			}});
		
		this.addCloseClickHandler(new CloseClickHandler(){

			@Override
			public void onCloseClick(CloseClickEvent event) {
				_self.hide();
				if(cw!=null)cw.hide();
				if(nw!=null)nw.hide();
				
			}});
		
		
	}
}
