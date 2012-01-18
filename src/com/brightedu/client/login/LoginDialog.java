package com.brightedu.client.login;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class LoginDialog extends Window {

	TextItem textItem = new TextItem("用户");

	Label infolable = new Label();
	PasswordItem passItem = new PasswordItem("密码");
	IButton okBtn = new IButton("登录");
	IButton cancelBtn = new IButton("取消");

	public LoginDialog() {
		// setWidth(250);
		// setHeight(150);
		setTitle("登录");
		setShowMinimizeButton(false);
		setIsModal(true);
		// setCanDragReposition(true);
		setCanDragResize(true);
		setShowModalMask(true);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(true);
		// setLayoutAlign(VerticalAlignment.CENTER);

		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				notLogin();
			}
		});
		DynamicForm form = new DynamicForm();
		// form.setHeight100();
		// form.setWidth100();

		form.setPadding(5);
		form.setCellPadding(5);
		form.setCellPadding(5);
		
		form.setWrapItemTitles(false);
		form.setLayoutAlign(VerticalAlignment.CENTER);
//		textItem.setLength(100);
//		
//		passItem.setLength(100);
		form.setFields(textItem, passItem);

		HLayout hLayout = new HLayout(10);
		hLayout.setLayoutAlign(VerticalAlignment.BOTTOM);
		hLayout.setPadding(5);
		okBtn.setWidth(50);
		cancelBtn.setWidth(50);
//		cancelBtn.setPadding(10);
		hLayout.addMember(okBtn);
		hLayout.addMember(cancelBtn);
		hLayout.setAutoHeight();
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				okBtn.setDisabled(true);
				// infolable.setVisible(true);
				// infolable.setContents("good");
				SC.say("失败", "用户名或密码错误");
//				Dialog dialog = new Dialog();
//				dialog.setTitle("失败");
//			    dialog.setShowModalMask(true);
//				SC.warn("失败","用户名或密码错误", new BooleanCallback() {
//					
//					@Override
//					public void execute(Boolean value) {
//						// TODO Auto-generated method stub
//						
//					}
//				},dialog);
//				com.google.gwt.user.client.Window.alert( "用户名或密码错误");
			}
		});
		cancelBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				notLogin();
			}
		});

		addItem(form);
		infolable.setHeight(30);
		infolable.setVisible(false);
		infolable.setPadding(5);
		addItem(infolable);
		addItem(hLayout);

		form.setOverflow(Overflow.VISIBLE);
		form.setAutoWidth();
		form.setAutoHeight();
		centerInPage();
	}

	private void notLogin() {
		destroy();
	}

}
