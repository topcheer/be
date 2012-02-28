package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AgentTypeAdminPanel extends BasicAdminPanel {

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<AgentType>> callback = new CommonAsyncCall<List<AgentType>>() {
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
					AgentType bi = (AgentType) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getAgent_type_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("obj_name", bi.getAgent_type_name());
					rec.setAttribute("is_return", bi.getIs_return());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getAgentTypeList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name", "is_return" },
				new String[] { "机构类型", "是否有返利" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.BOOLEAN },
				new boolean[] { true, true }, new int[] { -1, 200 },
				new Validator[] { standardLenthValidator, null });
	}

	@Override
	public void search(String keyWords, Record range) {

	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteAgentType(deleteIds, delAsync);
	}

	@Override
	public void update(final Record rec) {
		final AgentType editedAgentType = (AgentType) rec
				.getAttributeAsObject("object");
		final String oldName = editedAgentType.getAgent_type_name();
		final boolean oldIsReturn = editedAgentType.getIs_return();
		editedAgentType
				.setAgent_type_name(rec.getAttributeAsString("obj_name"));
		editedAgentType.setIs_return(rec.getAttributeAsBoolean("is_return"));
		dbService.saveAgentType(editedAgentType,
				new CommonAsyncCall<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						BrightEdu.showTip("已保存!");
					}

					protected void failed() { // rollback in UI
						editedAgentType.setAgent_type_name(oldName);
						editedAgentType.setIs_return(oldIsReturn);
						rec.setAttribute("obj_name", oldName);
						rec.setAttribute("is_return", oldIsReturn);
					}
				});
	}

	@Override
	public AdminDialog createAdminDialog() {
		AgentTypeAddDialog admin = new AgentTypeAddDialog();
		admin.setAdminPanel(this);
		return admin;
	}

	private class AgentTypeAddDialog extends AdminDialog {

		private TextItem agentTypeNameItem = new TextItem("agentTypeName",
				"机构类型");
		private BooleanItem isReturnItem = new BooleanItem("is_retrun", "是否有返利");
		int len = 250;

		public void init() {
			super.init();
			setSize(len + 70 + "", "120");
		}

		@Override
		protected Serializable getAddedModel() {

			AgentType at = new AgentType();
			at.setAgent_type_name(agentTypeNameItem.getValueAsString());
			at.setIs_return(isReturnItem.getValueAsBoolean());
			return at;
		}

		@Override
		protected DynamicForm createContentForm() {
			form = new DynamicForm();
			agentTypeNameItem.setWidth(len);
			isReturnItem.setWidth(len);
			isReturnItem.setValue(false);
			form.setPadding(5);
			form.setFields(agentTypeNameItem, isReturnItem);
			return form;
		}

	}

}
