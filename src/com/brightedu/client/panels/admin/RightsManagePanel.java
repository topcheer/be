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
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
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
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
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

public class RightsManagePanel extends VLayout {
	
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	HLayout mainPanel = new HLayout();
	
	SectionStack leftStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("权限类别管理");
	SectionStackSection selectionSection = new SectionStackSection ("功能列表");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	
	DynamicForm df = new DynamicForm();
	
	SelectItem categoryItem =  new SelectItem("categoryId","类别");
	ButtonItem removeItem  = new ButtonItem("removeCategory","删除类别");
	ButtonItem addItem  = new ButtonItem("addCategory","新增类别");
	
	ListGrid functionGrid =  new ListGrid();
	ListGrid selectedGrid =  new ListGrid();
	
    ListGridField funcidField = new ListGridField("funcId", "功能代码");
    ListGridField funcNameField = new ListGridField("funcName", "功能描述");   
   
    ListGridField funcidField2 = new ListGridField("funcId", "功能代码");
    ListGridField funcNameField2 = new ListGridField("funcName", "功能描述");   

	
	HLayout listGridLayout = new HLayout();
	
	//TransferImgButton arrowImg = new TransferImgButton(TransferImgButton.RIGHT);   
	IButton saveButton = new IButton("保存");

	
	public RightsManagePanel()
	{
		
		init();
		
	}
	
	protected void init()
	{
		setPadding(5);
		
		leftStack.setWidth(600);
		leftStack.setHeight100();
		leftStack.setVisibilityMode(VisibilityMode.MULTIPLE);

		conditionSection.setExpanded(true);
		selectionSection.setExpanded(true);

//        BrightEdu.createDataBaseRPC().getBatchList(-1, -1, false, new AsyncCallback<List<BatchIndex>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				SC.say("获取批次时发生错误");
//			}
//
//			@Override
//			public void onSuccess(List<BatchIndex> result) {
//				// TODO Auto-generated method stub
//				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
//				Iterator<BatchIndex> biit = result.iterator();
//				while(biit.hasNext())
//				{
//					BatchIndex bi = biit.next();
//					list.put(bi.getBatch_id() + "", bi.getBatch_name());
//				}
//				
//				categoryItem.setValueMap(list);
//			}
//        	
//        });
		
		categoryItem.setDefaultToFirstOption(true);
		
//		categoryItem.addChangedHandler(new ChangedHandler(){
//
//			@Override
//			public void onChanged(ChangedEvent event) {
////				reload();
////				refreshCurrentList(new Integer(batchItem.getValueAsString()));
//				
//			}}
//		);




		
		
		
		df.setItems(categoryItem,removeItem,addItem);
		
		df.setHeight(20);
		df.setWidth100();
		df.setTitleOrientation(TitleOrientation.TOP);
		
		conditionSection.addItem(df);
		leftStack.addSection(conditionSection);
		
		
        
		listGridLayout.setPadding(4);
		listGridLayout.setHeight(350);
     
		functionGrid.setWidth(250);   
		functionGrid.setHeight(350);   
		functionGrid.setShowAllRecords(true);   
		functionGrid.setCanReorderRecords(true);   
		functionGrid.setCanDragRecordsOut(true);   
		functionGrid.setCanAcceptDroppedRecords(true);   
		functionGrid.setDragDataAction(DragDataAction.COPY);   
		functionGrid.setShowHeaderContextMenu(false);
 
		funcidField.setWidth(100);
        
        functionGrid.setFields(funcidField, funcNameField);   
        listGridLayout.addMember(functionGrid);
        
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setWidth(20);
        listGridLayout.addMember(spacer);
      
        selectedGrid.setWidth(250);   
        selectedGrid.setHeight(350);   
        selectedGrid.setEmptyMessage("拖动左边的项目到这里");   
        selectedGrid.setCanReorderFields(true);   
        selectedGrid.setCanDragRecordsOut(false);   
        selectedGrid.setCanAcceptDroppedRecords(true);   
        selectedGrid.setCanRemoveRecords(true);
        selectedGrid.setShowHeaderContextMenu(false);
        
        funcidField2.setWidth(100);
        
        selectedGrid.setFields(funcidField2, funcNameField2);   
        
        selectedGrid.setEditEvent(ListGridEditEvent.CLICK);
        selectedGrid.setPreventDuplicates(true);
        selectedGrid.setDuplicateDragMessage("所选功能已经在已选择列表中,请仔细检查");

        listGridLayout.addMember(selectedGrid);
        
        selectionSection.addItem(listGridLayout);
        
        leftStack.addSection(selectionSection);
        
        actionSection.setShowHeader(true);
        actionSection.setExpanded(true);
        
		
		saveButton.setLeft(30);
		saveButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
//				saveMe();
				
			}});
		

		LayoutSpacer ls = new LayoutSpacer();
		ls.setWidth(20);
		
		actionSection.addItem(ls);
		
		actionSection.addItem(saveButton);
		
		leftStack.addSection(actionSection);

		addMember(leftStack);
        
        show();
        

         
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
//	protected void saveMe()
//	{
//		if (collegeItem.getValue() == null 
//				|| batchItem.getValue() == null 
//				|| levelItem.getValue() == null)
//		{
//			BrightEdu.showTip("开玩笑？");
//			return;
//		}
//			
//		
//		Integer collegeId = new Integer(collegeItem.getValue().toString());
//		final Integer batchId = new Integer(batchItem.getValue().toString());
//		Integer levelId = new Integer(levelItem.getValue().toString());
//		
//		if(selectedList2.getRecords().length == 0) 
//			
//			{
//				
//				CollegeSubject cs = new CollegeSubject();
//				cs.setBatch_id(batchId);
//				cs.setClassified_id(levelId);
//				cs.setCollege_id(collegeId);
//		
//				dbService.deletCollegeSubject(cs, new AsyncCallback<Boolean>(){
//		
//					@Override
//					public void onFailure(Throwable caught) {
//						
//						
//					}
//		
//					@Override
//					public void onSuccess(Boolean result) {
//						
//						BrightEdu.showTip("没有啥东西好存的,开玩笑? 我顺便把所有的组合全删了，你没意见吧？");
//					}});
//			
//				return;
//			}
//		
//		
//		
//		//保存界面上的组合
//		
//		ArrayList<CollegeSubject> lst = new ArrayList<CollegeSubject>();
//		
//		RecordList data = selectedList2.getRecordList();
//		
//		for(int i = 0 ; i< data.getLength() ; i++)
//		{
//			
//			CollegeSubject cs2 = new CollegeSubject();
//			cs2.setBatch_id(batchId);
//			cs2.setClassified_id(levelId);
//			cs2.setCollege_id(collegeId);
//
//			cs2.setSubeject_id(data.get(i).getAttributeAsInt("subjectID"));
//			cs2.setLength_of_schooling(new Short(data.get(i).getAttribute("lol")));
//			lst.add(cs2);
//			
//		}
//		
//		dbService.addCollegeSubject(lst, new AsyncCallback<Boolean>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//
//				
//			}
//
//			@Override
//			public void onSuccess(Boolean result) {
//					BrightEdu.showTip("保存成功");
//					
//					refreshCurrentList(batchId);
//				
//			}});
//		
//		
//		
//	}
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
