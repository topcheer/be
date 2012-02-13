package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.model.edu.College;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;

public class CollegeDS extends DataSource{
    private static CollegeDS instance = null;   
    
    public static CollegeDS getInstance() {   
        if (instance == null) {   
            instance = new CollegeDS("collegeDS");   
        }   
        return instance;   
    }  
	public CollegeDS(String id) {
		
        setID(id);   
        setClientOnly(true);       
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("collegeID");   
        pkField.setHidden(true);   
        pkField.setPrimaryKey(true);   
  
        DataSourceTextField collegeNameField = new DataSourceTextField("collegeName", "大学", 128, true);   
    
  
        setFields(pkField, collegeNameField);   
       
        
        setClientOnly(true);       
		
        BrightEdu.createDataBaseRPC().getCollegeList(-1, -1, false, new AsyncCallback<List<College>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<College> result) {
				// TODO Auto-generated method stub
				Iterator<College> biit = result.iterator();
				while(biit.hasNext())
				{
					College bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("collegeID",bi.getCollege_id());
					rc.setAttribute("collegeName", bi.getCollege_name());
					instance.addData(rc);
				}
				
			}
        	
        });
	}
	
}