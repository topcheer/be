package com.brightedu.client.window;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.frame.BrightFrame;
import com.brightedu.client.frame.BrightFrame.LoadHandler;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.CollegeAgreement;
import com.google.gwt.user.client.Event;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class FileUpdateDialog extends Window {

	UploadItem fileItem = new UploadItem("file", "文件");
	IButton uploadBtn = new IButton("上传");
	BrightFrame hiddenFrame;
	boolean hiddenFrameLoaded = false;
	Img busyImg = new Img("loadingSmall.gif");
	BasicAdminPanel adminPanel;
	Record record;
	DynamicForm form = new DynamicForm();

	public FileUpdateDialog(String title, String hiddenTarget, Record record) {
		setTitle(title);
		fileItem.setRequired(true);
		hiddenFrame = new BrightFrame(hiddenTarget);
		hiddenFrame.setWidth("1");
		hiddenFrame.setHeight("1");
		hiddenFrame.setVisible(false);
		form.setTarget(hiddenTarget);
		this.record = record;
		init();
		setSize("240", "100");
	}

	protected void init() {
		setPadding(5);
		setShowMinimizeButton(false);
		setIsModal(true);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(false);
		setCanDragResize(true);
		// TextItem item = new TextItem("aa");
		// item.setVisible(false);
		form.setWrapItemTitles(false);
		form.setPadding(5);
		form.setEncoding(Encoding.MULTIPART);
		form.setFields(fileItem);

		addItem(form);
		busyImg.setSize("16", "16");
		busyImg.setVisible(false);

		HLayout bottomLayout = new HLayout();
		bottomLayout.setPadding(5);
		bottomLayout.setAlign(Alignment.RIGHT);
		bottomLayout.addMember(hiddenFrame);
		// bottomLayout.addMember(new Label(" "));
		bottomLayout.addMember(busyImg);
		// bottomLayout.addMember(new Label("  "));
		// bottomLayout.addMember(new LayoutSpacer());
		uploadBtn.setWidth(70);
		//
		bottomLayout.addMember(uploadBtn);
		addItem(bottomLayout);
		centerInPage();

		uploadBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				busyImg.setVisible(true);
				uploadBtn.disable();
				form.submitForm();
			}
		});
		hiddenFrame.addMyLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(Event event) {
				if (!hiddenFrameLoaded) {
					hiddenFrameLoaded = true;
				} else {
					if (!hiddenFrame.isSuccess()) {
						SC.warn("保存失败!" + hiddenFrame.getMessage());
					} else {
						// 保存成功
						String newAttachmentName = hiddenFrame.getMessage();
						CollegeAgreement agreement = (CollegeAgreement) record
								.getAttributeAsObject("object");
						agreement.setAgreement_name(newAttachmentName);
						record.setAttribute("object", agreement);
						BrightEdu.showTip("已更新!");
					}
					hide();
				}
			}
		});
	}

	public void show() {
		super.show();
		busyImg.setVisible(false);
		uploadBtn.enable();
		fileItem.setValue("");
	}

	public BasicAdminPanel getAdminPanel() {
		return adminPanel;
	}

	public void setAdminPanel(BasicAdminPanel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public void setActionURL(String action) {
		form.setAction(action);
	}
}
