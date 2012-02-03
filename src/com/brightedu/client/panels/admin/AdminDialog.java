package com.brightedu.client.panels.admin;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public abstract class AdminDialog extends Window {

	IButton okBtn = new IButton("确定");

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
		
		
		
		DynamicForm form = getContentForm();
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createNewAdminItem();
			}
		});
		form.setWrapItemTitles(false);
		form.setAutoHeight();
		addItem(form);

		HLayout hLayout = new HLayout();
//		LayoutSpacer spacer = new LayoutSpacer();
//		spacer.setAutoWidth();
		hLayout.addMember(new Label("  "));
		hLayout.addMember(new LayoutSpacer());
		hLayout.setPadding(5);
		okBtn.setWidth(70);
//		
		hLayout.addMember(okBtn);
		hLayout.setAutoHeight();
		hLayout.setWidth100();
		
		addItem(hLayout);
//		form.setStyleName("test");
//		hLayout.setStyleName("test2");
		setAutoSize(true);
		setCanDragResize(true);
		addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				System.out.println("down");
			}
		});
	}

	protected abstract DynamicForm getContentForm();

	public void show() {
		init();
		super.show();
	}

	protected abstract void createNewAdminItem();

}
