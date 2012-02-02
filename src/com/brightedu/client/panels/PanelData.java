package com.brightedu.client.panels;

import com.brightedu.client.panels.admin.BatchAdmin;

public class PanelData {

	public static PanelFactory getPanelFactory(String id) {
		if (id == null)
			return null;
		id = id.toLowerCase();
		if (id.equals("batch_manage")) {
			return new BatchAdmin.Factory();
		}
		return null;
	}

}
