package com.brightedu.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceRPCAsync {

	void startMessageSession(AsyncCallback<Void> callback);

}
