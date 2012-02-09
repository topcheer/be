package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.widgets.Canvas;

/**
 * 专业代码维护
 * @author chetwang
 *
 */
public final class SubjectsAdmin extends FunctionPanel {

	public static String DESCRIPTION = "专业代码维护";



	public static class Factory implements PanelFactory {
		String id;
		public Canvas create() {
			SubjectsAdmin panel = new SubjectsAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		BasicAdminPanel p = new SubjectsAdminPanel();
		return p;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
