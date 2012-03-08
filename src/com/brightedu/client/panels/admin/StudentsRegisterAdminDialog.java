package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.smartgwt.client.widgets.form.DynamicForm;

public class StudentsRegisterAdminDialog extends AdminDialog {

	private StudentsRegister masterDetail;
	StudentsRegisterDetailedPanel detailedPanel;

	public StudentsRegisterAdminDialog(StudentsRegister md) {
		this.masterDetail = md;
		detailedPanel = new StudentsRegisterDetailedPanel(masterDetail);
	}

	public void init() {
		super.init();
		detailedPanel.mainTabSet.setWidth(615);
		detailedPanel.mainTabSet.setHeight(300);
		form.setNumCols(4);
		// form.getsav
		form.setWrapItemTitles(false);
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
			if (serverTmpFile != null) {
				StudentPicture pic = new StudentPicture();
				pic.setPic_type_id(picForm.getPicTypeId());
				// pic.setStudent_id(stu)
				pic.setRemark(serverTmpFile); // 将就下，没字段可用了
				pictures.add(pic);
			}
		}
		Serializable[] models = new Serializable[1 + pictures.size()];
		models[0] = stu;
		for (int i = 0; i < pictures.size(); i++) {
			models[i + 1] = pictures.get(i);
		}
//		BrightEdu.createDataBaseRPC().modelAction(models, "insertSelective",
//				getAddAsync());
		BrightEdu.createDataBaseRPC().addStudent(stu,pictures,getAddAsync());
	}

	@Override
	protected DynamicForm createContentForm() {
		detailedPanel.infoForm.hideSaveItem();
		return detailedPanel.infoForm;
	}

}
