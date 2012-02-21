package com.brightedu.client.panels;

import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class MasterDetailAdmin extends FunctionPanel {

	protected BasicAdminPanel master;
	protected BasicAdminDetailPanel detailed;

	public abstract BasicAdminPanel createMasterPanel();

	public abstract BasicAdminDetailPanel createDetailPanel();

	protected abstract String getMasterTitle();

	protected abstract String getDetialTitle();

	@Override
	public Canvas getViewPanel() {
		master = createMasterPanel();
		master.setHeight(300);
		detailed = createDetailPanel();
		SectionStack rightSideLayout = new SectionStack();
		rightSideLayout.setScrollSectionIntoView(true);
		rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightSideLayout.setAnimateSections(true);

		SectionStackSection masterSection = new SectionStackSection(
				getMasterTitle());// "招生点列表"
		masterSection.setItems(master);
		masterSection.setExpanded(true);

		SectionStackSection detailedSection = new SectionStackSection(
				getDetialTitle());// "详细信息"
		VLayout detailedV = new VLayout();

		detailedV.addMember(detailed);
		detailedSection.setItems(detailedV);
		detailedSection.setExpanded(true);

		rightSideLayout.setSections(masterSection, detailedSection);
		return rightSideLayout;
	}

	public BasicAdminPanel getMaster() {
		return master;
	}

	public BasicAdminDetailPanel getDetailed() {
		return detailed;
	}

}
