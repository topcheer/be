package com.brightedu.client.panels;

import com.brightedu.client.panels.admin.*;
import com.brightedu.client.panels.admin.students.StudentsRegister;

public class PanelData {

	// GWT client不支持反射，只好通过条件判断来生成PanelFactory

	public static PanelFactory getPanelFactory(String id) {
		if (id == null)
			return null;
		id = id.toLowerCase();
		if (id.equals("batch_manage")) {// 批次代码维护
			return new BatchAdmin.Factory();
		}
		if (id.equals("student_class_manage")) {// 学生层次代码维护
			return new StudentClassesAdmin.Factory();
		}
		if (id.equals("student_type_manage")) {// 学生类型代码维护
			return new StudentTypeAdmin.Factory();
		}
		if (id.equals("college_manage")) {
			return new CorpCollegeAdmin.Factory();// 合作高校维护
		}
		if (id.equals("subject_manage")) {
			return new SubjectsAdmin.Factory();// 合作高校维护
		}
		if (id.equals("college_agreement_manage")) { // 高校协议管理
			return new CorpCollegeAgreementAdmin.Factory();
		}
		if (id.equals("agent_type_manage")) { // 机构类型维护
			return new AgentTypeAdmin.Factory();
		}
		if (id.equals("agent_manage")) { // 机构维护
			return new AgentAdmin.Factory();
		}
		if (id.equals("fee_type_manage")) { // 费用类型维护
			return new FeeTypeAdmin.Factory();
		}
		if (id.equals("charge_type_manage")) { // 收费类型维护
			return new ChargeTypeAdmin.Factory();
		}
		if (id.equals("user_type_manage")) { // 用户类型维护
			return new UserTypeAdmin.Factory();
		}
		if (id.equals("pic_type_manage")) { // 照片类型维护
			return new PictureTypeAdmin.Factory();
		}
		if (id.equals("student_status_type_manage")) { // 学生状态类型维护
			return new StudentStatusAdmin.Factory();
		}
		if (id.equals("recurit_plan_manage")) { // 招生计划管理设置
			return new RecruitPlanManage.Factory();
		}
		if (id.equals("entrance_cost_manage")) { // 入学费用标准设置
			return new RecruitPlanManage.Factory();
		}

		if (id.equals("rights_manage")) { // 权限基础数据设置
			return new RightsManage.Factory();
		}


		// student management
		if (id.equals("student_register")) {
			return new StudentsRegister.Factory();
		}

		if (id.equals("default_rights_manage")) { //缺省权限设置
			return new DefaultRightsManage.Factory();
		}		
		if (id.equals("user_manage")) { //用户管理
			return new UserAdmin.Factory();
		}		

		return null;
	}
}
