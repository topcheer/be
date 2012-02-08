<%
/*
 * @(#)B2CAgentSignResult.jsp	V1.5.1	2009/05/05
 */
%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-委托扣款签约解约结果接收</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>委托扣款签约解约结果<br>
<%
//1、取得MSG参数，并利用此参数值生成验证结果对象
B2CAgentSignResult tResult = new B2CAgentSignResult(request.getParameter("MSG"));
//System.out.println(request.getParameter("MSG"));
//2、判断验证结果状态，进行后续操作
if (tResult.isSuccess()) {
  //3、签约解约成功
  if(tResult.getValue("TrxType").equals(TrxType.TRX_TYPE_B2C_AgentSignContract_REQ))
  {
    out.println("签约成功<br>");
    out.println("签约协议号: [" + tResult.getValue("AgentSignNo") + "]<br>");
    out.println("签约卡号后4位: [" + tResult.getValue("Last4CardNo") + "]<br>");
  }else if(tResult.getValue("TrxType").equals(TrxType.TRX_TYPE_B2C_AgentUnsignContract_REQ))
  {
  	out.println("解约成功<br>");
  }
  out.println("OrderNo         = [" + tResult.getValue("OrderNo"        ) + "]<br>");

}
else {
  //4、签约解约失败
  out.println("ReturnCode   = [" + tResult.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tResult.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>