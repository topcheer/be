package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.ds.BatchDS;
import com.brightedu.client.ds.CollegeDS;
import com.brightedu.client.ds.LevelDS;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class RecruitPlanManagePanel extends VLayout {
	
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	SectionStack aStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("条件");
	SectionStackSection selectionSection = new SectionStackSection ("选择 - 将左边列表中的专业拖动到右边列表中");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	 
	SelectItem batchItem =  new SelectItem("batchID","批次");
	SelectItem levelItem =  new SelectItem("levelID","层次");
	SelectItem collegeItem =  new SelectItem("collegeID","大学");
	
	ListGrid subjectList = new ListGrid();   
	
	ListGrid selectedList2 = new ListGrid();  
	
    ListGridField pkField = new ListGridField("subjectID", "id");
    ListGridField subjectField = new ListGridField("subjectName", "专业");   
    ListGridField lolField = new ListGridField("lol", "学制(年)"); 

    ListGridField pkField2 = new ListGridField("subjectID", "id");
    ListGridField subjectField2 = new ListGridField("subjectName", "专业");   
    ListGridField lolField2 = new ListGridField("lol", "学制(年)"); 
    
	DynamicForm df = new DynamicForm();
	HLayout hl = new HLayout();
	Validator numberVd = new Validator();
	//TransferImgButton arrowImg = new TransferImgButton(TransferImgButton.RIGHT);   
	HStack buttonStack = new HStack();
	IButton saveButton = new IButton("保存");
	IButton cloneButton = new IButton("克隆...");
	
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
		
		
        
        hl.setPadding(4);
        hl.setHeight(350);
		
        
//        
        subjectList.setWidth(180);   
        subjectList.setHeight(350);   
        subjectList.setShowAllRecords(true);   
        subjectList.setCanReorderRecords(true);   
        subjectList.setCanDragRecordsOut(true);   
        subjectList.setCanAcceptDroppedRecords(true);   
        subjectList.setDragDataAction(DragDataAction.COPY);   
        subjectList.setShowHeaderContextMenu(false);
        
        subjectField.setWidth(150);
       
        
        pkField.setHidden(true);
        lolField.setHidden(true);
        
        pkField2.setHidden(true);
       
        
        lolField2.setCanEdit(true);
        lolField2.setWidth(100);
        
        
        numberVd.setType(ValidatorType.INTEGERRANGE);
        numberVd.setValidateOnChange(true);
        numberVd.setAttribute("min", 1);
        numberVd.setAttribute("max", 6);
        
        lolField2.setValidators(numberVd);
        
        TextItem ti = new TextItem();
        
        ti.setMask("#");
        
        lolField2.setEditorType(ti);
        
        
        subjectList.setFields(pkField, subjectField,lolField);   
//        //subjectList.setDataSource(SubjectDS.getInstance());
//
        hl.addMember(subjectList);
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setWidth(10);
        hl.addMember(spacer);
        
        selectedList2.setWidth(250);   
        selectedList2.setLeft(250);
        
        selectedList2.setHeight(350);   
        //selectedList.setShowAllRecords(true);   
        selectedList2.setEmptyMessage("拖动左边的项目到这里");   
        selectedList2.setCanReorderFields(true);   
        selectedList2.setCanDragRecordsOut(false);   
        selectedList2.setCanAcceptDroppedRecords(true);   
        selectedList2.setCanRemoveRecords(true);
        selectedList2.setShowHeaderContextMenu(false);
        //selectedList.setDragDataAction(DragDataAction.MOVE);   
        
        selectedList2.setFields(pkField2, subjectField2,lolField2);   
        
        selectedList2.setEditEvent(ListGridEditEvent.CLICK);
        selectedList2.setPreventDuplicates(true);
        selectedList2.setDuplicateDragMessage("所选专业已经在已选择列表中,请仔细检查");

        hl.addMember(selectedList2);
        
        //canvas.addChild(countryGrid2); 
        selectionSection.addItem(hl);
        aStack.addSection(selectionSection);
        
        actionSection.setShowHeader(true);
        actionSection.setExpanded(true);
        
        
        buttonStack.setHeight(50);
        buttonStack.setLayoutMargin(10);
        
		
		saveButton.setLeft(30);
		saveButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				saveMe();
				
			}});
		
		buttonStack.addMember(saveButton);

		
		cloneButton.setLeft(30);
		buttonStack.addMember(cloneButton);
		
		actionSection.addItem(buttonStack);
		aStack.addSection(actionSection);
		
		cloneButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
			}});
		

		addMember(aStack);
        
        show();
        
        BrightEdu.createDataBaseRPC().getSubjectsList(-1, -1, false, new AsyncCallback<List<Subjects>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<Subjects> result) {
				RecordList list = new RecordList();
				// TODO Auto-generated method stub
				Iterator<Subjects> biit = result.iterator();
				
				while(biit.hasNext())
				{
					Subjects bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("subjectID",bi.getSubject_id());
					rc.setAttribute("subjectName", bi.getSubject_name());
					rc.setAttribute("lol",new Integer(0));
					
					list.add(rc);
				}
				subjectList.setData(list);
				//selectedList2.setData(list);
				
				
			}
        });
        
         subjectList.addCellDoubleClickHandler(new CellDoubleClickHandler(){
			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				selectedList2.transferSelectedData(subjectList);
				
			}});

         selectedList2.addRecordDoubleClickHandler(new RecordDoubleClickHandler(){


			@Override
			public void onRecordDoubleClick(RecordDoubleClickEvent event) {
				
				selectedList2.removeData(event.getRecord());
				
			}});
        
