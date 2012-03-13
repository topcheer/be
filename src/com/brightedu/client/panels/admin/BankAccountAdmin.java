package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class BankAccountAdmin extends FunctionPanel {

	public static String DESCRIPTION = "学生打款帐号维护";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			BankAccountAdmin panel = new BankAccountAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BankAccountAdminPanel p = new BankAccountAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
