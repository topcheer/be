package com.brightedu.client.panels.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.frame.BrightFrame;
import com.brightedu.client.frame.BrightFrame.LoadHandler;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.client.validator.StandardLengthValidator;
import com.brightedu.client.window.FileUpdateDialog;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeAgreement;
import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.shared.Constants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class CorpCollegeAgreementAdminPanel extends BasicAdminPanel {

	LinkedHashMap<String, String> colleges;
	LinkedHashMap<String, String> agents;
	LinkedHashMap<String, String> statusMap;

	ListGridField[] fields;

	FileUpdateDialog fileUpdate;

	public void init() {

		statusMap = new LinkedHashMap<String, String>();
		statusMap.put("1", "有效");
		statusMap.put("-1", "无效");
		super.init();
		initSelectionValueMaps();
	}

	private void initSelectionValueMaps() {
		colleges = new LinkedHashMap<String, String>();
		agents = new LinkedHashMap<String, String>();

		fields[2].setValueMap(statusMap); // 状态
		dbService.getNameValuePareList(
				new String[] { "College", "RecruitAgent" },
				new CommonAsyncCall<List>() {
					public void onSuccess(List result) {
						for (int i = 0; i < result.size(); i++) {
							List nameValuePares = (List) result.get(i);
							switch (i) {
							case 0:
								for (int x = 0; x < nameValuePares.size(); x++) {
									College c = (College) nameValuePares.get(x);
									colleges.put(c.getCollege_id() + "",
											c.getCollege_name());
								}
								break;
							case 1:
								for (int x = 0; x < nameValuePares.size(); x++) {
									RecruitAgent r = (RecruitAgent) nameValuePares
											.get(x);
									agents.put(r.getAgent_id() + "",
											r.getAgent_name());
								}
								break;
							default:
								break;
							}
						}
						fields[0].setValueMap(colleges);// 合作院校
						fields[1].setValueMap(agents);// 我方高校
						resultList.redraw();
					}
				});
	}

	public void refresh() {
		initSelectionValueMaps();
		super.refresh();
	}

	public ListGrid createResultList() {
		ListGrid grid = new ListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record,
					Integer colNum) {

				String fieldName = this.getFieldName(colNum);
				if (fieldName.equals("agreement")) {
					HLayout recordCanvas = new HLayout(3);
					recordCanvas.setHeight(22);
					recordCanvas.setAlign(Alignment.CENTER);
					ImgButton editImg = new ImgButton();
					editImg.setShowDown(false);
					editImg.setShowRollOver(false);
					editImg.setLayoutAlign(Alignment.CENTER);
					editImg.setSrc("edit.png");
					editImg.setPrompt("上传新协议");
					editImg.setHeight(16);
					editImg.setWidth(16);
					editImg.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							if (fileUpdate == null) {
								fileUpdate = new FileUpdateDialog("新协议",
										"newCollegeAgreement", record);
								fileUpdate.setActionURL(GWT.getModuleBaseURL()
										+ Constants.FILE_HANDLE_SERVLET_NAMAE
										+ "?action="
										+ Constants.ACTION_UPDATE_COLLEGE_AGREEMENT
										+ "&id="
										+ record.getAttributeAsInt("id"));
							}
							fileUpdate.show();
						}
					});

					ImgButton openImg = new ImgButton();
					openImg.setShowDown(false);
					openImg.setShowRollOver(false);
					openImg.setAlign(Alignment.CENTER);
					openImg.setSrc("open.gif");
					openImg.setPrompt("打开/下载协议");
					openImg.setHeight(16);
					openImg.setWidth(16);
					openImg.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							CollegeAgreement agreement = (CollegeAgreement) record
									.getAttributeAsObject("object");
							String url = GWT.getModuleBaseURL()
									+ Constants.FILE_HANDLE_SERVLET_NAMAE
									+ "?action="
									+ Constants.ACTION_GET_COLLEGE_AGREEMENT
									+ "&agreement_name="
									+ agreement.getAgreement_name();
							Window.open(URL.encode(url), "", null);
						}
					});

					recordCanvas.addMember(openImg);
					recordCanvas.addMember(editImg);
					return recordCanvas;
				} else {
					return null;
				}

			}
		};
		grid.setShowRecordComponents(true);
		grid.setShowRecordComponentsByCell(true);
		return grid;
	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<CollegeAgreement>> callback = new CommonAsyncCall<List<CollegeAgreement>>() {
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
					CollegeAgreement bi = (CollegeAgreement) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", bi.getAgreement_id());
					rec.setAttribute("object", bi);
					rec.setAttribute("college", bi.getCollege_id());
					rec.setAttribute("agent", bi.getAgent_id());
					rec.setAttribute("status", bi.getAgreement_status() ? "1"
							: "-1");
					rec.setAttribute("modify_date", bi.getUpdate_date());

					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};

		dbService.getCollegeAgreementList((indexGoto - 1)
				* currentRowsInOnePage, currentRowsInOnePage, init, callback);
	}

	@Override
	public ListGridField[] createGridFileds() {
		fields = parseGridFields(new String[] { "college", "agent", "status",
				"modify_date", "agreement" }, new String[] { "合作高校", "我方学校",
				"状态", "修改时间", "协议" }, new ListGridFieldType[] {
				ListGridFieldType.TEXT, ListGridFieldType.TEXT,
				ListGridFieldType.TEXT, ListGridFieldType.DATE, null },
				new boolean[] { true, true, true, false, false }, new int[] {
						-1, -1, 100, 200, 80 });
		return fields;
	}

	@Override
	public void search(String keyWords, Record range) {

	}

	@Override
	protected void del() {
		RecordList recList = resultList.getDataAsRecordList();
		final List<CollegeAgreement> deletedAggrements = new ArrayList<CollegeAgreement>();
		for (int i = 0; i < recList.getLength(); i++) {
			if (recList.get(i).getAttributeAsBoolean("select")) {
				deletedAggrements.add((CollegeAgreement) recList.get(i)
						.getAttributeAsObject("object"));
			}
		}
		if (deletedAggrements.size() == 0) {
			SC.say("请选择一些记录");
			return;
		}
		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value) {
					dbService.deleteCollegeAgreement(deletedAggrements,
							delAsync);
				}
			}
		});
	}

	@Override
	public void deleteRecords(List deletedAggrements) {
		// DO Nothing, as the delete action is completely done in del()
	}

	@Override
	public void update(final Record rec) {
		final CollegeAgreement agreement = (CollegeAgreement) rec
				.getAttributeAsObject("object");
		final int oldCollege = agreement.getCollege_id();
		final int oldAgent = agreement.getAgent_id();
		final boolean status = agreement.getAgreement_status();
		agreement.setCollege_id(Integer.parseInt(rec.getAttribute("college")));
		agreement.setAgent_id(Integer.parseInt(rec.getAttribute("agent")));
		agreement.setAgreement_status(rec.getAttribute("status").equals("1"));
		dbService.saveCollegeAgreement(agreement,
				new CommonAsyncCall<Boolean>() {
					@Override
					public void onSuccess(Boolean result) {
						BrightEdu.showTip("已保存!");
					}

					protected void failed() { // rollback in UI
						agreement.setAgent_id(oldAgent);
						agreement.setCollege_id(oldCollege);
						rec.setAttribute("college", oldCollege);
						rec.setAttribute("agent", oldAgent);
						rec.setAttribute("status", status ? "1" : "-1");
					}
				});
	}

	@Override
	public void add(Serializable model) {
		// FORM形式的提交，不需要用到这个方法
	}

	@Override
	public AdminDialog createAdminDialog() {

		return new AgreementDialog();
	}

	private class AgreementDialog extends AdminDialog {

		private String faketarget = "fakeuploadframe_agreement";

		private SelectItem collegeItem = new SelectItem("college", "合作高校");
		private SelectItem agentItem = new SelectItem("agent", "我方学校");
		private SelectItem statusItem = new SelectItem("status", "状态");
		UploadItem fileItem = new UploadItem("agreement", "协议");
		DynamicForm form = new DynamicForm();
		ValuesManager vm = new ValuesManager();
		boolean fakeFrameLoaded = false;
		Img busyImg = new Img("loadingSmall.gif");
		BrightFrame frame = new BrightFrame(faketarget);

		public void init() {
			StandardLengthValidator notEmptyValidator = new StandardLengthValidator();
			collegeItem.setValidators(notEmptyValidator);
			collegeItem.setRequired(true);
			agentItem.setValidators(notEmptyValidator);
			agentItem.setRequired(true);
			statusItem.setValidators(notEmptyValidator);
			statusItem.setRequired(true);
			fileItem.setValidators(notEmptyValidator);
			fileItem.setRequired(true);
			frame.setWidth("1");
			frame.setHeight("1");
			frame.setVisible(false);

			frame.addMyLoadHandler(new LoadHandler() {

				@Override
				public void onLoad(Event event) {
					if (!fakeFrameLoaded) {
						fakeFrameLoaded = true;
					} else {
						if (!frame.isSuccess()) {
							SC.warn("保存失败!" + frame.getMessage());
						} else {
							// 保存成功
							afterAdd();
							BrightEdu.showTip("已添加!");
						}
						hide();
					}
				}
			});
			// fileItem.addChangeHandler(new ChangeHandler() {
			//
			// @Override
			// public void onChange(ChangeEvent event) {
			// // System.out.println("change "+ event.g);
			// }
			// });
			bottomLayout.addMember(frame);
			busyImg.setSize("16", "16");
			busyImg.setVisible(false);
			bottomLayout.addMember(busyImg);
			super.init();
			setSize("325", "170");
			form.setAction(GWT.getModuleBaseURL()
					+ Constants.FILE_HANDLE_SERVLET_NAMAE + "?action="
					+ Constants.ACTION_ADD_COLLEGE_AGREEMENT);
			agentItem.setValueMap(agents);
			collegeItem.setValueMap(colleges);
		}

		@Override
		protected Serializable getAddedModel() {
			// DO NOTHING, FORM 形式的提交，不需要用到这个方法
			return null;
		}

		// here it is form submit action
		protected void add() {
			if (form.validate()) {
				busyImg.setVisible(true);
				okBtn.disable();
				form.submitForm();
			}
		}

		protected void reset() {
			okBtn.enable();
			busyImg.setVisible(false);
			super.reset();
		}

		@Override
		protected DynamicForm createContentForm() {
			int len = 250;
			collegeItem.setWidth(len);
			agentItem.setWidth(len);
			statusItem.setWidth(len);

			statusItem.setValueMap(statusMap);
			form.setPadding(5);
			form.setFields(collegeItem, agentItem, statusItem, fileItem);
			form.setValuesManager(vm);
			form.setEncoding(Encoding.MULTIPART);
			form.setTarget(faketarget);
			return form;
		}
	}

}
