package com.brightedu.client.login;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class LoginDialog extends Window {

	TextItem textItem = new TextItem();

	PasswordItem passItem = new PasswordItem();
	IButton okBtn = new IButton("登录");
	IButton cancelBtn = new IButton("取消");

	public LoginDialog() {
		setWidth(250);
		setHeight(125);
		setTitle("登录");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		setLayoutAlign(VerticalAlignment.BOTTOM);

		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				destroy();
			}
		});
		DynamicForm form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setLayoutAlign(VerticalAlignment.CENTER);
		textItem.setTitle("用户名: ");
		passItem.setTitle("密码: ");
		textItem.setWidth("100%");
		passItem.setWidth("100%");
		form.setFields(textItem, passItem);
		
		
		HLayout hLayout = new HLayout(5);
		hLayout.setAlign(Alignment.RIGHT);
		hLayout.setPadding(5);
		okBtn.setWidth(80);
		cancelBtn.setWidth(80);
		hLayout.addMember(okBtn);
		hLayout.addMember(cancelBtn);

		
		addItem(form);
		addItem(hLayout);
		centerInPage();		
	}

}
