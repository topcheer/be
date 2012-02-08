<%@ page pageEncoding="gb2312" %>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<% request.setCharacterEncoding("gb2312"); %>
<%
String tReturnCode=request.getParameter("ReturnCode");
String tErrorMsg=request.getParameter("ErrorMessage");
%>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-支付请求</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>支付请求<br>
<%
  out.println("ReturnCode   = [" +tReturnCode+ "]<br>");
  out.println("ErrorMessage = [" + tErrorMsg+ "]<br>");
%>
<a href='MerchantPaymentIE.html'>回商户首页</a></CENTER>
</BODY></HTML>
