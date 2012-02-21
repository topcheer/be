package com.brightedu.client.panels.admin.students;

import com.brightedu.client.panels.BasicAdminDetailPanel;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.FunctionPanel;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.client.panels.PanelFactory;
import com.brightedu.client.panels.admin.SubjectsAdminPanel;
import com.smartgwt.client.widgets.Canvas;

/**
 * 专业代码维护
 * 
 * @author chetwang
 * 
 */
public final class StudentsRegister extends MasterDetailAdmin {

	public static String DESCRIPTION = "学生注册";

	public static class Factory implements PanelFactory {
		String id;

		public Canvas create() {
			StudentsRegister panel = new StudentsRegister();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	public class StudentAdminDetaiPanel extends BasicAdminDetailPanel {

		public StudentAdminDetaiPanel(MasterDetailAdmin masterDetail) {
			super(masterDetail);

		}

		@Override
		protected DetailedEditorForm createDetailEditorForm() {
			return new StudentsRegisterEditorForm();
		}

	}

	@Override
	public BasicAdminPanel createMasterPanel() {
		return new StudentsRegisterMasterPanel();
	}

	@Override
	public BasicAdminDetailPanel createDetailPanel() {
		return new StudentAdminDetaiPanel(this);
	}

	@Override
	protected String getMasterTitle() {
		return "学生信息汇总";
	}

	@Override
	protected String getDetialTitle() {
		return "学生详细信息";
	}
}
