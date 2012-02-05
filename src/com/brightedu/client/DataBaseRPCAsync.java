package com.brightedu.client;

import java.util.List;

import com.brightedu.model.edu.BatchIndex;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataBaseRPCAsync {

	void getBatchList(AsyncCallback<List<BatchIndex>> callback);

	void addBatch(String batch_name, AsyncCallback<Boolean> callback);

	void deleteBatch(Integer batch_id, AsyncCallback<Boolean> callback);


}
