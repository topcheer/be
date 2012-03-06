package com.brightedu.client.panels.admin;

import com.brightedu.client.panels.BasicAdminDetailPanel;
import com.brightedu.client.panels.DetailedEditorForm;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class StudentsRegisterDetailedPanel extends BasicAdminDetailPanel {
	TabSet mainTabSet;
	StudentsRegister reg;
	StudentsRegisterPictureForm picForm;
	StudentsRegisterEditorForm infoForm;

	public StudentsRegisterDetailedPanel(StudentsRegister reg) {
		super(reg);
		mainTabSet = new TabSet();
		Layout paneContainerProperties = new Layout();
		paneContainerProperties.setLayoutMargin(0);
		paneContainerProperties.setLayoutTopMargin(1);
		mainTabSet.setPaneContainerProperties(paneContainerProperties);

		mainTabSet.setWidth100();
		mainTabSet.setHeight100();
		mainTabSet.addTabSelectedHandler(new TabSelectedHandler() {
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = event.getTab();
				String historyToken = selectedTab.getAttribute("historyToken");
				if (historyToken != null) {
					History.newItem(historyToken, false);
				} else {
					History.newItem("main", false);
				}
			}
		});
		Tab studentInfoTab = new Tab();

		studentInfoTab.setPrompt("学生信息");
		studentInfoTab.setAttribute("height", "50");
		studentInfoTab.setIcon("student_info.gif");
		// studentInfoTab.setWidth(80);

		HLayout studentTabPanel = new HLayout();
		studentTabPanel.setHeight100();
		studentTabPanel.setWidth100();
		studentInfoTab.setPane(studentTabPanel);

		Tab picTab = new Tab();
		// picTab.setTitle("照片信息");
		picTab.setPrompt("照片信息");
		picTab.setIcon("student_pic.png");
		picTab.setAttribute("height", "50");
		// picTab.setWidth(80);
		HLayout picTabPanel = new HLayout();
		picTabPanel.setHeight100();
		picTabPanel.setWidth100();
		picTab.setPane(picTabPanel);

		picForm = new StudentsRegisterPictureForm(reg);
		picForm.setWidth100();
		picForm.setHeight100();
		infoForm = new StudentsRegisterEditorForm(reg);
		infoForm.setWidth100();
		infoForm.setHeight100();
		picTabPanel.addMember(picForm);
		studentTabPanel.addMember(infoForm);
		mainTabSet.addTab(studentInfoTab);
		mainTabSet.addTab(picTab);
		mainTabSet.setWidth100();
		mainTabSet.setHeight100();
		mainTabSet.setTabBarPosition(Side.LEFT);
		addMember(mainTabSet);
		setWidth100();
		setHeight100();
	}

	@Override
	public DetailedEditorForm createDetailEditorForm() {
		// this is not used here
		return null;
	}

	public StudentsRegisterPictureForm getPictureForm() {
		return picForm;
	}

	public StudentsRegisterEditorForm getInfoForm() {
		return infoForm;
	}
}
