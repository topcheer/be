package com.brightedu.client.panels.admin;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.BatchIndex;
import com.smartgwt.client.types.FieldType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class BatchAdminPanel extends BasicAdminPanel {
	final AdminDialog dialog = new NewBatchDialog();
	protected void postInit()
	{
		
		final DataSource ds= new DataSource();
		ds.setClientOnly(true);
		ds.addField(new DataSourceField("batch_index",FieldType.TEXT,"批次代码"));
		ds.addField(new DataSourceField("batch_name",FieldType.TEXT,"批次名称"));
		ds.addField(new DataSourceField("reg_time",FieldType.TEXT,"录入时间"));
		
	   AsyncCallback<List<BatchIndex>> callback = new AsyncCallback<List<BatchIndex>>()
	   {

		@Override
		public void onFailure(Throwable caught) {

			caught.printStackTrace();
			
		}

		@Override
		public void onSuccess(List<BatchIndex> result) {
			
			Iterator<BatchIndex> it = result.iterator();
			
			while(it.hasNext())
			{
				BatchIndex bi = it.next();
				Record rec = new Record();
				rec.setAttribute("batch_index", bi.getBatch_id());
				rec.setAttribute("batch_name", bi.getBatch_name());
				rec.setAttribute("reg_time", bi.getRegister_date());
				ds.addData(rec);
			}
		}
		   
	   };
		dbService.getBatchList(callback);
		
		resultList.setDataSource(ds);
		resultList.fetchData();
		resultList.redraw();
		
		
		
	}

	@Override
	protected void search(String keyWords, Record range) {
	}

	@Override
	protected void addRecord() {
		
		dialog.show();		
	}

	@Override
	protected void deleteRecords() {
		
		if(resultList.getSelectedRecord() == null)
		{
			SC.say("请选择一些记录");
			return;
		}
		
		//final Integer batch_id = resultList.getSelectedRecord().getAttributeAsInt("batch_index");
		
		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback(){

			@Override
			public void execute(Boolean value) {
				
				if(value)
				{
					ListGridRecord[] records=resultList.getSelectedRecords();
					
					for(int i=0;i<records.length;i++)
					{
						dbService.deleteBatch(records[i].getAttributeAsInt("batch_index"), new AsyncCallback<Boolean>()
						{
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
								caught.printStackTrace();
								
							}

							@Override
							public void onSuccess(Boolean result) {
								// TODO Auto-generated method stub
							
//								if(result.booleanValue())
//								{
//									SC.say("成功", "成功删除记录");
//								}
//								{
//									SC.say("失败", "操作失败，请稍后再试");
//								}
								
							}
						
					});

					}
				}
				postInit();
					
				}
				
			});
			
	

	}

	@Override
	protected void showLastPageRecords() {

	}

	@Override
	protected void showFirstPageRecords() {

	}

	@Override
	protected void showNextPageRecords() {

	}

	@Override
	protected void showPreviousPageRecords() {
	}

	@Override
	protected void gotoPage(int indexGoto) {
		

	}

	@Override
	protected void initPages() {

  
	}

	private class NewBatchDialog extends AdminDialog {

		TextItem batchItem = new TextItem("批次");

		
		protected void init(){
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
				dbService.addBatch(batch, new AsyncCallback<Boolean>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						caught.printStackTrace();
						
					}

					@Override
					public void onSuccess(Boolean result) {
						// TODO Auto-generated method stub
						SC.say("新建批次："+batch);
						
						//refresh GridList
						postInit();
						dialog.destroy();
					}
					
				});
				
				
				showLastPageRecords();
			}else{
				SC.say("空内容无效！");
			}
		}

	}

}
