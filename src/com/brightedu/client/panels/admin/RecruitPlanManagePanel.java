package com.brightedu.client.panels.admin;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.ds.BatchDS;
import com.brightedu.client.ds.CollegeDS;
import com.brightedu.client.ds.LevelDS;
import com.brightedu.client.ds.SubjectDS;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RecruitPlanManagePanel extends VLayout {
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	public RecruitPlanManagePanel()
	{
		
        //Canvas canvas = new Canvas();   
		setPadding(5);
		HLayout queryBar = new HLayout();
		
		queryBar.setPadding(3);
		queryBar.setHeight(40);
		
		DynamicForm df = new DynamicForm();
		df.setWidth(600);
		df.setColWidths(120,120,120);
		df.setNumCols(3);
		
		SelectItem batchItem =  new SelectItem("batchID","批次");
		batchItem.setOptionDataSource(BatchDS.getInstance());
		batchItem.setValueField("batchID");
		batchItem.setDisplayField("batchName");
		
		SelectItem collegeItem =  new SelectItem("collegeID","大学");
		collegeItem.setOptionDataSource(CollegeDS.getInstance());
		collegeItem.setValueField("collegeID");
		collegeItem.setDisplayField("collegeName");

		SelectItem levelItem =  new SelectItem("levelID","层次");
		levelItem.setOptionDataSource(LevelDS.getInstance());
		levelItem.setValueField("levelID");
		levelItem.setDisplayField("levelName");
		
		df.setTitleOrientation(TitleOrientation.TOP);
		
		df.setItems(batchItem,collegeItem,levelItem);
		queryBar.addMember(df);
		
		IButton saveButton = new IButton("保存");
		saveButton.setLeft(500);
		queryBar.addMember(saveButton);
		
		saveButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
								
			}});
		
		
        HLayout hl = new HLayout();
        hl.setHeight(500);
		
        ListGrid subjectList = new ListGrid();   
        subjectList.setWidth(300);   
        subjectList.setHeight(350);   
        subjectList.setShowAllRecords(true);   
        subjectList.setCanReorderRecords(true);   
        subjectList.setCanDragRecordsOut(true);   
        subjectList.setCanAcceptDroppedRecords(true);   
        subjectList.setDragDataAction(DragDataAction.MOVE);   
 
        ListGridField pkField = new ListGridField("subjectID", "id");
        ListGridField subjectField = new ListGridField("subjectName", "专业");   
        pkField.setHidden(true);
        ListGridField lolField = new ListGridField("lol", "学制(年)");  
        subjectList.setFields(pkField, subjectField);   
        subjectList.setDataSource(SubjectDS.getInstance());

        hl.addMember(subjectList);
        
        
        ListGrid selectedList = new ListGrid();   
        selectedList.setWidth(350);   
        selectedList.setHeight(350);   
        selectedList.setLeft(350);   
        selectedList.setShowAllRecords(true);   
        selectedList.setEmptyMessage("拖动左边的项目到这里");   
        selectedList.setCanReorderFields(true);   
        selectedList.setCanDragRecordsOut(true);   
        selectedList.setCanAcceptDroppedRecords(true);   
        selectedList.setCanRemoveRecords(true);
        selectedList.setDragDataAction(DragDataAction.MOVE);   
        selectedList.setFields(pkField, subjectField,lolField);   
        
        
        hl.addMember(selectedList);
        //canvas.addChild(countryGrid2); 
        addMember(queryBar);
        addMember(hl);
        
        draw();
        
        subjectList.fetchData();
        //canvas.draw();   

	}
	



}
