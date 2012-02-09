package com.brightedu.client.panels.admin;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class TextAdminDialog extends AdminDialog {

	protected String[] titles;

	protected TextItem[] items;

	public TextAdminDialog() {

	}

	public void init() {
		items = new TextItem[titles.length];
		for (int i = 0; i < titles.length; i++) {
			items[i] = new TextItem(titles[i]);
		}
		super.init();		
		setSize("200", "80");
	}

	@Override
	protected DynamicForm getContentForm() {
		DynamicForm form = new DynamicForm();
		form.setPadding(5);
		form.setFields(items);
		return form;
	}

	@Override
	protected Object getAddedModel() {
		String[] values = new String[titles.length];
		for (int i = 0; i < titles.length; i++) {
			values[i] = items[i].getValueAsString();
		}
		return values;
	}

}
