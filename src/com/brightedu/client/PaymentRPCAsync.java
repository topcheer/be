package com.brightedu.client;

import java.util.List;

import com.brightedu.model.edu.ChargeAdmin;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PaymentRPCAsync {

	void createOrder(List<ChargeAdmin> chargeList, AsyncCallback<String> callback);

}
