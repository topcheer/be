package com.brightedu.client.panels;

import java.util.ArrayList;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.DataBaseRPC;
import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.panels.admin.AdminDialog;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.PickerIcon;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public abstract class BasicAdminPanel extends VLayout {

	protected final DataBaseRPCAsync dbService = GWT.create(DataBaseRPC.class);

	protected ToolStripButton addButton = new ToolStripButton("添加");
	protected ToolStripButton delButton = new ToolStripButton("删除");
	protected TextItem searchItem = new TextItem("搜索内容");
	protected SelectItem rangeItem = new SelectItem("搜索范围");
	protected ListGrid resultList = new ListGrid();

	protected SelectItem rowsPerPageItem = new SelectItem("每页行数");
	protected ToolStripButton firstPageBtn = new ToolStripButton();
	protected ToolStripButton previousPageBtn = new ToolStripButton();
	protected Label firstLabel = new Label("第");
	protected TextItem currentPageIndexField = new TextItem(
			"currentPageIndexFiled", "");
	protected Label totalPageLabel = new Label("共1页");

	protected ToolStripButton nextPageBtn = new ToolStripButton();
	protected ToolStripButton lastPageBtn = new ToolStripButton();
	protected ToolStripButton refreButton = new ToolStripButton();

	protected int currentPageIndex = 1;
	private int totalPageCounts = 1;

	protected int currentRowsInOnePage = 20;

	private AdminDialog dialog;

	public BasicAdminPanel() {
		init();
		postInit();
	}

	/**
	 * other init actions for UI only
	 */
	protected void postInit() {
	}

	private void init() {
		initListGrid();
		addButton.setAutoFit(true);
		delButton.setAutoFit(true);
		addButton.setIcon("add.gif");
		delButton.setIcon("delete.gif");
		addButton.setShowFocused(false);
		addButton.setShowFocusedAsOver(false);
		delButton.setShowFocused(false);
		delButton.setShowFocusedAsOver(false);
		final PickerIcon findIcon = new PickerIcon(PickerIcon.SEARCH);
		final PickerIcon cancelIcon = new PickerIcon(PickerIcon.CLEAR);
		searchItem.setIcons(findIcon, cancelIcon);
		searchItem.addIconClickHandler(new IconClickHandler() {
			public void onIconClick(IconClickEvent event) {
				FormItemIcon icon = event.getIcon();
				if (icon.getSrc().equals(cancelIcon.getSrc())) {
					searchItem.setValue("");
				} else {
					String keyWords = searchItem.getValueAsString();
					keyWords = keyWords == null ? "" : keyWords.trim();
					Record range = rangeItem.getSelectedRecord();
					search(keyWords, range);
				}
			}
		});
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (dialog == null) {
					dialog = createAdminDialog();
					dialog.init();
				}
				dialog.show();
			}
		});
		delButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				del();
			}
		});

		ToolStrip toolbar = new ToolStrip();
		toolbar.addMember(addButton);
		toolbar.addMember(delButton);
		toolbar.addFill();
		DynamicForm form = new DynamicForm();
		form.setWrapItemTitles(false);
		form.setNumCols(4);
		form.setAutoHeight();
		form.setFields(rangeItem, searchItem);

		toolbar.addMember(form);

		setPadding(5);
		toolbar.setAutoHeight();
		addMember(toolbar);
		addMember(resultList);

		ToolStrip pagetools = new ToolStrip();
		rowsPerPageItem.setValueMap("1", "2", "3", "10", "20", "50", "100",
				"300");
		rowsPerPageItem.setWidth(60);
		rowsPerPageItem.setValue(String.valueOf(currentRowsInOnePage));
		rowsPerPageItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				currentRowsInOnePage = Integer.parseInt(rowsPerPageItem
						.getValueAsString());
				initPages();
			}
		});
		firstLabel.setAutoFit(true);
		totalPageLabel.setWidth(50);
		firstPageBtn.setIcon("pagination_first.gif");
		lastPageBtn.setIcon("pagination_last.gif");
		previousPageBtn.setIcon("pagination_prev.gif");
		nextPageBtn.setIcon("pagination_next.gif");
		refreButton.setIcon("refresh.gif");

		firstPageBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showFirstPageRecords();
			}
		});
		lastPageBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showLastPageRecords();
			}
		});
		previousPageBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showPreviousPageRecords();
			}
		});
		nextPageBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showNextPageRecords();
			}
		});
		currentPageIndexField.setWidth(40);
		currentPageIndexField.setMaskPromptChar("");// without underline '_'
		currentPageIndexField.setMask("####");
		currentPageIndexField.setShowTitle(false);
		currentPageIndexField.setShowHint(false);

		currentPageIndexField.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName().toLowerCase().equals("enter")) {
					if (currentPageIndexField.getValue() != null) {
						int indexGoto = Integer.parseInt(currentPageIndexField
								.getValue().toString());
						if (indexGoto > totalPageCounts) {
							SC.warn("当前记录最多只有" + totalPageCounts + "页");
						} else if (indexGoto == 0) {
							SC.warn("跳转的页数必须大于0");
						} else {
							gotoPage(indexGoto);
						}
					}
				}
			}
		});

		refreButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				gotoPage(currentPageIndex);
			}
		});
		DynamicForm pageForm1 = new DynamicForm();
		pageForm1.setWrapItemTitles(false);
		pageForm1.setNumCols(2);
		pageForm1.setAutoWidth();
		pageForm1.setFields(rowsPerPageItem);

		DynamicForm pageForm2 = new DynamicForm();
		pageForm2.setWrapItemTitles(false);
		pageForm2.setNumCols(1);
		pageForm2.setAutoHeight();
		pageForm2.setAutoWidth();
		pageForm2.setFields(currentPageIndexField);

		pagetools.addMember(pageForm1);
		pagetools.addSpacer(3);
		pagetools.addSeparator();
		pagetools.addMember(firstPageBtn);
		pagetools.addMember(previousPageBtn);
		pagetools.addMember(firstLabel);
		pagetools.addMember(pageForm2);
		pagetools.addMember(totalPageLabel);
		pagetools.addMember(nextPageBtn);
		pagetools.addMember(lastPageBtn);
		pagetools.addSeparator();
		pagetools.addMember(refreButton);
		addMember(pagetools);
		initPages();
	}

	private void initListGrid() {
		ListGridField[] fields = createGridFileds();
		if (fields == null) {
			fields = new ListGridField[0];// only for select
		}
		ListGridField selectField = new ListGridField("select", "选择", 100);
		// static hidden fields
		ListGridField idField = new ListGridField("id", "代码");// used for id
		ListGridField objectField = new ListGridField("object", "完整对象");// used
		ListGridField[] newFields = new ListGridField[fields.length + 3];
		selectField.setType(ListGridFieldType.BOOLEAN);
		selectField.setAlign(Alignment.CENTER);
		newFields[0] = selectField;
		for (int i = 0; i < fields.length; i++) {
			newFields[i + 1] = fields[i];
		}
		newFields[newFields.length - 2] = idField;
		newFields[newFields.length - 1] = objectField;
		idField.setHidden(true);
		objectField.setHidden(true);
		resultList.setFields(newFields);
		resultList.setCanEdit(true);
		resultList.setEditEvent(ListGridEditEvent.DOUBLECLICK);
	}

	private void del() {
		RecordList recList = resultList.getDataAsRecordList();
		final List<Integer> deleteIds = new ArrayList<Integer>();
		for (int i = 0; i < recList.getLength(); i++) {
			if (recList.get(i).getAttributeAsBoolean("select")) {
				deleteIds.add(recList.get(i).getAttributeAsInt("id"));
			}
		}
		if (deleteIds.size() == 0) {
			SC.say("请选择一些记录");
			return;
		}
		SC.ask("确认", "你确认要删除选中的记录吗？", new BooleanCallback() {
			@Override
			public void execute(Boolean value) {
				if (value) {
					deleteRecords(deleteIds);
				}
			}
		});
	}

	public void showLastPageRecords(boolean force) {
		if (force) {
			gotoPage(totalPageCounts);
		} else {
			if (currentPageIndex != totalPageCounts) {
				gotoPage(totalPageCounts);
			}
		}
	}

	public void showFirstPageRecords(boolean force) {
		if (force) {
			gotoPage(1);
		} else {
			if (currentPageIndex != 1) {
				gotoPage(1);
			}
		}
	}

	public void showLastPageRecords() {
		showLastPageRecords(false);
	}

	public void showFirstPageRecords() {
		showFirstPageRecords(false);
	}

	public void showNextPageRecords() {
		if (currentPageIndex + 1 <= totalPageCounts) {
			gotoPage(currentPageIndex + 1);
		}
	}

	public void showPreviousPageRecords() {
		if (currentPageIndex - 1 > 0) {
			gotoPage(currentPageIndex - 1);
		}
	}

	protected void setCurrentPage(int pageIndex) {
		currentPageIndexField.setValue(pageIndex);
		currentPageIndex = pageIndex;
	}

	protected void setTotalCounts(int totalCounts) {
		totalPageCounts = totalCounts % currentRowsInOnePage == 0 ? (totalCounts / currentRowsInOnePage)
				: (totalCounts / currentRowsInOnePage + 1);
		if(totalPageCounts==0){
			totalPageCounts = 1;
		}
		totalPageLabel.setContents(" 共 " + totalPageCounts + " 页");
	}

	protected void gotoPage(int indexGoto) {
		gotoPage(indexGoto, false);
	}

	protected void initPages() {
		gotoPage(1, true);
	}

	protected ListGridField[] parseGridFields(String[] names, String[] titles,
			ListGridFieldType[] types, boolean[] canEdit, int[] width) {
		ListGridField[] fields = new ListGridField[names.length];
		for (int i = 0; i < names.length; i++) {
			fields[i] = new ListGridField(names[i], titles[i]);
			if (width[i] > 0) {
				fields[i].setWidth(width[i]);
			}
			fields[i].setType(types[i]);
			fields[i].setCanEdit(canEdit[i]);
			fields[i].setAlign(Alignment.CENTER);
			if (canEdit[i]) {
				fields[i].addCellSavedHandler(new CellSavedHandler() {

					@Override
					public void onCellSaved(CellSavedEvent event) {
						final Record rec = event.getRecord();
						update(rec);
					}
				});
			}
		}
		return fields;
	}

	public abstract void gotoPage(int indexGoto, boolean init);

	public abstract ListGridField[] createGridFileds();

	public abstract void search(String keyWords, Record range);

	// public abstract void addRecord();

	public abstract void deleteRecords(List<Integer> deleteIds);

	public abstract void update(Record record);

	public abstract void add(Object model);

	public abstract AdminDialog createAdminDialog();

	public AdminDialog getAdminDialog() {
		return dialog;
	}
	
	protected DeleteAsync delAsync = new DeleteAsync();
	
	private class DeleteAsync<T> extends CommonAsyncCall<T>{

		@Override
		public void onSuccess(T result) {
			BrightEdu.showTip("已删除！");
			gotoPage(currentPageIndex);
		}
		
	}
}
