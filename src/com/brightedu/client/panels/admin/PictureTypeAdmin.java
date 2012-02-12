package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

public final class PictureTypeAdmin extends FunctionPanel {

	public static String DESCRIPTION = "照片类型维护";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			PictureTypeAdmin panel = new PictureTypeAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BasicAdminPanel p = new PictureTypeAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
