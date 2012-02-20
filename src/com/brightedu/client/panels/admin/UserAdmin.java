package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.PanelFactory;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public final class UserAdmin extends FunctionPanel {

	public static String DESCRIPTION = "用户管理";
	UserAdminMasterPanel master;
	UserAdminDetaiPanel detailed;
	UserRightsOverridePanel rights;
	
	SectionStack rightSideLayout ;
	SectionStackSection masterSection ;
	SectionStackSection detailedSection;
	SectionStackSection rightsSection ;
	
	public static class Factory implements PanelFactory {
		String id;

		public Canvas create() {
			UserAdmin panel = new UserAdmin();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public Canvas getViewPanel() {
		master = new UserAdminMasterPanel(this);
		master.setHeight(300);
		detailed = new UserAdminDetaiPanel(this);
		rights = new UserRightsOverridePanel(this);
		
		rightSideLayout = new SectionStack();
		masterSection = new SectionStackSection("用户列表");
		detailedSection = new SectionStackSection("详细信息");
		rightsSection = new SectionStackSection("权限");
		
		rightSideLayout.setScrollSectionIntoView(true);
		rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
		rightSideLayout.setAnimateSections(true);

		
		masterSection.setItems(master);
		masterSection.setExpanded(true);

		
		VLayout detailedV = new VLayout();

		detailedV.addMember(detailed);
		detailedSection.setItems(detailedV);
		detailedSection.setExpanded(true);

		
		VLayout rightsV = new VLayout();
		rightsV.addMember(rights);
		rightsSection.addItem(rightsV);
		rightsSection.setExpanded(false);
		
		
		rightSideLayout.setSections(masterSection, detailedSection,rightsSection);
		return rightSideLayout;
	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

}
