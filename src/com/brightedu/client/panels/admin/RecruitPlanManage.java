package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class RecruitPlanManage extends FunctionPanel {

	public static String DESCRIPTION = "招生计划管理";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			RecruitPlanManage panel = new RecruitPlanManage();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		RecruitPlanManagePanel p = new RecruitPlanManagePanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
