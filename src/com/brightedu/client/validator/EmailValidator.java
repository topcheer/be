package com.brightedu.client.validator;

import com.smartgwt.client.widgets.form.validator.RegExpValidator;

public class EmailValidator extends RegExpValidator {

	public EmailValidator() {
		setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
	}

}
