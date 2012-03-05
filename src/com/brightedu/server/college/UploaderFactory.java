package com.brightedu.server.college;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;

public class UploaderFactory {
	
	public static IUploader getInstance(StudentInfo student, RecruitAgent agent)
	{
		int collegeID = student.getCollege_owner();
		if(collegeID == 0) // 郑州大学
		{
			return ZZUUploader.getInstance(agent);
		}
		else if(collegeID == 1) //天津大学
		{
			return TJUUploader.getInstance(agent);
		}
		else if(collegeID == 2) //石油大学
		{
			return UPOLUploader.getInstance(agent);
		}
		else if(collegeID == 4) //川农
		{
			return CNZXUploader.getInstance(agent);
		}		
		
		return null;
	}

}
