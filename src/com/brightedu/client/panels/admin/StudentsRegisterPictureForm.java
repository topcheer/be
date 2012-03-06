package com.brightedu.client.panels.admin;

import com.smartgwt.client.widgets.form.DynamicForm;

public class StudentsRegisterPictureForm extends DynamicForm{
	
	StudentsRegister reg;
	
	public StudentsRegisterPictureForm(StudentsRegister reg){
		this.reg = reg;
		StudentsRegisterMasterPanel master = (StudentsRegisterMasterPanel)reg.getMaster();
	}
	
	

}
