package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.model.edu.College;
import com.brightedu.model.edu.Subjects;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;

public class SubjectDS extends DataSource{
    private static SubjectDS instance = null;   
    
    public static SubjectDS getInstance() {   
    		if(instance != null) instance.destroy(); 
            instance = new SubjectDS("subjectDS");   
 
        return instance;   
    }  
	public SubjectDS(String id) {
		
        setID(id);
        setClientOnly(true);       
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("subjectID");   
        pkField.setHidden(true);   
        pkField.setPrimaryKey(true);   
  
        DataSourceTextField subjectNameField = new DataSourceTextField("subjectName", "专业", 128, true);   
        DataSourceIntegerField lolField = new DataSourceIntegerField("lol", "学制(年)", 50, true);   
        lolField.setCanEdit(true);
        setFields(pkField, subjectNameField,lolField);   
		
        BrightEdu.createDataBaseRPC().getSubjectsList(-1, -1, false, new AsyncCallback<List<Subjects>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<Subjects> result) {
				// TODO Auto-generated method stub
				Iterator<Subjects> biit = result.iterator();
				while(biit.hasNext())
				{
					Subjects bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("subjectID",bi.getSubject_id());
					rc.setAttribute("subjectName", bi.getSubject_name());
					rc.setAttribute("lol",0);
					instance.addData(rc);
				}
				
			}
        });
	}
	
}