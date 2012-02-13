package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.StudentClassified;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentClassesAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<StudentClassified>> callback = new CommonAsyncCall<List<StudentClassified>>() {
			@Override
			public void onSuccess(List result) {
				int size = result.size();
				Record[] listData = init ? new Record[size - 1]
						: new Record[size];
				for (int i = 0; i < size; i++) {
					if (i == size - 1) {
						if (init) {
							int counts = (Integer) result.get(size - 1);
							setTotalCounts(counts);
							break;
						}
					}
					StudentClassified sc = (StudentClassified) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", sc.getClassified_id());
					rec.setAttribute("object", sc);
					rec.setAttribute("obj_name", sc.getClassified_name());
					rec.setAttribute("reg_time", sc.getRegister_date());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getStudentClassesList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteStudentClasses(deleteIds, delAsync);
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name", "reg_time" },
				new String[] { "学生层次名称", "录入时间" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.DATE },
				new boolean[] { true, false }, new int[] { -1, 200 });
	}

	@Override
	public void update(final Record rec) {
		final StudentClassified editedSC = (StudentClassified) rec
				.getAttributeAsObject("object");
		final String oldName = editedSC.getClassified_name();
		editedSC.setClassified_name(rec.getAttributeAsString("obj_name"));
		dbService.saveStudentClasses(editedSC, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedSC.setClassified_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

	@Override
	public void add(Object model) {
		final String studentClass = ((String[]) model)[0];
		if (studentClass != null && studentClass.trim().length() > 0) {
			dbService.addStudentClass(studentClass, getAdminDialog()
					.getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "层次" };
		text.adminPanel = this;
		return text;
	}

}
