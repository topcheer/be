package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

/**
 * 合作高校协议管理
 * 
 * @author chetwang
 * 
 */
public final class CorpCollegeAgreementAdmin extends FunctionPanel {

	public static String DESCRIPTION = "合作高校协议管理";

	public static class Factory implements PanelFactory {
		String id;

		public Canvas create() {
			CorpCollegeAgreementAdmin panel = new CorpCollegeAgreementAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BasicAdminPanel p = new CorpCollegeAgreementAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
