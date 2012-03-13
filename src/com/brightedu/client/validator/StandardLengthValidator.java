package com.brightedu.client.validator;

import com.smartgwt.client.widgets.form.validator.LengthRangeValidator;

public class StandardLengthValidator extends LengthRangeValidator {

	public StandardLengthValidator() {
		super();
		String errorMsg = "内容已经超过了最大允许数量!";
		setMax(127);//中英文一刀切！！！
		setMin(1);
		setErrorMessage(errorMsg);
	}

}
