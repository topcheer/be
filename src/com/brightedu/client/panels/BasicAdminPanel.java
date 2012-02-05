package com.brightedu.client.panels;

import com.brightedu.client.DataBaseRPC;
import com.brightedu.client.DataBaseRPCAsync;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
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

	protected int currentPageIndex = 0;
	protected int totalPageCounts = 1;

	protected int currentRowsInOnePage = 20;

	public BasicAdminPanel() {
		init();
		postInit();
	}

	protected void postInit() {
	}

	private void init() {
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
				addRecord();
			}
		});
		delButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteRecords();
			}
		});

		ToolStrip toolbar = new ToolStrip();
		// toolbar.setPadding(5);
		// toolbar.addSpacer(3);
		toolbar.addMember(addButton);
		// toolbar.addSpacer(10);
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
		rowsPerPageItem.setValueMap("10", "20", "50", "100", "300");
		rowsPerPageItem.setWidth(60);
		rowsPerPageItem.setValue(String.valueOf(currentRowsInOnePage));
		rowsPerPageItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {

				initPages();
			}
		});
		firstLabel.setAutoFit(true);
		totalPageLabel.setWidth(50);
		firstPageBtn.setIcon("pagination_first.gif");
		lastPageBtn.setIcon("pagination_last.gif");
		previousPageBtn.setIcon("pagination_prev.gif");
		nextPageBtn.setIcon("pagination_next.gif");

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
		currentPageIndexField.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().toLowerCase().equals("enter")) {
					if (currentPageIndexField.getValue() != null) {
						int indexGoto = Integer.parseInt(currentPageIndexField
								.getValue().toString());
						if (indexGoto > totalPageCounts) {
							SC.say("当前记录最多只有" + totalPageCounts + "页");
						} else {
							gotoPage(indexGoto);
						}
					}
				}
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

		addMember(pagetools);

	}

	public ToolStripButton getAddButton() {
		return addButton;
	}

	public ToolStripButton getDelButton() {
		return delButton;
	}

	public TextItem getSearchItem() {
		return searchItem;
	}

	public SelectItem getRangeItem() {
		return rangeItem;
	}

	public Label getTotalPageLabel() {
		return totalPageLabel;
	}

	public int getTotalPageCounts() {
		return totalPageCounts;
	}

	public void setTotalPageCounts(int totalPageCounts) {
		this.totalPageCounts = totalPageCounts;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	protected abstract void search(String keyWords, Record range);

	protected abstract void addRecord();

	protected abstract void deleteRecords();

	protected abstract void showLastPageRecords();

	protected abstract void showFirstPageRecords();

	protected abstract void showNextPageRecords();

	protected abstract void showPreviousPageRecords();

	protected abstract void gotoPage(int indexGoto);

	protected abstract void initPages();

}
