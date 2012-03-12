package com.brightedu.server;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.List;

import org.mortbay.log.Log;

import com.brightedu.model.edu.StudentInfo;
import com.brightedu.model.edu.StudentPicture;
import com.brightedu.server.util.ServerProperties;

public class StudentFileHandler {

	private StudentInfo student;
	private List<StudentPicture> pictures;
	private static String datemarkFormat = "yyyyMM";

	public static final boolean UPDATE = false;

	public static final boolean ADD = true;

	public StudentFileHandler() {
	}

	public StudentFileHandler(StudentInfo student, List<StudentPicture> pictures) {
		this.student = student;
		this.pictures = pictures;
	}

	public boolean movePictrues(boolean status) {
		// 图片路径：
		// war/data/student_pics/register_year/pic_type_id/stuid_UUID.jpg
		SimpleDateFormat sdf = new SimpleDateFormat(datemarkFormat);
		String tmstmp = sdf.format(student.getRegister_date());
		for (final StudentPicture p : pictures) {
			if (p.getPic() == null) {
				if (status == ADD) {

					continue;
				} else if (status == UPDATE) {
					// 说明图片已被删除
					File picFolder = new File(ServerProperties.studentPicDir
							+ tmstmp + "/" + p.getPic_type_id());
					if (picFolder.exists()) {
						File[] files = picFolder.listFiles(new FileFilter() {

							@Override
							public boolean accept(File f) {
								if (f.getName().startsWith(
										p.getStudent_id() + "_")) {
									return true;
								}
								return false;
							}
						});
						for (File f : files) {
							f.delete();
						}
					}
					continue;
				}
			}

			File tmpPic = new File(ServerProperties.tempFileDir
					+ new File(p.getPic()).getName());
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
					Log.warn("Cannot create dir: " + parent.getAbsolutePath()+", please check user permission");
					return false;
				}
			}
			boolean result = tmpPic.renameTo(destPic);
			p.setPic(picURL);
			if (!result)
				return false;
		}
		return true;
	}

	public void deletePictures() {
		for (StudentPicture pic : pictures) {
			File destPic = new File(ServerProperties.deployPath
					+ pic.getPic());
			if (destPic.exists()) {
				destPic.delete();
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
