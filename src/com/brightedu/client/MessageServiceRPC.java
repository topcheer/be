package com.brightedu.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MessageService")
public interface MessageServiceRPC extends RemoteService {

	public void startMessageSession();

}
