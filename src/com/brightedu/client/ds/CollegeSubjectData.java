package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.CollegeSubject;
import com.brightedu.model.edu.CollegeSubjectView;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CollegeSubjectData {
	
	
	private static RecordList data = new RecordList();
	
	public static RecordList getData(Integer collegeId , Integer levelId , Integer batchId)
	{
		if(data != null)
		{
			data = new RecordList();
			
		}
		
		new CollegeSubjectData().pupolate(collegeId ,levelId,batchId);
		
		return data;
	}
	
	public CollegeSubjectData()
	{
		
	}
	
	private void pupolate( Integer collegeId , Integer levelId , Integer batchId) {

        BrightEdu.createDataBaseRPC().getCollegeSubjectList(collegeId, levelId, batchId, new AsyncCallback<List<CollegeSubjectView>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取招生计划时发生错误");
			}

			@Override
			public void onSuccess(List<CollegeSubjectView> result) {
				// TODO Auto-generated method stub
				Iterator<CollegeSubjectView> biit = result.iterator();
				while(biit.hasNext())
				{
					CollegeSubjectView bi = biit.next();
					ListGridRecord rc = new ListGridRecord();
					rc.setAttribute("subjectID",bi.getSubeject_id());
					rc.setAttribute("subjectName", bi.getSubject_name());
					rc.setAttribute("lol",bi.getLength_of_schooling());
					System.out.println(bi.getSubeject_id() + " " + bi.getSubject_name() + " " + bi.getLength_of_schooling());
					if(data == null)System.out.println("instance is null");
					data.add(rc);
					
				}
				
				
				
			}


        	
        });
	}
	
	
}