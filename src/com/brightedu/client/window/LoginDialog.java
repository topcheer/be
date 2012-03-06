package com.brightedu.client.window;

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
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class LoginDialog extends Window {

	TextItem userItem = new TextItem("用户");

	PasswordItem passItem = new PasswordItem("密码");
	IButton okBtn = new IButton("登录");
	Label forgetPass = null;
	DynamicForm loginForm = new DynamicForm();

	public LoginDialog() {

		setTitle("登录");
		setShowMinimizeButton(false);
		setIsModal(true);
		// setCanDragReposition(true);
		// setCanDragResize(true);
		setShowModalMask(true);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(true);
		setWidth(205);
		setHeight(100);
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

		// form.setHeight100();
		// form.setWidth100();
		loginForm.setAutoFocus(true);
		loginForm.setPadding(5);
		loginForm.setCellPadding(5);

		loginForm.setWrapItemTitles(false);
		loginForm.setLayoutAlign(VerticalAlignment.CENTER);
		// textItem.setLength(100);
		//
		// passItem.setLength(100);
		loginForm.setFields(userItem, passItem);

		HLayout hLayout = new HLayout(10);
		hLayout.setLayoutAlign(VerticalAlignment.BOTTOM);
		hLayout.setPadding(5);

		okBtn.setWidth(70);

		hLayout.addMember(forgetPass);
		hLayout.addMember(okBtn);

		hLayout.setAutoHeight();

		addItem(loginForm);

		addItem(hLayout);

		loginForm.setOverflow(Overflow.VISIBLE);
		loginForm.setAutoWidth();
		loginForm.setAutoHeight();
		setEdgeMarginSize(4);
		// setEdgeOpacity(60);
		setOpacity(80);
		setEdgeOffset(5);
		centerInPage();

	}

	public void addFiledsKeyPressHandler(KeyPressHandler pressHandler) {
		userItem.addKeyPressHandler(pressHandler);
		passItem.addKeyPressHandler(pressHandler);
	}

	public IButton getOkBtn() {
		return okBtn;
	}

	public TextItem getUserItem() {
		return userItem;
	}

	public PasswordItem getPassItem() {
		return passItem;
	}

	public Label getForgetPass() {
		return forgetPass;
	}

	private void notLogin() {
		destroy();
		Page.checkBrowserAndRedirect("notAuth.html");
		// String url = Page.getURL("BrightEdu.html");
		// System.out.println(url);
		// com.google.gwt.user.client.Window.Location.reload();
		// com.google.gwt.user.client.Window.open("http://www.sohu.com", "xx",
		// "feauture");
	}

}
