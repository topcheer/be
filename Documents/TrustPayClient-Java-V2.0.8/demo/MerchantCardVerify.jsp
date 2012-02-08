<%
/*
 * @(#)MerchantFundPayment.jsp	V1.5.1	2005/10/31
 *
 * Project: BJP03004
 *
 * Description:
 *    商户支付请求范例程序。
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    Wang Yaling   2005/10/31  V1.0 Release
 *    龚晓松			2005-12-19  float to double 需求修改
 * Copyright Notice:
 *   Copyright (c) 2001-2005 Beijing HiTRUST Technology Co., Ltd.
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
String tResultNotifyURL = request.getParameter("ResultNotifyURL");
String tCertificateType = request.getParameter("CertificateType");
String tCertificateNo   = request.getParameter("CertificateNo"  );

//2、生成卡身份验证请求对象
CardVerifyRequest tCardVerifyRequest = new CardVerifyRequest();
tCardVerifyRequest.setResultNotifyURL(tResultNotifyURL); //设定支付结果回传网址 （必要信息）
tCardVerifyRequest.setCertificateType(tCertificateType);//设定证件类型
tCardVerifyRequest.setCertificateNo(tCertificateNo);//设定证件号码

//5、传送卡身份验证请求并取得支付网址
TrxResponse tTrxResponse = tCardVerifyRequest.postRequest();
if (tTrxResponse.isSuccess()) {
   //6、卡身份验证请求提交成功，将客户端导向支付页面
   response.sendRedirect(tTrxResponse.getValue("VerifyURL"));
}
else {
   //7、卡身份验证请求提交失败，商户自定后续动作
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