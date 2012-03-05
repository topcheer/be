package com.brightedu.server.college;

import java.util.HashMap;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
/**
 * 石油大学上传接口 
 * 
 * @author junzhang
 *
 */
public class UPOLUploader implements IUploader{

	private static HashMap<String,UPOLUploader> _instances = new HashMap<String,UPOLUploader>();
	private RecruitAgent _agent;
	public static UPOLUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			UPOLUploader s = new UPOLUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private UPOLUploader(RecruitAgent agent)
	{
		_agent = agent;
	}
	@Override
	public boolean upload(StudentInfo student) {
		// TODO 代码
		return false;
	}

}
