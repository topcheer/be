package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class CorpCollegeAdmin extends FunctionPanel {

	public static String DESCRIPTION = "合作高校代码维护";


	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			CorpCollegeAdmin panel = new CorpCollegeAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		CorpCollegeAdminPanel p = new CorpCollegeAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
