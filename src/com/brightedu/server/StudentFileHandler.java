package com.brightedu.server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.server.util.ServerProperties;

public class StudentFileHandler {

	StudentInfo student;
	List<StudentPicture> pictures;

	public StudentFileHandler() {
	}

	public StudentFileHandler(StudentInfo student, List<StudentPicture> pictures) {
		this.student = student;
		this.pictures = pictures;
	}

	public boolean movePictrues() {
		// 图片路径：
		// war/data/student_pics/register_year/pic_type_id/stu_id_pic.abc.jpg
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String tmstmp = sdf.format(student.getRegister_date());
		for (StudentPicture p : pictures) {
			File tmpPic = new File(ServerProperties.tempFileDir + p.getRemark());
			File destPic = new File(ServerProperties.studentPicDir + tmstmp
					+ File.separator + p.getPic_type_id() + File.separator
					+ student.getStudent_name() + "_" + p.getRemark());
			File parent = destPic.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
			boolean result = tmpPic.renameTo(destPic);
			if (!result)
				return false;
		}
		return true;
	}

	public StudentInfo getStudent() {
		return student;
	}

	public void setStudent(StudentInfo student) {
		this.student = student;
	}

	public List<StudentPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<StudentPicture> pictures) {
		this.pictures = pictures;
	}

}