//        new Timer(){
//
//			@Override
//			public void run() {
//				
//				SC.say("Helllo Forever");
//				
//			}}.scheduleRepeating(3000);
        
//        GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
//
//			@Override
//			public void onUncaughtException(Throwable e) {
//				// TODO Auto-generated method stub
//				e.printStackTrace();
//			}});
        
	}
	
	protected void reload()
	{
		//检查所选的组合是否有记录已经存在与college_subject表中，如果有，初始化selectedList列表
		
		Integer collegeId = (Integer)collegeItem.getValue();
		Integer batchId = (Integer)batchItem.getValue();
		Integer levelId = (Integer)levelItem.getValue();
		

		AsyncCallback<List<CollegeSubjectView>> callback = new AsyncCallback<List<CollegeSubjectView>>(){


			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取招生计划时发生错误");
			}

			@Override
			public void onSuccess(List<CollegeSubjectView> result) {
				// TODO Auto-generated method stub
				
				Iterator<CollegeSubjectView> biit = result.iterator();
				RecordList data = new RecordList();
				selectedList2.setData(data);
				while(biit.hasNext())
				{
					CollegeSubjectView bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("subjectID",bi.getSubeject_id());
					rc.setAttribute("subjectName", bi.getSubject_name());
					rc.setAttribute("lol",bi.getLength_of_schooling() + "");
					data.add(rc);
				}
				
				selectedList2.setData(data);

			}

        };
		
        BrightEdu.createDataBaseRPC().getCollegeSubjectList(collegeId, levelId, batchId,callback );
        
        
        
		

	
	}

	protected void saveMe()
	{
		Integer collegeId = (Integer)collegeItem.getValue();
		Integer batchId = (Integer)batchItem.getValue();
		Integer levelId = (Integer)levelItem.getValue();
		
		if(selectedList2.getRecords().length == 0) 
			
			{
				
				CollegeSubject cs = new CollegeSubject();
				cs.setBatch_id(batchId);
				cs.setClassified_id(levelId);
				cs.setCollege_id(collegeId);
		
				dbService.deletCollegeSubject(cs, new AsyncCallback<Boolean>(){
		
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
		
					@Override
					public void onSuccess(Boolean result) {
						
						BrightEdu.showTip("没有啥东西好存的,开玩笑? 我顺便把所有的组合全删了，你没意见吧？");
					}});
			
				return;
			}
		
		
		
		//保存界面上的组合
		
		//step 1 , 删除所有已经存在的组合
//		
//		CollegeSubject cs = new CollegeSubject();
//		cs.setBatch_id(batchId);
//		cs.setClassified_id(levelId);
//		cs.setCollege_id(collegeId);
//
//		dbService.deletCollegeSubject(cs, new AsyncCallback(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Object result) {
//				
//				
//			}});
		
		
		//step 2 , 插入所有选中的组合
		// 改动 :: dbService.addCollegeSubject 里先把原有的全删了,所以step1不要了
		
		ArrayList<CollegeSubject> lst = new ArrayList<CollegeSubject>();
		
		RecordList data = selectedList2.getRecordList();
		
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
		
		dbService.addCollegeSubject(lst, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {

				
			}

			@Override
			public void onSuccess(Boolean result) {
					BrightEdu.showTip("保存成功");
				
			}});
		
		
		
	}


}
