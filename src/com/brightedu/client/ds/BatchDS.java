package com.brightedu.client.ds;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.brightedu.client.BrightEdu;
import com.brightedu.model.edu.BatchIndex;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.SelectItem;

public class BatchDS extends DataSource{

    private static BatchDS instance = null;   

    public static BatchDS getInstance() {   
    	if(instance != null) instance.destroy(); 
            instance = new BatchDS("batchDS");   

        return instance;   
    }  
	public BatchDS(String id) {
		
        setID(id);   
        setClientOnly(true);       
        
        DataSourceIntegerField pkField = new DataSourceIntegerField("batchID");   
        pkField.setHidden(true);   
        pkField.setPrimaryKey(true);   
  
        DataSourceTextField batchNameField = new DataSourceTextField("batchName", "批次", 128, true);   
    
  
        setFields(pkField, batchNameField);   
       
        
        setClientOnly(true);       
		
        BrightEdu.createDataBaseRPC().getBatchList(-1, -1, false, new AsyncCallback<List<BatchIndex>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				SC.say("获取批次时发生错误");
			}

			@Override
			public void onSuccess(List<BatchIndex> result) {
				// TODO Auto-generated method stub
				Iterator<BatchIndex> biit = result.iterator();
				while(biit.hasNext())
				{
					BatchIndex bi = biit.next();
					Record rc = new Record();
					rc.setAttribute("batchID",bi.getBatch_id() );
					rc.setAttribute("batchName", bi.getBatch_name());
					instance.addData(rc);
				}
				
			}
        	
        });
	}
	
//	public static void setValueMap(final SelectItem item)
//	{
//		 
//		final LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
//		
//        BrightEdu.createDataBaseRPC().getBatchList(-1, -1, false, new AsyncCallback<List<BatchIndex>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				SC.say("获取批次时发生错误");
//			}
//
//			@Override
//			public void onSuccess(List<BatchIndex> result) {
//				// TODO Auto-generated method stub
//				Iterator<BatchIndex> biit = result.iterator();
//				while(biit.hasNext())
//				{
//					BatchIndex bi = biit.next();
////					Record rc = new Record();
////					rc.setAttribute("batchID",bi.getBatch_id() );
////					rc.setAttribute("batchName", bi.getBatch_name());
//					valueMap.put(bi.getBatch_id() + "", bi.getBatch_name());
//					System.out.println(valueMap.toString());
//				}
//				item.setValueMap(valueMap);
//				
//			}
//        	
//        });
//		
//		
//		
//		
//	}
	
}