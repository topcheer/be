package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.UserType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentStatusAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void deleteRecords(final List<Integer> deleteIds) {
		dbService.deletStudentStatus(deleteIds, delAsync);
	}

	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name" },
				new String[] { "学生状态" },
				new ListGridFieldType[] { ListGridFieldType.TEXT },
				new boolean[] { true }, new int[] { -1 });
	}

	public void update(final Record rec) {
		final StudentStatus editedBatch = (StudentStatus) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getStu_status_name();
		editedBatch.setStu_status_name(rec.getAttributeAsString("obj_name"));
		dbService.saveStudentStatus(editedBatch,
				new CommonAsyncCall<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						BrightEdu.showTip("已保存!");
					}

					protected void failed() { // rollback in UI
						editedBatch.setStu_status_name(oldName);
						rec.setAttribute("obj_name", oldName);
					}
				});
	}

	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<StudentStatus>> callback = new CommonAsyncCall<List<StudentStatus>>() {
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
					StudentStatus bi = (StudentStatus) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getStu_status_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getStu_status_name());

					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getStudentStatusList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "学生状态" };
		text.adminPanel = this;
		return text;
	}

	@Override
	public void add(Serializable model) {
		final String batch = ((String[]) model)[0];
		if (batch != null && batch.trim().length() > 0) {
			StudentStatus status = new StudentStatus();
			status.setStu_status_name(batch);
			dbService.addModel(status, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

}
