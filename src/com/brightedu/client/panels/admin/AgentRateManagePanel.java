package com.brightedu.client.panels.admin;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.model.edu.AgentReturnKey;
import com.brightedu.model.edu.AgentReturnType;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAggregation;
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
import com.smartgwt.client.widgets.form.FormItemValueParser;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.CellEditValueFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordCollapseEvent;
import com.smartgwt.client.widgets.grid.events.RecordCollapseHandler;
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
	HLayout mainPane = new HLayout();
	VLayout leftPane = new VLayout();
	VLayout	rightPane = new VLayout();
	
	
	
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
	
	ListGrid currentList = new ListGrid();
	
	LinkedHashMap<String,String> feeTypeMap = new LinkedHashMap<String,String>();


	
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
		collegeList.setDragDataAction(DragDataAction.MOVE);
		collegeList.setSelectionType(SelectionStyle.SIMPLE);
		collegeList.setTitle("选中合并计算的记录并拖动到下面的列表中以创建合并计算高校组合");
		
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
		
		ListGridField  fee_typeIdField2 = new ListGridField("fee_type_id","费用类型ID");
		ListGridField  fee_typeNameField2 = new ListGridField("fee_type","费用类型");
		fee_typeIdField2.setHidden(true);	
		
		ListGridField group = new ListGridField("groupName","组别");
		ListGridField dropedRecord = new ListGridField("records","组别");
		dropedRecord.setHidden(true);
		groupList = new ListGrid(){
					
			 @Override  
	            protected Canvas getExpansionComponent(final ListGridRecord record) {
					
				
				 
	                final ListGrid grid = this;   
	                
	                VLayout layout = new VLayout(5);   
	                layout.setPadding(5);  
	        		
	        		final ListGrid rateList = new ListGrid();
	        		rateList.setEditEvent(ListGridEditEvent.CLICK);
	        		ListGridField  collegeIDField2 = new ListGridField("collegeID","大学ID");
	        		ListGridField  collegeNameField2 = new ListGridField("collegeName","大学");
	        		ListGridField  pepleCountField2 = new ListGridField("people_count","人数上限");
	        		ListGridField  returnRateField = new ListGridField("return_rate","返利系数");
	        		
//	        		TextItem people = new TextItem("people");
//	        		TextItem rate = new TextItem("rate");
//	        		
//	        		people.setMask("###");
//	        		
//	        		rate.setMask("##");
//	        		rate.setEditorValueParser(new FormItemValueParser(){
//
//						@Override
//						public Object parseValue(String value,
//								DynamicForm form, FormItem item) {
//							if(value != null)
//							{
//								return value + "%";
//							}
//							return null;
//						}});
	        		pepleCountField2.setCanEdit(true);
	        		
//	        		pepleCountField2.setEditorType(people);
	        		returnRateField.setCanEdit(true);
//	        		returnRateField.setEditorType(rate);
	        		
	        		collegeIDField2.setHidden(true);
	        		rateList.setShowHeaderContextMenu(false);
	        		rateList.setCanReorderRecords(true);
	        		rateList.setCanRemoveRecords(true);
	        		rateList.setDragDataAction(DragDataAction.COPY);
	        		
	        		rateList.setFields(collegeIDField2,collegeNameField2,pepleCountField2,returnRateField);
	        		rateList.setTitle("拖动并放开选中的记录将在本列表框中拷贝相同的记录，便于设置多个级别");
	                rateList.setWidth100();
	                rateList.setHeight(150);
	                layout.addMember(rateList);   
	  
	                Record[] dropedRecords = record.getAttributeAsRecordArray("records");
	                
	                for(Record rec : dropedRecords)
	                {
	                	Record rec2 = new Record();
	                	rec2.setAttribute("collegeID", rec.getAttribute("collegeID"));
	                	rec2.setAttribute("collegeName", rec.getAttribute("collegeName"));
	                	rec2.setAttribute("people_count", 0);
	                	rec2.setAttribute("return_rate", 0.1);
	                	rateList.addData(rec2);
	                	
	                }
	                
	                HLayout hLayout = new HLayout(10);   
	                hLayout.setAlign(Alignment.CENTER);   
	  
	                IButton saveButton = new IButton("保存");   
	                saveButton.setTop(250);   
	                saveButton.addClickHandler(new ClickHandler() {   
	                    public void onClick(ClickEvent event) {   
	                        saveGroup(record,rateList);   
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
			 
				protected void saveGroup(ListGridRecord record, final ListGrid list) {
					if(batchList.getSelectedRecords().length == 0 || agentList.getSelectedRecords().length == 0)
					{
						BrightEdu.showTip("批次或者招生点没有选!");
						return;
					}
					AgentReturnType type = new AgentReturnType();
					type.setAggregation_desc(record.getAttribute("groupName"));
					type.setFee_id(new Integer(record.getAttribute("fee_type_id")));
					type.setUpdate_date(new Date());
					
					dbService.addAgentReturnType(type, new CommonAsyncCall<AgentReturnType>(){

						@Override
						public void onSuccess(AgentReturnType result) {
							
							saveAgentReturn(result);
							saveAggression(result,list);
							
						}



					});
				} 
				private void saveAggression(AgentReturnType result,ListGrid list) {
					
					ArrayList<CollegeAggregation> al = new ArrayList<CollegeAggregation>();
					for(Record r : list.getRecords())
					{
						CollegeAggregation ca = new CollegeAggregation();
						ca.setAg_return_type_id(result.getAg_return_type_id());
						ca.setCollege_id(new Integer(r.getAttribute("collegeID")));
						ca.setHeadcount(new Integer(r.getAttribute("people_count")));
						ca.setReturn_percent(new BigDecimal(r.getAttribute("return_rate")));
						al.add(ca);
					}
					
					dbService.addCollegeAggregation(al, new CommonAsyncCall<Boolean>(){

						@Override
						public void onSuccess(Boolean result) {
							BrightEdu.showTip("保存成功");
							
						}});
					
				}
				private void saveAgentReturn(AgentReturnType result) {
					
					AgentReturnKey rtn = new AgentReturnKey() ;
					rtn.setAg_return_type_id(result.getAg_return_type_id());
					rtn.setAgent_id(new Integer(agentList.getSelectedRecord().getAttribute("agentId")));
					rtn.setBatch_id(new Integer(batchList.getSelectedRecord().getAttribute("batchId")));
					dbService.addAgentReturn(rtn , new CommonAsyncCall<Boolean>(){

						@Override
						public void onSuccess(Boolean result) {
							// TODO Auto-generated method stub
							
						}});
					
				}			
		};
		groupList.addRecordCollapseHandler(new RecordCollapseHandler(){

			@Override
			public void onRecordCollapse(RecordCollapseEvent event) {
				
				
			}}
		);
		groupList.setCanRemoveRecords(true);
		groupList.setCanAcceptDrop(true);
		groupList.setCanAcceptDroppedRecords(true);
		groupList.setCanExpandRecords(true);
		groupList.addRecordDropHandler(new RecordDropHandler(){

			@Override
			public void onRecordDrop(RecordDropEvent event) {
				
				if(batchList.getSelectedRecords().length == 0 || agentList.getSelectedRecords().length == 0)
				{
					BrightEdu.showTip("批次或者招生点没有选!");
					event.cancel();
					return;
				}
				String college = "";
				for(Record rec : event.getDropRecords())
				{
					college += "-" + rec.getAttribute("collegeName");
				}
				
				Iterator<String> keys = feeTypeMap.keySet().iterator();
				while(keys.hasNext())
				{	String feeTypeID = keys.next();
					String feeType = feeTypeMap.get(feeTypeID);
					Record r = new Record();
					r.setAttribute("groupName", batchList.getSelectedRecord().getAttribute("batchName") 
												+ "-" 
												+ agentList.getSelectedRecord().getAttribute("agentName")
												+ "-"
												+ feeType
												+ college
												);
					r.setAttribute("fee_type_id", feeTypeID);
					r.setAttribute("fee_type", feeType);
					r.setAttribute("records", event.getDropRecords());
					groupList.addData(r);
				}

				event.cancel();
				collegeList.removeSelectedData();
				
			}});
		
		fee_typeNameField2.setWidth(60);
		groupList.setShowHeaderContextMenu(false);
		groupList.setFields(group,fee_typeIdField2,fee_typeNameField2,dropedRecord);
		
		
		groupStack.addMember(groupList);
		
		selectionStack.addMember(ls4);

		batchList.setFields(batchIdField,batchNameField);
		agentList.setFields(agentIdField,agentNameField);
		collegeList.setFields(collegeIDField,collegeNameField);


		leftPane.setWidth(600);
		leftPane.addMember(selectionStack);
		leftPane.addMember(groupStack);
		
		mainPane.addMember(leftPane);
		
		Label currentLabel = new Label("当前招生点");
		currentLabel.setHeight(20);
		currentLabel.setMargin(5);
		rightPane.addMember(currentLabel);
		rightPane.addMember(currentList);
		
		mainPane.addMember(rightPane);
		
		addMember(mainPane);
	
		
		
		loadBatch();
		loadAgent();
		loadCollege();
		loadFeeType();

		agentList.addSelectionChangedHandler(new SelectionChangedHandler(){

			@Override
			public void onSelectionChanged(SelectionEvent event) {
				
				loadCollege();
				
			}});

	}

	private void loadFeeType() {
		
		dbService.getFeeTypeList(-1, -1, false
				, new CommonAsyncCall<List<FeeType>>(){

					@Override
					public void onSuccess(List<FeeType> result) {
						for(FeeType fee : result)
						{
							if(fee.getCan_return())
							{
								feeTypeMap.put(fee.getFee_id()+"", fee.getFee_name());
							}
							
						}
						
					}})
				;
		
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


	private void loadAgent() {
		
		dbService.getRecruitAgentList(-1, -1, false,true, new CommonAsyncCall<List<RecruitAgent>>(){

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
		
		//get/select current batch after 2 second
		
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
						

						
					}
				});
				
				
			}}.schedule(2000);
		
	}
	

	
}
