package com.brightedu.client.panels.admin;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserRightsOverridePanel extends VLayout {

	ListGrid userRightsGrid = new ListGrid();
	UserAdmin useradmin;
	public UserRightsOverridePanel(UserAdmin useradmin) {

		this.useradmin = useradmin;
		
		ListGridField selectField = new ListGridField("select","选择");
		selectField.setType(ListGridFieldType.BOOLEAN);
		selectField.setCanEdit(true);
		ListGridField categoryField = new ListGridField("categoryID","功能类别ID");
		categoryField.setType(ListGridFieldType.TEXT);
		ListGridField functionField = new ListGridField("functionID","功能ID");
		functionField.setType(ListGridFieldType.TEXT);
		ListGridField categoryNameField = new ListGridField("categoryName","功能类别");
		categoryNameField.setType(ListGridFieldType.TEXT);
		ListGridField functionNameField = new ListGridField("functionName","功能");
		functionNameField.setType(ListGridFieldType.TEXT);
		
		userRightsGrid.setFields(selectField,categoryField,categoryNameField,functionField,functionNameField);
		userRightsGrid.setShowHeaderContextMenu(false);
		addMember(userRightsGrid);
	}

}
