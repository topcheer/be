package com.brightedu.client.panels;

import com.brightedu.client.panels.admin.AgentTypeAdmin;
import com.brightedu.client.panels.admin.BatchAdmin;
import com.brightedu.client.panels.admin.CorpCollegeAdmin;
import com.brightedu.client.panels.admin.CorpCollegeAgreementAdmin;
import com.brightedu.client.panels.admin.StudentClassesAdmin;
import com.brightedu.client.panels.admin.StudentTypeAdmin;
import com.brightedu.client.panels.admin.SubjectsAdmin;

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
		return null;
	}

}
