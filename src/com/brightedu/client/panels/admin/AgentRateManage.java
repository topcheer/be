package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class AgentRateManage extends FunctionPanel {

	public static String DESCRIPTION = "招生点返利规则设置";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			AgentRateManage panel = new AgentRateManage();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		AgentRateManagePanel p = new AgentRateManagePanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
