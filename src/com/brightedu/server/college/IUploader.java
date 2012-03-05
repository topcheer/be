package com.brightedu.server.college;

import com.brightedu.model.edu.StudentInfo;

public interface IUploader {
	/**
	 * 上传接口
	 * 
	 * @param student  学生信息
	 * @return
	 */
	public boolean upload(StudentInfo student);
}
