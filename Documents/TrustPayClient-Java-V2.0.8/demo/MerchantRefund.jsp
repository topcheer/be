<%
/*
 * @(#)MerchantRefund.jsp	V1.5.1	2004/11/10
 *
 * Project: BJP03004
 *
 * Description:
 *    商户退货请求范例程序。
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
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-退货</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>退货<br>
<%
//1、取得退货所需要的信息
String tOrderNo      = request.getParameter("OrderNo"  );
String tNewOrderNo      = request.getParameter("NewOrderNo"  );
String tTrxAmountStr = request.getParameter("TrxAmount");
double  tTrxAmount    = Double.parseDouble(tTrxAmountStr);

//2、生成退货请求对象
RefundRequest tRequest = new RefundRequest();
tRequest.setOrderNo  (tOrderNo  );  //订单号   （必要信息）
tRequest.setNewOrderNo  (tNewOrderNo  );  //新订单号   （必要信息）
tRequest.setTrxAmount(tTrxAmount);  //退货金额 （必要信息）

//3、传送退货请求并取得退货结果
TrxResponse tResponse = tRequest.postRequest();

//4、判断退货结果状态，进行后续操作
if (tResponse.isSuccess()) {
  //5、退货成功
  out.println("TrxType   = [" + tResponse.getValue("TrxType"  ) + "]<br>");
  out.println("OrderNo   = [" + tResponse.getValue("OrderNo"  ) + "]<br>");
  out.println("NewOrderNo   = [" + tResponse.getValue("NewOrderNo"  ) + "]<br>");
  out.println("TrxAmount = [" + tResponse.getValue("TrxAmount") + "]<br>");
  out.println("BatchNo   = [" + tResponse.getValue("BatchNo"  ) + "]<br>");
  out.println("VoucherNo = [" + tResponse.getValue("VoucherNo") + "]<br>");
  out.println("HostDate  = [" + tResponse.getValue("HostDate" ) + "]<br>");
  out.println("HostTime  = [" + tResponse.getValue("HostTime" ) + "]<br>");
  out.println("TrnxNo    = [" + tResponse.getValue("iRspRef"  ) + "]<br>");
}
else {
  //6、退货失败
  out.println("ReturnCode   = [" + tResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>