package com.brightedu.client.panels.admin;

import java.util.ArrayList;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.ds.BatchDS;
import com.brightedu.client.ds.CollegeDS;
import com.brightedu.client.ds.CollegeSubjectData;
import com.brightedu.client.ds.LevelDS;
import com.brightedu.client.ds.SubjectDS;
import com.brightedu.model.edu.CollegeSubject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.TransferImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.Validator;
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
	
	//本功能模块在Firefox 10.0开发模式下死活不工作， IE下工作一切正常
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	SectionStack aStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("条件");
	SectionStackSection selectionSection = new SectionStackSection ("选择 - 将左边列表中的专业拖动到右边列表中");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	 
	SelectItem batchItem =  new SelectItem("batchID","批次");
	SelectItem levelItem =  new SelectItem("levelID","层次");
	SelectItem collegeItem =  new SelectItem("collegeID","大学");
	
	ListGrid subjectList = new ListGrid();   
	ListGrid selectedList = new ListGrid();  
	
    ListGridField pkField = new ListGridField("subjectID", "id");
    ListGridField subjectField = new ListGridField("subjectName", "专业");   
    ListGridField lolField = new ListGridField("lol", "学制(年)");  
   
	DynamicForm df = new DynamicForm();
	
	public RecruitPlanManagePanel()
	{
		init();
		
	}
	
	protected void init()
	{
		setPadding(5);

		aStack.setWidth100();
		aStack.setHeight(600);
		aStack.setVisibilityMode(VisibilityMode.MULTIPLE);

		conditionSection.setExpanded(true);
		selectionSection.setExpanded(true);
		
		
		batchItem.setOptionDataSource(BatchDS.getInstance());
		batchItem.setValueField("batchID");
		batchItem.setDisplayField("batchName");
		batchItem.setDefaultToFirstOption(true);
		batchItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				reload();
				
			}}
		);
		
		
		collegeItem.setLeft(20);
		collegeItem.setOptionDataSource(CollegeDS.getInstance());
		collegeItem.setValueField("collegeID");
		collegeItem.setDisplayField("collegeName");
		collegeItem.setDefaultToFirstOption(true);
		collegeItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				reload();
				
			}}
		);
		
		
		levelItem.setLeft(20);
		levelItem.setOptionDataSource(LevelDS.getInstance());
		levelItem.setValueField("levelID");
		levelItem.setDisplayField("levelName");
		levelItem.setDefaultToFirstOption(true);
		levelItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				reload();
				
			}}
		);
		
		df.setItems(batchItem,collegeItem,levelItem);
		conditionSection.addItem(df);
		aStack.addSection(conditionSection);
		
		
        HLayout hl = new HLayout();
        hl.setPadding(4);
        hl.setHeight(400);
		
        
        
        subjectList.setWidth(300);   
        subjectList.setHeight(350);   
        subjectList.setShowAllRecords(true);   
        subjectList.setCanReorderRecords(true);   
        subjectList.setCanDragRecordsOut(true);   
        subjectList.setCanAcceptDroppedRecords(true);   
        subjectList.setDragDataAction(DragDataAction.COPY);   
 

        
        pkField.setHidden(true);
        lolField.setCanEdit(true);
        Validator numberVd = new Validator();
        numberVd.setType(ValidatorType.INTEGERRANGE);
        numberVd.setValidateOnChange(true);
        numberVd.setAttribute("min", 1);
        numberVd.setAttribute("max", 6);
        lolField.setValidators(numberVd);
        
        
        subjectList.setFields(pkField, subjectField);   
        subjectList.setDataSource(SubjectDS.getInstance());

        hl.addMember(subjectList);
        
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
        
        actionSection.setShowHeader(true);
        actionSection.setExpanded(true);
        
        HStack buttonStack = new HStack();
        buttonStack.setHeight(50);
        buttonStack.setLayoutMargin(10);
        
		IButton saveButton = new IButton("保存");
		saveButton.setLeft(30);
		saveButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				saveMe();
				
			}});
		
		buttonStack.addMember(saveButton);

		IButton cloneButton = new IButton("克隆...");
		cloneButton.setLeft(30);
		buttonStack.addMember(cloneButton);
		
		actionSection.addItem(buttonStack);
		aStack.addSection(actionSection);

		addMember(aStack);
        
        draw();
        
        subjectList.fetchData();

        subjectList.addCellDoubleClickHandler(new CellDoubleClickHandler(){
        
        
        	
			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				selectedList.transferSelectedData(subjectList);
				
			}});
        
       
		
	}
	
	protected void reload()
	{
		//检查所选的组合是否有记录已经存在与college_subject表中，如果有，初始化selectedList列表
		
		Integer collegeId = (Integer)collegeItem.getValue();
		Integer batchId = (Integer)batchItem.getValue();
		Integer levelId = (Integer)levelItem.getValue();
		
		//selectedList.setDataSource(new CollegeSubjectDS().getInstance(collegeId, levelId, batchId));
		//selectedList.setDataSource(CollegeSubjectData.getInstance(collegeId, levelId, batchId));
		//selectedList.setData(CollegeSubjectData.getData(collegeId, levelId, batchId));
		//selectedList.redraw();
		
		selectedList.setData(CollegeSubjectData.getData(collegeId, levelId, batchId));
		selectedList.redraw();
	
	}

	protected void saveMe()
	{
		if(selectedList.getRecords().length == 0) 
			
			{
				BrightEdu.showTip("没有啥东西好存的,你在开玩笑?");
				return;
			}
		
		
		
		//保存界面上的组合
		
		//step 1 , 删除所有已经存在的组合
		
		Integer collegeId = (Integer)collegeItem.getValue();
		Integer batchId = (Integer)batchItem.getValue();
		Integer levelId = (Integer)levelItem.getValue();
		
		CollegeSubject cs = new CollegeSubject();
		cs.setBatch_id(batchId);
		cs.setClassified_id(levelId);
		cs.setCollege_id(collegeId);

		dbService.deletCollegeSubject(cs, new AsyncCallback(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Object result) {
				
				
			}});
		
		
		//step 2 , 插入所有选中的组合
		
		ArrayList<CollegeSubject> lst = new ArrayList<CollegeSubject>();
		
		RecordList data = selectedList.getRecordList();
		
		for(int i = 0 ; i< data.getLength() ; i++)
		{
			
			CollegeSubject cs2 = new CollegeSubject();
			cs2.setBatch_id(batchId);
			cs2.setClassified_id(levelId);
			cs2.setCollege_id(collegeId);

			cs2.setSubeject_id(data.get(i).getAttributeAsInt("subjectID"));
			cs2.setLength_of_schooling(new Short(data.get(i).getAttribute("lol")));
			lst.add(cs2);
			
		}
		
		dbService.addCollegeSubject(lst, new AsyncCallback(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Object result) {
					BrightEdu.showTip("保存成功");
				
			}});
		
		
		
	}


}
