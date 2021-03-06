package com.brightedu.client.panels;

import java.io.Serializable;

import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class MasterDetailAdmin extends FunctionPanel {

	protected BasicAdminPanel master;
	protected BasicAdminDetailPanel detailed;
	protected VLayout detailedV;
	public abstract BasicAdminPanel createMasterPanel();

	public abstract BasicAdminDetailPanel createDetailPanel();

	protected abstract String getMasterTitle();

	protected abstract String getDetialTitle();

	@Override
	public Canvas getViewPanel() {
		master = createMasterPanel();
		master.setHeight(300);
		initSelectionChange();
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
	
		detailedV = new VLayout();
		detailedV.addMember(detailed);
		detailedSection.setItems(detailedV);
		detailedSection.setExpanded(true);
		rightSideLayout.setSections(masterSection, detailedSection);
		return rightSideLayout;
	}

	protected void initSelectionChange() {
		master.resultList
				.addSelectionChangedHandler(new SelectionChangedHandler() {

					@Override
					public void onSelectionChanged(SelectionEvent event) {
						if (event.getState()) {
							getDetailed().getDetailedForm().setValue(
									(Serializable) event.getRecord()
											.getAttributeAsObject("object"));
							getDetailed().getDetailedForm().enableSaveItem();
						} else {
							getDetailed().getDetailedForm().reset();
							getDetailed().getDetailedForm().disableSaveItem();
						}
					}
				});
	}

	public BasicAdminPanel getMaster() {
		return master;
	}

	public BasicAdminDetailPanel getDetailed() {
		return detailed;
	}

}
