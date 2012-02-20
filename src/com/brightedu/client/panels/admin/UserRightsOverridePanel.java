package com.brightedu.client.panels.admin;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserRightsOverridePanel extends VLayout {

	ListGrid caetgory_functionGrid = new ListGrid();
	SelectItem categoryItem = new SelectItem("category_ID","功能类别");
	DynamicForm aForm = new DynamicForm();
	ListGridField categoryField  =new ListGridField("categoryName","function");
	UserAdmin useradmin;
	public UserRightsOverridePanel(UserAdmin useradmin) {
		this.useradmin = useradmin;
		
	}

}
