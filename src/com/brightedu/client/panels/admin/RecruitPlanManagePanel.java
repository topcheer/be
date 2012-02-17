package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
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
	BooleanItem bi = new BooleanItem("使用当前批次");
	HiddenItem hi = new HiddenItem("currentBatch");
	HiddenItem hi2 = new HiddenItem("default_lol");
	
	public RecruitPlanManagePanel()
	{
		init();
		
	}
	
	protected void init()
	{
		setPadding(5);

		
		aStack.setWidth(700);
		aStack.setHeight100();
		aStack.setVisibilityMode(VisibilityMode.MULTIPLE);

		conditionSection.setExpanded(true);
		selectionSection.setExpanded(true);
		

        BrightEdu.createDataBaseRPC().getBatchList(-1, -1, false, new AsyncCallback<List<BatchIndex>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<BatchIndex> result) {
				// TODO Auto-generated method stub
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				Iterator<BatchIndex> biit = result.iterator();
				while(biit.hasNext())
				{
					BatchIndex bi = biit.next();
					list.put(bi.getBatch_id() + "", bi.getBatch_name());
				}
				
				batchItem.setValueMap(list);
			}
        	
        });
		
		batchItem.setDefaultToFirstOption(true);
		
		batchItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				reload();
				
			}}
		);


        BrightEdu.createDataBaseRPC().getCollegeList(-1, -1, false, new AsyncCallback<List<College>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<College> result) {
				// TODO Auto-generated method stub
				Iterator<College> biit = result.iterator();
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				while(biit.hasNext())
				{
					College bi = biit.next();
					list.put(bi.getCollege_id() + "", bi.getCollege_name());
				}
				collegeItem.setValueMap(list);
			}
        	
        });
        
		collegeItem.setDefaultToFirstOption(true);
		collegeItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				reload();
				
			}}
		);


		final LinkedHashMap<String,String> levelMaplol = new LinkedHashMap<String,String>();
		
        BrightEdu.createDataBaseRPC().getStudentClassesList(-1, -1, false, new AsyncCallback<List<StudentClassified>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<StudentClassified> result) {
				// TODO Auto-generated method stub
				Iterator<StudentClassified> biit = result.iterator();
				LinkedHashMap<String,String> levelMap = new LinkedHashMap<String,String>();
				while(biit.hasNext())
				{
					StudentClassified bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("levelID",bi.getClassified_id() );
					rc.setAttribute("levelName", bi.getClassified_name() );
					levelMap.put(bi.getClassified_id() + "", bi.getClassified_name());
					levelMaplol.put(bi.getClassified_id() + "", bi.getDefault_lol() + "");
				}
				levelItem.setValueMap(levelMap);
				
			}
        	
        });
		levelItem.setDefaultToFirstOption(true);
		levelItem.addChangedHandler(new ChangedHandler(){

		
			@Override
			public void onChanged(ChangedEvent event) {
				
				hi2.setValue(levelMaplol.get(event.getItem().getValue()));
				
				setDefaultLOL(subjectList,hi2.getValue().toString());
				reload();
			
			}}
		);
		
		
		
		df.setItems(batchItem,bi,collegeItem,levelItem,hi,hi2);
		
		df.setHeight(20);
		df.setWidth100();
		df.setTitleOrientation(TitleOrientation.TOP);
		
		conditionSection.addItem(df);
		aStack.addSection(conditionSection);
		
		
        
        hl.setPadding(4);
        hl.setHeight(350);
     
        subjectList.setWidth(200);   
        subjectList.setHeight(350);   
        subjectList.setShowAllRecords(true);   
        subjectList.setCanReorderRecords(true);   
        subjectList.setCanDragRecordsOut(true);   
        subjectList.setCanAcceptDroppedRecords(true);   
        subjectList.setDragDataAction(DragDataAction.COPY);   
        subjectList.setShowHeaderContextMenu(false);
 
        pkField.setHidden(true);
        lolField.setHidden(true);
        
        pkField2.setHidden(true);
       
        
        lolField2.setCanEdit(true);
       
        
        numberVd.setType(ValidatorType.INTEGERRANGE);
        numberVd.setValidateOnChange(true);
        numberVd.setAttribute("min", 1);
        numberVd.setAttribute("max", 6);
        
        lolField2.setValidators(numberVd);
        
        TextItem ti = new TextItem();
        
        ti.setMask("#");
        
        lolField2.setEditorType(ti);
        
        
        subjectList.setFields(pkField, subjectField,lolField);   
        hl.addMember(subjectList);
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setWidth(20);
        hl.addMember(spacer);
        
        selectedList2.setWidth(350);   
        subjectField2.setWidth(200);
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
		LayoutSpacer ls = new LayoutSpacer();
		ls.setWidth(20);
		
		buttonStack.addMember(ls);
		
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
        
         
         dbService.getCurrentBatch(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				//do nothing
			}

			@Override
			public void onSuccess(Integer result) {
				
				if(result>=0)
				{
					
					batchItem.setValue(result);
					batchItem.setDisabled(true);
					bi.setValue(true);
					hi.setValue(result);
					
				}
				else
				{
					bi.setValue(false);
					bi.setDisabled(true);
					hi.setValue(-1);
				}
				
			}});
         bi.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				if(!(Boolean)event.getItem().getValue())
				{
					batchItem.setDisabled(false);
				}
				else
				{
					batchItem.setValue(hi.getValue());
					batchItem.setDisabled(true);

				}
				
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
		if (collegeItem.getValue() == null 
				|| batchItem.getValue() == null 
				|| levelItem.getValue() == null) return;
		Integer collegeId = new Integer(collegeItem.getValue().toString());
		Integer batchId =new Integer(batchItem.getValue().toString());
		Integer levelId = new Integer(levelItem.getValue().toString());
		

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
		if (collegeItem.getValue() == null 
				|| batchItem.getValue() == null 
				|| levelItem.getValue() == null)
		{
			BrightEdu.showTip("开玩笑？");
			return;
		}
			
		
		Integer collegeId = new Integer(collegeItem.getValue().toString());
		Integer batchId = new Integer(batchItem.getValue().toString());
		Integer levelId = new Integer(levelItem.getValue().toString());
		
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
	
	private void setDefaultLOL(ListGrid subjectList,String lol)
	
	{
		RecordList newList = new RecordList();
		Record[] oldList = subjectList.getRecords();
		
		for (Record rec : oldList)
		{
			rec.setAttribute("lol", new Integer(lol));
			newList.add(rec);
			
		}
		subjectList.setData(newList);
		
	}


}
