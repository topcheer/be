package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class BatchImport extends FunctionPanel {

	public static String DESCRIPTION = "批量导入学生";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			BatchImport panel = new BatchImport();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BatchImportPanel p = new BatchImportPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
