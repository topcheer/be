package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.shared.SearchCriteria;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;

public class BatchAdminPanel extends BasicAdminPanel {


	@Override
	public void deleteRecords(final List<Integer> deleteIds) {
		// dbService.deleteBatch(deleteIds, delAsync);
		dbService.deleteModel("BatchIndex", "batch_id", deleteIds, delAsync);
	}

	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name", "reg_time" },
				new String[] { "批次名称", "录入时间" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.DATE },
				new boolean[] { true, false }, new int[] { -1, 200 },
				new Validator[] { standardLenthValidator, null });
	}

	public void update(final Record rec) {
		final BatchIndex editedBatch = (BatchIndex) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getBatch_name();
		editedBatch.setBatch_name(rec.getAttributeAsString("obj_name"));
		dbService.saveBatch(editedBatch, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedBatch.setBatch_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

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
					BatchIndex bi = (BatchIndex) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getBatch_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getBatch_name());
					rec.setAttribute("reg_time", bi.getRegister_date());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getModels("BatchIndex", searchCriteria,
				((indexGoto - 1) * currentRowsInOnePage), currentRowsInOnePage,
				init, callback);
	}

	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[][] { new String[]{"batch_name","批次"} };
		text.adminPanel = this;
		return text;
	}

	@Override
	public void add(Serializable model) {
		final String batch = ((String[]) model)[0];
		if (batch != null && batch.trim().length() > 0) {
			BatchIndex batchIndex = new BatchIndex();
			batchIndex.setBatch_name(batch);
			dbService.addModel(batchIndex, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

}
