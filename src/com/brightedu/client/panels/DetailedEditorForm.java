package com.brightedu.client.panels;

import java.io.Serializable;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public abstract class DetailedEditorForm extends DynamicForm {

	protected ButtonItem saveBtn = new ButtonItem("save", "修改");

	MasterDetailAdmin admin;

	public DetailedEditorForm(MasterDetailAdmin masterDetailAdmin) {
		this.admin = masterDetailAdmin;
		setNumCols(4);
		setOverflow(Overflow.SCROLL);
		saveBtn.setStartRow(true);
		saveBtn.setEndRow(false);
		saveBtn.setWidth(60);
		saveBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Record rec = admin.master.getResultList().getSelectedRecord();
				admin.master.update(rec);
			}
		});
		disableSaveItem();
	}

	public void hideSaveItem() {
		saveBtn.setVisible(false);
	}

	public void enableSaveItem() {
		saveBtn.enable();
	}

	public void disableSaveItem() {
		saveBtn.disable();
	}

	// public ButtonItem getSaveItem() {
	// return saveBtn;
	// }

	public abstract void setValue(Serializable model);

	public abstract Serializable getModel();

	public Integer getValueAsInteger(SelectItem item){
		String s = item.getValueAsString();
		if(s!=null){
			return new Integer(s);
		}
		return null;
	}
	
}
