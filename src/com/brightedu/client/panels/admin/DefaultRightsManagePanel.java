package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsDefaultKey;
import com.brightedu.model.edu.UserType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class DefaultRightsManagePanel extends VLayout {
	
	
	protected static final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	
	HLayout mainPanel = new HLayout();
	
	SectionStack leftStack = new SectionStack();   
	SectionStackSection conditionSection = new SectionStackSection ("条件");
	SectionStackSection selectionSection = new SectionStackSection ("类别列表 -- 要将功能类别赋予某个用户，拖动到左边的列表中");
	SectionStackSection actionSection = new SectionStackSection ("操作");
	
	DynamicForm df = new DynamicForm();

	SelectItem userTypeItem =  new SelectItem("userTypeId","用户类型");

	
	ListGrid categoryGrid =  new ListGrid();
	ListGrid selectedGrid =  new ListGrid();
	
    ListGridField catidField = new ListGridField("catId", "权限类别代码");
    ListGridField catNameField = new ListGridField("catName", "权限类别名称");   
   
    ListGridField catidField2 = new ListGridField("catId", "权限类别代码");
    ListGridField catNameField2 = new ListGridField("catName", "权限类别名称");   

	
	HLayout listGridLayout = new HLayout();
	

	IButton saveButton = new IButton("保存");

	LinkedHashMap<String,String> funlist = new LinkedHashMap<String,String>();
	public DefaultRightsManagePanel()
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

		userTypeItem.setDefaultToFirstOption(true);
		userTypeItem.setWidth(350);
		
		userTypeItem.addChangedHandler(new ChangedHandler(){

			@Override
			public void onChanged(ChangedEvent event) {
				refreshUsertypeCategory(userTypeItem.getValueAsString());
				
			}}
		);

		//df.setItemLayout(FormLayoutType.ABSOLUTE);
		df.setMargin(5);
		df.setHeight(20);
		df.setWidth100();
		df.setTitleOrientation(TitleOrientation.LEFT);		

		df.setItems(userTypeItem);
		

		
		conditionSection.addItem(df);
		leftStack.addSection(conditionSection);
		
		
        
		listGridLayout.setPadding(4);
		listGridLayout.setHeight(350);
     
		categoryGrid.setWidth(450);   
		categoryGrid.setHeight(350);   
		categoryGrid.setShowAllRecords(true);   
		categoryGrid.setCanReorderRecords(true);   
		categoryGrid.setCanDragRecordsOut(true);   
		categoryGrid.setCanAcceptDroppedRecords(true);   
		categoryGrid.setDragDataAction(DragDataAction.COPY);   
		categoryGrid.setShowHeaderContextMenu(false);
 
		catidField.setWidth(200);
        
        categoryGrid.setFields(catidField, catNameField);   
        categoryGrid.addDoubleClickHandler(new DoubleClickHandler(){

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				selectedGrid.transferSelectedData(categoryGrid);
				
			}});
        
        listGridLayout.addMember(categoryGrid);
        
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
        
        catidField2.setWidth(200);
        
        selectedGrid.setFields(catidField2, catNameField2);   
        
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
		buttonStack.addMember(saveButton);
		buttonStack.addMember(ls);
		
		actionSection.addItem(buttonStack);
		
		leftStack.addSection(actionSection);

		addMember(leftStack);
        
        show();

        populateData();
	}

	protected void populateData()
	{
		refreshUsertype();
		refreshCategory();
	}
	
	protected void refreshUsertype()
	{
		dbService.getUserTypeList(-1, -1, false,new AsyncCallback<List<UserType>>(){

			@Override
			public void onFailure(Throwable caught) {
					SC.say("获取用户类型失败");
				
			}

			@Override
			public void onSuccess(List<UserType> result) {
				LinkedHashMap<String,String> list = new LinkedHashMap<String,String>();
				userTypeItem.clearValue();		
				for(UserType c : result)
				{
					list.put(c.getUser_type_id() + "", c.getUser_type_name());
				}
			
				userTypeItem.setValueMap(list);
				
			}});
		
		
	}
	protected void refreshCategory()
	{
		dbService.getRightsCategory(new AsyncCallback<List<RightsCategory>>(){

			@Override
			public void onFailure(Throwable caught) {
					SC.say("获取功能列表失败");
				
			}

			@Override
			public void onSuccess(List<RightsCategory> result) {
				
				RecordList list = new RecordList();
				funlist.clear();
				
				for(RightsCategory c : result)
				{
					Record rec = new Record();
					rec.setAttribute("catId", c.getCategory_id());
					rec.setAttribute("catName", c.getCategory_name());
					list.add(rec);
					funlist.put(c.getCategory_id(), c.getCategory_name());
				}
				categoryGrid.setData(list);
				
				
			}});
		
	}
	
	protected void refreshUsertypeCategory(String category_id)
	
	{
		
		dbService.getRightsDefault(category_id, new AsyncCallback<List<RightsDefaultKey>>(){

			@Override
			public void onFailure(Throwable caught) {
				SC.say("获取功能列表失败");
				
			}

			@Override
			public void onSuccess(List<RightsDefaultKey> result) {
				
				RecordList list = new RecordList();
				
				for(RightsDefaultKey c : result)
				{
					Record rec = new Record();
					rec.setAttribute("catId", c.getCategory_id());
					rec.setAttribute("catName", funlist.get(c.getCategory_id()));
					list.add(rec);
				}
				selectedGrid.setData(list);
				
				
			}});
		
		
	}

	protected void saveMe()
	{
		if (userTypeItem.getValue() == null)
		{
			BrightEdu.showTip("开玩笑？");
			return;
		}
			
		
		Integer user_type_id = new Integer(userTypeItem.getValue().toString());
		

		if(selectedGrid.getRecords().length == 0) 
			
			{
				dbService.deleteRightsDefault(user_type_id, new AsyncCallback<Boolean>(){
		
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
		
		ArrayList<RightsDefaultKey> lst = new ArrayList<RightsDefaultKey>();
		
		RecordList data = selectedGrid.getRecordList();
		
		for(int i = 0 ; i< data.getLength() ; i++)
		{
			
			RightsDefaultKey cs2 = new RightsDefaultKey();
			cs2.setUser_type_id(user_type_id);
			cs2.setCategory_id(data.get(i).getAttributeAsString("catId"));
			lst.add(cs2);
			
		}
		
		dbService.addRightsDefault(lst, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {

				
			}

			@Override
			public void onSuccess(Boolean result) {
					BrightEdu.showTip("保存成功");
					
					
				
			}});

	}
}
