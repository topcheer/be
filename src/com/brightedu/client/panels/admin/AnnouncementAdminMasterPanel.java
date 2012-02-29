package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.panels.MasterDetailAdmin;
import com.brightedu.model.edu.Announcement;
import com.brightedu.model.edu.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AnnouncementAdminMasterPanel extends BasicAdminPanel {
	MasterDetailAdmin admin;
	LinkedHashMap<String, String> agentTypes;
	LinkedHashMap<String, String> agentRelations;
	ListGridField[] fields;

	public AnnouncementAdminMasterPanel(MasterDetailAdmin agentadmin) {
		this.admin = agentadmin;
	}
	@Override
	public void postInit() {
		
		dbService.getUserList(-1, -1, false, new CommonAsyncCall<List<User>>(){
			LinkedHashMap<String, String> users = new  LinkedHashMap<String, String>();
			@Override
			public void onSuccess(List<User> result) {
				for(User user : result){
					users.put(user.getUser_id()+"", user.getUser_name());
				}
				resultList.getField("created_by").setValueMap(users);
			}});



		
	}
	public void refresh() {
		super.refresh();
//		initValueMaps();
	}

//	private void initValueMaps() {
//		dbService.getNameValuePareList(new String[] { "AgentType",
//				"RecruitAgent" }, new CommonAsyncCall<List>() {
//
//			@Override
//			public void onSuccess(List result) {
//				agentTypes = new LinkedHashMap<String, String>();
//				agentRelations = new LinkedHashMap<String, String>();
//				agentRelations.put("-1", "无");
//				for (int i = 0; i < result.size(); i++) {
//					if (i == 0) {// "AgentType"
//						List<AgentType> types = (List<AgentType>) result.get(i);
//						for (AgentType at : types) {
//							agentTypes.put(at.getAgent_type_id() + "",
//									at.getAgent_type_name());
//						}
//						fields[1].setValueMap(agentTypes);
//						((AgentAdminEditorForm) admin.getDetailed()
//								.getDetailedForm()).agent_typeItem
//								.setValueMap(agentTypes);
//						if (getAdminDialog() != null) {
//							AgentAdminEditorForm adminForm = (AgentAdminEditorForm) getAdminDialog()
//									.getContentForm();
//							adminForm.agent_typeItem.setValueMap(agentTypes);
//						}
//					} else if (i == 1) {// "AgentRelation"
//						List<RecruitAgent> types = (List<RecruitAgent>) result
//								.get(i);
//						for (RecruitAgent at : types) {
//							agentRelations.put(at.getAgent_id() + "",
//									at.getAgent_name());
//						}
//						((AgentAdminEditorForm) admin.getDetailed()
//								.getDetailedForm()).parentAgentItem
//								.setValueMap(agentRelations);
//						if (getAdminDialog() != null) {
//							AgentAdminEditorForm adminForm = (AgentAdminEditorForm) getAdminDialog()
//									.getContentForm();
//							adminForm.parentAgentItem.setValueMap(agentTypes);
//						}
//					}
//				}
//			}
//		});
//	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<Announcement>> callback = new CommonAsyncCall<List<Announcement>>() {
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
					Announcement ann = (Announcement) result.get(i);
					Record rec = new Record();

					rec.setAttribute("select", false);
					rec.setAttribute("id", ann.getAnn_id());
					rec.setAttribute("object", ann);
					rec.setAttribute("obj_name",ann.getAnn_title());
					rec.setAttribute("created_by",ann.getCreated_by()+"");
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getAnnouncementList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		fields = parseGridFields(new String[] { "obj_name", "created_by",
				 }, new String[] { "标题", "创建者" },
				new ListGridFieldType[] { ListGridFieldType.TEXT,
						ListGridFieldType.TEXT, }, new boolean[] { 
						false, false }, new int[] {  -1, 80 });
//		initValueMaps();

		return fields;
	}

	public void add(Serializable model) {
		Announcement ann = (Announcement) model;
		ann.setCreated_by(BrightEdu.getUser().getUser_id());
		dbService.addModel(model, getAdminDialog().getAddAsync());
	}

	@Override
	public void search(String keyWords, Record range) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteAnnouncement(deleteIds, delAsync);
	}

	@Override
	public void update(Record record) {
		final Announcement newann = (Announcement) admin.getDetailed()
				.getDetailedForm().getModel();
		final Record rec = resultList.getSelectedRecord();
		final Announcement oldAnn = (Announcement) rec
				.getAttributeAsObject("object");
		newann.setAnn_id(oldAnn.getAnn_id());
		newann.setLast_edited_by(BrightEdu.getUser().getUser_id());
		newann.setUpdate_time(new Date());
		newann.setCreated_by(oldAnn.getCreated_by());
		
		dbService.saveAnnouncement(newann, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
				rec.setAttribute("object", newann);
				rec.setAttribute("obj_name", newann.getAnn_title());
				rec.setAttribute("created_by", newann.getCreated_by()+"");
				
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
			AnnouncementAdminEditorForm form = new AnnouncementAdminEditorForm(admin);

			public void init() {
				super.init();
				// 这里form不能自适应大小，shit！
//				form.setWidth(500);
//				form.setHeight(280);
				form.hideSaveItem();
				form.setPadding(5);
				form.setWrapItemTitles(true);
			}

			@Override
			protected DynamicForm createContentForm() {

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
		adminDialog.setSize("525", "350");
		adminDialog.setAdminPanel(this);
		
		return adminDialog;
	}

}
