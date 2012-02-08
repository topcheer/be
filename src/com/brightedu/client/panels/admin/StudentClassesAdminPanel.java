package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGrid;

public class StudentClassesAdminPanel extends BasicAdminPanel {

	@Override
	protected void search(String keyWords, Record range) {
	}

	@Override
	protected void addRecord() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void deleteRecords() {
		// TODO Auto-generated method stub

	}
//	
//	@Override
//	protected DataSource createDataSource() {
//		DataSource ds = new DataSource();
//		ds.setClientOnly(true);
//		DataSourceField selectField = new DataSourceField("select",
//				FieldType.BOOLEAN, "选择", 30);
//		selectField.setCanEdit(true);
//		
//		ds.setFields(selectField);
//		return ds;
//	}

	@Override
	protected void gotoPage(int indexGoto, boolean init) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ListGrid createListGrid() {
		ListGrid result = new ListGrid();
		return result;
	}

}
