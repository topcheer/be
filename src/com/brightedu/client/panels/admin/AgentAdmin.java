package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminDetailPanel;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class AgentAdmin extends MasterDetailAdmin {

	public static String DESCRIPTION = "机构维护";

	public static class Factory implements PanelFactory {
		String id;

		public Canvas create() {
			AgentAdmin panel = new AgentAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public BasicAdminPanel createMasterPanel() {
		return new AgentAdminMasterPanel(this);
	}

	@Override
	public BasicAdminDetailPanel createDetailPanel() {
		return new AgentAdminDetaiPanel(this);
	}

	@Override
	protected String getMasterTitle() {
		return "招生点列表";
	}

	@Override
	protected String getDetialTitle() {
		return "详细信息";
	}
	
	public class AgentAdminDetaiPanel extends BasicAdminDetailPanel {

		public AgentAdminDetaiPanel(MasterDetailAdmin masterDetail) {
			super(masterDetail);
		}

		@Override
		protected DetailedEditorForm createDetailEditorForm() {
			return new AgentAdminEditorForm();
		}

	}

}
