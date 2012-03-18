package com.brightedu.client.panels.admin;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.IUploader.UploaderConstants;
import gwtupload.client.SingleUploader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.EthnicGroup;
import com.brightedu.model.edu.MajorCategory;
import com.brightedu.model.edu.PoliticalStatus;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.School;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentStatus;
import com.brightedu.model.edu.StudentType;
import com.brightedu.model.edu.Subjects;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class BatchImportPanel extends VLayout {
	private ListGrid uploaded = new ListGrid();
	private LinkedHashMap<String,String> batchReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> levelReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> collegeReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> subjectReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> agentReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> ethnicReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> politicalReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> schoolReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> statusReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> studentTypeReverse = new LinkedHashMap<String,String>();
	private LinkedHashMap<String,String> majorCategoryReverse = new LinkedHashMap<String,String>();
	private String[] nameValuePareModels;

	public BatchImportPanel() {

		nameValuePareModels = new String[] { "BatchIndex", "College",
				"StudentClassified", "Subjects", "RecruitAgent",
				"StudentStatus", "EthnicGroup", "PoliticalStatus", "School",
				"StudentType", "MajorCategory", "PictureType" };
		initValueMaps();

		init();

	}

	private void initValueMaps() {

		BrightEdu.createDataBaseRPC().getNameValuePareList(nameValuePareModels,
				new CommonAsyncCall<List>() {
					@Override
					public void onSuccess(List result) {
						parseValueMaps(result);
					}
				});
	}

	private void parseValueMaps(List totalNameValuePares) {

		for (int i = 0; i < totalNameValuePares.size(); i++) {
			List nameValuePare = (List) totalNameValuePares.get(i);
			switch (i) {
			case 0:
				for (int x = 0; x < nameValuePare.size(); x++) {
					BatchIndex bi = (BatchIndex) nameValuePare.get(x);
					batchReverse.put(bi.getBatch_name(), bi.getBatch_id() + "");
				}

				break;
			case 1:
				for (int x = 0; x < nameValuePare.size(); x++) {
					College c = (College) nameValuePare.get(x);
					collegeReverse.put(c.getCollege_name(), c.getCollege_id()
							+ "");
				}
				break;
			case 2:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentClassified c = (StudentClassified) nameValuePare
							.get(x);
					levelReverse.put(c.getClassified_name(),
							c.getClassified_id() + "");
				}
				break;
			case 3:
				for (int x = 0; x < nameValuePare.size(); x++) {
					Subjects c = (Subjects) nameValuePare.get(x);
					subjectReverse.put(c.getSubject_name(), c.getSubject_id()
							+ "");
				}
				break;
			case 4:
				for (int x = 0; x < nameValuePare.size(); x++) {
					RecruitAgent agent = (RecruitAgent) nameValuePare.get(x);

					agentReverse.put(agent.getAgent_name(), agent.getAgent_id()
							+ "");

				}
				break;
			case 5:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentStatus c = (StudentStatus) nameValuePare.get(x);
					statusReverse.put(c.getStu_status_name(),
							c.getStu_status_id() + "");
				}
				break;
			case 6:
				for (int x = 0; x < nameValuePare.size(); x++) {
					EthnicGroup c = (EthnicGroup) nameValuePare.get(x);
					ethnicReverse.put(c.getEthnic_group_name(),
							c.getEthnic_group_id() + "");
				}
				break;
			case 7:
				for (int x = 0; x < nameValuePare.size(); x++) {
					PoliticalStatus c = (PoliticalStatus) nameValuePare.get(x);
					politicalReverse.put(c.getPol_name(), c.getPol_id() + "");
				}
				break;
			case 8:
				for (int x = 0; x < nameValuePare.size(); x++) {
					School c = (School) nameValuePare.get(x);
					schoolReverse.put(c.getSchool_name(), c.getSchool_code());
				}
				break;
			case 9:
				for (int x = 0; x < nameValuePare.size(); x++) {
					StudentType c = (StudentType) nameValuePare.get(x);
					studentTypeReverse.put(c.getStudent_type_name(),
							c.getStudent_type_id() + "");
				}
				break;
			case 10:
				for (int x = 0; x < nameValuePare.size(); x++) {
					MajorCategory c = (MajorCategory) nameValuePare.get(x);
					majorCategoryReverse.put(
							c.getStudent_major_category_name(),
							c.getStudent_major_category_id() + "");
				}
				break;
			default:
				break;
			}
		}
	}

	protected void init() {

		this.setMargin(5);

		HLayout uploadLayout = new HLayout();
		uploadLayout.setHeight(25);
		uploadLayout.setMembersMargin(10);
		uploadLayout.setWidth100();

		// Create a new uploader panel and attach it to the document
		SingleUploader defaultUploader = new SingleUploader();
		defaultUploader.setValidExtensions(".xls", ".xlsx");

		// defaultUploader.setSize("400px", "20px");
		// defaultUploader.setStyleName("sendButton");
		defaultUploader.setI18Constants(new UploaderConstants() {

			@Override
			public String uploadLabelCancel() {
				return "取消";
			}

			@Override
			public String uploadStatusCanceled() {

				return "已取消";
			}

			@Override
			public String uploadStatusCanceling() {

				return "取消...";
			}

			@Override
			public String uploadStatusDeleted() {

				return "已经删除";
			}

			@Override
			public String uploadStatusError() {

				return "出错啦";
			}

			@Override
			public String uploadStatusInProgress() {

				return "上传中";
			}

			@Override
			public String uploadStatusQueued() {

				return "队列";
			}

			@Override
			public String uploadStatusSubmitting() {

				return "提交";
			}

			@Override
			public String uploadStatusSuccess() {

				return "成功";
			}

			@Override
			public String uploaderActiveUpload() {

				return "上传中";
			}

			@Override
			public String uploaderAlreadyDone() {

				return "已经完成";
			}

			@Override
			public String uploaderBlobstoreError() {

				return "保存失败";
			}

			@Override
			public String uploaderBrowse() {

				return "浏览";
			}

			@Override
			public String uploaderInvalidExtension() {

				return "无效后缀名,有效的后缀为";
			}

			@Override
			public String uploaderSend() {

				return "上传";
			}

			@Override
			public String uploaderServerError() {

				return "服务器端错误";
			}

			@Override
			public String submitError() {

				return "提交错误";
			}

			@Override
			public String uploaderServerUnavailable() {

				return "服务器错误";
			}

			@Override
			public String uploaderTimeout() {

				return "超时";
			}
		});

		// Add a finish handler which will load the image once the upload
		// finishes

		defaultUploader
				.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
					public void onFinish(IUploader uploader) {
						if (uploader.getStatus() == Status.SUCCESS) {

							// The server sends useful information to the client
							// by
							// default
							UploadedInfo info = uploader.getServerInfo();
							createDataGrid(info.message);
						}
					}
				});

		// TileView tileView = new TileView(mainPanel);
		Label choose = new Label("选择要上传的文件:");
		uploadLayout.addMember(choose);
		uploadLayout.addMember(defaultUploader);
		LayoutSpacer sp0 = new LayoutSpacer();
		sp0.setWidth100();
		uploadLayout.addMember(sp0);
		IButton saveBtn = new IButton("保存");

		uploadLayout.addMember(saveBtn);
		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				transformAndSave();

			}
		});

		addMember(uploadLayout);

		LayoutSpacer sp = new LayoutSpacer();
		sp.setHeight(10);
		addMember(sp);

		uploaded.setShowHeaderContextMenu(false);
		uploaded.setShowHeaderMenuButton(false);
		uploaded.setOverflow(Overflow.VISIBLE);

		addMember(uploaded);
	}

	protected void transformAndSave() {

		if (uploaded.getRecords().length == 0) {
			BrightEdu.showTip("你还没有上传文件");
			return;
		}

		if (uploaded.getFields().length != 33) {
			BrightEdu.showTip("你上传的文件格式不正确,请与系统管理员联系");
			return;
		}

		List<StudentInfo> students = new ArrayList<StudentInfo>();

		for (Record x : uploaded.getRecords()) {
			students.add(transform(x));
		}
		BrightEdu.createDataBaseRPC().addStudents(students,
				new CommonAsyncCall<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						BrightEdu.showTip("成功倒入");

					}
				});

	}

	private StudentInfo transform(Record x) {
		// transform each row into a StudentInfo object
		//TODO code not finished yet
		StudentInfo student = new StudentInfo();
		
		student.setAgent_owner(new Integer(agentReverse.get(x.getAttributeAsString("F0"))));  //F0 agent owner
		
		return student;
	}

	protected void createDataGrid(String message) {

		String[] lines = message.split("\n");
		String[] columnNames = lines[0].split(":");
		ListGridField[] fields = new ListGridField[columnNames.length];
		for (int i = 0; i < columnNames.length; i++) {

			fields[i] = new ListGridField("F" + i, columnNames[i]);

		}
		uploaded.setWidth(120 * columnNames.length);
		uploaded.setFields(fields);
		RecordList list = new RecordList();
		for (int j = 1; j < lines.length; j++) {
			ListGridRecord record = new ListGridRecord();
			String[] row = lines[j].split(":");
			for (int i = 0; i < row.length; i++) {
				record.setAttribute("F" + i, "" + row[i]);
			}
			list.add(record);

		}
		uploaded.setData(list);

	}

}
