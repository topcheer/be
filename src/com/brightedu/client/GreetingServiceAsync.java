package com.brightedu.client;

import java.io.Serializable;

import com.brightedu.model.edu.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void login(User user, AsyncCallback<Serializable[]> callback);

	void logout(User user, AsyncCallback<Void> callback);
}
