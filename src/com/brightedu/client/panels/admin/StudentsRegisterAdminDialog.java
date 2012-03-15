package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;

public class StudentsRegisterAdminDialog extends AdminDialog {

	private StudentsRegister masterDetail;
	StudentsRegisterDetailedPanel detailedPanel;

	public StudentsRegisterAdminDialog(StudentsRegister md) {
		this.masterDetail = md;
		detailedPanel = new StudentsRegisterDetailedPanel(masterDetail);
		adminPanel = masterDetail.getMaster();
	}

	public void init() {
		super.init();
		detailedPanel.studentTabSet.setWidth(615);
		detailedPanel.studentTabSet.setHeight(300);
		form.setNumCols(4);
		// form.getsav
		form.setWrapItemTitles(false);
		StudentsRegisterMasterPanel m = (StudentsRegisterMasterPanel) masterDetail
				.getMaster();
//		detailedPanel.infoForm.setValueMaps(m.batchValues, m.collegeValues,
//				m.sdudentClassfiedValues, m.subjectsValues, m.fundAgentValues,
//				m.managedAgentValues, m.stu_statustValues,
//				m.ethnic_groupValues, m.political_statusValues,
//				m.graduate_collegeValues, m.student_typeValues,
//				m.major_categoryValues);
		detailedPanel.infoForm.setValueMaps(m.batchValues, m.fundAgentValues,
				m.managedAgentValues, m.stu_statustValues,
				m.ethnic_groupValues, m.political_statusValues,
				m.graduate_collegeValues, m.student_typeValues,
				m.major_categoryValues);
	}

	@Override
	protected Serializable getAddedModel() {
		StudentInfo stu = detailedPanel.infoForm.getModel();
		return stu;
	}

	protected void addAdminContentUI() {
		addItem(detailedPanel);
	}

	protected void add() {
		StudentInfo stu = (StudentInfo) getAddedModel();
		List<StudentsRegisterPictureForm> picForms = detailedPanel
				.getPictureForms();
		List<StudentPicture> pictures = new ArrayList<StudentPicture>();
		for (StudentsRegisterPictureForm picForm : picForms) {
			String serverTmpFile = picForm.getServerTempFile();
			StudentPicture pic = new StudentPicture();
			pic.setPic_type_id(picForm.getPicTypeId());
			pic.setPic(serverTmpFile); // 将就下，没字段可用了
			pictures.add(pic);
		}
		BrightEdu.createDataBaseRPC().addStudent(stu, pictures, getAddAsync());
	}

	@Override
	protected DynamicForm createContentForm() {
		detailedPanel.infoForm.hideSaveItem();
		return detailedPanel.infoForm;
	}

	protected void reset() {
		detailedPanel.infoForm.reset();
		detailedPanel.cleanPictures();
	}

}
