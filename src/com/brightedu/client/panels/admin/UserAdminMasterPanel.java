package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.RightsCategoryFunctionKey;
import com.brightedu.model.edu.User;
import com.brightedu.model.edu.UserRights;
import com.brightedu.model.edu.UserType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.EditCompleteEvent;
import com.smartgwt.client.widgets.grid.events.EditCompleteHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

public class UserAdminMasterPanel extends BasicAdminPanel {
	UserAdmin admin;
	LinkedHashMap<String, String> userTypes;
	LinkedHashMap<String, String> agentList;

	public UserAdminMasterPanel(UserAdmin agentadmin) {
		this.admin = agentadmin;

		resultList.addSelectionChangedHandler(new SelectionChangedHandler() {

			@Override
			public void onSelectionChanged(SelectionEvent event) {
				if (event.getState()) {
					admin.detailed.getDetailedForm().setValue(
							(User) event.getRecord().getAttributeAsObject(
									"object"));
					admin.detailed.getDetailedForm().getSaveBtn().enable();
					populateRights((User) event.getRecord()
							.getAttributeAsObject("object"));

				} else {
					admin.detailed.getDetailedForm().setValue(new User());// empty
																			// all
																			// fields
					admin.detailed.getDetailedForm().getSaveBtn().disable();
					
					admin.rights.userRightsGrid.setData(new RecordList());
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
					User bi = (User) result.get(i);
					Record rec = new Record();

					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getUser_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getUser_name());
					rec.setAttribute("user_type", bi.getUser_type_id());
					rec.setAttribute("display_name", bi.getDisplay_name());
					rec.setAttribute("agent", bi.getAgent_id());

					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
//		BrightEdu.createDataBaseRPC().getUserList(
//				(indexGoto - 1) * currentRowsInOnePage, currentRowsInOnePage,
//				init, callback);
		dbService.getModels("User", searchCriteria,
				((indexGoto - 1) * currentRowsInOnePage), currentRowsInOnePage,
				init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		final ListGridField[] fields = parseGridFields(new String[] {
				"obj_name", "user_type", "display_name", "agent" },
				new String[] { "用户名", "用户类型", "显示名称", "所属机构" },
				new ListGridFieldType[] { ListGridFieldType.TEXT,
						ListGridFieldType.TEXT, ListGridFieldType.TEXT,
						ListGridFieldType.TEXT }, new boolean[] { false, false,
						false, false }, new int[] { -1, -1, -1, -1 });
		dbService.getUserTypeList(-1, -1, false,
				new CommonAsyncCall<List<UserType>>() {

					@Override
					public void onSuccess(List<UserType> result) {
						userTypes = new LinkedHashMap<String, String>();
						for (UserType at : result) {
							userTypes.put(at.getUser_type_id() + "",
									at.getUser_type_name());
						}
						fields[1].setValueMap(userTypes);
						admin.detailed.getDetailedForm().user_typeItem
								.setValueMap(userTypes);
					}
				});
		dbService.getRecruitAgentList(-1, -1, false, false,
				new CommonAsyncCall<List<RecruitAgent>>() {

					@Override
					public void onSuccess(List<RecruitAgent> result) {
						agentList = new LinkedHashMap<String, String>();
						for (RecruitAgent at : result) {
							agentList.put(at.getAgent_id() + "",
									at.getAgent_name());
						}
						fields[3].setValueMap(agentList);
						admin.detailed.getDetailedForm().agentItem
								.setValueMap(agentList);
					}
				});
		return fields;
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deletUser(deleteIds, delAsync);
	}

	@Override
	public void update(Record record) {
		final User newuser = admin.detailed.getDetailedForm().getModel();
		final Record rec = resultList.getSelectedRecord();
		final User user = (User) rec.getAttributeAsObject("object");

		// newuser.setAgent_id(agentList.get);
		newuser.setRegister_date(user.getRegister_date());
		newuser.setUser_id(user.getUser_id());
		newuser.setUpdate_date(new Date());

		dbService.saveUser(newuser, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
				rec.setAttribute("object", newuser);
				rec.setAttribute("obj_name", newuser.getUser_name());
				rec.setAttribute("user_type", newuser.getUser_type_id());
				rec.setAttribute("display_name", newuser.getDisplay_name());
				rec.setAttribute("agent", newuser.getAgent_id());
				resultList.redraw();
			}

			protected void failed() { // rollback in UI
				// List UI would not be changed
			}
		});
	}

	public void updateRights(Record record) {
		final Record rec = resultList.getSelectedRecord();
		final User user = (User) rec.getAttributeAsObject("object");

		// newuser.setAgent_id(agentList.get);

		RightsCategoryFunctionKey override = new RightsCategoryFunctionKey();
		override.setCategory_id(record.getAttributeAsString("categoryID"));
		override.setFunction_id(record.getAttributeAsString("functionID"));

		dbService.setOverride(override, user,
				record.getAttributeAsBoolean("select"),
				new CommonAsyncCall<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {

					}
				});

	}

	@Override
	public void add(Serializable model) {
		User user = (User) model;
		// FIXME need validator
		// user.setUpdate_date(new Date());
		dbService.addModel(user, getAdminDialog().getAddAsync());

	}

	@Override
	public AdminDialog createAdminDialog() {
		AdminDialog admin = new AdminDialog() {
			UserAdminEditorForm form = new UserAdminEditorForm();

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
			protected DynamicForm createContentForm() {

				form.user_typeItem.setValueMap(userTypes);
				form.agentItem.setValueMap(agentList);
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
		admin.setAutoHeight();
		admin.setAutoWidth();
		admin.setSize("520", "340");
		admin.setAdminPanel(this);
		return admin;
	}

	protected void populateRights(User user) {
		dbService.getUserRights(user, new AsyncCallback<List<UserRights>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say(caught.getLocalizedMessage());
			}

			@Override
			public void onSuccess(List<UserRights> result) {
				RecordList list = new RecordList();

				for (UserRights rights : result) {
					Record userrights = new Record();
					if (rights.getIsset().equalsIgnoreCase("T")) {
						userrights.setAttribute("select", true);
					} else {
						userrights.setAttribute("select", false);
					}
					userrights.setAttribute("categoryID",
							rights.getCategory_id());
					userrights.setAttribute("categoryName",
							rights.getCategory_name());
					userrights.setAttribute("functionID",
							rights.getFunction_id());
					userrights.setAttribute("functionName",
							rights.getFunction_name());
					list.add(userrights);
				}

				admin.rights.userRightsGrid.setData(list);
			}

		});
	}
}
