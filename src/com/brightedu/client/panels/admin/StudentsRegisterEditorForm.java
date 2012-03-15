package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.client.validator.StandardLengthValidator;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.shared.SearchCriteria;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;

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
	SelectItem batchItem = new SelectItem("batch_owner", "隶属批次");
	SelectItem collegwOwnerItem = new SelectItem("college_owner", "隶属学校");

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
	SelectItem edu_levelItem = new SelectItem("edu_level", "学历");
	TextItem college_passwordItem = new TextItem("college_password", "高校登陆密码");
	TextItem test_passwordItem = new TextItem("test_password", "考试密码");
	SelectItem care_typeItem = new SelectItem("care_type", "照顾类型");
	SelectItem edu_typeItem = new SelectItem("edu_type", "学历状态");

	// DateItem register_dateItem = new DateItem("regster_date", "登记时间");
	// DateItem update_dateItem = new DateItem("update_date", "更新时间");

	List<CollegeSubject> collegeSubjects = new ArrayList<CollegeSubject>();

	public StudentsRegisterEditorForm(MasterDetailAdmin adm) {
		super(adm);
		sexItem.setValueMap("男", "女");
		// 高中毕业 同等学历 中专技校 大专毕业 本科以上
		edu_levelItem.setValueMap("高中毕业", "同等学历", "中专技校", "大专毕业", "本科以上");
		care_typeItem.setValueMap("无加分项", "有无加分项");
		edu_typeItem.setValueMap("学历", "非学历");
		setNumCols(6);
		setLayoutAlign(VerticalAlignment.BOTTOM);
		setFields(student_nameItem, identity_cardItem, sexItem, exam_numItem,
				student_addressItem, student_phoneItem, postal_codeItem,
				student_linkmanItem, linkman_phoneItem, student_college_idItem,
				batchItem, collegwOwnerItem, classfiedItem, subject_ownerItem,
				fund_agentItem, managed_agentItem, stu_status_idItem,
				birthdayItem, ethnic_group_idItem, political_status_idItem,
				employerItem, graduate_college_idItem, graduate_dateItem,
				graduate_certificate_numberItem, student_type_idItem,
				major_category_idItem, edu_levelItem, college_passwordItem,
				test_passwordItem, care_typeItem, edu_typeItem, saveBtn);
		setValidators();
		setRequired();
		batchItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// 决定高校
				collegwOwnerItem.setValueMap("");
				classfiedItem.setValueMap("");
				subject_ownerItem.setValueMap("");
				String batchIdStr = (String) event.getValue();
				batchChanged(batchIdStr);
			}
		});
		collegwOwnerItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// 决定层次
				String collegeIdStr = (String) event.getValue();
				if (collegeIdStr != null && !collegeIdStr.trim().equals("")) {
					LinkedHashMap<String, String> classfiedMap = new LinkedHashMap<String, String>();
					for (int i = 0; i < collegeSubjects.size(); i++) {
						CollegeSubject cs = (CollegeSubject) collegeSubjects
								.get(i);
						StudentsRegisterMasterPanel master = (StudentsRegisterMasterPanel) admin
								.getMaster();
						classfiedMap.put(
								cs.getClassified_id() + "",
								master.studentClassfiedValues.get(cs
										.getClassified_id() + ""));
					}
					classfiedItem.setValueMap(classfiedMap);
				}
			}
		});
		classfiedItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// 与批次、高校、层次决定专业
				String classifiedIdStr = (String) event.getValue();
				if (classifiedIdStr != null
						&& !classifiedIdStr.toString().trim().equals("")) {
					LinkedHashMap<String, String> subjectsMap = new LinkedHashMap<String, String>();
					for (int i = 0; i < collegeSubjects.size(); i++) {
						CollegeSubject cs = (CollegeSubject) collegeSubjects
								.get(i);
						StudentsRegisterMasterPanel master = (StudentsRegisterMasterPanel) admin
								.getMaster();
						subjectsMap.put(
								cs.getSubeject_id() + "",
								master.studentClassfiedValues.get(cs
										.getSubeject_id() + ""));
					}
					subject_ownerItem.setValueMap(subjectsMap);
				}
			}
		});
		//第一次还是要模拟变换下
		batchChanged(BrightEdu.currentBatch + "");
	}

	private void batchChanged(String batchIdStr) {
		if (batchIdStr != null && !batchIdStr.toString().trim().equals("")) {
			SearchCriteria sc = new SearchCriteria("batch_id",
					(Serializable) batchIdStr);
			sc.setLike(false);
			AsyncCallback<List> cb = new CommonAsyncCall<List>() {

				@Override
				public void onSuccess(List result) {
					collegeSubjects = result;
					StudentsRegisterMasterPanel master = (StudentsRegisterMasterPanel) admin
							.getMaster();
					LinkedHashMap<String, String> collegeMap = new LinkedHashMap<String, String>();
					for (int i = 0; i < collegeSubjects.size(); i++) {
						CollegeSubject cs = (CollegeSubject) result.get(i);
						collegeMap.put(
								cs.getCollege_id() + "",
								master.collegeValues.get(cs.getCollege_id()
										+ ""));
					}
					collegwOwnerItem.setValueMap(collegeMap);
				}
			};
			BrightEdu.createDataBaseRPC().getModels("CollegeSubject",
					new SearchCriteria[] { sc }, -1, -1, false, cb);
		}
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
		edu_levelItem.setValue(student.getEdu_level());
		college_passwordItem.setValue(student.getCollege_passwd());
		test_passwordItem.setValue(student.getExam_passwd());
		care_typeItem.setValue(student.getCare_type());
		edu_typeItem.setValue(student.getEdu_type());
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
		s.setEdu_level(edu_levelItem.getValueAsString());
		s.setCollege_passwd(college_passwordItem.getValueAsString());
		s.setExam_passwd(test_passwordItem.getValueAsString());
		s.setCare_type(care_typeItem.getValueAsString());
		s.setEdu_type(edu_typeItem.getValueAsString());
		return s;
	}

	public void setValueMaps(LinkedHashMap<String, String>... valueMaps) {
		if (valueMaps.length != 9) {
			SC.say("数据结构所需数量未达到需求");
			return;
		} else {
			int i = 0;
			batchItem.setValueMap(valueMaps[i++]);
			// collegwOwnerItem.setValueMap(valueMaps[i++]);
			// classfiedItem.setValueMap(valueMaps[i++]);
			// subject_ownerItem.setValueMap(valueMaps[i++]);
			fund_agentItem.setValueMap(valueMaps[i++]);
			managed_agentItem.setValueMap(valueMaps[i++]);
			stu_status_idItem.setValueMap(valueMaps[i++]);
			ethnic_group_idItem.setValueMap(valueMaps[i++]);
			political_status_idItem.setValueMap(valueMaps[i++]);
			graduate_college_idItem.setValueMap(valueMaps[i++]);
			student_type_idItem.setValueMap(valueMaps[i++]);
			major_category_idItem.setValueMap(valueMaps[i++]);
			// default:
			setDefault();
		}
	}

	private void setDefault() {
		batchItem.setDefaultValue(BrightEdu.currentBatch);
		ethnic_group_idItem.setDefaultValue(1);
		political_status_idItem.setDefaultValue(3);
	}

	@Override
	public void reset() {
		setValue(new StudentInfo());
		setDefault();
	}

	private void setValidators() {
		LengthRangeValidator standardLenthValidator = new StandardLengthValidator();
		String errorMsg = "身份证长度必须是15或18位";
		CustomValidator idCardLenthValidator = new CustomValidator() {

			@Override
			protected boolean condition(Object value) {
				String id = (String) value;
				if (value != null && id.length() != 15 && id.length() != 18)
					return false;
				return true;
			}
		};
		idCardLenthValidator.setErrorMessage(errorMsg);
		identity_cardItem.setValidators(idCardLenthValidator);
		TextItem[] standardLengthItems = new TextItem[] { employerItem,
				exam_numItem, graduate_certificate_numberItem,
				linkman_phoneItem, postal_codeItem, student_addressItem,
				student_college_idItem, student_linkmanItem, student_nameItem,
				student_phoneItem };
		for (TextItem item : standardLengthItems) {
			item.setValidators(standardLenthValidator);
		}

	}

	private void setRequired() {
		FormItem[] requiredItems = new FormItem[] { batchItem, birthdayItem,
				classfiedItem, collegwOwnerItem, ethnic_group_idItem,
				fund_agentItem, graduate_certificate_numberItem,
				identity_cardItem, linkman_phoneItem, managed_agentItem,
				political_status_idItem, sexItem, stu_status_idItem,
				student_addressItem, student_linkmanItem, student_nameItem,
				student_phoneItem, student_type_idItem, subject_ownerItem };
		for (FormItem item : requiredItems) {
			item.setRequired(true);
		}
	}
}
