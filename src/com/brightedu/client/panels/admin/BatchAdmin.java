package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;

public class BatchAdmin extends FunctionPanel {

	public static String DESCRIPTION = "批次代码维护";

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			BatchAdmin panel = new BatchAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {

		BasicAdminPanel p = new BasicAdminPanel() {

			@Override
			protected void search() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void addRecord() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void deleteRecords() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void showLastPageRecords() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void showFirstPageRecords() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void showNextPageRecords() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void showPreviousPageRecords() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void updatePage(int indexGoto) {
				// TODO Auto-generated method stub
				
			}
		};
		return p;
	}

	public static final class BatchDataSource extends DataSource {
		public BatchDataSource() {

		}
	}

}
