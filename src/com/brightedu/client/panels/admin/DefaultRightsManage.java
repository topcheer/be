package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class DefaultRightsManage extends FunctionPanel {

	public static String DESCRIPTION = "用户缺省权限设置";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			DefaultRightsManage panel = new DefaultRightsManage();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		DefaultRightsManagePanel p = new DefaultRightsManagePanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
