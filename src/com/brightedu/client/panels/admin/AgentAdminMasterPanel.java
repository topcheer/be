package com.brightedu.client.panels.admin;

import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.RecruitAgent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

public class AgentAdminMasterPanel extends BasicAdminPanel {
	AgentAdmin admin;

	public AgentAdminMasterPanel(AgentAdmin agentadmin) {
		this.admin = agentadmin;
		admin.detailed.getDetailedForm().getSaveBtn().disable();
		resultList.addSelectionChangedHandler(new SelectionChangedHandler() {

			@Override
			public void onSelectionChanged(SelectionEvent event) {
				System.out.println("Selection changed");
				if (event.getState()) {
					admin.detailed.getDetailedForm().setValue(
							(RecruitAgent) event.getRecord()
									.getAttributeAsObject("object"));
					admin.detailed.getDetailedForm().getSaveBtn().enable();
				} else {
					admin.detailed.getDetailedForm().setValue(
							new RecruitAgent());
					admin.detailed.getDetailedForm().getSaveBtn().disable();
				}
			}
		});

	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<RecruitAgent>> callback = new CommonAsyncCall<List<RecruitAgent>>() {
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
		BrightEdu.createDataBaseRPC().getRecruitAgentList(
				(indexGoto - 1) * currentRowsInOnePage, currentRowsInOnePage,
				init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		ListGridField[] fields = parseGridFields(new String[] { "obj_name",
				"agent_type", "responsible_person" }, new String[] { "机构名称",
				"机构类型", "负责人" }, new ListGridFieldType[] {
				ListGridFieldType.TEXT, ListGridFieldType.TEXT,
				ListGridFieldType.TEXT, ListGridFieldType.TEXT },
				new boolean[] { false, false, false, false }, new int[] { -1,
						-1, -1, -1 });
		return fields;
	}

	@Override
	public void search(String keyWords, Record range) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deletRecruitAgent(deleteIds, delAsync);
	}

	@Override
	public void update(Record record) {
		final RecruitAgent newagt = admin.detailed.getDetailedForm().getModel();
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
				// List UI would be changed
			}
		});
	}

	@Override
	public void add(Object model) {
		RecruitAgent agent = (RecruitAgent) model;
		// FIXME need validator
		dbService.addRecruitAgent(agent, getAdminDialog().getAddAsync());

	}

	@Override
	public AdminDialog createAdminDialog() {
		AdminDialog admin = new AdminDialog() {
			AgentAdminEditorForm form = new AgentAdminEditorForm();

			public void init() {
				super.init();
				form.setOverflow(Overflow.HIDDEN);
				// 这里form不能自适应大小，shit！
				form.setWidth(500);
				form.setHeight(280);
				form.getSaveBtn().setVisible(false);
				form.setPadding(5);
				form.setWrapItemTitles(true);
			}

			@Override
			protected DynamicForm getContentForm() {
				dbService.getAgentTypeList(-1, -1, false,
						new CommonAsyncCall<List<AgentType>>() {

							@Override
							public void onSuccess(List<AgentType> result) {
								LinkedHashMap<String, String> agentTypes = new LinkedHashMap<String, String>();
								for (AgentType at : result) {
									agentTypes.put(at.getAgent_type_id() + "",
											at.getAgent_type_name());
								}
								form.agent_typeItem.setValueMap(agentTypes);
							}
						});

				return form;
			}

			@Override
			protected Object getAddedModel() {
				return form.getModel();
			}

			public void show() {
				super.show();
			}
		};
		admin.setAutoHeight();
		admin.setAutoWidth();
		admin.setSize("520", "340");
		admin.setAdminPanel(this);
		return admin;
	}

}
