package com.brightedu.shared;

import java.io.Serializable;
import java.util.List;

import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;

public class BrightStudent implements Serializable {

	private static final long serialVersionUID = 5481186505326534802L;
	
	private StudentInfo studentInfo;
	private List<StudentPicture> pictures;

	public BrightStudent() {

	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public List<StudentPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<StudentPicture> pictures) {
		this.pictures = pictures;
	}

}
