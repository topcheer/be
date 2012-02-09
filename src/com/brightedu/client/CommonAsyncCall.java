package com.brightedu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;

public abstract class CommonAsyncCall<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		failed();
		SC.warn(caught.getMessage());
	}

	protected void failed() {
	}
}
