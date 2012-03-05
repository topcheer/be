package com.brightedu.server.college;

import java.util.HashMap;

import org.apache.catalina.util.InstanceSupport;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
/**
 * 郑州大学上传接口
 * 
 * @author junzhang
 *
 */
public class ZZUUploader implements IUploader {

	private static HashMap<String,ZZUUploader> _instances = new HashMap<String,ZZUUploader>();
	private RecruitAgent _agent;
	public static ZZUUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			ZZUUploader s = new ZZUUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private ZZUUploader(RecruitAgent agent)
	{
		_agent = agent;
	}
	@Override
	public boolean upload(StudentInfo student) {
		// TODO Auto-generated method stub
		return false;
	}

}
