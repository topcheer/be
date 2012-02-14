package com.brightedu.client.panels.admin;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class AgentAdminDetaiPanel extends VLayout {

	AgentAdminEditorForm detailedForm = new AgentAdminEditorForm();

	AgentAdmin admin;

	public AgentAdminDetaiPanel(AgentAdmin agentAdmin) {
		this.admin = agentAdmin;
		addMember(detailedForm);
		detailedForm.getSaveBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Record rec = admin.master.getResultList().getSelectedRecord();
				admin.master.update(rec);
			}
		});
		
		detailedForm.getSaveBtn().disable();
	}

	public AgentAdminEditorForm getDetailedForm() {
		return detailedForm;
	}

}
