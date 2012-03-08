package com.brightedu.client.panels.admin;

import java.io.Serializable;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public abstract class AdminDialog extends Window {

	IButton okBtn = new IButton("确定");
	DynamicForm form;
	protected HLayout bottomLayout = new HLayout();
	LengthRangeValidator standardLenthValidator = new LengthRangeValidator();
	

	public AdminDialog() {
		standardLenthValidator.setMax(127);standardLenthValidator.setMin(1);
		standardLenthValidator.setErrorMessage("内容已经超过了最大允许数量!");
	}

	public void init() {
		setEdgeMarginSize(4);
		setEdgeOffset(5);
		setAutoCenter(true);
		setTitle("新增");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(false);
		setOverflow(Overflow.VISIBLE);
		setAutoSize(true);
		setCanDragResize(true);
		setAnimateShowEffect(AnimationEffect.WIPE);
		setAnimateShowTime(800);
		form = createContentForm();
//		form.setAutoShowParent(true);
//		form.setAutoDraw(true);
		parseRequired();
		// form.setWidth100();
		// form.setHeight100();
		// form.setLayoutAlign(Alignment.CENTER);
		okBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (form.validate()) {
					add();
				}
			}
		});
		form.setAutoFocus(true);
		form.setWrapItemTitles(false);
		// form.setAutoHeight();

		addAdminContentUI();
		
		bottomLayout.setAlign(Alignment.RIGHT);
		// bottomLayout.addMember(new Label("  "));
		bottomLayout.addMember(new LayoutSpacer());
		bottomLayout.setPadding(5);
		okBtn.setWidth(70);
		//
		bottomLayout.addMember(okBtn);

		bottomLayout.setAutoHeight();

		bottomLayout.setWidth100();

		addItem(bottomLayout);

		// RegExpValidator lenValidator = new RegExpValidator("^\\d{10}$");
		// lenValidator.setErrorMessage("len error");
		addFieldsHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName() != null
						&& event.getKeyName().toLowerCase().equals("enter")) {
					add();
				}
			}
		}, null);
		addCloseClickHandler(new CloseClickHandler() {

			@Override
			public void onCloseClick(CloseClickEvent event) {
				hide();
			}
		});
	}
	
	protected void addAdminContentUI(){
		addItem(form);
	}

	private void parseRequired() {
		FormItem[] items = form.getFields();
		for (FormItem item : items) {
			if (item.getRequired()!=null && item.getRequired()) {
				item.setTitle(item.getTitle() + "*");
			}
		}
	}

	protected void add() {
		adminPanel.add(getAddedModel());
	}

	@Override
	public void hide() {
		super.hide();
		reset();
	}

	public void addFieldsHandler(KeyPressHandler pressHandler,
			Validator lenValidator) {
		FormItem[] items = form.getFields();
		for (FormItem item : items) {
			item.addKeyPressHandler(pressHandler);
			if (lenValidator != null) {
				item.setValidators(lenValidator);
			}
		}
	}

	protected void reset() {
		FormItem[] items = form.getFields();
		for (FormItem item : items) {
			item.setValue("");
		}
	}

	protected abstract Serializable getAddedModel();

	protected abstract DynamicForm createContentForm();

	public DynamicForm getContentForm() {
		return form;
	}

	protected BasicAdminPanel adminPanel;

	private AdminAddAsyncCall addAsync = new AdminAddAsyncCall();

	public AdminAddAsyncCall getAddAsync() {
		return addAsync;
	}

	public void setAdminPanel(BasicAdminPanel adminPanel) {
		this.adminPanel = adminPanel;
	}

	private class AdminAddAsyncCall<T> extends CommonAsyncCall<T> {

		@Override
		public void onSuccess(T result) {
			adminPanel.afterAdd();
			hide();
			BrightEdu.showTip("已添加!");
		}

	}

}
