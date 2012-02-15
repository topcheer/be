package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.List;

import org.mortbay.log.Log;

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

public class CollegeSubjectDS extends DataSource {
	
	 private static CollegeSubjectDS instance = null;   
	 
    public static CollegeSubjectDS getInstance(Integer collegeId, Integer levelId, Integer batchId) {   
		if(instance != null) instance.destroy(); 
        instance = new CollegeSubjectDS("collegeSubjectDS",collegeId,levelId,batchId);   

    return instance;   
}  
	
	public CollegeSubjectDS(String id ,Integer collegeId , Integer levelId , Integer batchId)
	{
        setID(id);
//        if (getClientOnly() == null || getClientOnly() == false)
//        {
            setClientOnly(true);       
            
            
            DataSourceIntegerField pkField = new DataSourceIntegerField("subjectID");   
            pkField.setHidden(true);   
            pkField.setPrimaryKey(true);   
      
            DataSourceTextField subjectNameField = new DataSourceTextField("subjectName", "专业", 128, true);   
            DataSourceIntegerField lolField = new DataSourceIntegerField("lol", "学制(年)", 50, true);   
            lolField.setCanEdit(true);
            
            setFields(pkField, subjectNameField,lolField);   
//        }

		if(!(collegeId == -1) && !(levelId== -1) && !(batchId == -1))
		{
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
						Record rc = new Record();
						rc.setAttribute("subjectID",bi.getSubeject_id());
						rc.setAttribute("subjectName", bi.getSubject_name());
						rc.setAttribute("lol",bi.getLength_of_schooling());
						instance.addData(rc);
						
					}
					
				}
	        });
		}

	}

	
	
}