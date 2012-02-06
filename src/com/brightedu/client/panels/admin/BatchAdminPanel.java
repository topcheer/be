package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.BatchIndex;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;

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

		if (resultList.getSelectedRecord() == null) {
			SC.say("请选择一些记录");
			return;
		}

		// final Integer batch_id =
		// resultList.getSelectedRecord().getAttributeAsInt("batch_index");

		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback() {

			@Override
			public void execute(Boolean value) {

				if (value) {
					ListGridRecord[] records = resultList.getSelectedRecords();

					for (int i = 0; i < records.length; i++) {
						dbService.deleteBatch(
								records[i].getAttributeAsInt("batch_index"),
								new AsyncCallback<Boolean>() {
									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub

										caught.printStackTrace();

									}

									@Override
									public void onSuccess(Boolean result) {

									}

								});

					}
				}
				// initPages();
				gotoPage(currentPageIndex);

			}

		});

	}

	@Override
	protected void gotoPage(final int indexGoto, final boolean init) {
		final DataSource ds = new DataSource();
		ds.setClientOnly(true);
		ds.addField(new DataSourceField("batch_index", FieldType.TEXT, "批次代码"));
		ds.addField(new DataSourceField("batch_name", FieldType.TEXT, "批次名称"));
		ds.addField(new DataSourceField("reg_time", FieldType.TEXT, "录入时间"));

		AsyncCallback<List<BatchIndex>> callback = new AsyncCallback<List<BatchIndex>>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(List result) {
				int size = result.size();
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
					rec.setAttribute("batch_index", bi.getBatch_id());
					rec.setAttribute("batch_name", bi.getBatch_name());
					rec.setAttribute("reg_time", bi.getRegister_date());
					ds.addData(rec);
				}
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
		resultList.setDataSource(ds);
		resultList.fetchData();
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
				dbService.addBatch(batch, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						caught.printStackTrace();

					}

					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						// SC.say("新建批次：" + batch);

						// refresh GridList
						// postInit();
						showLastPageRecords();
						destroy();
					}

				});

			} else {
				SC.say("空内容无效！");
			}
		}

	}

}
