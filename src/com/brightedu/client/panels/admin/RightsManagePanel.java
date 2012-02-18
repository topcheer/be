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
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsFunction;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.docs.FormLayout;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.FormLayoutType;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
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

public class RightsManagePanel extends VLayout {
	
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	HLayout mainPanel = new HLayout();
	
	SectionStack leftStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("条件");
	SectionStackSection selectionSection = new SectionStackSection ("功能列表 -- 要将某个功能加到选中的类别，拖动到左边的列表中");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	
	DynamicForm df = new DynamicForm();

	SelectItem categoryItem =  new SelectItem("categoryId","类别");

	
	ListGrid functionGrid =  new ListGrid();
	ListGrid selectedGrid =  new ListGrid();
	
    ListGridField funcidField = new ListGridField("funcId", "功能代码");
    ListGridField funcNameField = new ListGridField("funcName", "功能描述");   
   
    ListGridField funcidField2 = new ListGridField("funcId", "功能代码");
    ListGridField funcNameField2 = new ListGridField("funcName", "功能描述");   

	
	HLayout listGridLayout = new HLayout();
	
	//TransferImgButton arrowImg = new TransferImgButton(TransferImgButton.RIGHT);   
	IButton saveButton = new IButton("保存");
	IButton newCategoryButton = new IButton("新建类别...");
	IButton newFunctionButton = new IButton("新建功能...");
	IButton deleteCategoryButton = new IButton("删除类别...");
	IButton deleteFunctionButton = new IButton("删除功能...");
	LinkedHashMap<String,String> funlist = new LinkedHashMap<String,String>();
	public RightsManagePanel()
	{
		
		init();
		
	}
	
