package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.RecruitAgent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AgentAdminMasterPanel extends BasicAdminPanel {
	MasterDetailAdmin admin;
	LinkedHashMap<String, String> agentTypes;
	LinkedHashMap<String, String> agentRelations;
	ListGridField[] fields;

	public AgentAdminMasterPanel(MasterDetailAdmin agentadmin) {
		this.admin = agentadmin;
	}

	public void refresh() {
		super.refresh();
		initValueMaps();
	}

	private void initValueMaps() {
		dbService.getNameValuePareList(new String[] { "AgentType",
				"RecruitAgent" }, new CommonAsyncCall<List>() {

			@Override
			public void onSuccess(List result) {
				agentTypes = new LinkedHashMap<String, String>();
				agentRelations = new LinkedHashMap<String, String>();
				for (int i = 0; i < result.size(); i++) {
					if (i == 0) {// "AgentType"
						List<AgentType> types = (List<AgentType>) result.get(i);
						for (AgentType at : types) {
							agentTypes.put(at.getAgent_type_id() + "",
									at.getAgent_type_name());
						}
						fields[1].setValueMap(agentTypes);
						((AgentAdminEditorForm) admin.getDetailed()
								.getDetailedForm()).agent_typeItem
								.setValueMap(agentTypes);
						if (getAdminDialog() != null) {
							AgentAdminEditorForm adminForm = (AgentAdminEditorForm) getAdminDialog()
									.getContentForm();
							adminForm.agent_typeItem.setValueMap(agentTypes);
						}
					} else if (i == 1) {// "AgentRelation"
						List<RecruitAgent> types = (List<RecruitAgent>) result
								.get(i);
						for (RecruitAgent at : types) {
							agentRelations.put(at.getAgent_id() + "",
									at.getAgent_name());
						}
						((AgentAdminEditorForm) admin.getDetailed()
								.getDetailedForm()).parentAgentItem
								.setValueMap(agentRelations);
						if (getAdminDialog() != null) {
							AgentAdminEditorForm adminForm = (AgentAdminEditorForm) getAdminDialog()
									.getContentForm();
							adminForm.parentAgentItem.setValueMap(agentTypes);
						}
					}
				}
			}
		});
	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List> callback = new CommonAsyncCall<List>() {
			@Override
			public void onSuccess(List result) {
				int size = result.size();
				Record[] listData = init ? new Record[size - 1]
						: new Record[size];
				for (int i = 0; i < size; i++) {
					if (i == size - 1) {
						if (init) {
							int counts = (Integer) result.get(size - 1);
							setTotalCounts(counts);
							break;
						}
					}
					RecruitAgent bi = (RecruitAgent) result.get(i);
					Record rec = new Record();

					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getAgent_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getAgent_name());
					rec.setAttribute("agent_type", bi.getAgent_type_id());
					rec.setAttribute("responsible_person",
							bi.getResponsible_person());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
//		dbService.getRecruitAgentList((indexGoto - 1) * currentRowsInOnePage,
//				currentRowsInOnePage, init, false, callback);
		dbService.getModels("RecruitAgent", searchCriteria,
				((indexGoto - 1) * currentRowsInOnePage), currentRowsInOnePage,
				init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		fields = parseGridFields(new String[] { "obj_name", "agent_type",
				"responsible_person" }, new String[] { "机构名称", "机构类型", "负责人" },
				new ListGridFieldType[] { ListGridFieldType.TEXT,
						ListGridFieldType.TEXT, ListGridFieldType.TEXT,
						ListGridFieldType.TEXT }, new boolean[] { false, false,
						false, false }, new int[] { -1, -1, -1, -1 });
		initValueMaps();

		return fields;
	}

	public void add(Serializable model) {
		RecruitAgent agent = (RecruitAgent) model;
		agent.setUser_id(BrightEdu.getUser().getUser_id());
		dbService.addModel(model, getAdminDialog().getAddAsync());
	}


	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deletRecruitAgent(deleteIds, delAsync);
	}

	@Override
	public void update(Record record) {
		final RecruitAgent newagt = (RecruitAgent) admin.getDetailed()
				.getDetailedForm().getModel();
		final Record rec = resultList.getSelectedRecord();
		final RecruitAgent oldagt = (RecruitAgent) rec
				.getAttributeAsObject("object");
		newagt.setAgent_id(oldagt.getAgent_id());
		newagt.setRegister_date(oldagt.getRegister_date());
		newagt.setUser_id(oldagt.getUser_id());
		dbService.saveRecruitAgent(newagt, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
				rec.setAttribute("object", newagt);
				rec.setAttribute("obj_name", newagt.getAgent_name());
				rec.setAttribute("agent_type", newagt.getAgent_type_id());
				rec.setAttribute("responsible_person",
						newagt.getResponsible_person());
				resultList.redraw();
			}

			protected void failed() { // rollback in UI
				// List UI would not be changed here
			}
		});
	}

	@Override
	public AdminDialog createAdminDialog() {
		AdminDialog adminDialog = new AdminDialog() {
			AgentAdminEditorForm form = new AgentAdminEditorForm(admin);

			public void init() {
				super.init();
				// 这里form不能自适应大小，shit！
				form.setWidth(700);
				form.setHeight(500);
				form.hideSaveItem();
				form.setPadding(5);
				form.setWrapItemTitles(true);
			}

			@Override
			protected DynamicForm createContentForm() {

				form.agent_typeItem.setValueMap(agentTypes);
				form.parentAgentItem.setValueMap(agentRelations);
				form.setBorder("1px solid gray");
				return form;
			}

			@Override
			protected Serializable getAddedModel() {
				return form.getModel();
			}

			public void show() {
				super.show();
			}
		};
		adminDialog.setAutoHeight();
		adminDialog.setAutoWidth();
		adminDialog.setSize("535", "340");
		adminDialog.setAdminPanel(this);
		
		return adminDialog;
	}

}
