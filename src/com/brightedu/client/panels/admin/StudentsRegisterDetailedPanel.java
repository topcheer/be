package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminDetailPanel;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.model.edu.PictureType;
import com.google.gwt.user.client.History;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class StudentsRegisterDetailedPanel extends BasicAdminDetailPanel {
	TabSet mainTabSet;
	// StudentsRegisterPictureForm picForm;
	StudentsRegisterEditorForm infoForm;

	LinkedHashMap<String, String> pic_typeValues;
	HLayout picPanel = new HLayout();
	List<StudentsRegisterPictureForm> picForms = new ArrayList<StudentsRegisterPictureForm>();

	public StudentsRegisterDetailedPanel(StudentsRegister reg) {
		super(reg);
		mainTabSet = new TabSet();
		Layout paneContainerProperties = new Layout();
		paneContainerProperties.setLayoutMargin(0);
		paneContainerProperties.setLayoutTopMargin(1);
		mainTabSet.setPaneContainerProperties(paneContainerProperties);

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

		createPicPanel();
		infoForm = new StudentsRegisterEditorForm(reg);
		// infoForm.setWidth100();
		// infoForm.setHeight100();
		picTabPanel.addMember(picPanel);
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

	private void createPicPanel() {
		BrightEdu.createDataBaseRPC().getNameValuePareList(
				new String[] { "PictureType" }, new CommonAsyncCall<List>() {

					@Override
					public void onSuccess(List result) {
						List<PictureType> types = (List<PictureType>) result
								.get(0);
						for (PictureType type : types) {
							VLayout layout = new VLayout();
							String typeName = type.getPic_type_name();
							StudentsRegisterPictureForm picForm = new StudentsRegisterPictureForm(
									getMasterDetail(), typeName, type
											.getPic_type_id(), typeName);
							layout.addMember(picForm);
							Img img = new Img();
							img.setPadding(10);
							img.setBorder("1px solid gray");
							img.setWidth(200);
							img.setHeight(200);
							layout.addMember(img);
							layout.addMember(picForm.getBrightFrame());
							picForm.setViewImg(img);
							picForms.add(picForm);
							picPanel.addMember(layout);
						}
						picPanel.redraw();
					}
				});
	}

	@Override
	public DetailedEditorForm createDetailEditorForm() {
		// this is not used here
		return null;
	}

	public List<StudentsRegisterPictureForm> getPictureForms() {
		return picForms;
	}

	public StudentsRegisterEditorForm getInfoForm() {
		return infoForm;
	}
}
