package com.brightedu.client.panels;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class FunctionPanel extends VLayout {

	private Canvas viewPanel;

	private Label billboard;

	public FunctionPanel() {

		setWidth100();
		setHeight100();

		boolean topIntro = isTopIntro();
		Layout layout = topIntro ? new VLayout() : new HLayout();

		layout.setWidth100();
		layout.setMargin(10);
		layout.setMembersMargin(10);

		viewPanel = getViewPanel();
		HLayout wrapper = new HLayout();
		wrapper.setWidth100();
		wrapper.addMember(viewPanel);
		String desc = getDescription();
		if (desc != null) {
			billboard = new Label(desc);
			billboard.setAutoHeight();
			billboard.setStyleName("billboard");
			addMember(billboard);
		}
		String intro = getIntro();
		if (intro != null) {
			Window introWindow = new Window();
			introWindow.setTitle("Overview");
			introWindow.setHeaderIcon("pieces/16/cube_green.png", 16, 16);
			introWindow.setKeepInParentRect(true);

			String introContents = "<p class='intro-para'>" + intro + "</p>";
			Canvas contents = new Canvas();
			contents.setCanSelectText(true);
			contents.setPadding(10);
			contents.setContents(introContents);
			if (topIntro) {
				contents.setWidth100();
			} else {
				contents.setDefaultWidth(200);
			}

			introWindow.setAutoSize(true);
			introWindow.setAutoHeight();
			introWindow.addItem(contents);

			if (topIntro) {
				layout.addMember(introWindow);
				layout.addMember(wrapper);
			} else {
				layout.addMember(wrapper);
				layout.addMember(introWindow);
			}
		} else {
			addMember(wrapper);
		}

		addMember(layout);
	}

	protected boolean isTopIntro() {
		return false;
	}

	public String getHtmlUrl() {
		return null;
	}

	public String getXmlDataUrl() {
		return null;
	}

	public String getJsonDataUrl() {
		return null;
	}

	public String getCssUrl() {
		return null;
	}

	public String getIntro() {
		return null;
	}

	public String getDescription() {
		return null;
	}

	public abstract Canvas getViewPanel();

}
