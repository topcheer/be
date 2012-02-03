package com.brightedu.client.panels;

import com.brightedu.client.panels.admin.BatchAdmin;
import com.brightedu.client.panels.admin.StudentClassesAdmin;

public class PanelData {

	public static PanelFactory getPanelFactory(String id) {
		if (id == null)
			return null;
		id = id.toLowerCase();
		if (id.equals("batch_manage")) {//批次代码维护
			return new BatchAdmin.Factory();
		}
		if(id.equals("student_class_manage")){//学生类别代码维护
			return new StudentClassesAdmin.Factory();
		}
		return null;
	}

}
