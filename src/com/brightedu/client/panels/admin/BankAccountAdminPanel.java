package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.BankAccount;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.FeeType;
import com.brightedu.model.edu.RecruitPlan;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentClassified;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
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
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class BankAccountAdminPanel extends VLayout {
	
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	HLayout mainPanel = new HLayout();
	
	SectionStack leftStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("条件");
//	SectionStackSection selectionSection = new SectionStackSection ("选择 - 将左边列表中的专业拖动到右边列表中");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	
	SelectItem agentItem =  new SelectItem("agentID","学习中心");
	
	SelectItem batchItem =  new SelectItem("batchID","批次");

	SelectItem collegeItem =  new SelectItem("collegeID","合作大学");
	
	TextItem accountItem = new TextItem("account","打款帐号");
	
//	ListGrid subjectList = new ListGrid();   
//	
//	ListGrid selectedList2 = new ListGrid();  
//	

	DynamicForm df = new DynamicForm();
	HLayout hl = new HLayout();
	Validator numberVd = new Validator();
	
	HStack buttonStack = new HStack();
	IButton saveButton = new IButton("保存/修改");
	IButton cloneButton = new IButton("初始化本批次...");
	BooleanItem useCurrent = new BooleanItem("当前批次");
	HiddenItem hi = new HiddenItem("currentBatch");
	HiddenItem addOrUpdate = new HiddenItem("addOrUpdate");
	
	VLayout rightHand = new VLayout();
	
	Label lb = new Label("当前批次设置");
	
	ListGrid currentList = new ListGrid();
	ListGridField collegeField = new ListGridField("college","合作大学");
	ListGridField agentField = new ListGridField("agent","学习中心");
	ListGridField  accountField = new ListGridField("account","打款帐号");
	ListGridField  objField = new ListGridField("obj","打款帐号");	
	
	public BankAccountAdminPanel()
	{
		init();
		
	}
	
	protected void init()
	{
		setPadding(5);
		
		leftStack.setWidth(520);
		leftStack.setHeight100();
		leftStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		addOrUpdate.setValue("add");
		conditionSection.setExpanded(true);
//		selectionSection.setExpanded(true);

		
		
        BrightEdu.createDataBaseRPC().getBatchList(-1, -1, false, new AsyncCallback<List<BatchIndex>>(){

			@Override
			public void onFailure(Throwable caught) {

				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<BatchIndex> result) {

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
				refreshAccount();
				refreshCurrentList(new Integer(batchItem.getValueAsString()));
				
			}});
		
        BrightEdu.createDataBaseRPC().getCollegeList(-1, -1, false, new AsyncCallback<List<College>>(){

			@Override
			public void onFailure(Throwable caught) {

				SC.say("获取合作学校列表时发生错误");
			}

			@Override
			public void onSuccess(List<College> result) {

				Iterator<College> biit = result.iterator();
				LinkedHashMap<String,String> list  = new LinkedHashMap<String,String>();
				while(biit.hasNext())
				{
					College bi = biit.next();
					list.put(bi.getCollege_id() + "", bi.getCollege_name());
				}
				collegeItem.setValueMap(list);
				collegeField.setValueMap(list);
			}
        	
        });
        
		collegeItem.setDefaultToFirstOption(true);
		collegeItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				refreshAccount();
				
				
			}});
		final LinkedHashMap<String,String> levelMaplol = new LinkedHashMap<String,String>();
		
        BrightEdu.createDataBaseRPC().getRecruitAgentList(-1, -1, false,false, new AsyncCallback<List<RecruitAgent>>(){

			@Override
			public void onFailure(Throwable caught) {

				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<RecruitAgent> result) {

				Iterator<RecruitAgent> biit = result.iterator();
				LinkedHashMap<String,String> levelMap = new LinkedHashMap<String,String>();
				while(biit.hasNext())
				{
					RecruitAgent bi = biit.next();
					levelMap.put(bi.getAgent_id() + "", bi.getAgent_name());
					
				}
				agentItem.setValueMap(levelMap);
				agentField.setValueMap(levelMap);
				
			}
        	
        });
        agentItem.setDefaultToFirstOption(true);
        agentItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				refreshAccount();
				
				
			}});
		
		df.setMargin(5);
		df.setNumCols(2);
		batchItem.setWidth(200);
		useCurrent.setWidth(200);
		collegeItem.setWidth(200);
		agentItem.setWidth(200);
		
		
		df.setTitleOrientation(TitleOrientation.TOP);
		
		df.setItems(batchItem,useCurrent,collegeItem,agentItem,accountItem);
		

		df.setWidth100();
		
		
		
		conditionSection.addItem(df);
		leftStack.addSection(conditionSection);
		
        
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
		leftStack.addSection(actionSection);
		
		cloneButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				SC.confirm("初始化","你确定为本批次所有的学习中心和合作高校生成缺省的银行帐号吗?<br>这会删除本批次所有已经存在的纪录<br>不影响其他批次", new BooleanCallback(){

					@Override
					public void execute(Boolean value) {
						if(value)
						{
							dbService.initBankAccount(new Integer(batchItem.getValueAsString()), new CommonAsyncCall<Boolean>(){

								@Override
								public void onSuccess(Boolean result) {
									if(result)
									{
										BrightEdu.showTip("初始化成功");
									}
									else
									{
										SC.say("初始化失败，请检查服务器日志");
									}
									
								}}
							);
						}
						
					}}
				);
				
			}});
		
		currentList.setWidth100();
		currentList.setHeight100();
		currentList.setShowHeaderContextMenu(false);
		collegeField.setWidth(120);
		agentField.setWidth(80);
		accountField.setWidth(180);
		objField.setHidden(true);
		currentList.setFields(collegeField,agentField,accountField,objField);
		currentList.addRecordClickHandler(new RecordClickHandler(){

			@Override
			public void onRecordClick(RecordClickEvent event) {
				BankAccount ba = (BankAccount)event.getRecord().getAttributeAsObject("obj");
				
				agentItem.setValue(ba.getAgent_id()+"");
				collegeItem.setValue(ba.getCollege_id()+"");
				accountItem.setValue(ba.getAccount());
				
				
			}});
		
		 currentList.addRecordDoubleClickHandler(new RecordDoubleClickHandler(){

			@Override
			public void onRecordDoubleClick(final RecordDoubleClickEvent event) {
				SC.confirm("确认删除", "你确认要删除本纪录吗?", new BooleanCallback(){

					@Override
					public void execute(Boolean value) {
						if(value)
						{
							dbService.deleteBankAccount((BankAccount)event.getRecord().getAttributeAsObject("obj"), new CommonAsyncCall<Boolean>(){

								@Override
								public void onSuccess(Boolean result) {
									BrightEdu.showTip("成功删除");
									currentList.removeData(event.getRecord());
								}}
							);
						}
						
					}}
				);
			
				
			}});
		 
		
		
		lb.setHeight(10);
		lb.setMargin(5);
		
		rightHand.addMember(lb);
		rightHand.addMember(currentList);
		mainPanel.setAlign(VerticalAlignment.TOP);
		
		mainPanel.setWidth100();
		mainPanel.setHeight100();
		mainPanel.addMember(leftStack);
		LayoutSpacer sp2 = new LayoutSpacer();
		sp2.setWidth(20);
		mainPanel.addMember(sp2);
		mainPanel.addMember(rightHand);
		addMember(mainPanel);
        
        show();
    
       
        
         
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
					useCurrent.setValue(true);
					hi.setValue(result);
					
					refreshCurrentList(result);
					
				}
				else
				{
					useCurrent.setValue(false);
					useCurrent.setDisabled(true);
					hi.setValue(-1);
				}
				
			}

         });
         useCurrent.addChangedHandler(new ChangedHandler(){

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
					refreshCurrentList(new Integer(batchItem.getValueAsString()));
					

				}
				
			}});
         
         
        
	}
	
	protected void refreshAccount() {
		//检查所选的组合是否有记录已经存在与college_subject表中，如果有，初始化selectedList列表
		if (collegeItem.getValue() == null 
				|| batchItem.getValue() == null 
				|| agentItem.getValue() == null) return;
		Integer collegeId = new Integer(collegeItem.getValue().toString());
		Integer batchId =new Integer(batchItem.getValue().toString());
		Integer agentId = new Integer(agentItem.getValue().toString());
		BankAccount ba = new BankAccount();
		ba.setAgent_id(agentId);
		ba.setCollege_id(collegeId);
		ba.setBatch_id(batchId);
		
		dbService.getBankAccount(ba, new CommonAsyncCall<BankAccount>(){

			@Override
			public void onSuccess(BankAccount result) {
				if(result == null) 
					{
						addOrUpdate.setValue("add");
						accountItem.setValue("");
						return;
					}
				
				accountItem.setValue(result.getAccount());
				addOrUpdate.setValue("update");
			}});
		
		
	}

