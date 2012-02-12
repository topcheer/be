package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public class StudentTypeAdmin extends FunctionPanel {

	public static String DESCRIPTION = "学生类型代码维护";

	public static class Factory implements PanelFactory {
		private String id;

		public Canvas create() {
			StudentTypeAdmin panel = new StudentTypeAdmin();
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
		BasicAdminPanel p = new StudentTypeAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
