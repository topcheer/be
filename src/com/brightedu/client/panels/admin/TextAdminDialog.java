package com.brightedu.client.panels.admin;

import java.io.Serializable;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;

public class TextAdminDialog extends AdminDialog {

	protected String[][] titles;

	protected TextItem[] items;

	public TextAdminDialog() {

	}

	public void init() {
		int maxTitleLen = 0;
		
		items = new TextItem[titles.length];
		for (int i = 0; i < titles.length; i++) {
			items[i] = new TextItem(titles[i][0],titles[i][1]);
			items[i].setValidators(standardLenthValidator);
			items[i].setRequired(true);
			if (titles[i][1].length() > maxTitleLen) {
				maxTitleLen = titles[i][1].length();
			}
		}
		super.init();
		
		setOverflow(Overflow.HIDDEN);
		setSize(180 + 10 * maxTitleLen + "", 80+(items.length-1)*25+"");
	}

	@Override
	protected DynamicForm createContentForm() {
		DynamicForm form = new DynamicForm();
		form.setPadding(5);
		form.setWidth100();
		form.setFields(items);
		
		return form;
	}

	@Override
	protected Serializable getAddedModel() {
		
		String[] values = new String[titles.length];
		for (int i = 0; i < titles.length; i++) {
			values[i] = items[i].getValueAsString();
		}
		return values;
	}

}
