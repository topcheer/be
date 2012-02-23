package com.brightedu.client.panels.admin;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.EntranceCost;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.CellEditValueFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDropEvent;
import com.smartgwt.client.widgets.grid.events.RecordDropHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class AgentRateManagePanel extends VLayout {
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
 
	//SectionStackSection conditionSection = new SectionStackSection ("条件");
	
	HLayout selectionStack = new HLayout();
	HLayout groupStack = new HLayout();
	ListGrid batchList = new ListGrid();
	ListGridField batchIdField = new ListGridField("batchId","批次ID");
	ListGridField batchNameField = new ListGridField("batchName","批次名称");
	
	ListGrid agentList = new ListGrid();
	ListGridField agentIdField = new ListGridField("agentID","招生点/学习中心ID");
	ListGridField agentNameField = new ListGridField("agentName","招生点/学习中心名称");
	
	
	ListGrid collegeList = new ListGrid();
	ListGridField collegeIDField = new ListGridField("collegeID","大学ID");
	ListGridField collegeNameField = new ListGridField("collegeName","大学");
	
	ListGrid groupList ;
	
	


	
	IButton saveButton = new IButton("保存");
	IButton cloneButton = new IButton("克隆...");
	
	LinkedHashMap<String,Integer> levelReverse = new LinkedHashMap<String,Integer>();
	LinkedHashMap<String,Integer> collegeReverse = new LinkedHashMap<String,Integer>();
	LinkedHashMap<String,Integer> subjectReverse = new LinkedHashMap<String,Integer>();
	
	public AgentRateManagePanel()
	{
		batchIdField.setHidden(true);
		agentIdField.setHidden(true);
		collegeIDField.setHidden(true);

		
		batchList.setSelectionType(SelectionStyle.SINGLE);
		agentList.setSelectionType(SelectionStyle.SINGLE);
		

		batchList.setShowHeaderContextMenu(false);
		agentList.setShowHeaderContextMenu(false);
		collegeList.setShowHeaderContextMenu(false);
		
		collegeList.setCanDragRecordsOut(true);
		collegeList.setDragAppearance(DragAppearance.TRACKER);
		collegeList.setDragDataAction(DragDataAction.COPY);
		

		
		selectionStack.setHeight(250);
		selectionStack.setPadding(5);
		selectionStack.setMargin(5);
		
		groupStack.setHeight100();
		groupStack.setWidth(635);
		groupStack.setPadding(5);
		groupStack.setMargin(5);
		
		LayoutSpacer ls1 = new LayoutSpacer();
		LayoutSpacer ls2 = new LayoutSpacer();
		LayoutSpacer ls3 = new LayoutSpacer();
		LayoutSpacer ls4 = new LayoutSpacer();
		
		ls1.setWidth(10);
		ls2.setWidth(10);
		ls3.setWidth(10);
		
		
		VLayout vbatch = new VLayout();
		vbatch.setWidth(200);
		vbatch.setAlign(VerticalAlignment.TOP);
		Label lBatch = new Label("选择批次");
		lBatch.setHeight(20);
		vbatch.addMember(lBatch);
		vbatch.addMember(batchList);
		selectionStack.addMember(vbatch);
		
		selectionStack.addMember(ls1);
		
		VLayout vAgent = new VLayout();
		vAgent.setWidth(200);
		
		vAgent.setAlign(VerticalAlignment.TOP);
		Label lAgent = new Label("选择招生点/学习中心");
		lAgent.setHeight(20);
		vAgent.addMember(lAgent);
		vAgent.addMember(agentList);
		selectionStack.addMember(vAgent);
		
		selectionStack.addMember(ls2);
		
		VLayout vPlan = new VLayout();
		vPlan.setWidth(200);
		vPlan.setAlign(VerticalAlignment.TOP);
		Label lPlan = new Label("拖动大学到下边列表以创建组别");
		lPlan.setHeight(20);
		vPlan.addMember(lPlan);
		vPlan.addMember(collegeList);
		selectionStack.addMember(vPlan);
		
		selectionStack.addMember(ls3);
		
		groupStack.setIsGroup(true);
		groupStack.setGroupTitle("合并计算高校组别设置");
		
		ListGridField group = new ListGridField("groupName","组别");
		ListGridField dropedRecord = new ListGridField("records","组别");
		dropedRecord.setHidden(true);
		groupList = new ListGrid(){
			
			 @Override  
	            protected Canvas getExpansionComponent(final ListGridRecord record) {
					
	                final ListGrid grid = this;   
	                
	                VLayout layout = new VLayout(5);   
	                layout.setPadding(5);  
	        		
	        		ListGrid rateList = new ListGrid();
//	        		ListGridField  batchIdField2 = new ListGridField("batchId","批次ID");
	        		ListGridField  collegeIDField2 = new ListGridField("collegeID","大学ID");
	        		ListGridField  collegeNameField2 = new ListGridField("collegeName","大学");
	        		ListGridField  fee_typeIdField2 = new ListGridField("fee_type_id","费用类型ID");
	        		ListGridField  fee_typeNameField2 = new ListGridField("fee_type","费用类型");
	        		ListGridField  pepleCountField2 = new ListGridField("people_count","人数上限");
	        		ListGridField  returnRateField = new ListGridField("return_rate","返利系数");
	        		
	        		collegeIDField2.setHidden(true);
	        		fee_typeIdField2.setHidden(true);	
	        		
	        		rateList.setCanDragRecordsOut(false);
	        		rateList.setCanAcceptDroppedRecords(true);
	        		rateList.setCanRemoveRecords(true);
	        		
	        		rateList.setFields(collegeIDField2,collegeNameField2,fee_typeIdField2,fee_typeNameField2,pepleCountField2,returnRateField);

	                rateList.setWidth100();
	                rateList.setHeight(100);
	                layout.addMember(rateList);   
	  
	                HLayout hLayout = new HLayout(10);   
	                hLayout.setAlign(Alignment.CENTER);   
	  
	                IButton saveButton = new IButton("保存");   
	                saveButton.setTop(250);   
	                saveButton.addClickHandler(new ClickHandler() {   
	                    public void onClick(ClickEvent event) {   
	                        saveGroup();   
	                    }

	                });   
	                hLayout.addMember(saveButton);   
	  

	  
	                IButton closeButton = new IButton("关闭");   
	                closeButton.addClickHandler(new ClickHandler() {   
	                    public void onClick(ClickEvent event) {   
	                        grid.collapseRecord(record);   
	                    }   
	                });   
	                hLayout.addMember(closeButton);   
	                                                  
	                layout.addMember(hLayout);   
	  
	                return layout;   

	  
			 }
			
		};
		groupList.setCanAcceptDrop(true);
		groupList.setCanAcceptDroppedRecords(true);
		groupList.setCanExpandRecords(true);
		groupList.addRecordDropHandler(new RecordDropHandler(){

			@Override
			public void onRecordDrop(RecordDropEvent event) {
				
				Record r = new Record();
				r.setAttribute("groupName", "Group1");
				r.setAttribute("records", event.getDropRecords());
				groupList.addData(r);
				event.cancel();
				
			}});
		
		
		groupList.setFields(group,dropedRecord);
		
		
		groupStack.addMember(groupList);
		
		selectionStack.addMember(ls4);

		batchList.setFields(batchIdField,batchNameField);
		agentList.setFields(agentIdField,agentNameField);
		collegeList.setFields(collegeIDField,collegeNameField);



		addMember(selectionStack);
		addMember(groupStack);
		
//		initValueMaps();
		
		loadBatch();
		loadAgent();
		loadCollege();
//		batchList.addSelectionChangedHandler(new SelectionChangedHandler(){
//
//			@Override
//			public void onSelectionChanged(SelectionEvent event) {
//				loadRecruitPlan(new Integer(event.getRecord().getAttribute("batchId")));
//				refreshCurrentEntranceCostList(event.getRecord().getAttribute("batchId"));
//			}});
//		saveButton.addClickHandler(new ClickHandler(){
//
//			@Override
//			public void onClick(ClickEvent event) {
//				saveMe();
//				
//			}}
//		);
//		

	}

	protected void saveGroup() {
		// TODO Auto-generated method stub
		
	}   
private void loadCollege() {
	dbService.getCollegeList(-1,-1,  false, new CommonAsyncCall<List<College>>(){

		@Override
		public void onSuccess(List<College> result) {
			RecordList list  = new RecordList();
			for(College c : result)
			{
				Record rc = new Record();
				rc.setAttribute("collegeID", c.getCollege_id()+"");
				rc.setAttribute("collegeName", c.getCollege_name());
				list.add(rc);
				
			}
			collegeList.setData(list);
			
		}}
	);
		
	}

//	protected void refreshCurrentEntranceCostList(String batchID) {
//		
//		dbService.getEntranceCost(batchID, null, new CommonAsyncCall<List<EntranceCost>>(){
//
//			@Override
//			public void onSuccess(List<EntranceCost> result) {
//				RecordList list = new RecordList();
//				for (EntranceCost cost : result)
//				{
//					Record r = new Record();
//					r.setAttribute("obj", cost);
//					r.setAttribute("agentName2", cost.getAgent_id()+"");
//					r.setAttribute("college2", cost.getCollege_id()+"");
//					r.setAttribute("level2", cost.getClassified_id()+"");
//					r.setAttribute("subject2", cost.getSubject_id()+"");
//					r.setAttribute("fee_type2", cost.getFee_id()+"");
//					r.setAttribute("fee2", cost.getFee()+"");
//					list.add(r);
//				}
//				entranceCostList.setData(list);
//				
//			}}
//		);
//		
//	}

//	private void initValueMaps() {
//
//		dbService.getCollegeList(-1,-1,  false, new CommonAsyncCall<List<College>>(){
//
//			@Override
//			public void onSuccess(List<College> result) {
//				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
//				for(College c : result)
//				{
//					list.put(c.getCollege_id()+"", c.getCollege_name());
//					collegeReverse.put(c.getCollege_name(), c.getCollege_id());
//				}
//				collegeField2.setValueMap(list);
//				
//			}}
//		);
//		
//		dbService.getStudentClassesList(-1,-1,  false, new CommonAsyncCall<List<StudentClassified>>(){
//
//			@Override
//			public void onSuccess(List<StudentClassified> result) {
//				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
//				for(StudentClassified c : result)
//				{
//					list.put(c.getClassified_id()+"", c.getClassified_name());
//					levelReverse.put(c.getClassified_name(), c.getClassified_id());
//				}
//				levelField2.setValueMap(list);
//			}}
//		);
//		
//		dbService.getSubjectsList(-1,-1,  false, new CommonAsyncCall<List<Subjects>>(){
//
//			@Override
//			public void onSuccess(List<Subjects> result) {
//				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
//				for(Subjects c : result)
//				{
//					list.put(c.getSubject_id()+"", c.getSubject_name());
//					subjectReverse.put(c.getSubject_name(), c.getSubject_id());
//				}
//				subjField2.setValueMap(list);
//			}}
//		);
//
//		dbService.getFeeTypeList(-1,-1,  false, new CommonAsyncCall<List<FeeType>>(){
//
//			@Override
//			public void onSuccess(List<FeeType> result) {
//				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
//				for(FeeType c : result)
//				{
//					list.put(c.getFee_id()+"", c.getFee_name());
//				}
//				fee_typeField2.setValueMap(list);
//			}}
//		);
//				
//	}

//	protected void saveMe() {
//		
//		
//		if(batchList.getSelectedRecords().length == 0 || agentList.getSelectedRecords().length == 0 || recruitPlanList.getSelectedRecords().length == 0) 
//		{
//			BrightEdu.showTip("批次,招生点,招生计划一个都不能少!");
//			return;
//		}
//		
//		String batchID = batchList.getSelectedRecord().getAttribute("batchId");
//		Record[] agents = agentList.getSelectedRecords();
//		Record[] plans = recruitPlanList.getSelectedRecords();
//		Record[] fees = feeList.getRecords();
//		ArrayList<EntranceCost> entranceCosts = new ArrayList<EntranceCost>();
//		for(Record fee : fees)
//		{
//			
//			if(new Integer(fee.getAttribute("fee")).intValue() ==0 )continue;
//			
//			for(Record agent : agents)
//			{
//				for(Record  plan : plans)
//				{
//					EntranceCost cost = new EntranceCost();
//					cost.setFee(new BigDecimal(fee.getAttribute("fee")));
//					cost.setBatch_id(new Integer(batchID));
//					cost.setAgent_id(new Integer(agent.getAttribute("agentId")));
//					cost.setClassified_id(levelReverse.get(plan.getAttribute("level")));
//					cost.setCollege_id(collegeReverse.get(plan.getAttribute("college")));
//					cost.setSubject_id(subjectReverse.get(plan.getAttribute("subject")));
//					cost.setFee_id(new Integer(fee.getAttribute("fee_type_id")));
//					entranceCosts.add(cost);
//				}
//			}
//
//			
//		}
//		dbService.saveEntranceCost(entranceCosts, new CommonAsyncCall<Boolean>(){
//
//			@Override
//			public void onSuccess(Boolean result) {
//				BrightEdu.showTip("保存成功!");
//				refreshCurrentEntranceCostList(batchList.getSelectedRecord().getAttribute("batchId"));
//			}});
//		
//		
//	}

//	private void loadFeetype() {
//		dbService.getFeeTypeList(-1, -1, false, new CommonAsyncCall<List<FeeType>>(){
//
//			@Override
//			public void onSuccess(List<FeeType> result) {
//				
//				RecordList list = new RecordList();
//				
//				for(FeeType f : result)
//				{
//					Record r = new Record();
//					r.setAttribute("fee_type_id", f.getFee_id());
//					r.setAttribute("fee_type", f.getFee_name());
//					r.setAttribute("fee", 0.00);
//					list.add(r);
//				}
//						feeList.setData(list);
//				
//			}});
//		
//	}

	private void loadAgent() {
		
		dbService.getRecruitAgentList(-1, -1, false, new CommonAsyncCall<List<RecruitAgent>>(){

			@Override
			public void onSuccess(List<RecruitAgent> result) {
				
				RecordList list = new RecordList();
				LinkedHashMap<String,String> agentMap = new LinkedHashMap<String,String>();
				for(RecruitAgent x : result)
				{
					Record record = new Record();
					record.setAttribute("agentId", x.getAgent_id()+"");
					record.setAttribute("agentName", x.getAgent_name());
					list.add(record);
					agentMap.put(x.getAgent_id() + "",x.getAgent_name());
				}
				agentList.setData(list);
//				agentField2.setValueMap(agentMap);
				
			}});	
	}

	private void loadBatch() {
		dbService.getBatchList(-1, -1, false, new CommonAsyncCall<List<BatchIndex>>(){

			@Override
			public void onSuccess(List<BatchIndex> result) {
				
				RecordList list = new RecordList();
				for(BatchIndex x : result)
				{
					Record record = new Record();
					record.setAttribute("batchId", x.getBatch_id()+"");
					record.setAttribute("batchName", x.getBatch_name());
					list.add(record);
				}
				batchList.setData(list);
				
			}});
		
		//get current batch after 1 second
//		
//		new Timer(){
//
//			@Override
//			public void run() {
//				dbService.getCurrentBatch(new CommonAsyncCall<Integer>(){
//
//					@Override
//					public void onSuccess(Integer result) {
//						
//						if(result == -1) return;
//						
//						Record[] list = batchList.getDataAsRecordList().duplicate();
//						
//						for(Record rec: list)
//						{
//							if(rec.getAttribute("batchId").equals(result+""))
//							{
//								batchList.selectRecord(rec);
//								break;
//							}
//						}
//						
//						//load current batch recruit plan
//						loadRecruitPlan(new Integer(batchList.getSelectedRecord().getAttribute("batchId")));
//						
//					}
//				});
//				
//				
//			}}.schedule(2000);
		
	}
	


//	private void loadRecruitPlan(Integer batchId) {
//		
//		dbService.getRecruitPlanList(batchId, new AsyncCallback<List<RecruitPlan>>(){
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
//					recruitPlanList.setData(new RecordList());
//					return;
//				}
//				Iterator<RecruitPlan> rpit = result.iterator();
//				RecordList data = new RecordList();
//				
//				while(rpit.hasNext())
//				{
//					RecruitPlan rp = rpit.next();
//					Record rc = new Record();
//					rc.setAttribute("college",rp.getCollege_name());
//					rc.setAttribute("level", rp.getClassified_name());
//					rc.setAttribute("subject", rp.getSubject_name());
//					data.add(rc);
//				}
//				
//				recruitPlanList.setData(data);
//				
//			}}
//		);
//	}
	
}
