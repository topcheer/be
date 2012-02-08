<%
/*
 * @(#)MerchantPayment.jsp	V1.5.1	2004/11/10
 *
 * Project: BJP03004
 *
 * Description:
 *    商户支付请求范例程序。
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    HiTRUST       2004/01/05  V1.0 Release
 *    HiTRUST       2004/01/07  V1.0.1 Release
 *    HiTRUST       2004/04/16  V1.0.3 Release 新增支付类型
 *    HiTRUST       2004/09/16  V1.2   Release 新增支付结果通知类型
 *    HiTRUST       2004/09/27  V1.5 Release
 *    HiTRUST       2004/11/10  V1.5.1 Release
 *
 * Copyright Notice:
 *   Copyright (c) 2001-2004 Beijing HiTRUST Technology Co., Ltd.
 *   1808 Room, Science & Technology Building, No. 9 South Street,
 *   Zhong Guan Cun, Haidian District, Beijing ,100081, China
 *   All rights reserved.
 *
 *   This software is the confidential and proprietary information of
 *   HiTRUST.COM, Inc. ("Confidential Information"). You shall not
 *   disclose such Confidential Information and shall use it only in
 *   accordance with the terms of the license agreement you entered
 *   into with HiTRUST.
 */
%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<%
//1、取得支付请求所需要的信息
String tOrderNo         = request.getParameter("OrderNo"        );
String tOrderDesc       = request.getParameter("OrderDesc"      );
String tOrderDate       = request.getParameter("OrderDate"      );
String tOrderTime       = request.getParameter("OrderTime"      );
String tOrderAmountStr  = request.getParameter("OrderAmount"    );
String tOrderURL        = request.getParameter("OrderURL"       );
String tProductType     = request.getParameter("ProductType"    );
String tPaymentType     = request.getParameter("PaymentType"    );
String tNotifyType      = request.getParameter("NotifyType"     );
String tResultNotifyURL = request.getParameter("ResultNotifyURL");
String tMerchantRemarks = request.getParameter("MerchantRemarks");
double  tOrderAmount    = Double.parseDouble(tOrderAmountStr);
String tPaymentLinkType = request.getParameter("PaymentLinkType");
String tBuyIP = request.getParameter("BuyIP");

//2、生成订单对象
Order tOrder = new Order();
tOrder.setOrderNo    (tOrderNo    ); //设定订单编号 （必要信息）
tOrder.setOrderDesc  (tOrderDesc  ); //设定订单说明
tOrder.setOrderDate  (tOrderDate  ); //设定订单日期 （必要信息 - YYYY/MM/DD）
tOrder.setOrderTime  (tOrderTime  ); //设定订单时间 （必要信息 - HH:MM:SS）
tOrder.setOrderAmount(tOrderAmount); //设定订单金额 （必要信息）
tOrder.setOrderURL   (tOrderURL   ); //设定订单网址
tOrder.setBuyIP   (tBuyIP   ); //设定订单IP

//3、生成定单订单对象，并将订单明细加入定单中（可选信息）
tOrder.addOrderItem(new OrderItem("IP000001", "中国移动IP卡", 100.00f, 1));
tOrder.addOrderItem(new OrderItem("IP000002", "网通IP卡"    ,  90.00f, 2));

//4、生成支付请求对象
PaymentRequest tPaymentRequest = new PaymentRequest();
tPaymentRequest.setOrder      (tOrder      ); //设定支付请求的订单 （必要信息）
tPaymentRequest.setProductType(tProductType); //设定商品种类 （必要信息）
                                              //PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、...
                                              //PaymentRequest.PRD_TYPE_TWO：实体商品
tPaymentRequest.setPaymentType(tPaymentType); //设定支付类型
                                              //PaymentRequest.PAY_TYPE_ABC：农行卡支付
                                              //PaymentRequest.PAY_TYPE_INT：国际卡支付
tPaymentRequest.setNotifyType(tNotifyType);   //设定商户通知方式
                                              //0：URL页面通知
                                              //1：服务器通知
tPaymentRequest.setResultNotifyURL(tResultNotifyURL); //设定支付结果回传网址 （必要信息）
tPaymentRequest.setMerchantRemarks(tMerchantRemarks); //设定商户备注信息
tPaymentRequest.setPaymentLinkType(tPaymentLinkType);//设定支付接入方式

//5、传送支付请求并取得支付网址
//TrxResponse tTrxResponse = tPaymentRequest.postRequest();
TrxResponse tTrxResponse = tPaymentRequest.extendPostRequest(1);
if (tTrxResponse.isSuccess()) {
   //6、支付请求提交成功，将客户端导向支付页面
   System.out.println("PaymentURL-->"+tTrxResponse.getValue("PaymentURL"));
   response.sendRedirect(tTrxResponse.getValue("PaymentURL"));
}
else {
   //7、支付请求提交失败，商户自定后续动作
%>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-支付请求</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>支付请求<br>
<%
  out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>