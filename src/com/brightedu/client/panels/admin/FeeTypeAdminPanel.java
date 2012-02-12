package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.FeeType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;

public class FeeTypeAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void deleteRecords(final List<Integer> deleteIds) {
		dbService.deletFeeType(deleteIds, delAsync);
	}

	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name"},
				new String[] { "费用名称" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT },
				new boolean[] { true}, new int[] { -1 });
	}

	public void update(final Record rec) {
		final FeeType editedBatch = (FeeType) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getFee_name();
		editedBatch.setFee_name(rec.getAttributeAsString("obj_name"));
		dbService.saveFeeType(editedBatch, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedBatch.setFee_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<FeeType>> callback = new CommonAsyncCall<List<FeeType>>() {
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
					FeeType bi = (FeeType) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getFee_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getFee_name());
					
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		if (init) {
			dbService.getFeeTypeListAndTotalCounts((indexGoto - 1)
					* currentRowsInOnePage, currentRowsInOnePage, callback);
		} else {
			dbService.getFeeTypeList((indexGoto - 1) * currentRowsInOnePage,
					currentRowsInOnePage, callback);
		}
	}

	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "费用类型" };
		text.adminPanel = this;
		return text;
	}

	@Override
	public void add(Object model) {
		final String batch = ((String[]) model)[0];
		if (batch != null && batch.trim().length() > 0) {
			dbService.addFeeType(batch, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

}
