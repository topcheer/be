package com.brightedu.client.panels.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;

import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.model.edu.RightsCategory;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.RightsFunction;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
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
		categoryItem.setWidth(350);
		
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
		
		newCategoryButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addCategory();
				
			}});

		deleteCategoryButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				removeCategory();
			}});
		newFunctionButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				addFunction();
			}});
		deleteFunctionButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				removeFunction();
			}});
		
		actionSection.addItem(buttonStack);
		
		leftStack.addSection(actionSection);

		addMember(leftStack);
        
        show();

        populateData();
	}
	
	protected void removeFunction() {
		if(functionGrid.getSelectedRecords().length == 0)
		{
			BrightEdu.showTip("删除哪个？");
			return;
		}
		ArrayList<String> list = new ArrayList<String>();
				
		for (Record rec : functionGrid.getSelectedRecords())
		{
			list.add(rec.getAttributeAsString("funcId"));
//			

		}
		
		dbService.deleteRightsFunction(list, new AsyncCallback<Boolean>(){
			
			@Override
			public void onFailure(Throwable caught) {
				BrightEdu.showTip("删除失败");

			}

			@Override
			public void onSuccess(Boolean result) {
				
				BrightEdu.showTip("删除成功");
				refreshFunction();
			}});
	}

	protected void addFunction() {
		
		final Window w = new Window();
		
		
		w.setEdgeMarginSize(4);
		w.setEdgeOffset(5);
		w.setAutoCenter(true);
		w.setTitle("新增");
		w.setShowMinimizeButton(false);
		w.setIsModal(true);
		w.setShowModalMask(false);
		w.setOverflow(Overflow.VISIBLE);
		w.setAutoSize(true);
		w.setCanDragResize(true);
		
		IButton okBtn = new IButton("确定");
		final DynamicForm form = new DynamicForm();
		HLayout bottomLayout = new HLayout();
		
		final TextItem functionId = new TextItem("funID","功能ID");
		final TextItem functionName = new TextItem("funName","功能名称");
		
		functionId.setWidth(140);
		functionName.setWidth(140);
		
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				if(form.validate())
				{
					RightsFunction rc = new RightsFunction();
					rc.setFunction_id(functionId.getValue().toString());
					rc.setFunction_name(functionName.getValue().toString());
					dbService.addRightsFunction(rc, new AsyncCallback<Boolean>(){

						@Override
						public void onFailure(Throwable caught) {
							BrightEdu.showTip("保存失败");

						}

						@Override
						public void onSuccess(Boolean result) {
							
							BrightEdu.showTip("保存成功");
							refreshFunction();
							
						}});
					w.hide();
				}
				
			}
		});
		form.setPadding(10);
		form.setAutoFocus(true);
		form.setWrapItemTitles(false);
		// form.setAutoHeight();
		form.setFields(functionId,functionName);

		Validator v = new Validator();
		v.setType(ValidatorType.REQUIRED);
		functionId.setValidators(v);
		functionName.setValidators(v);
		functionId.setValidateOnExit(true);
		functionName.setValidateOnExit(true);
		
		w.addItem(form);

		bottomLayout.addMember(new Label("  "));
		bottomLayout.addMember(new LayoutSpacer());
		bottomLayout.setPadding(5);
		okBtn.setWidth(70);
		//
		bottomLayout.addMember(okBtn);

		bottomLayout.setAutoHeight();
		bottomLayout.setWidth100();

		w.addItem(bottomLayout);

		// RegExpValidator lenValidator = new RegExpValidator("^\\d{10}$");
		// lenValidator.setErrorMessage("len error");

		w.addCloseClickHandler(new CloseClickHandler() {
			
			@Override
			public void onCloseClick(CloseClickEvent event) {
				
				w.hide();
				
				
			}
		});
		
		w.show();
	}

	protected void removeCategory() {
		
		if(categoryItem.getValue() == null)
		{
			BrightEdu.showTip("删除哪个？");
			return;
		}
		RightsCategory rc = new RightsCategory();
		rc.setCategory_id(categoryItem.getValue().toString());
		dbService.deleteRightsCategory(rc, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				BrightEdu.showTip("删除失败");

			}

			@Override
			public void onSuccess(Boolean result) {
				
				BrightEdu.showTip("删除成功");
				refreshCategory();
			}});
		dbService.deleteRightsCatetoryFunctions(categoryItem.getValue().toString(), new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				

			}

			@Override
			public void onSuccess(Boolean result) {
				selectedGrid.setData(new RecordList());
			}});
	}

	protected void addCategory() {
		
		final Window w = new Window();
		
		
		w.setEdgeMarginSize(4);
		w.setEdgeOffset(5);
		w.setAutoCenter(true);
		w.setTitle("新增");
		w.setShowMinimizeButton(false);
		w.setIsModal(true);
		w.setShowModalMask(false);
		w.setOverflow(Overflow.VISIBLE);
		w.setAutoSize(true);
		w.setCanDragResize(true);
		
		IButton okBtn = new IButton("确定");
		final DynamicForm form = new DynamicForm();
		HLayout bottomLayout = new HLayout();
		
		final TextItem categoryId = new TextItem("catID","类别ID");
		final TextItem categoryName = new TextItem("catName","类别名称");
		
		categoryId.setWidth(140);
		categoryName.setWidth(140);
		
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				if(form.validate())
				{
					RightsCategory rc = new RightsCategory();
					rc.setCategory_id(categoryId.getValue().toString());
					rc.setCategory_name(categoryName.getValue().toString());
					dbService.addRightsCategory(rc, new AsyncCallback<Boolean>(){

						@Override
						public void onFailure(Throwable caught) {
							BrightEdu.showTip("保存失败");

						}

						@Override
						public void onSuccess(Boolean result) {
							
							BrightEdu.showTip("保存成功");
							refreshCategory();
							
						}});
					w.hide();
				}
				
			}
		});
		form.setPadding(10);
		form.setAutoFocus(true);
		form.setWrapItemTitles(false);
		// form.setAutoHeight();
		form.setFields(categoryId,categoryName);

		Validator v = new Validator();
		v.setType(ValidatorType.REQUIRED);
		categoryId.setValidators(v);
		categoryName.setValidators(v);
		categoryId.setValidateOnExit(true);
		categoryName.setValidateOnExit(true);
		
		w.addItem(form);

		bottomLayout.addMember(new Label("  "));
		bottomLayout.addMember(new LayoutSpacer());
		bottomLayout.setPadding(5);
		okBtn.setWidth(70);
		//
		bottomLayout.addMember(okBtn);

		bottomLayout.setAutoHeight();
		bottomLayout.setWidth100();

		w.addItem(bottomLayout);

		// RegExpValidator lenValidator = new RegExpValidator("^\\d{10}$");
		// lenValidator.setErrorMessage("len error");

		w.addCloseClickHandler(new CloseClickHandler() {
			
			@Override
			public void onCloseClick(CloseClickEvent event) {
				
				w.hide();
				
				
			}
		});
		
		w.show();
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
				categoryItem.clearValue();		
				for(RightsCategory c : result)
				{
					list.put(c.getCategory_id(), "[ " +  c.getCategory_id() + " ] "+ c.getCategory_name());
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
}
