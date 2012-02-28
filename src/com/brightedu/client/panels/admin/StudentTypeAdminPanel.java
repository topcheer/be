package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.StudentType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentTypeAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<StudentType>> callback = new CommonAsyncCall<List<StudentType>>() {
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
					StudentType sc = (StudentType) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", sc.getStudent_type_id());
					rec.setAttribute("object", sc);
					rec.setAttribute("obj_name", sc.getStudent_type_name());

					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getStudentTypeList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteStudentType(deleteIds, delAsync);
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name" },
				new String[] { "学生类型名称" },
				new ListGridFieldType[] { ListGridFieldType.TEXT },
				new boolean[] { true }, new int[] { -1 },
				new Validator[] { standardLenthValidator });
	}

	@Override
	public void update(final Record rec) {
		final StudentType editedSC = (StudentType) rec
				.getAttributeAsObject("object");
		final String oldName = editedSC.getStudent_type_name();
		editedSC.setStudent_type_name(rec.getAttributeAsString("obj_name"));
		dbService.saveStudentType(editedSC, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedSC.setStudent_type_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

	@Override
	public void add(Serializable model) {
		final String studentClass = ((String[]) model)[0];
		if (studentClass != null && studentClass.trim().length() > 0) {
			StudentType st = new StudentType();
			st.setStudent_type_name(studentClass);
			dbService.addModel(st, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "类型" };
		text.adminPanel = this;
		return text;
	}

}
