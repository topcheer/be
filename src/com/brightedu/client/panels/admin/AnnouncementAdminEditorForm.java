package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.brightedu.client.panels.DetailedEditorForm;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.Announcement;
import com.brightedu.model.edu.RecruitAgent;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.RichTextItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class AnnouncementAdminEditorForm extends DetailedEditorForm {
	TextItem annTitleItem = new TextItem("ann_title", "标题");

	RichTextItem annBodyItem = new RichTextItem("ann_body","内容");

	public AnnouncementAdminEditorForm(MasterDetailAdmin admin) {
		super(admin);
		init();
	}

	private void init() {

		// agent_typeItem.
		annTitleItem.setWidth(700);
		
		annBodyItem.setWidth(750);
		annBodyItem.setHeight(250);
		
		this.setShowEdges(true);
		this.setEdgeSize(2);
		setWidth(800);
		setHeight(350);
		setFields(annTitleItem,annBodyItem, saveBtn);
	}

	public Serializable getModel() {
		Announcement ann = new Announcement();
		ann.setAnn_title(annTitleItem.getValueAsString());
		ann.setAnn_body(annBodyItem.getValue().toString());
		
		
		return ann;
	}

	@Override
	public void setValue(Serializable model) {
		Announcement ann = (Announcement) model;
		annTitleItem.setValue(ann.getAnn_title());
		annBodyItem.setValue(ann.getAnn_body());
	}

//	public void setValueMaps(LinkedHashMap<String, String>... valueMaps) {
//		if (valueMaps.length != 2) {
//			SC.say("数据结构所需数量未达到需求");
//			return;
//		} else {
//
//		}
//	}

	@Override
	public void reset() {
		setValue(new Announcement());		
	}

}