	protected void init()
	{
		setPadding(5);
		
		leftStack.setWidth100();
		leftStack.setHeight100();
		leftStack.setVisibilityMode(VisibilityMode.MULTIPLE);

		conditionSection.setExpanded(true);
		selectionSection.setExpanded(true);

		categoryItem.setDefaultToFirstOption(true);
		
		categoryItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				refreshCategoryFunction(categoryItem.getValueAsString());
				
			}}
		);




		
		//df.setItemLayout(FormLayoutType.ABSOLUTE);
		df.setMargin(5);
		df.setHeight(20);
		df.setWidth100();
		df.setTitleOrientation(TitleOrientation.LEFT);		

		df.setItems(categoryItem);
		

		
		conditionSection.addItem(df);
		leftStack.addSection(conditionSection);
		
		
        
		listGridLayout.setPadding(4);
		listGridLayout.setHeight(350);
     
		functionGrid.setWidth(450);   
		functionGrid.setHeight(350);   
		functionGrid.setShowAllRecords(true);   
		functionGrid.setCanReorderRecords(true);   
		functionGrid.setCanDragRecordsOut(true);   
		functionGrid.setCanAcceptDroppedRecords(true);   
		functionGrid.setDragDataAction(DragDataAction.COPY);   
		functionGrid.setShowHeaderContextMenu(false);
 
		funcidField.setWidth(200);
        
        functionGrid.setFields(funcidField, funcNameField);   
        functionGrid.addDoubleClickHandler(new DoubleClickHandler(){

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				selectedGrid.transferSelectedData(functionGrid);
				
			}});
        
        listGridLayout.addMember(functionGrid);
        
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setWidth(40);
        listGridLayout.addMember(spacer);
      
        selectedGrid.setWidth(450);   
        selectedGrid.setHeight(350);   
        selectedGrid.setEmptyMessage("拖动左边的项目到这里");   
        selectedGrid.setCanReorderFields(true);   
        selectedGrid.setCanDragRecordsOut(false);   
        selectedGrid.setCanAcceptDroppedRecords(true);   
        selectedGrid.setCanRemoveRecords(true);
        selectedGrid.setShowHeaderContextMenu(false);
        
        funcidField2.setWidth(200);
        
        selectedGrid.setFields(funcidField2, funcNameField2);   
        
        selectedGrid.setEditEvent(ListGridEditEvent.CLICK);
        selectedGrid.setPreventDuplicates(true);
        selectedGrid.setDuplicateDragMessage("所选功能已经在已选择列表中,请仔细检查");

        listGridLayout.addMember(selectedGrid);
        
        selectionSection.addItem(listGridLayout);
        
        leftStack.addSection(selectionSection);
        
        actionSection.setShowHeader(true);
        actionSection.setExpanded(true);
        

        HStack buttonStack = new HStack();
        buttonStack.setPadding(10);
        buttonStack.setHeight(30);
		
		saveButton.setLeft(30);
		saveButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				saveMe();
				
			}});
		LayoutSpacer ls = new LayoutSpacer();
		ls.setWidth(20);
		LayoutSpacer ls2 = new LayoutSpacer();
		ls2.setWidth(20);
		buttonStack.addMember(saveButton);
		buttonStack.addMember(ls);
		buttonStack.addMember(newCategoryButton);
		buttonStack.addMember(deleteCategoryButton);
		buttonStack.addMember(ls2);
		buttonStack.addMember(newFunctionButton);
		buttonStack.addMember(deleteFunctionButton);
		
		actionSection.addItem(buttonStack);
		
		leftStack.addSection(actionSection);

		addMember(leftStack);
        
        show();

        populateData();
	}
	
	protected void populateData()
	{
		refreshCategory();
		refreshFunction();
	}
	
	protected void refreshCategory()
	{
		dbService.getRightsCategory(new AsyncCallback<List<RightsCategory>>(){

			@Override
			public void onFailure(Throwable caught) {
					SC.say("获取权限类别失败");
				
			}

			@Override
			public void onSuccess(List<RightsCategory> result) {
				LinkedHashMap<String,String> list = new LinkedHashMap<String,String>();
								
				for(RightsCategory c : result)
				{
					list.put(c.getCategory_id(), c.getCategory_name());
				}
			
				categoryItem.setValueMap(list);
				
			}});
		
		
	}
	protected void refreshFunction()
	{
		dbService.getRightsFunction(new AsyncCallback<List<RightsFunction>>(){

			@Override
			public void onFailure(Throwable caught) {
					SC.say("获取功能列表失败");
				
			}

			@Override
			public void onSuccess(List<RightsFunction> result) {
				
				RecordList list = new RecordList();
				funlist.clear();
				for(RightsFunction c : result)
				{
					Record rec = new Record();
					rec.setAttribute("funcId", c.getFunction_id());
					rec.setAttribute("funcName", c.getFunction_name());
					list.add(rec);
					funlist.put(c.getFunction_id(), c.getFunction_name());
				}
				functionGrid.setData(list);
				
				
			}});
		
	}
	
	protected void refreshCategoryFunction(String category_id)
	
	{
		
		dbService.getRightsCategoryFunction(category_id, new AsyncCallback<List<RightsCategoryFunctionKey>>(){

			@Override
			public void onFailure(Throwable caught) {
				SC.say("获取功能列表失败");
				
			}

			@Override
			public void onSuccess(List<RightsCategoryFunctionKey> result) {
				
				RecordList list = new RecordList();
				
				for(RightsCategoryFunctionKey c : result)
				{
					Record rec = new Record();
					rec.setAttribute("funcId", c.getFunction_id());
					rec.setAttribute("funcName", funlist.get(c.getFunction_id()));
					list.add(rec);
				}
				selectedGrid.setData(list);
				
				
			}});
		
		
	}
