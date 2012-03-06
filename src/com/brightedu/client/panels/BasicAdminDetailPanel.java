package com.brightedu.client.panels;

import com.smartgwt.client.widgets.layout.VLayout;

public abstract class BasicAdminDetailPanel extends VLayout {

	private MasterDetailAdmin masterDetail;

	private DetailedEditorForm detailedForm;

	public BasicAdminDetailPanel(MasterDetailAdmin masterDetail) {
		this.masterDetail = masterDetail;
		detailedForm = createDetailEditorForm();
		if (detailedForm != null) {
			addMember(detailedForm);
		}
	}

	protected abstract DetailedEditorForm createDetailEditorForm();

	public DetailedEditorForm getDetailedForm() {
		return detailedForm;
	}

	public MasterDetailAdmin getMasterDetail() {
		return masterDetail;
	}

}
