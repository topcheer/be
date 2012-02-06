package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
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
		
	}

	@Override
	public Canvas getViewPanel() {
		BasicAdminPanel p = new BatchAdminPanel();
		return p;
	}
	
	@Override
	public String getDescription(){
		return DESCRIPTION;
	}

}
