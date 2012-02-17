package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class RightsManage extends FunctionPanel {

	public static String DESCRIPTION = "权限基础数据设置";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			RightsManage panel = new RightsManage();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		RightsManagePanel p = new RightsManagePanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
