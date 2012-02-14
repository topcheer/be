package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public final class AgentAdmin extends FunctionPanel {

	public static String DESCRIPTION = "机构维护";
	AgentAdminMasterPanel master;
	AgentAdminDetaiPanel detailed;

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
	public Canvas getViewPanel() {
		master = new AgentAdminMasterPanel(this);
		master.setHeight(300);
		detailed = new AgentAdminDetaiPanel(this);
		SectionStack rightSideLayout = new SectionStack();
		rightSideLayout.setScrollSectionIntoView(true);
		rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightSideLayout.setAnimateSections(true);

		SectionStackSection masterSection = new SectionStackSection("招生点列表");
		masterSection.setItems(master);
		masterSection.setExpanded(true);

		SectionStackSection detailedSection = new SectionStackSection("详细信息");
		VLayout detailedV = new VLayout();

		detailedV.addMember(detailed);
		detailedSection.setItems(detailedV);
		detailedSection.setExpanded(true);

		rightSideLayout.setSections(masterSection, detailedSection);
		return rightSideLayout;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
