package com.brightedu.client.panels;

import com.brightedu.client.panels.admin.AgentTypeAdmin;
import com.brightedu.client.panels.admin.BatchAdmin;
import com.brightedu.client.panels.admin.ChargeTypeAdmin;
import com.brightedu.client.panels.admin.CorpCollegeAdmin;
import com.brightedu.client.panels.admin.CorpCollegeAgreementAdmin;
import com.brightedu.client.panels.admin.FeeTypeAdmin;
import com.brightedu.client.panels.admin.PictureTypeAdmin;
import com.brightedu.client.panels.admin.RecruitPlanManage;
import com.brightedu.client.panels.admin.StudentClassesAdmin;
import com.brightedu.client.panels.admin.StudentStatusAdmin;
import com.brightedu.client.panels.admin.StudentTypeAdmin;
import com.brightedu.client.panels.admin.SubjectsAdmin;
import com.brightedu.client.panels.admin.UserTypeAdmin;

public class PanelData {

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
		if (id.equals("fee_type_manage")) { // 机构类型维护
			return new FeeTypeAdmin.Factory();
		}
		if (id.equals("charge_type_manage")) { // 机构类型维护
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
		if (id.equals("recurit_plan_manage")) { // 学生状态类型维护
			return new RecruitPlanManage.Factory();
		}		
		
		return null;
	}

}
