package com.brightedu.client.panels;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;

public class FileUpdateDialog extends Window{
	
	UploadItem fileItem = new UploadItem("file", "新文件");
	ButtonItem uploadBtn = new ButtonItem("uploadButton", "上传");
	
	public FileUpdateDialog(){
		init();
	}
	
	protected void init(){
		setTitle("登录");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(true);
		DynamicForm form = new DynamicForm();
		form.setFields(fileItem);
	}
	

}
