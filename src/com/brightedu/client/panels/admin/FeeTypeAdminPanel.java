package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.FeeType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
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
		return parseGridFields(new String[] { "obj_name","is_split","is_by_end","can_return" },
				new String[] { "费用名称","是否按年支付","是否期末支付","是否涉及返利计算" },
				new ListGridFieldType[] { ListGridFieldType.TEXT,ListGridFieldType.BOOLEAN,ListGridFieldType.BOOLEAN ,ListGridFieldType.BOOLEAN },
				new boolean[] { true,true,true,true }, new int[] { -1,120,120,120 });
	}

	public void update(final Record rec) {
		final FeeType editedBatch = (FeeType) rec
				.getAttributeAsObject("object");
		final String oldName = editedBatch.getFee_name();
		final boolean is_split = editedBatch.getSplit_by_year();
		final boolean is_by_end = editedBatch.getCharge_by_end();
		final boolean can_return = editedBatch.getCan_return();
		editedBatch.setFee_name(rec.getAttributeAsString("obj_name"));
		editedBatch.setCharge_by_end(rec.getAttributeAsBoolean("is_by_end"));
		editedBatch.setSplit_by_year(rec.getAttributeAsBoolean("is_split"));
		editedBatch.setCan_return(rec.getAttributeAsBoolean("can_return"));
		dbService.saveFeeType(editedBatch, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedBatch.setFee_name(oldName);
				editedBatch.setCharge_by_end(is_by_end);
				editedBatch.setSplit_by_year(is_split);
				editedBatch.setCan_return(can_return);
				rec.setAttribute("obj_name", oldName);
				rec.setAttribute("is_split", is_split);
				rec.setAttribute("is_by_end", is_by_end);
				rec.setAttribute("can_return", can_return);
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
					rec.setAttribute("is_split", bi.getSplit_by_year());
					rec.setAttribute("is_by_end", bi.getCharge_by_end());
					rec.setAttribute("can_return", bi.getCan_return());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getFeeTypeList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}


	@Override
	public void add(Object model) {
		final FeeType at = (FeeType) model;

		if (at.getFee_name() != null
				&& at.getFee_name().trim().length() > 0) {

			dbService.addFeeType(at, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		FeeTypeAddDialog admin = new FeeTypeAddDialog();
		admin.setAdminPanel(this);
		return admin;
	}

	private class FeeTypeAddDialog extends AdminDialog {

		private TextItem feeTypeNameItem = new TextItem("feeTypeName",
				"费用名称");
		private BooleanItem isEndItem = new BooleanItem("is_by_end", "是否期末支付");
		private BooleanItem isSplitItem = new BooleanItem("is_split", "是否按年支付");
		private BooleanItem canReturnItem = new BooleanItem("can_return", "是否涉及返利计算");
		int len = 250;

		public void init() {
			super.init();
			setSize(len + 70 + "", "160");
		}

		@Override
		protected Object getAddedModel() {

			FeeType at = new FeeType();
			at.setFee_name(feeTypeNameItem.getValueAsString());
			at.setCharge_by_end(isEndItem.getValueAsBoolean());
			at.setSplit_by_year(isSplitItem.getValueAsBoolean());
			at.setCan_return(canReturnItem.getValueAsBoolean());
			return at;
		}

		@Override
		protected DynamicForm createContentForm() {
			form = new DynamicForm();
			feeTypeNameItem.setWidth(len);
			isEndItem.setWidth(len);
			isEndItem.setValue(false);
			isSplitItem.setWidth(len);
			isSplitItem.setValue(false);
			canReturnItem.setWidth(len);
			canReturnItem.setValue(false);
			form.setPadding(5);
			form.setFields(feeTypeNameItem, isEndItem,isSplitItem,canReturnItem);
			return form;
		}

	}


}
