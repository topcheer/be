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
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class RecruitPlanManagePanel extends VLayout {
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	public RecruitPlanManagePanel()
	{
        //Canvas canvas = new Canvas();   
		setPadding(5);
		SectionStack aStack = new SectionStack();   
		aStack.setWidth100();
		aStack.setHeight(600);
		aStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		SectionStackSection conditionSection = new SectionStackSection ("条件");
		SectionStackSection selectionSection = new SectionStackSection ("选择 - 将左边列表中的专业拖动到右边列表中");
		conditionSection.setExpanded(true);
		selectionSection.setExpanded(true);
		
		DynamicForm df = new DynamicForm();
		df.setHeight(30);
		df.setWidth(600);
		df.setColWidths(150,150,150);
		df.setNumCols(3);
		
		SelectItem batchItem =  new SelectItem("batchID","批次");
		batchItem.setOptionDataSource(BatchDS.getInstance());
		batchItem.setValueField("batchID");
		batchItem.setDisplayField("batchName");
		batchItem.setDefaultToFirstOption(true);
		
		
		SelectItem collegeItem =  new SelectItem("collegeID","大学");
		collegeItem.setLeft(20);
		collegeItem.setOptionDataSource(CollegeDS.getInstance());
		collegeItem.setValueField("collegeID");
		collegeItem.setDisplayField("collegeName");
		collegeItem.setDefaultToFirstOption(true);
		
		SelectItem levelItem =  new SelectItem("levelID","层次");
		levelItem.setLeft(20);
		levelItem.setOptionDataSource(LevelDS.getInstance());
		levelItem.setValueField("levelID");
		levelItem.setDisplayField("levelName");
		levelItem.setDefaultToFirstOption(true);
		df.setItems(batchItem,collegeItem,levelItem);
		conditionSection.addItem(df);
		aStack.addSection(conditionSection);
		
		
        HLayout hl = new HLayout();
        hl.setPadding(4);
        hl.setHeight(400);
		
        final ListGrid subjectList = new ListGrid();   
        
        subjectList.setWidth(300);   
        subjectList.setHeight(350);   
        subjectList.setShowAllRecords(true);   
        subjectList.setCanReorderRecords(true);   
        subjectList.setCanDragRecordsOut(true);   
        subjectList.setCanAcceptDroppedRecords(true);   
        subjectList.setDragDataAction(DragDataAction.COPY);   
 
        ListGridField pkField = new ListGridField("subjectID", "id");
        ListGridField subjectField = new ListGridField("subjectName", "专业");   
        pkField.setHidden(true);
        ListGridField lolField = new ListGridField("lol", "学制(年)");  
        
        lolField.setCanEdit(true);
        
        subjectList.setFields(pkField, subjectField);   
        subjectList.setDataSource(SubjectDS.getInstance());
        
 

        hl.addMember(subjectList);
        
        final ListGrid selectedList = new ListGrid();  
        
       TransferImgButton arrowImg = new TransferImgButton(TransferImgButton.RIGHT);   
        arrowImg.addClickHandler(new ClickHandler() {   
            public void onClick(ClickEvent event) {   
            	selectedList.transferSelectedData(subjectList);   
            }   
        }); 
        arrowImg.setLeft(10);
        arrowImg.setRight(10);
        hl.addMember(arrowImg);  
        
        selectedList.setWidth(350);   
        selectedList.setHeight(350);   
        //selectedList.setLeft(400);   
        selectedList.setShowAllRecords(true);   
        selectedList.setEmptyMessage("拖动左边的项目到这里");   
        selectedList.setCanReorderFields(true);   
        selectedList.setCanDragRecordsOut(true);   
        selectedList.setCanAcceptDroppedRecords(true);   
        selectedList.setCanRemoveRecords(true);
        selectedList.setDragDataAction(DragDataAction.MOVE);   
        selectedList.setFields(pkField, subjectField,lolField);   
        selectedList.setEditEvent(ListGridEditEvent.CLICK);
        selectedList.setPreventDuplicates(true);
        selectedList.setDuplicateDragMessage("所选专业已经在已选择列表中,请仔细检查");
        
        hl.addMember(selectedList);
        
        //canvas.addChild(countryGrid2); 
        selectionSection.addItem(hl);
        aStack.addSection(selectionSection);
        
        SectionStackSection actionSection = new SectionStackSection ("操作");
        actionSection.setShowHeader(true);
        actionSection.setExpanded(true);
        
        HStack buttonStack = new HStack();
        buttonStack.setHeight(50);
        buttonStack.setLayoutMargin(10);
        
		IButton saveButton = new IButton("保存");
		saveButton.setLeft(30);
		buttonStack.addMember(saveButton);

		IButton cloneButton = new IButton("克隆...");
		cloneButton.setLeft(30);
		buttonStack.addMember(cloneButton);
		
		actionSection.addItem(buttonStack);
		aStack.addSection(actionSection);
//		
//		saveButton.addClickHandler(new ClickHandler(){
//			@Override
//			public void onClick(ClickEvent event) {
//								
//			}});
//		
        
        
        
        
        
        addMember(aStack);
        //addMember(hl);
        
        draw();
        
        subjectList.fetchData();
        
		subjectList.addCellDoubleClickHandler(new CellDoubleClickHandler(){

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				selectedList.transferSelectedData(subjectList);
				
			}});
        //canvas.draw();   

	}
	



}
