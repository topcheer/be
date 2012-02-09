package com.brightedu.client.panels.admin;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public abstract class AdminDialog extends Window {

	IButton okBtn = new IButton("确定");
	DynamicForm form;

	public AdminDialog() {

	}

	protected void init() {
		setEdgeMarginSize(4);
		setEdgeOffset(5);
		setAutoCenter(true);
		setTitle("新增");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(false);
		setOverflow(Overflow.VISIBLE);

		form = getContentForm();
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createNewAdminItem();
			}
		});
		form.setAutoFocus(true);
		form.setWrapItemTitles(false);
		form.setAutoHeight();
		addItem(form);

		HLayout hLayout = new HLayout();
		hLayout.addMember(new Label("  "));
		hLayout.addMember(new LayoutSpacer());
		hLayout.setPadding(5);
		okBtn.setWidth(70);
		//
		hLayout.addMember(okBtn);

		hLayout.setAutoHeight();
		hLayout.setWidth100();

		addItem(hLayout);

		setAutoSize(true);
		setCanDragResize(true);

		addFieldsKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName().toLowerCase().equals("enter")) {
					createNewAdminItem();
				}
			}
		});
	}

	public void addFieldsKeyPressHandler(KeyPressHandler pressHandler) {
		FormItem[] items = form.getFields();
		for (FormItem item : items) {
			item.addKeyPressHandler(pressHandler);
		}
	}

	protected abstract DynamicForm getContentForm();

	public void show() {
		init();
		super.show();
	}

	protected abstract void createNewAdminItem();

}
