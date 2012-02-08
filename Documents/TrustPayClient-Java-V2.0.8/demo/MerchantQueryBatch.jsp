<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import = "com.hitrust.trustpay.client.b2c.*" %>
<%@ page import = "com.hitrust.trustpay.client.*" %>
<%@ page import = "java.util.ArrayList" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<HTML>
<HEAD><TITLE>农行网上支付平台-商户接口范例-退款批量结果查询</TITLE></HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>退款批量结果查询<br>
<%
//1、取得退款批量结果查询请求所需要的信息
String tSerialNumber         = request.getParameter("SerialNumber");

//2、生成退款批量结果查询请求对象
QueryBatchRequest tQueryBatchRequest = new QueryBatchRequest();
tQueryBatchRequest.setSerialNumber      (tSerialNumber); //设定退款批量结果查询请求的流水号（必要信息）

//3、传送退款批量结果查询请求并取得结果
TrxResponse tResponse = tQueryBatchRequest.postRequest();
//4、判断退款批量结果查询状态，进行后续操作
if (tResponse.isSuccess()) {
 //5、生成批量对象
  Batch tBatch = new Batch(new XMLDocument(tResponse.getValue("RefundBatch")));
  out.println("BatchNo      = [" + tBatch.getSerialNumber     () + "]<br>");
  out.println("RefundAmount  = [" + tBatch.getRefundAmount () + "]<br>");
  out.println("RefundCount    = [" + tBatch.getRefundCount   () + "]<br>");
  out.println("BatchStatus    = [" + tBatch.getStatus   () + "]<br>");
  
  //6、取得订单明细
  ArrayList tOrders = tBatch.getOrder();
  for(int i = 0; i < tOrders.size(); i++) {
    Order tOrder = (Order) tOrders.get(i);
    out.println("OrderNo   = [" + tOrder.getOrderNo  () + "]<br>");
    out.println("RefundAmount = [" + tOrder.getRefundAmount() + "]<br>");
    out.println("OrderStatus  = [" + tOrder.getOrderStatus () + "]<br>");
  }
}
else {
  //7、退款批量结果查询失败
  out.println("ReturnCode   = [" + tResponse.getReturnCode  () + "]<br>");
  out.println("ErrorMessage = [" + tResponse.getErrorMessage() + "]<br>");
}
%>
<a href='Merchant.html'>回商户首页</a></CENTER>
</BODY></HTML>