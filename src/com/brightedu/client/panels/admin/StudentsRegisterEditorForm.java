package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import sun.security.action.GetIntegerAction;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class StudentsRegisterEditorForm extends DetailedEditorForm {

	TextItem student_nameItem = new TextItem("student_name", "姓名");
	TextItem identity_cardItem = new TextItem("identity_card", "身份证");
	SelectItem sexItem = new SelectItem("student_sex", "性别");
	TextItem exam_numItem = new TextItem("exam_num", "准考证");
	TextItem student_addressItem = new TextItem("student_address", "地址");
	TextItem student_phoneItem = new TextItem("student_phone", "电话");
	TextItem postal_codeItem = new TextItem("postal_code", "邮编");

	TextItem student_linkmanItem = new TextItem("student_linkman", "联系人");
	TextItem linkman_phoneItem = new TextItem("linkman_phone", "联系人电话");
	TextItem student_college_idItem = new TextItem("student_college_id", "学号");
	SelectItem collegwOwnerItem = new SelectItem("college_owner", "隶属学校");
	SelectItem batchItem = new SelectItem("batch_owner", "隶属批次");
	SelectItem classfiedItem = new SelectItem("classified_owner", "隶属层次");
	SelectItem subject_ownerItem = new SelectItem("subject_owner", "隶属专业");
	// SelectItem agent_ownerItem = new SelectItem("agent_owner", "隶属招生点");
	SelectItem fund_agentItem = new SelectItem("fund_agent", "费用接收机构");
	SelectItem managed_agentItem = new SelectItem("managed_agent", "学生管理机构");
	SelectItem stu_status_idItem = new SelectItem("stu_status_id", "学生状态");

	DateItem birthdayItem = new DateItem("birthday", "出生年月日");
	SelectItem ethnic_group_idItem = new SelectItem("ethnic_group_id", "民族");
	SelectItem political_status_idItem = new SelectItem("political_status_id",
			"政治面貌");
	TextItem employerItem = new TextItem("employer", "工作单位");
	SelectItem graduate_college_idItem = new SelectItem("graduate_college_id",
			"之前毕业院校");
	DateItem graduate_dateItem = new DateItem("graduate_date", "之前毕业时间");
	TextItem graduate_certificate_numberItem = new TextItem(
			"graduate_certificate_number", "之前毕业证号码");

	SelectItem student_type_idItem = new SelectItem("student_type_id", "学生类型");
	SelectItem major_category_idItem = new SelectItem("major_category_id",
			"成教学生大类");

	// DateItem register_dateItem = new DateItem("regster_date", "登记时间");
	// DateItem update_dateItem = new DateItem("update_date", "更新时间");

	public StudentsRegisterEditorForm(MasterDetailAdmin admin) {
		super(admin);
		sexItem.setValueMap("男", "女");

		setFields(student_nameItem, identity_cardItem, sexItem, exam_numItem,
				student_addressItem, student_phoneItem, postal_codeItem,
				student_linkmanItem, linkman_phoneItem, student_college_idItem,
				collegwOwnerItem, batchItem, classfiedItem, subject_ownerItem,
				fund_agentItem, managed_agentItem, stu_status_idItem,
				birthdayItem, ethnic_group_idItem, political_status_idItem,
				employerItem, graduate_college_idItem, graduate_dateItem,
				graduate_certificate_numberItem, student_type_idItem,
				major_category_idItem, saveBtn);
	}

	@Override
	public void setValue(Serializable model) {
		StudentInfo student = (StudentInfo) model;

		batchItem.setValue(student.getBatch_owner());
		if (student.getBirthday() != null) {
			long birthTime = Long.parseLong(student.getBirthday());
			birthdayItem.setValue(new Date(birthTime));
		} else {
			birthdayItem.setValue(new Date());
		}
		classfiedItem.setValue(student.getClassified_owner());
		collegwOwnerItem.setValue(student.getCollege_owner());
		employerItem.setValue(student.getEmployer());
		ethnic_group_idItem.setValue(student.getEthnic_group_id());
		exam_numItem.setValue(student.getExam_num());
		fund_agentItem.setValue(student.getFund_agent());
		graduate_certificate_numberItem.setValue(student
				.getGraduate_certificate_number());
		graduate_college_idItem.setValue(student.getGraduate_college_id());
		graduate_dateItem.setValue(student.getGraduate_date());
		identity_cardItem.setValue(student.getIdentity_card());
		linkman_phoneItem.setValue(student.getLinkman_phone());
		major_category_idItem.setValue(student.getMajor_category_id());
		managed_agentItem.setValue(student.getManaged_agent());
		political_status_idItem.setValue(student.getPolitical_status_id());
		postal_codeItem.setValue(student.getPostal_code());
		// register_dateItem.setValue(student.getRegister_date());
		stu_status_idItem.setValue(student.getStu_status_id());
		student_addressItem.setValue(student.getStudent_address());
		student_college_idItem.setValue(student.getStudent_college_id());
		// student.getStudent_id()
		student_linkmanItem.setValue(student.getStudent_linkman());
		student_nameItem.setValue(student.getStudent_name());
		student_phoneItem.setValue(student.getStudent_phone());
		sexItem.setValue(student.getStudent_sex());
		student_type_idItem.setValue(student.getStudent_type_id());
		subject_ownerItem.setValue(student.getSubject_owner());
		// update_dateItem.setValue(student.getUpdate_date());
	}

	@Override
	public StudentInfo getModel() {
		StudentInfo s = new StudentInfo();
		s.setAgent_owner(BrightEdu.getUser().getAgent_id());
		// s.setAgent_owner(getValueAsInteger(agent_ownerItem));
		s.setBatch_owner(getValueAsInteger(batchItem));
		Date birthday = (Date) birthdayItem.getValue();
		s.setBirthday(String.valueOf(birthday.getTime()));
		s.setClassified_owner(getValueAsInteger(classfiedItem));
		s.setCollege_owner(getValueAsInteger(collegwOwnerItem));
		s.setEmployer(employerItem.getValueAsString());
		s.setEthnic_group_id(getValueAsInteger(ethnic_group_idItem));
		s.setExam_num(exam_numItem.getValueAsString());
		s.setFund_agent(getValueAsInteger(fund_agentItem));
		s.setGraduate_certificate_number(graduate_certificate_numberItem
				.getValueAsString());
		s.setGraduate_college_id(getValueAsInteger(graduate_college_idItem));
		s.setGraduate_date((Date) graduate_dateItem.getValue());
		s.setIdentity_card(identity_cardItem.getValueAsString());
		s.setLinkman_phone(linkman_phoneItem.getValueAsString());
		s.setMajor_category_id(getValueAsInteger(major_category_idItem));
		s.setManaged_agent(getValueAsInteger(managed_agentItem));
		s.setPolitical_status_id(getValueAsInteger(political_status_idItem));
		s.setPostal_code(postal_codeItem.getValueAsString());
		// s.setRegister_date(register_date)
		s.setStu_status_id(getValueAsInteger(stu_status_idItem));
		s.setStudent_address(student_addressItem.getValueAsString());
		s.setStudent_college_id(student_college_idItem.getValueAsString());
		// s.setStudent_id(student_id)
		s.setStudent_linkman(student_linkmanItem.getValueAsString());
		s.setStudent_name(student_nameItem.getValueAsString());
		s.setStudent_phone(student_phoneItem.getValueAsString());
		s.setStudent_sex(sexItem.getValueAsString());
		s.setStudent_type_id(getValueAsInteger(student_type_idItem));
		s.setSubject_owner(getValueAsInteger(subject_ownerItem));
		// s.setUpdate_date(update_date)

		return s;
	}

	public void setValueMaps(LinkedHashMap<String, String>... valueMaps) {
		if (valueMaps.length != 12) {
			SC.say("数据结构所需数量未达到需求");
			return;
		} else {
			int i = 0;
			batchItem.setValueMap(valueMaps[i++]);
			collegwOwnerItem.setValueMap(valueMaps[i++]);
			classfiedItem.setValueMap(valueMaps[i++]);
			subject_ownerItem.setValueMap(valueMaps[i++]);
			fund_agentItem.setValueMap(valueMaps[i++]);
			managed_agentItem.setValueMap(valueMaps[i++]);
			stu_status_idItem.setValueMap(valueMaps[i++]);
			ethnic_group_idItem.setValueMap(valueMaps[i++]);
			political_status_idItem.setValueMap(valueMaps[i++]);
			graduate_college_idItem.setValueMap(valueMaps[i++]);
			student_type_idItem.setValueMap(valueMaps[i++]);
			major_category_idItem.setValueMap(valueMaps[i++]);
		}
	}

	@Override
	public void reset() {
		setValue(new StudentInfo());
	}
}
