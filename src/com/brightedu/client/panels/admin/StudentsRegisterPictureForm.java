package com.brightedu.client.panels.admin;

import com.brightedu.client.frame.BrightFrame;
import com.brightedu.client.frame.BrightFrame.LoadHandler;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class StudentsRegisterPictureForm extends DynamicForm {

	MasterDetailAdmin masterDetail;
	StudentsRegisterMasterPanel master;
	BrightFrame brightFrame;
	boolean fakeFrameLoaded = false;
	private Img viewImg;
	String serverTempFile;
	int picTypeId;

	public StudentsRegisterPictureForm(MasterDetailAdmin masterDetail,
			String title, int picTypeId, String faketarget) {
		this.picTypeId = picTypeId;
		this.masterDetail = masterDetail;
		master = (StudentsRegisterMasterPanel) masterDetail.getMaster();
		brightFrame = new BrightFrame(faketarget);
		setTarget(faketarget);
		brightFrame.setWidth("1");
		brightFrame.setHeight("1");
		brightFrame.setVisible(false);

		brightFrame.addMyLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(Event event) {
				if (!fakeFrameLoaded) {
					fakeFrameLoaded = true;
				} else {
					if (!brightFrame.isSuccess()) {
						SC.warn("保存失败!" + brightFrame.getMessage());
					} else {
						// 保存成功
						String tempfile = brightFrame.getMessage();
						setServerTempFile(tempfile);

						viewImg.setSrc(GWT.getHostPageBaseURL() + tempfile);
					}
				}
			}
		});
		UploadItem fileItem = new UploadItem(title.trim(), title);
		fileItem.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				submitForm();
			}
		});

		setAction(GWT.getModuleBaseURL() + "formwithfile?action=pic_upload");
		setWrapItemTitles(false);
		setFields(fileItem);
		setEncoding(Encoding.MULTIPART);
	}

	public String getServerTempFile() {
		return serverTempFile;
	}

	public void setServerTempFile(String serverTempFile) {
		this.serverTempFile = serverTempFile;
	}

	public BrightFrame getBrightFrame() {
		return brightFrame;
	}

	public Img getViewImg() {
		return viewImg;
	}

	public void setViewImg(Img viewImg) {
		this.viewImg = viewImg;
	}

	public int getPicTypeId() {
		return picTypeId;
	}

	public void setPicTypeId(int picTypeId) {
		this.picTypeId = picTypeId;
	}

}
