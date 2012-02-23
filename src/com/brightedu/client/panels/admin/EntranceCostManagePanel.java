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
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.CellEditValueFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class EntranceCostManagePanel extends VLayout {
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	SectionStack mainStack = new SectionStack();   
	//SectionStackSection conditionSection = new SectionStackSection ("条件");
	SectionStackSection selectionSection = new SectionStackSection ("操作");
	SectionStackSection listSection = new SectionStackSection ("列表");
	HLayout selectionStack = new HLayout();
	ListGrid batchList = new ListGrid();
	ListGridField batchIdField = new ListGridField("batchId","批次ID");
	ListGridField batchNameField = new ListGridField("batchName","批次名称");
	
	ListGrid agentList = new ListGrid();
	ListGridField agentIdField = new ListGridField("agentID","招生点/学习中心ID");
	ListGridField agentNameField = new ListGridField("agentName","招生点/学习中心名称");
	
	ListGrid recruitPlanList = new ListGrid();
	
	ListGridField collegeField = new ListGridField("college","大学");
	ListGridField levelField = new ListGridField("level","层次");
	ListGridField  subjField = new ListGridField("subject","专业");
	
	ListGrid feeList = new ListGrid();
	ListGridField  fee_typeIdField = new ListGridField("fee_type_id","费用类型ID");
	ListGridField  fee_typeField = new ListGridField("fee_type","费用类型");
	ListGridField  feeField = new ListGridField("fee","费用");
	
	ListGrid entranceCostList = new ListGrid();
	
	ListGridField agentField2 = new ListGridField("agentName2","招生点/学习中心名称");
	ListGridField collegeField2 = new ListGridField("college2","大学");
	ListGridField levelField2 = new ListGridField("level2","层次");
	ListGridField  subjField2 = new ListGridField("subject2","专业");
	ListGridField  fee_typeField2 = new ListGridField("fee_type2","费用类型");
	ListGridField  feeField2 = new ListGridField("fee2","费用");
	
	IButton saveButton = new IButton("保存");
	IButton cloneButton = new IButton("克隆...");
	
	LinkedHashMap<String,Integer> levelReverse = new LinkedHashMap<String,Integer>();
	LinkedHashMap<String,Integer> collegeReverse = new LinkedHashMap<String,Integer>();
	LinkedHashMap<String,Integer> subjectReverse = new LinkedHashMap<String,Integer>();
	
	public EntranceCostManagePanel()
	{
		batchIdField.setHidden(true);
		agentIdField.setHidden(true);
		fee_typeIdField.setHidden(true);
		feeField.setCanEdit(true);
		feeList.setEditEvent(ListGridEditEvent.CLICK);
		
		batchList.setSelectionType(SelectionStyle.SINGLE);
		agentList.setSelectionType(SelectionStyle.SIMPLE);
		recruitPlanList.setSelectionType(SelectionStyle.SIMPLE);
		batchList.setShowHeaderContextMenu(false);
		agentList.setShowHeaderContextMenu(false);
		recruitPlanList.setShowHeaderContextMenu(false);
		
		selectionStack.setHeight(350);
		selectionStack.setPadding(10);
		selectionStack.setMargin(10);
		
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
		vPlan.setWidth(300);
		vPlan.setAlign(VerticalAlignment.TOP);
		Label lPlan = new Label("选择招生计划");
		lPlan.setHeight(20);
		vPlan.addMember(lPlan);
		vPlan.addMember(recruitPlanList);
		selectionStack.addMember(vPlan);
		
		selectionStack.addMember(ls3);
		
		VLayout vFee = new VLayout();
		vFee.setWidth(250);
		vFee.setAlign(VerticalAlignment.TOP);
		Label lFee = new Label("设置费用");
		lFee.setHeight(20);
		vFee.addMember(lFee);
		vFee.addMember(feeList);
		selectionStack.addMember(vFee);
		
		selectionStack.addMember(ls4);
		
		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(30);
		buttonLayout.setWidth(1000);
		buttonLayout.setAlign(Alignment.RIGHT);
		
		LayoutSpacer ls5 = new LayoutSpacer();
		ls5.setWidth(40);
		
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(ls5);
		buttonLayout.addMember(cloneButton);
		//selectionStack.addMember(buttonLayout);
		
		selectionSection.addItem(selectionStack);
		selectionSection.addItem(buttonLayout);
		
		selectionSection.setExpanded(true);
		selectionSection.setShowHeader(false);
		batchList.setFields(batchIdField,batchNameField);
		agentList.setFields(agentIdField,agentNameField);
		recruitPlanList.setFields(collegeField,levelField,subjField);
		feeList.setFields(fee_typeIdField,fee_typeField,feeField);
		
		mainStack.addSection(selectionSection);
		
		entranceCostList.setFields(agentField2,collegeField2,levelField2,subjField2,fee_typeField2,feeField2);
		
		listSection.addItem(entranceCostList);
		listSection.setExpanded(true);
		listSection.setResizeable(true);
		
		mainStack.addSection(listSection);
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		addMember(mainStack);
		
		initValueMaps();
		
		loadBatch();
		loadAgent();
		loadFeetype();
		batchList.addSelectionChangedHandler(new SelectionChangedHandler(){

			@Override
			public void onSelectionChanged(SelectionEvent event) {
				loadRecruitPlan(new Integer(event.getRecord().getAttribute("batchId")));
				refreshCurrentEntranceCostList(event.getRecord().getAttribute("batchId"));
			}});
		saveButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				saveMe();
				
			}}
		);
		
	}

	protected void refreshCurrentEntranceCostList(String batchID) {
		
		dbService.getEntranceCost(batchID, null, new CommonAsyncCall<List<EntranceCost>>(){

			@Override
			public void onSuccess(List<EntranceCost> result) {
				RecordList list = new RecordList();
				for (EntranceCost cost : result)
				{
					Record r = new Record();
					r.setAttribute("agentName2", cost.getAgent_id()+"");
					r.setAttribute("college2", cost.getCollege_id()+"");
					r.setAttribute("level2", cost.getClassified_id()+"");
					r.setAttribute("subject2", cost.getSubject_id()+"");
					r.setAttribute("fee_type2", cost.getFee_id()+"");
					r.setAttribute("fee2", cost.getFee()+"");
					list.add(r);
				}
				entranceCostList.setData(list);
				
			}}
		);
		
	}

	private void initValueMaps() {

		dbService.getCollegeList(-1,-1,  false, new CommonAsyncCall<List<College>>(){

			@Override
			public void onSuccess(List<College> result) {
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				for(College c : result)
				{
					list.put(c.getCollege_id()+"", c.getCollege_name());
					collegeReverse.put(c.getCollege_name(), c.getCollege_id());
				}
				collegeField2.setValueMap(list);
				
			}}
		);
		
		dbService.getStudentClassesList(-1,-1,  false, new CommonAsyncCall<List<StudentClassified>>(){

			@Override
			public void onSuccess(List<StudentClassified> result) {
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				for(StudentClassified c : result)
				{
					list.put(c.getClassified_id()+"", c.getClassified_name());
					levelReverse.put(c.getClassified_name(), c.getClassified_id());
				}
				levelField2.setValueMap(list);
			}}
		);
		
		dbService.getSubjectsList(-1,-1,  false, new CommonAsyncCall<List<Subjects>>(){

			@Override
			public void onSuccess(List<Subjects> result) {
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				for(Subjects c : result)
				{
					list.put(c.getSubject_id()+"", c.getSubject_name());
					subjectReverse.put(c.getSubject_name(), c.getSubject_id());
				}
				subjField2.setValueMap(list);
			}}
		);

		dbService.getFeeTypeList(-1,-1,  false, new CommonAsyncCall<List<FeeType>>(){

			@Override
			public void onSuccess(List<FeeType> result) {
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				for(FeeType c : result)
				{
					list.put(c.getFee_id()+"", c.getFee_name());
				}
				fee_typeField2.setValueMap(list);
			}}
		);
				
	}

	protected void saveMe() {
		
		
		if(batchList.getSelectedRecords().length == 0 || agentList.getSelectedRecords().length == 0 || recruitPlanList.getSelectedRecords().length == 0) 
		{
			BrightEdu.showTip("批次,招生点,招生计划一个都不能少!");
			return;
		}
		
		String batchID = batchList.getSelectedRecord().getAttribute("batchId");
		Record[] agents = agentList.getSelectedRecords();
		Record[] plans = recruitPlanList.getSelectedRecords();
		Record[] fees = feeList.getRecords();
		ArrayList<EntranceCost> entranceCosts = new ArrayList<EntranceCost>();
		for(Record fee : fees)
		{
			
			if(new Integer(fee.getAttribute("fee")).intValue() ==0 )continue;
			
			for(Record agent : agents)
			{
				for(Record  plan : plans)
				{
					EntranceCost cost = new EntranceCost();
					cost.setFee(new BigDecimal(fee.getAttribute("fee")));
					cost.setBatch_id(new Integer(batchID));
					cost.setAgent_id(new Integer(agent.getAttribute("agentId")));
					cost.setClassified_id(levelReverse.get(plan.getAttribute("level")));
					cost.setCollege_id(collegeReverse.get(plan.getAttribute("college")));
					cost.setSubject_id(subjectReverse.get(plan.getAttribute("subject")));
					cost.setFee_id(new Integer(fee.getAttribute("fee_type_id")));
					entranceCosts.add(cost);
				}
			}

			
		}
		dbService.saveEntranceCost(entranceCosts, new CommonAsyncCall<Boolean>(){

			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("保存成功!");
				
			}});
		
		
	}

	private void loadFeetype() {
		dbService.getFeeTypeList(-1, -1, false, new CommonAsyncCall<List<FeeType>>(){

			@Override
			public void onSuccess(List<FeeType> result) {
				
				RecordList list = new RecordList();
				
				for(FeeType f : result)
				{
					Record r = new Record();
					r.setAttribute("fee_type_id", f.getFee_id());
					r.setAttribute("fee_type", f.getFee_name());
					r.setAttribute("fee", 0.00);
					list.add(r);
				}
						feeList.setData(list);
				
			}});
		
	}

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
				agentField2.setValueMap(agentMap);
				
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
		
		new Timer(){

			@Override
			public void run() {
				dbService.getCurrentBatch(new CommonAsyncCall<Integer>(){

					@Override
					public void onSuccess(Integer result) {
						
						if(result == -1) return;
						
						Record[] list = batchList.getDataAsRecordList().duplicate();
						
						for(Record rec: list)
						{
							if(rec.getAttribute("batchId").equals(result+""))
							{
								batchList.selectRecord(rec);
								break;
							}
						}
						
						//load current batch recruit plan
						loadRecruitPlan(new Integer(batchList.getSelectedRecord().getAttribute("batchId")));
						
					}
				});
				
				
			}}.schedule(2000);
		
	}
	


	private void loadRecruitPlan(Integer batchId) {
		
		dbService.getRecruitPlanList(batchId, new AsyncCallback<List<RecruitPlan>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<RecruitPlan> result) {
				
				if (result.size() == 0)
				{
					recruitPlanList.setData(new RecordList());
					return;
				}
				Iterator<RecruitPlan> rpit = result.iterator();
				RecordList data = new RecordList();
				
				while(rpit.hasNext())
				{
					RecruitPlan rp = rpit.next();
					Record rc = new Record();
					rc.setAttribute("college",rp.getCollege_name());
					rc.setAttribute("level", rp.getClassified_name());
					rc.setAttribute("subject", rp.getSubject_name());
					data.add(rc);
				}
				
				recruitPlanList.setData(data);
				
			}}
		);
	}
	
}
