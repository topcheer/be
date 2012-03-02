package com.brightedu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

public abstract class CommonAsyncCall<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		
//		if(caught instanceof InvocationTargetException){
//			caught = getRootCause((InvocationTargetException)caught);
//		}
		failed(caught);
		caught.printStackTrace();
		SC.warn("错误: "+caught.getMessage());
	}

	protected void failed(Throwable caught) {
	}
	
//	private Exception getRootCause(InvocationTargetException caught){
//		return (Exception)caught.getTargetException();
//	}
}
