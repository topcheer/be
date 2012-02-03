package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

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
			String batch = batchItem.getValueAsString();
			if (batch != null && batch.trim().length() > 0) {
				SC.say("新建批次："+batch);
				showLastPageRecords();
			}else{
				SC.say("空内容无效！");
			}
		}

	}

}
