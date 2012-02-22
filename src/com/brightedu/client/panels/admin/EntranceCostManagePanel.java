package com.brightedu.client.panels.admin;


import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class EntranceCostManagePanel extends VLayout {
	
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
	ListGridField  fee_typeField = new ListGridField("fee_type","费用类型");
	ListGridField  feeField = new ListGridField("fee","费用");
	
	ListGrid entranceCostList = new ListGrid();
	
	ListGridField collegeField2 = new ListGridField("college","大学");
	ListGridField levelField2 = new ListGridField("level","层次");
	ListGridField  subjField2 = new ListGridField("subject","专业");
	ListGridField  fee_typeField2 = new ListGridField("fee_type","费用类型");
	ListGridField  feeField2 = new ListGridField("fee","费用");
	
	public EntranceCostManagePanel()
	{
		batchIdField.setHidden(true);
		agentIdField.setHidden(true);
		
		selectionStack.setHeight(350);
		selectionStack.setPadding(10);
		selectionStack.setMargin(10);
		
		LayoutSpacer ls1 = new LayoutSpacer();
		LayoutSpacer ls2 = new LayoutSpacer();
		LayoutSpacer ls3 = new LayoutSpacer();
		
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
		
		selectionSection.addItem(selectionStack);
		selectionSection.setExpanded(true);
		
		batchList.setFields(batchIdField,batchNameField);
		agentList.setFields(agentIdField,agentNameField);
		recruitPlanList.setFields(collegeField,levelField,subjField);
		feeList.setFields(fee_typeField,feeField);
		
		mainStack.addSection(selectionSection);
		
		listSection.addItem(entranceCostList);
		listSection.setExpanded(true);
		
		mainStack.addSection(listSection);
		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		addMember(mainStack);
	}
	


}
