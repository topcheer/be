package com.brightedu.client.panels.admin.students;

import java.util.List;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.admin.AdminDialog;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentsRegisterMasterPanel extends BasicAdminPanel {

	ListGridField[] fileds;

	@Override
	protected void gotoPage(int indexGoto, boolean init) {

	}

	@Override
	public ListGridField[] createGridFileds() {
		fileds = parseGridFields(new String[] { "student_name",
				"identity_card", "exam_num" }, new String[] { "学生", "身份证",
				"准考证" }, new ListGridFieldType[] { ListGridFieldType.TEXT,
				ListGridFieldType.TEXT, ListGridFieldType.TEXT },
				new boolean[] { false, false, false },
				new int[] { 100, -1, -1 });
		return fileds;
	}

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Record record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(Object model) {
		// TODO Auto-generated method stub

	}

	@Override
	public AdminDialog createAdminDialog() {
		// TODO Auto-generated method stub
		return null;
	}

}
