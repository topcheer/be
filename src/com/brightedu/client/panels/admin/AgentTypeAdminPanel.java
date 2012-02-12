package com.brightedu.client.panels.admin;

import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.RecruitAgent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.NamedFrame;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
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
		if (init) {
			dbService.getAgentTypeListAndTotalCounts((indexGoto - 1)
					* currentRowsInOnePage, currentRowsInOnePage, callback);
		} else {
			dbService.getAgentTypeList((indexGoto - 1) * currentRowsInOnePage,
					currentRowsInOnePage, callback);
		}
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name", "is_return" },
				new String[] { "机构类型", "是否有返利" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT, ListGridFieldType.BOOLEAN },
				new boolean[] { true, true }, new int[] { -1, 200 });
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
		final boolean oldIsReturn  = editedAgentType.getIs_return();
		editedAgentType.setAgent_type_name(rec.getAttributeAsString("obj_name"));
		editedAgentType.setIs_return(rec.getAttributeAsBoolean("is_return"));
		dbService.saveAgentType(editedAgentType, new CommonAsyncCall<Boolean>() {
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
	public void add(Object model) {
		final AgentType at  = (AgentType) model;
		
		if (at.getAgent_type_name()!= null && at.getAgent_type_name().trim().length() > 0) {
			
			dbService.addAgentType(at, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		AgentTypeAddDialog admin = new AgentTypeAddDialog();
		admin.setAdminPanel(this);
		return admin;
	}
	
	private class AgentTypeAddDialog extends AdminDialog {

		private TextItem agentTypeNameItem = new TextItem("agentTypeName", "机构类型");
		private BooleanItem isReturnItem = new BooleanItem("is_retrun", "是否有返利");

		@Override
		protected Object getAddedModel() {
			
			AgentType at = new AgentType();
			at.setAgent_type_name(agentTypeNameItem.getValueAsString());
			at.setIs_return(isReturnItem.getValueAsBoolean());
			return at;
		}


		@Override
		protected DynamicForm getContentForm() {
			form = new DynamicForm();
			int len = 250;
			agentTypeNameItem.setWidth(len);
			isReturnItem.setWidth(len);
			isReturnItem.setValue(false);
			form.setPadding(5);
			form.setFields(agentTypeNameItem, isReturnItem);
			return form;
		}

	}


}
