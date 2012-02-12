package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class UserTypeAdmin extends FunctionPanel {

	public static String DESCRIPTION = "用户类型维护";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			UserTypeAdmin panel = new UserTypeAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BasicAdminPanel p = new UserTypeAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
