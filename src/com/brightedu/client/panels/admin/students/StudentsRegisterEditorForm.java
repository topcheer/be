package com.brightedu.client.panels.admin.students;

import java.io.Serializable;

import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.model.edu.StudentInfo;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class StudentsRegisterEditorForm extends DetailedEditorForm {

	TextItem student_nameItem = new TextItem("student_name", "姓名");
	SelectItem identity_cardItem = new SelectItem("identity_card", "身份证");
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
	SelectItem agent_ownerItem = new SelectItem("agent_owner", "隶属招生点");
	SelectItem fund_agentItem = new SelectItem("fund_agent", "费用接收机构");
	SelectItem managed_agentItem = new SelectItem("managed_agent", "学生管理机构");
	SelectItem stu_status_idItem = new SelectItem("stu_status_id", "学生状态");

	TextItem register_dateItem = new TextItem("regster_date", "登记时间");
	TextItem update_dateItem = new TextItem("update_date", "更新时间");
	TextItem birthdayItem = new TextItem("birthday", "出生年月日");
	SelectItem ethnic_group_idItem = new SelectItem("ethnic_group_id", "民族");
	SelectItem political_status_idItem = new SelectItem("political_status_id",
			"政治面貌");
	TextItem employerItem = new TextItem("employer", "工作单位");
	SelectItem graduate_college_idItem = new SelectItem("graduate_college_id",
			"之前毕业院校");
	TextItem graduate_dateItem = new TextItem("graduate_date", "之前毕业时间");
	TextItem graduate_certificate_numberItem = new TextItem(
			"graduate_certificate_number", "之前毕业证号码");

	SelectItem student_type_idItem = new SelectItem("student_type_id", "学生类型");
	SelectItem major_category_idItem = new SelectItem("major_category_id",
			"成教学生大类");

	@Override
	public void setValue(Serializable model) {
		StudentInfo student = (StudentInfo) model;
		agent_ownerItem.setValue(student.getAgent_owner());
		batchItem.setValue(student.getBatch_owner());
		birthdayItem.setValue(student.getBirthday());
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
		register_dateItem.setValue(student.getRegister_date());
		stu_status_idItem.setValue(student.getStu_status_id());
		student_addressItem.setValue(student.getStudent_address());
		student_college_idItem.setValue(student.getStudent_college_id());
//		student.getStudent_id()
		student_linkmanItem.setValue(student.getStudent_linkman());
		student_nameItem.setValue(student.getStudent_name());
		student_phoneItem.setValue(student.getStudent_phone());
		sexItem.setValue(student.getStudent_sex());
		student_type_idItem.setValue(student.getStudent_type_id());
//		student.getSubject_owner()
	}

	@Override
	public StudentInfo getModel() {
		return null;
	}

}
