package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.model.edu.BatchIndex;
import com.brightedu.model.edu.StudentClassified;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;

public class LevelDS extends DataSource{

    private static LevelDS instance = null;   
    
    public static LevelDS getInstance() {   
        if (instance == null) {   
            instance = new LevelDS("levelDS");   
        }   
        return instance;   
    }  
	public LevelDS(String id) {
		
        setID(id);   
        setClientOnly(true);       
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("levelID");   
        pkField.setHidden(true);   
        pkField.setPrimaryKey(true);   
  
        DataSourceTextField levelNameField = new DataSourceTextField("levelName", "层次", 128, true);   
    
  
        setFields(pkField, levelNameField);   
       
        
        setClientOnly(true);       
		
        BrightEdu.createDataBaseRPC().getStudentClassesList(-1, -1, false, new AsyncCallback<List<StudentClassified>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<StudentClassified> result) {
				// TODO Auto-generated method stub
				Iterator<StudentClassified> biit = result.iterator();
				while(biit.hasNext())
				{
					StudentClassified bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("levelID",bi.getClassified_id() );
					rc.setAttribute("levelName", bi.getClassified_name() );
					instance.addData(rc);
				}
				
			}
        	
        });
	}
	
}