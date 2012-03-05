package com.brightedu.server.college;

import java.util.HashMap;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
/**
 * 
 * 天津大学上传接口
 * @author junzhang
 *
 */
public class TJUUploader implements IUploader {
	private static HashMap<String,TJUUploader> _instances = new HashMap<String,TJUUploader>();
	private RecruitAgent _agent;
	public static TJUUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			TJUUploader s = new TJUUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private TJUUploader(RecruitAgent agent)
	{
		_agent = agent;
	}

	@Override
	public boolean upload(StudentInfo student) {
		// TODO 代码
		return false;
	}

}
