package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.College;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CorpCollegeAdminPanel extends BasicAdminPanel {

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List> callback = new CommonAsyncCall<List>() {
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
					College bi = (College) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getCollege_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getCollege_name());
					rec.setAttribute("reg_time", bi.getRegister_date());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		// dbService.getCollegeList((indexGoto - 1) * currentRowsInOnePage,
		// currentRowsInOnePage, init, callback);
		dbService.getModels("College", searchCriteria,
				((indexGoto - 1) * currentRowsInOnePage), currentRowsInOnePage,
				init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name", "reg_time" },
				new String[] { "高校名称", "录入时间" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.DATE },
				new boolean[] { true, false }, new int[] { -1, 200 },
				new Validator[] { standardLenthValidator, null });
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteCollege(deleteIds, delAsync);
	}

	@Override
	public void update(final Record rec) {
		final College editedBatch = (College) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getCollege_name();
		editedBatch.setCollege_name(rec.getAttributeAsString("obj_name"));
		dbService.saveCollege(editedBatch, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedBatch.setCollege_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

	@Override
	public void add(Serializable model) {
		final String collegeName = ((String[]) model)[0];
		if (collegeName != null && collegeName.trim().length() > 0) {
			College college = new College();
			college.setCollege_name(collegeName);
			dbService.addModel(college, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[][] { new String[] { "college_name", "高校" } };
		text.adminPanel = this;
		return text;
	}

}
