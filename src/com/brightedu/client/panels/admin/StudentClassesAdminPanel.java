package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.panels.BasicAdminPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentClassesAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void gotoPage(int indexGoto, boolean init) {

	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {

	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "stu_class_name", "reg_time" },
				new String[] { "学生类别名称", "录入时间" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.DATE },
				new boolean[] { true, false }, new int[] { -1, 200 });
	}

	@Override
	public void update(Record record) {

	}

	@Override
	public void add(Object model) {

	}

	@Override
	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "类别" };
		text.adminPanel = this;
		return text;
	}

}
