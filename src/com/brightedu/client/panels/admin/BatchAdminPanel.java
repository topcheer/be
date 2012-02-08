package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.List;

import com.brightedu.client.BrightCanvas;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.BatchIndex;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;

public class BatchAdminPanel extends BasicAdminPanel {

	@Override
	protected void search(String keyWords, Record range) {
	}

	@Override
	protected void addRecord() {
		AdminDialog dialog = new NewBatchDialog();
		dialog.show();
	}

	@Override
	protected void deleteRecords() {
		RecordList recList = resultList.getDataAsRecordList();
		final List<Integer> deleteIds = new ArrayList<Integer>();
		for (int i = 0; i < recList.getLength(); i++) {
			if (recList.get(i).getAttributeAsBoolean("select")) {
				deleteIds.add(recList.get(i).getAttributeAsInt("batch_id"));
			}
		}
		if (deleteIds.size() == 0) {
			SC.say("请选择一些记录");
			return;
		}

		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value) {
					dbService.deleteBatch(deleteIds,
							new CommonAsyncCall<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									showTip("已删除！");
									gotoPage(currentPageIndex);
								}
							});
				}
			}
		});
	}

	private void showTip(String tip) {
		BrightCanvas ca = (BrightCanvas) getById(BrightCanvas.ID);
		ca.showTip(tip);
	}

	@Override
	protected ListGrid createListGrid() {
		ListGrid result = new ListGrid();
		ListGridField selectField = new ListGridField("select", "选择", 100);
		ListGridField indexField = new ListGridField("batch_id", "批次代码", 100);
		ListGridField nameField = new ListGridField("batch_name", "批次名称");
		ListGridField regTimeField = new ListGridField("reg_time", "录入时间", 200);
		selectField.setType(ListGridFieldType.BOOLEAN);
		indexField.setType(ListGridFieldType.INTEGER);
		nameField.setType(ListGridFieldType.TEXT);
		regTimeField.setType(ListGridFieldType.DATE);

		indexField.setCanEdit(false);
		regTimeField.setCanEdit(false);

		selectField.setAlign(Alignment.CENTER);
		indexField.setAlign(Alignment.CENTER);
		nameField.setAlign(Alignment.CENTER);
		regTimeField.setAlign(Alignment.CENTER);
		// selectField
		result.setCanEdit(true);
		nameField.addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				Record rec = event.getRecord();
				BatchIndex editedBatch = new BatchIndex();
				editedBatch.setBatch_id(rec.getAttributeAsInt("batch_id"));
				editedBatch.setBatch_name(rec
						.getAttributeAsString("batch_name"));
				editedBatch.setRegister_date(rec.getAttributeAsDate("reg_time"));
				dbService.save(editedBatch, new CommonAsyncCall<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						showTip("保存成功!");
					}
				});
			}
		});
		result.setEditEvent(ListGridEditEvent.DOUBLECLICK);
		result.setFields(selectField, indexField, nameField, regTimeField);
		//隐藏ID列，好看一点
		result.hideField(indexField.getName());
		return result;
	}

	protected void gotoPage(final int indexGoto, final boolean init) {
		// 没烧到情况ds的方法，只好重新创建一次
		// ds = createDataSource();
		AsyncCallback<List<BatchIndex>> callback = new CommonAsyncCall<List<BatchIndex>>() {
			@Override
			public void onSuccess(List result) {
				int size = result.size();
				// List<Record> listData = new ArrayList<Record>();
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
					rec.setAttribute("batch_id", bi.getBatch_id());
					rec.setAttribute("batch_name", bi.getBatch_name());
					rec.setAttribute("reg_time", bi.getRegister_date());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		if (init) {
			dbService.getBatchListAndTotalCounts(0, currentRowsInOnePage,
					callback);
		} else {
			dbService.getBatchList((indexGoto - 1) * currentRowsInOnePage,
					currentRowsInOnePage, callback);
		}
	}

	private class NewBatchDialog extends AdminDialog {

		TextItem batchItem = new TextItem("批次");

		protected void init() {
			super.init();
			setSize("200", "80");
			setCanDragResize(false);
		}

		@Override
		protected DynamicForm getContentForm() {
			DynamicForm form = new DynamicForm();
			form.setPadding(5);
			form.setFields(batchItem);
			return form;
		}

		@Override
		protected void createNewAdminItem() {
			final String batch = batchItem.getValueAsString();
			if (batch != null && batch.trim().length() > 0) {
				dbService.addBatch(batch, new CommonAsyncCall<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						showLastPageRecords(true);
						destroy();
					}
				});
			} else {
				SC.say("空内容无效！");
			}
		}

	}

}
