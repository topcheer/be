package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminDetailPanel;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class AnnouncementAdmin extends MasterDetailAdmin {

	public static String DESCRIPTION = "通知通告维护";

	public static class Factory implements PanelFactory {
		String id;

		public Canvas create() {
			AnnouncementAdmin panel = new AnnouncementAdmin();
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
		return new AnnouncementAdminMasterPanel(this);
	}

	@Override
	public BasicAdminDetailPanel createDetailPanel() {
		return new AnnouncementDetaiPanel(this);
	}

	@Override
	protected String getMasterTitle() {
		return "通知通告";
	}

	@Override
	protected String getDetialTitle() {
		return "详细信息";
	}

	public class AnnouncementDetaiPanel extends BasicAdminDetailPanel {

		public AnnouncementDetaiPanel(MasterDetailAdmin masterDetail) {
			super(masterDetail);
		}

		@Override
		protected DetailedEditorForm createDetailEditorForm() {
			return new AnnouncementAdminEditorForm(getMasterDetail());
		}

	}

}