//	protected void reload()
//	{
//		//检查所选的组合是否有记录已经存在与college_subject表中，如果有，初始化selectedList列表
//		if (collegeItem.getValue() == null 
//				|| batchItem.getValue() == null 
//				|| agentItem.getValue() == null) return;
//		Integer collegeId = new Integer(collegeItem.getValue().toString());
//		Integer batchId =new Integer(batchItem.getValue().toString());
//		Integer agentId = new Integer(agentItem.getValue().toString());
//		
//
//		AsyncCallback<List<CollegeSubjectView>> callback = new AsyncCallback<List<CollegeSubjectView>>(){
//
//
//			@Override
//			public void onFailure(Throwable caught) {
//
//				SC.say("获取招生计划时发生错误");
//			}
//
//			@Override
//			public void onSuccess(List<CollegeSubjectView> result) {
//
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
//        BrightEdu.createDataBaseRPC().getCollegeSubjectList(collegeId, agentId, batchId,callback );
//        
//        
//        
//		
//
//	
//	}

	protected void saveMe()
	{
		if(!df.validate()) return;
		
		if (collegeItem.getValue() == null 
				|| batchItem.getValue() == null 
				|| agentItem.getValue() == null)
		{
			BrightEdu.showTip("开玩笑？");
			return;
		}
			
		
		Integer collegeId = new Integer(collegeItem.getValue().toString());
		final Integer batchId = new Integer(batchItem.getValue().toString());
		Integer agentId = new Integer(agentItem.getValue().toString());
		
		BankAccount ba = new BankAccount();
		ba.setAgent_id(agentId);
		ba.setBatch_id(batchId);
		ba.setCollege_id(collegeId);
		ba.setAccount(accountItem.getValueAsString());
		
		if(addOrUpdate.getValue().toString().equalsIgnoreCase("add"))
		{
			dbService.addBankAccount(ba, new AsyncCallback<Boolean>(){

				@Override
				public void onFailure(Throwable caught) {

					
				}

				@Override
				public void onSuccess(Boolean result) {
						BrightEdu.showTip("保存成功");
						
						refreshCurrentList(batchId);
						
					
				}});

		}
		else
			dbService.saveBankAccount(ba, new AsyncCallback<Boolean>(){

				@Override
				public void onFailure(Throwable caught) {

					
				}

				@Override
				public void onSuccess(Boolean result) {
						BrightEdu.showTip("保存成功");
						
						refreshCurrentList(batchId);
					
				}});

		addOrUpdate.setValue("update");
		
		
	}

	protected void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	private void refreshCurrentList(Integer result) {
		
		dbService.getBankAccountList(result,-1,-1,false, new AsyncCallback<List<BankAccount>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<BankAccount> result) {
				
				if (result.size() == 0)
				{
					lb.setContents("当前批次设置<b> [ 当前选中批次无帐号设,你可以先初始化生成缺省纪录 ] </b>");
					currentList.setData(new RecordList());
					return;
				}
				Iterator<BankAccount> rpit = result.iterator();
				RecordList data = new RecordList();
				String batchName = "";
				while(rpit.hasNext())
				{
					BankAccount rp = rpit.next();
					Record rc = new Record();
					rc.setAttribute("college",rp.getCollege_id());
					rc.setAttribute("agent", rp.getAgent_id());
					rc.setAttribute("account", rp.getAccount());
					rc.setAttribute("obj", rp);
					//batchName = rp.getBatch_name();
					data.add(rc);
				}
				lb.setContents("当前批次设置");
				
				currentList.setData(data);
				
			}}
		);
		
	}
}
