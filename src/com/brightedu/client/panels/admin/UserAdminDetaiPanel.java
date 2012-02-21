package com.brightedu.client.panels.admin;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserAdminDetaiPanel extends VLayout {

	UserAdminEditorForm detailedForm = new UserAdminEditorForm();
//	UserRightsOverridePanel rights = new UserRightsOverridePanel();
	UserAdmin admin;
	
	public UserAdminDetaiPanel(UserAdmin agentAdmin) {
		this.admin = agentAdmin;
		
//		HLayout r = new HLayout();
//		r.addMember(detailedForm);
//		r.addMember(rights);
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

	public UserAdminEditorForm getDetailedForm() {
		return detailedForm;
	}

}
