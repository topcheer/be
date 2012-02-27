package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.ChargeType;
import com.brightedu.model.edu.PictureType;
import com.brightedu.model.edu.UserType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PictureTypeAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void deleteRecords(final List<Integer> deleteIds) {
		dbService.deletPictureType(deleteIds, delAsync);
	}

	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name" },
				new String[] { "照片类型" },
				new ListGridFieldType[] { ListGridFieldType.TEXT },
				new boolean[] { true }, new int[] { -1 });
	}

	public void update(final Record rec) {
		final PictureType editedBatch = (PictureType) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getPic_type_name();
		editedBatch.setPic_type_name(rec.getAttributeAsString("obj_name"));
		dbService.savePictureType(editedBatch, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedBatch.setPic_type_name(oldName);
				rec.setAttribute("obj_name", oldName);
			}
		});
	}

	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<PictureType>> callback = new CommonAsyncCall<List<PictureType>>() {
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
					PictureType bi = (PictureType) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getPic_type_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getPic_type_name());

					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getPictureTypeList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	public AdminDialog createAdminDialog() {
		TextAdminDialog text = new TextAdminDialog();
		text.titles = new String[] { "用户类型" };
		text.adminPanel = this;
		return text;
	}

	@Override
	public void add(Serializable model) {
		final String batch = ((String[]) model)[0];
		if (batch != null && batch.trim().length() > 0) {
			PictureType pt = new PictureType();
			pt.setPic_type_name(batch);
			dbService.addModel(pt, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

}
