<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-委托扣款单笔代扣请求</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>委托扣款单笔代扣请求<br>
<%
//1、取得委托扣款单笔代扣请求所需要的信息
String tOrderNo = request.getParameter("OrderNo");
String tRequestDate       = request.getParameter("RequestDate");
String tRequestTime       = request.getParameter("RequestTime");
String tCurrency          = request.getParameter("Currency");
String tAmount            = request.getParameter("Amount");
String tProductId         = request.getParameter("ProductId");
String tProductName       = request.getParameter("ProductName");
String tQuantity          = request.getParameter("Quantity");
String tCertificateNo     = request.getParameter("CertificateNo");
String tAgentSignNo 	  = request.getParameter("AgentSignNo");


//2、生成委托扣款单笔代扣请求对象
B2CAgentPaymentRequest tRequest = new B2CAgentPaymentRequest();
tRequest.setIOrderNo(tOrderNo);//订单编号（必要信息）
tRequest.setIRequestDate  (tRequestDate  ); //验证请求日期 （必要信息 - YYYY/MM/DD）
tRequest.setIRequestTime  (tRequestTime  ); //验证请求时间 （必要信息 - HH:MM:SS）
tRequest.setICurrency(tCurrency);
tRequest.setIAmount(tAmount);
tRequest.setIProductId(tProductId);
tRequest.setIProductName(tProductName);
tRequest.setIQuantity(tQuantity);
tRequest.setICertificateNo(tCertificateNo);
tRequest.setIAgentSignNo(tAgentSignNo);

//3、传送委托扣款单笔代扣请求
TrxResponse tTrxResponse = tRequest.postRequest();
if (tTrxResponse.isSuccess()) {
   //4、委托扣款单笔代扣请求提交成功
   out.println("Success!!!");
}
else {
   //5、委托扣款单笔代扣请求提交失败，商户自定后续动作
  out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>