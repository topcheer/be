<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-委托扣款签约请求</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>身份验证请求<br>
<%
//1、取得委托扣款签约请求所需要的信息
String tOrderNo = request.getParameter("OrderNo");
String tCertificateType  = request.getParameter("CertificateType");
String tCertificateNo  = request.getParameter("CertificateNo");
String tResultNotifyURL  = request.getParameter("ResultNotifyURL");
String tRequestDate       = request.getParameter("RequestDate");
String tRequestTime       = request.getParameter("RequestTime");
//String tInvaidDate		  = request.getParameter("InvaidDate");
//String tALimitAmt         = request.getParameter("ALimitAmt");
//String tADayLimitAmt	  = request.getParameter("ADayLimitAmt");
String tNotifyType		  = request.getParameter("NotifyType");

//2、生成委托扣款签约请求对象
B2CAgentSignContractRequest tRequest = new B2CAgentSignContractRequest();
tRequest.setIOrderNo(tOrderNo);//订单编号（必要信息）
tRequest.setICertificateNo(tCertificateNo);   //证件号码       （必要信息）
tRequest.setICertificateType(tCertificateType); //证件类型       （必要信息）
tRequest.setIResultNotifyURL(tResultNotifyURL); //身份验证回传网址（必要信息）
tRequest.setIRequestDate  (tRequestDate  ); //验证请求日期 （必要信息 - YYYY/MM/DD）
tRequest.setIRequestTime  (tRequestTime  ); //验证请求时间 （必要信息 - HH:MM:SS）
//tRequest.setIInvaidDate(tInvaidDate);
//tRequest.setIALimitAmt(tALimitAmt);
//tRequest.setIADayLimitAmt(tADayLimitAmt);
tRequest.setINotifyType(tNotifyType);

//3、传送委托扣款签约请求并取得签约网址
TrxResponse tTrxResponse = tRequest.postRequest();
if (tTrxResponse.isSuccess()) {
   //4、委托扣款签约请求提交成功，将客户端导向签约页面
   response.sendRedirect(tTrxResponse.getValue("B2CAgentSignContractURL"));
}
else {
   //5、委托扣款签约请求提交失败，商户自定后续动作
  out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>