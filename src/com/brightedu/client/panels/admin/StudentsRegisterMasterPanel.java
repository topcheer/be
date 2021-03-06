package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.EthnicGroup;
import com.brightedu.model.edu.MajorCategory;
import com.brightedu.model.edu.PoliticalStatus;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.School;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentsRegisterMasterPanel extends BasicAdminPanel {

	ListGridField[] fileds;

	LinkedHashMap<String, String> batchValues;
	LinkedHashMap<String, String> collegeValues;
	LinkedHashMap<String, String> studentClassfiedValues;
	LinkedHashMap<String, String> subjectsValues;
	/************* 这两个都来自recruit_agent **************************************/
	LinkedHashMap<String, String> fundAgentValues;
	LinkedHashMap<String, String> managedAgentValues;
	/***************************************************/
	LinkedHashMap<String, String> stu_statustValues;
	LinkedHashMap<String, String> ethnic_groupValues;
	LinkedHashMap<String, String> political_statusValues;
	LinkedHashMap<String, String> graduate_collegeValues;
	LinkedHashMap<String, String> student_typeValues;
	LinkedHashMap<String, String> major_categoryValues;

	String[] nameValuePareModels;

	MasterDetailAdmin msterDetail;

	public StudentsRegisterMasterPanel(MasterDetailAdmin msterDetail) {
		this.msterDetail = msterDetail;
		nameValuePareModels = new String[] { "BatchIndex", "College",
				"StudentClassified", "Subjects", "RecruitAgent",
				"StudentStatus", "EthnicGroup", "PoliticalStatus", "School",
				"StudentType", "MajorCategory", "PictureType" };
		initValueMaps();
	}

	private void initValueMaps() {
		batchValues = new LinkedHashMap<String, String>();
		collegeValues = new LinkedHashMap<String, String>();
		studentClassfiedValues = new LinkedHashMap<String, String>();
		subjectsValues = new LinkedHashMap<String, String>();
		fundAgentValues = new LinkedHashMap<String, String>();
		managedAgentValues = new LinkedHashMap<String, String>();
		stu_statustValues = new LinkedHashMap<String, String>();
		ethnic_groupValues = new LinkedHashMap<String, String>();
		political_statusValues = new LinkedHashMap<String, String>();
		graduate_collegeValues = new LinkedHashMap<String, String>();
		student_typeValues = new LinkedHashMap<String, String>();
		major_categoryValues = new LinkedHashMap<String, String>();
		dbService.getNameValuePareList(nameValuePareModels,
				new CommonAsyncCall<List>() {
					@Override
					public void onSuccess(List result) {
						parseValueMaps(result);
					}
				});
	}

	private void parseValueMaps(List totalNameValuePares) {
		StudentsRegisterDetailedPanel detailedPanel = (StudentsRegisterDetailedPanel) msterDetail
				.getDetailed();
		StudentsRegisterEditorForm detailedForm = (StudentsRegisterEditorForm) detailedPanel
				.getInfoForm();
		for (int i = 0; i < totalNameValuePares.size(); i++) {
			List nameValuePare = (List) totalNameValuePares.get(i);
			switch (i) {
			case 0:
				for (int x = 0; x < nameValuePare.size(); x++) {
					BatchIndex bi = (BatchIndex) nameValuePare.get(x);
					batchValues.put(bi.getBatch_id() + "", bi.getBatch_name());
				}

				break;
			case 1:
				for (int x = 0; x < nameValuePare.size(); x++) {
					College c = (College) nameValuePare.get(x);
					collegeValues.put(c.getCollege_id() + "",
							c.getCollege_name());
				}
				break;
			case 2:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentClassified c = (StudentClassified) nameValuePare
							.get(x);
					studentClassfiedValues.put(c.getClassified_id() + "",
							c.getClassified_name());
				}
				break;
			case 3:
				for (int x = 0; x < nameValuePare.size(); x++) {
					Subjects c = (Subjects) nameValuePare.get(x);
					subjectsValues.put(c.getSubject_id() + "",
							c.getSubject_name());
				}
				break;
			case 4:
				for (int x = 0; x < nameValuePare.size(); x++) {
					RecruitAgent agent = (RecruitAgent) nameValuePare.get(x);

					fundAgentValues.put(agent.getAgent_id() + "",
							agent.getAgent_name());
					managedAgentValues.put(agent.getAgent_id() + "",
							agent.getAgent_name());
				}
				break;
			case 5:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentStatus c = (StudentStatus) nameValuePare.get(x);
					stu_statustValues.put(c.getStu_status_id() + "",
							c.getStu_status_name());
				}
				break;
			case 6:
				for (int x = 0; x < nameValuePare.size(); x++) {
					EthnicGroup c = (EthnicGroup) nameValuePare.get(x);
					ethnic_groupValues.put(c.getEthnic_group_id() + "",
							c.getEthnic_group_name());
				}
				break;
			case 7:
				for (int x = 0; x < nameValuePare.size(); x++) {
					PoliticalStatus c = (PoliticalStatus) nameValuePare.get(x);
					political_statusValues.put(c.getPol_id() + "",
							c.getPol_name());
				}
				break;
			case 8:
				for (int x = 0; x < nameValuePare.size(); x++) {
					School c = (School) nameValuePare.get(x);
					graduate_collegeValues.put(c.getSchool_code(),
							c.getSchool_name());
				}
				break;
			case 9:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentType c = (StudentType) nameValuePare.get(x);
					student_typeValues.put(c.getStudent_type_id() + "",
							c.getStudent_type_name());
				}
				break;
			case 10:
				for (int x = 0; x < nameValuePare.size(); x++) {
					MajorCategory c = (MajorCategory) nameValuePare.get(x);
					major_categoryValues.put(c.getStudent_major_category_id()
							+ "", c.getStudent_major_category_name());
				}
				break;
			default:
				break;
			}
		}
		detailedForm.setValueMaps(batchValues, fundAgentValues,
				managedAgentValues, stu_statustValues, ethnic_groupValues,
				political_statusValues, graduate_collegeValues,
				student_typeValues, major_categoryValues);
		if (getAdminDialog() != null) {
			StudentsRegisterEditorForm adminDialogEditorForm = (StudentsRegisterEditorForm) getAdminDialog()
					.getContentForm();
			adminDialogEditorForm.setValueMaps(batchValues,fundAgentValues,
					managedAgentValues, stu_statustValues, ethnic_groupValues,
					political_statusValues, graduate_collegeValues,
					student_typeValues, major_categoryValues);
		}
		fileds[3].setValueMap(managedAgentValues);
	}

	public void refresh() {
		this.initValueMaps();
		super.refresh();
	}

	@Override
	protected void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List> callback = new CommonAsyncCall<List>() {
			@Override
			public void onSuccess(List result) {
				int size = result.size();
				Record[] listData = init ? new Record[size - 1]
						: new Record[size];
				for (int i = 0; i < size; i++) {
					if (i == size - 1) {
						if (init) {
							int counts = (Integer) result.get(size - 1);
							setTotalCounts(counts);
							break;
						}
					}
					StudentInfo bi = (StudentInfo) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getStudent_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getStudent_name());
					rec.setAttribute("identity_card", bi.getIdentity_card());
					rec.setAttribute("student_college_id",
							bi.getStudent_college_id());
					rec.setAttribute("managed_agent", bi.getManaged_agent());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getStudentList(searchCriteria, (indexGoto - 1)
				* currentRowsInOnePage, currentRowsInOnePage, init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		fileds = parseGridFields(new String[] { "obj_name", "identity_card",
				"student_college_id", "managed_agent" }, new String[] { "学生",
				"身份证", "学号", "学生管理机构" }, new ListGridFieldType[] {
				ListGridFieldType.TEXT, ListGridFieldType.TEXT,
				ListGridFieldType.TEXT, ListGridFieldType.TEXT },
				new boolean[] { false, false, false, false }, new int[] { 100,
						-1, -1, 100 });

		return fileds;
	}

	@Override
	public void deleteRecords(List<Integer> students) {
		// implemented in del();
	}

	protected void del() {
		RecordList recList = resultList.getDataAsRecordList();
		final List<StudentInfo> students = new ArrayList<StudentInfo>();
		for (int i = 0; i < recList.getLength(); i++) {
			if (recList.get(i).getAttributeAsBoolean("select")) {
				students.add((StudentInfo) recList.get(i).getAttributeAsObject(
						"object"));
			}
		}
		if (students.size() == 0) {
			SC.say("请选择一些记录");
			return;
		}
		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value) {
					dbService.deleteStudent(students, delAsync);
				}
			}
		});
	}

	@Override
	public void update(Record record) {
		final StudentsRegisterDetailedPanel detailed = (StudentsRegisterDetailedPanel) msterDetail
				.getDetailed();
		final StudentInfo newStu = (StudentInfo) detailed.getInfoForm()
				.getModel();
		final Record rec = resultList.getSelectedRecord();
		final StudentInfo oldStu = (StudentInfo) rec
				.getAttributeAsObject("object");
		newStu.setStudent_id(oldStu.getStudent_id());
		newStu.setRegister_date(oldStu.getRegister_date());
		newStu.setUpload_flag(oldStu.getUpload_flag());
		List<StudentPicture> pictures = new ArrayList<StudentPicture>();
		for (StudentsRegisterPictureForm form : detailed.picForms) {
			StudentPicture pic = form.getPicture();

			if (form.getBrightFrame().isLoaded()) {

				pic.setPic(form.getServerTempFile());
				pictures.add(pic);
			}
		}
		dbService.saveStudent(newStu, pictures, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
				rec.setAttribute("object", newStu);
				rec.setAttribute("obj_name", newStu.getStudent_name());
				rec.setAttribute("identity_card", newStu.getIdentity_card());
				rec.setAttribute("student_college_id",
						newStu.getStudent_college_id());
				detailed.refreshPicture(newStu);
				resultList.redraw();
			}

			protected void failed() { // rollback in UI
				// List UI would not be changed here
			}
		});

		// dbService.saveModel(newStu, new CommonAsyncCall<Boolean>() {
		// @Override
		// public void onSuccess(Boolean result) {
		// BrightEdu.showTip("已保存!");
		// rec.setAttribute("object", newStu);
		// rec.setAttribute("obj_name", newStu.getStudent_name());
		// rec.setAttribute("identity_card", newStu.getIdentity_card());
		// rec.setAttribute("student_college_id",
		// newStu.getStudent_college_id());
		// resultList.redraw();
		// }
		//
		// protected void failed() { // rollback in UI
		// // List UI would not be changed here
		// }
		// });
	}

	@Override
	public void add(Serializable model) {
		// not used here, implemented in admindialog.add()
	}

	@Override
	public AdminDialog createAdminDialog() {
		StudentsRegisterAdminDialog dialog = new StudentsRegisterAdminDialog(
				(StudentsRegister) msterDetail);
		dialog.setSize("620", "340");
		return dialog;
	}

}
