package com.brightedu.dao.edu;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.server.college.UPOLUploader;

public class TestUpolUploader {

	@Test
	public void testUpload() {
		RecruitAgent agent = new RecruitAgent();
		agent.setUserid_for_college("hzzx");
		UPOLUploader.getInstance(agent).upload(new StudentInfo());
	}

}
