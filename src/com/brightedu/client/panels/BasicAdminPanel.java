package com.brightedu.client.panels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.DataBaseRPCAsync;
import com.brightedu.client.panels.admin.AdminDialog;
import com.brightedu.client.validator.StandardLengthValidator;
import com.brightedu.shared.SearchCriteria;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Timer;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
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
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public abstract class BasicAdminPanel extends VLayout {

	// protected final DataBaseRPCAsync dbService =
	// GWT.create(DataBaseRPC.class);
	protected final DataBaseRPCAsync dbService = BrightEdu.createDataBaseRPC();
	protected ToolStripButton addButton = new ToolStripButton("添加");
	protected ToolStripButton delButton = new ToolStripButton("删除");
	protected TextItem searchItem = new TextItem("搜索内容");
	protected ComboBoxItem rangeItem = new ComboBoxItem("搜索范围");
	protected ListGrid resultList;

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

	protected StandardLengthValidator standardLenthValidator = new StandardLengthValidator();

	private AdminDialog dialog;

	protected SearchCriteria[] searchCriteria;
	LinkedHashMap<String, String> searchItemValues = new LinkedHashMap<String, String>();

	public BasicAdminPanel() {
		init();
	}

	/**
	 * other init actions for UI only
	 */
	public void postInit() {
	}

	public void init() {
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
					searchCriteria = null;
					gotoPage(1, true);
				} else {
					String keyWords = searchItem.getValueAsString();
					keyWords = keyWords == null ? "" : keyWords.trim();
					search(keyWords);
				}
			}
		});
		searchItem.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName() != null
						&& event.getKeyName().toLowerCase().equals("enter")) {
					String keyWords = searchItem.getValueAsString();
					keyWords = keyWords == null ? "" : keyWords.trim();
					search(keyWords);
				}
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
				if (event.getKeyName() != null
						&& event.getKeyName().toLowerCase().equals("enter")) {
					if (currentPageIndexField.getValue() != null) {
						int indexGoto = Integer.parseInt(currentPageIndexField
								.getValue().toString());
						if (indexGoto > totalPageCounts) {
							SC.warn("当前记录最多只有" + totalPageCounts + "页");
						} else if (indexGoto == 0) {
							SC.warn("跳转的页数必须大于0");
						} else {
							showPage(indexGoto);
						}
					}
				}
			}
		});

		refreButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				refresh();
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
		postInit();
		Timer t = new Timer() {
			@Override
			public void run() {
				initAdminDialog();
			}
		};
		t.schedule(3000);
	}

	protected void initAdminDialog() {
		dialog = createAdminDialog();
		dialog.init();
		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialog.show();
			}
		});
		initSearchItems();
	}

	protected void initSearchItems() {
		searchItemValues = getSearchTitles();
		rangeItem.setValueMap(searchItemValues);
	}

	public void refresh() {
		showPage(currentPageIndex);
	}

	private void initListGrid() {
		resultList = createResultList();
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
		// resultList.add
		// disable context menu
		// resultList.setShowHeaderContextMenu(false);
	}

	protected void del() {
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
			showPage(totalPageCounts);
		} else {
			if (currentPageIndex != totalPageCounts) {
				showPage(totalPageCounts);
			}
		}
	}

	public void showFirstPageRecords(boolean force) {
		if (force) {
			showPage(1);
		} else {
			if (currentPageIndex != 1) {
				showPage(1);
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
			showPage(currentPageIndex + 1);
		}
	}

	public void showPreviousPageRecords() {
		if (currentPageIndex - 1 > 0) {
			showPage(currentPageIndex - 1);
		}
	}

	protected void setCurrentPage(int pageIndex) {
		currentPageIndexField.setValue(pageIndex);
		currentPageIndex = pageIndex;
	}

	protected void setTotalCounts(int totalCounts) {
		totalPageCounts = totalCounts % currentRowsInOnePage == 0 ? (totalCounts / currentRowsInOnePage)
				: (totalCounts / currentRowsInOnePage + 1);
		if (totalPageCounts == 0) {
			totalPageCounts = 1;
		}
		totalPageLabel.setContents(" 共 " + totalPageCounts + " 页");
	}

	protected void gotoPage(int indexGoto) {
		gotoPage(indexGoto, false);
	}

	protected void initPages() {
		showPage(1, true);
	}

	protected ListGridField[] parseGridFields(String[] names, String[] titles,
			ListGridFieldType[] types, boolean[] canEdit, int[] width) {
		return parseGridFields(names, titles, types, canEdit, width, null);
	}

	protected ListGridField[] parseGridFields(String[] names, String[] titles,
			ListGridFieldType[] types, boolean[] canEdit, int[] width,
			Validator[] val) {
		ListGridField[] fields = new ListGridField[names.length];
		for (int i = 0; i < names.length; i++) {
			fields[i] = new ListGridField(names[i], titles[i]);
			if (width[i] > 0) {
				fields[i].setWidth(width[i]);
			}
			if (types[i] != null) {
				fields[i].setType(types[i]);
			}
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
				if (val != null && val[i] != null) {
					fields[i].setValidators(val[i]);
				}
			}
		}
		return fields;
	}

	public void showPage(int indexGoto, boolean init) {
		// UI aciton before goto
		// resultList.showDataLoading();
		gotoPage(indexGoto, init);
		// UI action after goto
	}

	public void showPage(int indexGoto) {
		showPage(indexGoto, false);
	}

	protected abstract void gotoPage(int indexGoto, boolean init);

	public abstract ListGridField[] createGridFileds();

	public void search(String keyWords) {
		Serializable searchObj = searchItem.getValueAsString();
		// searchItem.getValueField()
		if (searchObj == null || searchObj.toString().trim().equals("")
				|| rangeItem.getValueAsString() == null
				|| rangeItem.getValueAsString().trim().equals("")) {
			searchCriteria = null;

		} else {
			if (searchItemValues.containsKey(rangeItem.getValueAsString())) {
				searchCriteria = new SearchCriteria[1];
				SearchCriteria sc = new SearchCriteria();
				String key = rangeItem.getValueAsString();
				FormItem[] items = dialog.getContentForm().getFields();
				for (FormItem item : items) {
					if (item.getName().equals(key)) {
						if (item instanceof BooleanItem) {
							searchObj = searchObj.equals("y")
									|| searchObj.equals("Y")
									|| searchObj.equals("是");
							sc.setLike(false);
						} else if (item instanceof SelectItem
								|| item instanceof ComboBoxItem) {
							JavaScriptObject o = (JavaScriptObject) item
									.getAttributeAsObject("valueMap");
							if (o != null) {
								// 有两种可能，一种是list（没有key-value配对），一种是key-value配对的map
								Object collection = JSOHelper.convertToJava(o);
								if (collection instanceof Map) {
									Map<String, String> values = (Map<String, String>) collection;
									if (values != null) {
										Iterator<String> it = values.keySet()
												.iterator();
										boolean updatedKey = false;
										while (it.hasNext()) {
											String itKey = it.next();
											if (searchObj.equals(values
													.get(itKey))) {
												searchObj = itKey;
												// //此时，需要转换到整型的Integer
												searchObj = Integer
														.parseInt(searchObj
																.toString());
												sc.setLike(false);
												updatedKey = true;
												break;
											}
										}
										if (!updatedKey) {
											searchObj = new Integer(-2311); // 一个不可能出现的数
										}
									}
								}
							}
						}

						break;
					}
				}
				sc.setCriteriaKey(key);
				sc.setCriteriaValue(searchObj);
				// sc.setLike(true);

				searchCriteria[0] = sc;
			} else {
				BrightEdu.showTip("无效搜索范围: " + rangeItem.getValueAsString());
				searchCriteria = null;
			}
			// rangeItem.getValue()
		}
		gotoPage(1, true);
	}

	// public abstract void addRecord();

	public abstract void deleteRecords(List<Integer> deleteIds);

	public abstract void update(Record record);

	public void add(Serializable model) {
		dbService.addModel(model, getAdminDialog().getAddAsync());
	}

	public abstract AdminDialog createAdminDialog();

	public void afterAdd() {
		showPage(1, true);
	}

	public AdminDialog getAdminDialog() {
		return dialog;
	}

	@SuppressWarnings("rawtypes")
	protected DeleteAsync delAsync = new DeleteAsync();

	private class DeleteAsync<T> extends CommonAsyncCall<T> {

		@Override
		public void onSuccess(T result) {
			BrightEdu.showTip("已删除！");
			showPage(currentPageIndex);
		}

	}

	public ListGrid createResultList() {
		return new ListGrid();
	}

	public ListGrid getResultList() {
		return resultList;
	}

	protected LinkedHashMap<String, String> getSearchTitles() {
		LinkedHashMap<String, String> searchTitles = new LinkedHashMap<String, String>();
		FormItem[] items = dialog.getContentForm().getFields();
		for (FormItem item : items) {
			String title = item.getTitle();
			String name = item.getName();
			if (acceptSearchTitle(name, title)) {
				searchTitles.put(name, title);
			}
		}
		return searchTitles;
	}

	protected boolean acceptSearchTitle(String name, String title) {
		if (name.contains("date") || name.contains("password")
				|| name.contains("day") || name.contains("url")
				|| name.equals("saveBtn"))
			return false;
		return true;
	}

	public SearchCriteria[] getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(SearchCriteria[] searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
}
