<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-身份验证请求</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>身份验证请求<br>
<%
//1、取得身份验证请求所需要的信息
String tCertificateType  = request.getParameter("CertificateType");
String tCertificateNo  = request.getParameter("CertificateNo");
String tBankCardNo = request.getParameter("BankCardNo");
String tResultNotifyURL  = request.getParameter("ResultNotifyURL");
String tRequestDate       = request.getParameter("RequestDate"      );
String tRequestTime       = request.getParameter("RequestTime"      );

//2、生成身份验证请求对象
IdentityVerifyRequest tRequest = new IdentityVerifyRequest();
tRequest.setBankCardNo(tBankCardNo);      //银行帐号       （必要信息）
tRequest.setCertificateNo(tCertificateNo);   //证件号码       （必要信息）
tRequest.setCertificateType(tCertificateType); //证件类型       （必要信息）
tRequest.setResultNotifyURL(tResultNotifyURL); //身份验证回传网址（必要信息）
tRequest.setRequestDate  (tRequestDate  ); //验证请求日期 （必要信息 - YYYY/MM/DD）
tRequest.setRequestTime  (tRequestTime  ); //验证请求时间 （必要信息 - HH:MM:SS）

//3、传送身份验证请求并取得支付网址
TrxResponse tTrxResponse = tRequest.postRequest();
if (tTrxResponse.isSuccess()) {
   //4、身份验证请求提交成功，将客户端导向身份验证页面
   response.sendRedirect(tTrxResponse.getValue("VerifyURL"));
}
else {
   //5、身份验证请求提交失败，商户自定后续动作
  out.println("ReturnCode   = [" + tTrxResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>