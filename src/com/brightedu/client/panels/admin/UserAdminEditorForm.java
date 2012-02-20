package com.brightedu.client.panels.admin;

import com.brightedu.model.edu.User;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class UserAdminEditorForm extends DynamicForm {
	
//	user_id
//	user_name
//	user_password
//	user_type_id
//	user_status
//	agent_id
//	register_date
//	update_date
//	display_name

	
	TextItem user_nameItem = new TextItem("user_name", "用户名");
	SelectItem user_typeItem = new SelectItem("user_type", "用户类型");
	PasswordItem user_passwordItem = new PasswordItem("user_password", "密码");
	BooleanItem user_statusItem = new BooleanItem("user_status","有效用户？");
	SelectItem agentItem = new SelectItem("agent", "所属机构");

	TextItem displayNameItem = new TextItem("display_name", "显示名称");

	ButtonItem saveBtn = new ButtonItem("save", "修改");



	public UserAdminEditorForm() {
		init();
	}

	private void init() {
		setMargin(20);
		setWidth(600);
		setNumCols(4);
		setOverflow(Overflow.AUTO);
		saveBtn.setLeft(300);
		saveBtn.setStartRow(false);
		saveBtn.setEndRow(false);
		// saveBtn.set
		// saveBtn.setColSpan(3);
		saveBtn.setWidth(60);
		setFields(user_nameItem, user_passwordItem, displayNameItem,
				user_typeItem, agentItem, user_statusItem,
				saveBtn);

		// saveBtn.addClickHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// save();
		// }
		// });

	}

	// private void save(){
	//
	// }

	public User getModel() {
		User user = new User();
		user.setUser_name(user_nameItem.getValueAsString());
		user.setUser_password(user_passwordItem.getValueAsString());
		user.setAgent_id(new Integer(agentItem.getValueAsString()));
		user.setDisplay_name(displayNameItem.getValueAsString());
		user.setUser_status(new Boolean(user_statusItem.getValue().toString()));
		user.setUser_type_id(new Integer(user_typeItem.getValue().toString()));
		
		return user;
	}

	public void setValue(User user) {
		
		user_nameItem.setValue(user.getUser_name());
		user_passwordItem.setValue(user.getUser_password());
		agentItem.setValue(user.getAgent_id());
		displayNameItem.setValue(user.getDisplay_name());
		user_statusItem.setValue(user.getUser_status());
		user_typeItem.setValue(user.getUser_type_id());
	}

	public ButtonItem getSaveBtn() {
		return saveBtn;
	}



}
