package com.brightedu.client.panels.admin;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.IUploader.UploaderConstants;
import gwtupload.client.MultiUploader;
import gwtupload.client.SingleUploader;

import java.io.LineNumberReader;
import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.ChargeType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class BatchImportPanel extends VLayout {
	private ListGrid uploaded  = new ListGrid();
	public BatchImportPanel() {
		init();

	}

	protected void init() {

		this.setMargin(5);
		
		HLayout uploadLayout = new HLayout();
		uploadLayout.setHeight(25);
		uploadLayout.setMembersMargin(10);
		uploadLayout.setWidth100();

		// Create a new uploader panel and attach it to the document
		SingleUploader defaultUploader = new SingleUploader();
		defaultUploader.setValidExtensions(".xls",".xlsx");
		
		//defaultUploader.setSize("400px", "20px");
		//defaultUploader.setStyleName("sendButton");
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
		addMember(uploadLayout);
		
		LayoutSpacer sp = new LayoutSpacer();
		sp.setHeight(10);
		addMember(sp);
		
		uploaded.setShowHeaderContextMenu(false);
		uploaded.setShowHeaderMenuButton(false);
		uploaded.setOverflow(Overflow.VISIBLE);
		
		
		addMember(uploaded);
	}

	protected void createDataGrid(String message) {
		
		String[] lines = message.split("\n");
		String[] columnNames = lines[0].split(":");
		ListGridField[] fields = new ListGridField[columnNames.length];
		for(int i =0 ; i< columnNames.length;i++)
		{
			
			fields[i] = new ListGridField("F" + i ,columnNames[i] );
			
		}
		uploaded.setWidth(120 * columnNames.length);
		uploaded.setFields(fields);
		RecordList list = new RecordList();
		for(int j = 1; j<lines.length;j++)
		{
			ListGridRecord record = new ListGridRecord();
			String[] row = lines[j].split(":");
			for(int i =0 ; i< row.length;i++ )
			{
				record.setAttribute("F" + i, "" + row[i]);
			}
			list.add(record);
			
		}
		uploaded.setData(list);
		
	}

}
