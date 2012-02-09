/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.brightedu.server;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.brightedu.client.PaymentRPC;
import com.brightedu.model.edu.ChargeAdmin;
import com.brightedu.server.util.Log;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hitrust.trustpay.client.TrxResponse;
import com.hitrust.trustpay.client.b2c.Order;
import com.hitrust.trustpay.client.b2c.PaymentRequest;



public class PaymentRPCImpl extends RemoteServiceServlet implements
		PaymentRPC {
	String resource = "com/brightedu/MapperConfig.xml";
	Reader reader = null;

	SqlSessionFactory sessionFactory;
	SqlSession session;

	public PaymentRPCImpl() {
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			Log.e(e.getMessage(), e);
		}
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		session = sessionFactory.openSession();
	}

	@Override
	public String createOrder(List<ChargeAdmin> chargeList) {
		
		Order order = createOrderInternal(chargeList);
		System.out.println(order.getBuyIP());
		// TODO Auto-generated method stub
		PaymentRequest tPaymentRequest = new PaymentRequest();
		
		tPaymentRequest.setOrder (order ); //设定支付请求的订单 （必要信息）
		tPaymentRequest.setProductType(PaymentRequest.PRD_TYPE_ONE); //设定商品种类 （必要信息）
		//PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、...
		//PaymentRequest.PRD_TYPE_TWO：实体商品
		tPaymentRequest.setPaymentType(PaymentRequest.PAY_TYPE_ABC); //设定支付类型
		//PaymentRequest.PAY_TYPE_ABC：农行卡支付
		//PaymentRequest.PAY_TYPE_INT：国际卡支付
		tPaymentRequest.setNotifyType("1"); //设定商户通知方式
		//0：URL页面通知
		//1：服务器通知
		tPaymentRequest.setResultNotifyURL("http://jmedu.wassecurity.net/BrightEdu/paymentReceive"); //设定支付结果回传网址 （必要信息）
		tPaymentRequest.setMerchantRemarks("学费"); //设定商户备注信息
		tPaymentRequest.setPaymentLinkType(PaymentRequest.PAY_LINK_TYPE_NET); //设定支付接入方式

		//5、传送支付请求并取得支付网址
		TrxResponse tTrxResponse = tPaymentRequest.postRequest();
		
		if (tTrxResponse.isSuccess()) {
		//6、支付请求提交成功，将客户端导向支付页面
		  return "true:" + tTrxResponse.getValue("PaymentURL");
		}
		else
		{
			return "false:" + tTrxResponse.getReturnCode() + "-" + tTrxResponse.getErrorMessage();
		}
	}
	
	private Order createOrderInternal(List<ChargeAdmin> chargeList)
	{
		
		long orderId= System.currentTimeMillis(); // use system current time millis as order number
		
		Order order = new Order();
		
		order.setOrderNo (new Long(orderId).toString()); //设定订单编号 （必要信息）
		order.setOrderDesc ("xx学杂费" ); //设定订单说明
		order.setOrderDate (new SimpleDateFormat("yyyy/MM/dd").format(new Date(System.currentTimeMillis())) ); //设定订单日期 （必要信息 - YYYY/MM/DD）
		order.setOrderTime (new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()))  ); //设定订单时间 （必要信息 - HH:MM:SS）
		//order.setOrderAmount(190); //设定订单金额 （必要信息）
		order.setOrderURL ("http://www.jmedu.com.cn" ); //设定订单网址
		
		order.setBuyIP (getThreadLocalRequest().getLocalAddr()); //设定订单IP

		
		double totalSum = 1;
		
		if(chargeList != null){
			
		
			
			for (int i =0 ; i< chargeList.size();i++)
			{
				//order.addOrderItem(new OrderItem("1", "学费", 100.00f, 1));
				//TODO add order Item per charge item , add up amount as totalSum
				
			}
		}
		
		order.setOrderAmount(totalSum);
		
		
		
		
		return order;
	}


}
