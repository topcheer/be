package com.brightedu.client.login;

import com.brightedu.client.UiUtils;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.Page;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class LoginDialog extends Window {

	TextItem textItem = new TextItem("用户");

	Label infolable = new Label();
	PasswordItem passItem = new PasswordItem("密码");
	IButton okBtn = new IButton("登录");
	Label forgetPass = null;
	
	

	public LoginDialog() {
		// setWidth(250);
		// setHeight(150);
		setTitle("登录");
		setShowMinimizeButton(false);
		setIsModal(true);
		// setCanDragReposition(true);
//		setCanDragResize(true);
		setShowModalMask(true);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(true);
		
		forgetPass = UiUtils.getLink("忘记密码?", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.say("忘记密码");
			}
		});
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
		okBtn.setWidth(70);

		hLayout.addMember(forgetPass);
		hLayout.addMember(okBtn);
		
		hLayout.setAutoHeight();
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				okBtn.setDisabled(true);
				// infolable.setVisible(true);
				// infolable.setContents("good");
				SC.say("失败", "用户名或密码错误");
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
		Page.checkBrowserAndRedirect("notAuth.html");
//		String url = Page.getURL("BrightEdu.html");
//		System.out.println(url);
//		com.google.gwt.user.client.Window.Location.reload();
//		com.google.gwt.user.client.Window.open("http://www.sohu.com", "xx", "feauture");
	}

}
