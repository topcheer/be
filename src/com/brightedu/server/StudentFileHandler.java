package com.brightedu.server;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.mortbay.log.Log;

import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.server.util.ServerProperties;

public class StudentFileHandler {

	StudentInfo student;
	List<StudentPicture> pictures;
	String datemarkFormat = "yyyyMM";

	public StudentFileHandler() {
	}

	public StudentFileHandler(StudentInfo student, List<StudentPicture> pictures) {
		this.student = student;
		this.pictures = pictures;
	}

	public boolean movePictrues() {
		// 图片路径：
		// war/data/student_pics/register_year/pic_type_id/stuid_UUID.jpg
		SimpleDateFormat sdf = new SimpleDateFormat(datemarkFormat);
		String tmstmp = sdf.format(student.getRegister_date());
		for (StudentPicture p : pictures) {

			File tmpPic = new File(ServerProperties.tempFileDir
					+ new File(p.getRemark()).getName());
			String picLocation = tmstmp + "/" + p.getPic_type_id() + "/"
					+ student.getStudent_id() + "_" + tmpPic.getName();
			String picURL = ServerProperties.dataConfig + "/student_pics/"
					+ picLocation;
			File destPic = new File(ServerProperties.studentPicDir
					+ picLocation);
			File parent = destPic.getParentFile();
			if (!parent.exists()) {
				boolean mkdirResult = parent.mkdirs();
				if (!mkdirResult) {
					Log.warn("Cannot create dir: " + parent.getAbsolutePath());
					return false;
				}
			}
			boolean result = tmpPic.renameTo(destPic);
			p.setRemark(picURL);
			if (!result)
				return false;
		}
		return true;
	}

	public void deletePictures() {
		for (StudentPicture pic : pictures) {
			File destPic = new File(ServerProperties.deployPath
					+ pic.getRemark());
			if (destPic.exists()) {
				destPic.delete();
			} else {
				Log.warn(student.getStudent_name() + "_"
						+ student.getStudent_id() + " does not have picture: "
						+ pic.getRemark());
			}
		}

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
