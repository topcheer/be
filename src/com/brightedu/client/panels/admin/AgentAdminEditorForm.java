package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.RecruitAgent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class AgentAdminEditorForm extends DetailedEditorForm {
	TextItem agent_nameItem = new TextItem("agent_name", "招生点名称");
	SelectItem agent_typeItem = new SelectItem("agent_type", "机构类型");
	SelectItem parentAgentItem = new SelectItem("parent_agent", "隶属于");
	TextItem responsible_personItem = new TextItem("responsible_person", "负责人");
	TextItem contact_personItem = new TextItem("contact_person", "联系人");
	TextItem contact_addressItem = new TextItem("contact_address", "联系地址");
	TextItem contact_phoneItem = new TextItem("contact_phone", "电话");
	TextItem contact_mobileItem = new TextItem("contact_mobile", "手机");
	TextItem account_nameItem = new TextItem("account_name", "账户名称");
	TextItem bank_nameItem = new TextItem("bank_name", "开户行");
	TextItem bank_accountItem = new TextItem("bank_account", "银行账号");
	TextItem college_urlItem = new TextItem("college_url", "高校链接");
	TextItem userid_for_collegeItem = new TextItem("userid_for_college",
			"学习中心在合作高校的用户名");
	TextItem password_for_collegeItem = new TextItem("password_for_college",
			"学习中心在合作高校的的密码");
	TextAreaItem remarkItem = new TextAreaItem("remark", "备注");

	public AgentAdminEditorForm(MasterDetailAdmin admin) {
		super(admin);
		init();
	}

	private void init() {
		remarkItem.setLength(2000);
		remarkItem.setRowSpan(3);
		remarkItem.setColSpan(4);
		// agent_typeItem.
		setFields(agent_nameItem, agent_typeItem, responsible_personItem,
				parentAgentItem, contact_personItem, contact_phoneItem,
				contact_mobileItem, account_nameItem, bank_nameItem,
				bank_accountItem, college_urlItem, userid_for_collegeItem,
				password_for_collegeItem, remarkItem, saveBtn);
	}

	public Serializable getModel() {
		RecruitAgent agent = new RecruitAgent();
		agent.setAccount_name(account_nameItem.getValueAsString());
		agent.setAgent_name(agent_nameItem.getValueAsString());
		agent.setAgent_type_id(getValueAsInteger(agent_typeItem));
		agent.setBank_account(bank_accountItem.getValueAsString());
		agent.setBank_name(bank_nameItem.getValueAsString());
		agent.setCollege_url(college_urlItem.getValueAsString());
		agent.setContact_address(contact_addressItem.getValueAsString());
		agent.setContact_mobile(contact_mobileItem.getValueAsString());
		agent.setContact_person(contact_personItem.getValueAsString());
		agent.setContact_phone(contact_phoneItem.getValueAsString());
		agent.setPassword_for_college(password_for_collegeItem
				.getValueAsString());

		agent.setRemark(remarkItem.getValueAsString());
		agent.setResponsible_person(responsible_personItem.getValueAsString());
		agent.setUserid_for_college(userid_for_collegeItem.getValueAsString());

		agent.setParent_agent_id(getValueAsInteger(parentAgentItem));
		
		return agent;
	}

	@Override
	public void setValue(Serializable model) {
		RecruitAgent agent = (RecruitAgent) model;
		account_nameItem.setValue(agent.getAccount_name());
		agent_nameItem.setValue(agent.getAgent_name());
		agent_typeItem.setValue(agent.getAgent_type_id());
		bank_accountItem.setValue(agent.getBank_account());
		bank_nameItem.setValue(agent.getBank_name());
		college_urlItem.setValue(agent.getCollege_url());
		college_urlItem.setValue(agent.getCollege_url());
		contact_addressItem.setValue(agent.getContact_address());
		contact_mobileItem.setValue(agent.getContact_mobile());
		contact_personItem.setValue(agent.getContact_person());
		contact_phoneItem.setValue(agent.getContact_phone());
		password_for_collegeItem.setValue(agent.getPassword_for_college());
		remarkItem.setValue(agent.getRemark());
		responsible_personItem.setValue(agent.getResponsible_person());
		userid_for_collegeItem.setValue(agent.getUserid_for_college());
		if (agent.getParent_agent_id() == null) {
			parentAgentItem.setValue("");
		} else {
			parentAgentItem.setValue(agent.getParent_agent_id());
		}
	}

	public void setValueMaps(LinkedHashMap<String, String>... valueMaps) {
		if (valueMaps.length != 2) {
			SC.say("数据结构所需数量未达到需求");
			return;
		} else {

		}
	}

	@Override
	public void reset() {
		setValue(new RecruitAgent());		
	}

}
