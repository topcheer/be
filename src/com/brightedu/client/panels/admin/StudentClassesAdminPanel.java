package com.brightedu.client.panels.admin;

import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.client.CommonAsyncCall;
import com.brightedu.client.panels.BasicAdminPanel;
import com.brightedu.model.edu.AgentType;
import com.brightedu.model.edu.StudentClassified;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.ValidatorType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BooleanItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.grid.ListGridField;

public class StudentClassesAdminPanel extends BasicAdminPanel {

	@Override
	public void search(String keyWords, Record range) {
	}

	@Override
	public void gotoPage(final int indexGoto, final boolean init) {
		AsyncCallback<List<StudentClassified>> callback = new CommonAsyncCall<List<StudentClassified>>() {
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
					StudentClassified sc = (StudentClassified) result.get(i);
					Record rec = new Record();
					rec.setAttribute("select", false);
					rec.setAttribute("id", sc.getClassified_id());
					rec.setAttribute("object", sc);
					rec.setAttribute("obj_name", sc.getClassified_name());
					rec.setAttribute("default_lol", (int)sc.getDefault_lol());
					rec.setAttribute("reg_time", sc.getRegister_date());
					listData[i] = rec;
				}
				resultList.setData(listData);
				setCurrentPage(indexGoto);
			}
		};
		dbService.getStudentClassesList((indexGoto - 1) * currentRowsInOnePage,
				currentRowsInOnePage, init, callback);
	}

	@Override
	public void deleteRecords(List<Integer> deleteIds) {
		dbService.deleteStudentClasses(deleteIds, delAsync);
	}

	@Override
	public ListGridField[] createGridFileds() {
		return parseGridFields(new String[] { "obj_name","default_lol", "reg_time" },
				new String[] { "学生层次名称","缺省学制(年)", "录入时间" }, new ListGridFieldType[] {
						ListGridFieldType.TEXT,ListGridFieldType.INTEGER, ListGridFieldType.DATE },
				new boolean[] { true,true, false }, new int[] { -1,120, 200 });
	}

	@Override
	public void update(final Record rec) {
		final StudentClassified editedSC = (StudentClassified) rec
				.getAttributeAsObject("object");
		final String oldName = editedSC.getClassified_name();
		final int lol = editedSC.getDefault_lol();
		editedSC.setClassified_name(rec.getAttributeAsString("obj_name"));
		editedSC.setDefault_lol((short)rec.getAttributeAsInt("default_lol").intValue());
		dbService.saveStudentClasses(editedSC, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				BrightEdu.showTip("已保存!");
			}

			protected void failed() { // rollback in UI
				editedSC.setClassified_name(oldName);
				editedSC.setDefault_lol((short)lol);
				rec.setAttribute("obj_name", oldName);
				
			}
		});
	}

	@Override
	public void add(Object model) {
		final StudentClassified at = (StudentClassified) model;

		if (at.getClassified_name() != null
				&& at.getClassified_name().trim().length() > 0) {

			dbService.addStudentClass(at, getAdminDialog().getAddAsync());
		} else {
			SC.say("内容不能为空！");
		}
	}

	@Override
	public AdminDialog createAdminDialog() {
		StudentClassifiedAddDialog admin = new StudentClassifiedAddDialog();
		admin.setAdminPanel(this);
		return admin;
	}

	private class StudentClassifiedAddDialog extends AdminDialog {

		private TextItem classifiedName = new TextItem("classifiedName",
				"层次名称");
		private TextItem lol = new TextItem("lol", "缺省学制(年)");
		int len = 250;

		public void init() {
			super.init();
			setSize(len + 70 + "", "120");
		}

		@Override
		protected Object getAddedModel() {

			StudentClassified sc = new StudentClassified();
			sc.setClassified_name(classifiedName.getValueAsString());
			sc.setDefault_lol(new Short(lol.getValueAsString()));
			return sc;
		}

		@Override
		protected DynamicForm createContentForm() {
			form = new DynamicForm();
			classifiedName.setWidth(len);
			lol.setWidth(len);
			lol.setValue(2);
			lol.setMask("#");
			Validator validator = new Validator();
			validator.setType(ValidatorType.INTEGERRANGE);
			validator.setAttribute("min", 1);
			validator.setAttribute("max", 6);
			lol.setValidators(validator);
			
			form.setPadding(5);
			form.setFields(classifiedName, lol);
			form.setValidateOnExit(true);
			
			return form;
		}

	}

}
