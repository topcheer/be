<%
/*
 * @(#)ReciveServerPage.jsp	  V2.0.0
 *
 * Project: BJP03004
 *
 * Description:
 *    商户接收服务器支付结果范例程序。
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    HiTRUST       2004/09/17  V1.2 Release
 *    HiTRUST       2004/09/27  V1.5 Release
 *    HiTRUST       2004/11/10  V1.5.1 Release
 *    HiTRUST       2005/05/11  V2.0.0 Release
 *
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
<% response.setHeader("Cache-Control", "no-cache"); %>
<%
//0、设定商户结果显示页面
String tMerchantPage = "";

//1、取得MSG参数，并利用此参数值生成支付结果对象
PaymentResult tResult = new PaymentResult(request.getParameter("MSG"));

//2、判断支付结果状态，进行后续操作
if (tResult.isSuccess()) {
  //3、支付成功
  tMerchantPage = "http://172.30.5.8:9084/b2cclient/ResultSuccess.jsp";
}
else {
  //4、支付失败
  tMerchantPage = "http://172.30.5.8:9084/b2cclient/ResultFail.jsp";
}
%>

<URL><%= tMerchantPage %></URL>

<HTML>
<HEAD>
<meta http-equiv="refresh" content="0; url='<%= tMerchantPage %>'">
</HEAD>
</HTML>