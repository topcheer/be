package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class EntranceCostManage extends FunctionPanel {

	public static String DESCRIPTION = "入学费用标准设置";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			EntranceCostManage panel = new EntranceCostManage();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		EntranceCostManagePanel p = new EntranceCostManagePanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