//	protected void reload()
//	{
//		//检查所选的组合是否有记录已经存在与college_subject表中，如果有，初始化selectedList列表
//		if (collegeItem.getValue() == null 
//				|| batchItem.getValue() == null 
//				|| levelItem.getValue() == null) return;
//		Integer collegeId = new Integer(collegeItem.getValue().toString());
//		Integer batchId =new Integer(batchItem.getValue().toString());
//		Integer levelId = new Integer(levelItem.getValue().toString());
//		
//
//		AsyncCallback<List<CollegeSubjectView>> callback = new AsyncCallback<List<CollegeSubjectView>>(){
//
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				SC.say("获取招生计划时发生错误");
//			}
//
//			@Override
//			public void onSuccess(List<CollegeSubjectView> result) {
//				// TODO Auto-generated method stub
//				
//				Iterator<CollegeSubjectView> biit = result.iterator();
//				RecordList data = new RecordList();
//				selectedList2.setData(data);
//				while(biit.hasNext())
//				{
//					CollegeSubjectView bi = biit.next();
//					Record rc = new Record();
//					rc.setAttribute("subjectID",bi.getSubeject_id());
//					rc.setAttribute("subjectName", bi.getSubject_name());
//					rc.setAttribute("lol",bi.getLength_of_schooling() + "");
//					data.add(rc);
//				}
//				
//				selectedList2.setData(data);
//
//			}
//
//        };
//		
//        BrightEdu.createDataBaseRPC().getCollegeSubjectList(collegeId, levelId, batchId,callback );
//        
//        
//        
//		
//
//	
//	}
//
	protected void saveMe()
	{
		if (categoryItem.getValue() == null)
		{
			BrightEdu.showTip("开玩笑？");
			return;
		}
			
		
		String categoryId = categoryItem.getValue().toString();
		

		if(selectedGrid.getRecords().length == 0) 
			
			{
				dbService.deleteRightsCatetoryFunctions(categoryId, new AsyncCallback<Boolean>(){
		
					@Override
					public void onFailure(Throwable caught) {
						
						
					}
		
					@Override
					public void onSuccess(Boolean result) {
						
						BrightEdu.showTip("没有啥东西好存的,开玩笑? 我顺便把所有的组合全删了，你没意见吧？");
					}});
			
				return;
			}
		
		
		
		//保存界面上的组合
		
		ArrayList<RightsCategoryFunctionKey> lst = new ArrayList<RightsCategoryFunctionKey>();
		
		RecordList data = selectedGrid.getRecordList();
		
		for(int i = 0 ; i< data.getLength() ; i++)
		{
			
			RightsCategoryFunctionKey cs2 = new RightsCategoryFunctionKey();
			cs2.setCategory_id(categoryId);
			cs2.setFunction_id(data.get(i).getAttributeAsString("funcId"));
			lst.add(cs2);
			
		}
		
		dbService.addRightsCatetoryFunctions(lst, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {

				
			}

			@Override
			public void onSuccess(Boolean result) {
					BrightEdu.showTip("保存成功");
					
					
				
			}});
		
		
		
	}
//	
//	private void setDefaultLOL(ListGrid subjectList,String lol)
//	
//	{
//		RecordList newList = new RecordList();
//		Record[] oldList = subjectList.getRecords();
//		
//		for (Record rec : oldList)
//		{
//			rec.setAttribute("lol", new Integer(lol));
//			newList.add(rec);
//			
//		}
//		subjectList.setData(newList);
//		
//	}
//
//	private void refreshCurrentList(Integer result) {
//		
//		dbService.getRecruitPlanList(result, new AsyncCallback<List<RecruitPlan>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				
//				
//			}
//
//			@Override
//			public void onSuccess(List<RecruitPlan> result) {
//				
//				if (result.size() == 0)
//				{
//					lb.setContents("当前招生计划<b> [ 当前选中批次无招生计划 ] </b>");
//					return;
//				}
//				Iterator<RecruitPlan> rpit = result.iterator();
//				RecordList data = new RecordList();
//				String batchName = "";
//				while(rpit.hasNext())
//				{
//					RecruitPlan rp = rpit.next();
//					Record rc = new Record();
//					rc.setAttribute("college",rp.getCollege_name());
//					rc.setAttribute("level", rp.getClassified_name());
//					rc.setAttribute("subject", rp.getSubject_name());
//					rc.setAttribute("lol2",rp.getLength_of_schooling() + "");
//					batchName = rp.getBatch_name();
//					data.add(rc);
//				}
//				lb.setContents("当前招生计划<b> [ " + batchName + " ] </b>");
//				
//				currentPlan.setData(data);
//				
//			}}
//		);
//		
//	}
}
