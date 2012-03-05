package com.brightedu.server.college;

import java.util.HashMap;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
/**
 * 
 * 川农在线上传接口 
 * @author junzhang
 *
 */
public class CNZXUploader implements IUploader {
	private static HashMap<String,CNZXUploader> _instances = new HashMap<String,CNZXUploader>();
	private RecruitAgent _agent;
	public static CNZXUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			CNZXUploader s = new CNZXUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private CNZXUploader(RecruitAgent agent)
	{
		_agent = agent;
	}

	@Override
	public boolean upload(StudentInfo student) {
		// TODO 代码
		return false;
	}

}
