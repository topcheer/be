<%
/*
 * @(#)MerchantResult.jsp	V1.5.1	2004/11/10
 *
 * Project: BJP03004
 *
 * Description:
 *    商户支付结果接收范例程序。
 *
 * Modify Information:
 *    Author        Date        Description
 *    ============  ==========  =======================================
 *    HiTRUST       2004/01/05  V1.0 Release
 *    HiTRUST       2004/01/07  V1.0.1 Release
 *    HiTRUST       2004/04/16  V1.0.3 Release
 *    HiTRUST       2004/06/01  V1.0.4 Release
 *    HiTRUST       2004/08/30  V1.2 Release
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
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-支付结果接收</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>支付结果<br>
<%
String tABC = request.getParameter("ABC");
System.out.println("tABC         = [" + tABC + "]<br>");

//1、取得MSG参数，并利用此参数值生成支付结果对象
PaymentResult tResult = new PaymentResult(request.getParameter("MSG"));

//2、判断支付结果状态，进行后续操作
if (tResult.isSuccess()) {
  //3、支付成功
  out.println("TrxType         = [" + tResult.getValue("TrxType"        ) + "]<br>");
  out.println("OrderNo         = [" + tResult.getValue("OrderNo"        ) + "]<br>");
  out.println("Amount          = [" + tResult.getValue("Amount"         ) + "]<br>");
  out.println("BatchNo         = [" + tResult.getValue("BatchNo"        ) + "]<br>");
  out.println("VoucherNo       = [" + tResult.getValue("VoucherNo"      ) + "]<br>");
  out.println("HostDate        = [" + tResult.getValue("HostDate"       ) + "]<br>");
  out.println("HostTime        = [" + tResult.getValue("HostTime"       ) + "]<br>");
  out.println("MerchantRemarks = [" + tResult.getValue("MerchantRemarks") + "]<br>");
  out.println("PayType         = [" + tResult.getValue("PayType"        ) + "]<br>");
  out.println("NotifyType      = [" + tResult.getValue("NotifyType"     ) + "]<br>");
  out.println("TrnxNo          = [" + tResult.getValue("iRspRef"        ) + "]<br>");
}
else {
  //4、支付失败
  out.println("ReturnCode   = [" + tResult.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tResult.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>