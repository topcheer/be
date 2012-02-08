<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="com.hitrust.trustpay.client.b2c.*"%>
<%@ page import="com.hitrust.trustpay.client.*"%>
<%@ page import="java.util.ArrayList"%>
<%
	request.setCharacterEncoding("GBK");
	response.setHeader("Cache-Control", "no-cache");
%>
<HTML>
<HEAD>
<TITLE>农行网上支付平台-商户接口范例-委托扣款批量处理结果</TITLE>
</HEAD>
<BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF'
	ALINK='#FF0000'>
<CENTER>委托扣款批量处理结果<br>
<%
	//1、取得赎回、分红、托收请求信息
	String tBatchNo = request.getParameter("BatchNo");
	String tBatchDate = request.getParameter("BatchDate");

	//2、生成请求对象
	B2CAgentBatchQueryRequest tRequest = new B2CAgentBatchQueryRequest();
	tRequest.setBatchNo(tBatchNo); //请求批次号       （必要信息）
	tRequest.setBatchDate(tBatchDate); //请求日期      YYYY/MM/DD

	//3、传送请求并取得结果
	TrxResponse tResponse = tRequest.postRequest();

	//4、判断结果状态，进行后续操作
	if (tResponse.isSuccess()) {
		//5、请求成功
		//5、商户对账单下载成功，生成对账单对象
		AgentBatch tAgentBatch = new AgentBatch(new XMLDocument(tResponse.getValue("AgentBatch")));
		out.println("BatchNo  = [" + tAgentBatch.getBatchNo() + "]<br>");
		out.println("BatchDate    = [" + tAgentBatch.getBatchDate() + "]<br>");
		out.println("BatchTime    = [" + tAgentBatch.getBatchTime() + "]<br>");
		out.println("AgentAmount  = [" + tAgentBatch.getAgentAmount() + "]<br>");
		out.println("AgentCount    = [" + tAgentBatch.getAgentCount() + "]<br>");
		out.println("BatchStatus    = [" + tAgentBatch.getBatchStatus() + "]<br>");
		out.println("BatchStatusZH    = [" + tAgentBatch.getBatchSatusChinese(tAgentBatch.getBatchStatus()) + "]<br>");
		//6、取得对账单明细
		ArrayList tAgentBatchDetails = tAgentBatch.getAgentBatchDetail();
		for (int i = 0; i < tAgentBatchDetails.size(); i++) {
			AgentBatchDetail tAgentBatchDetail = (AgentBatchDetail) tAgentBatchDetails.get(i);
			out.println("SeqNo   = [" + (i + 1) + "],");
			out.println("OrderNo   = [" + tAgentBatchDetail.getOrderNo() + "],");
			out.println("OrderAmount = [" + tAgentBatchDetail.getOrderAmount() + "],");
			out.println("CertificateNo  = [" + tAgentBatchDetail.getCertificateNo() + "],");
			out.println("ContractID = [" + tAgentBatchDetail.getContractID() + "],");
			out.println("ProductID   = [" + tAgentBatchDetail.getProductID() + "],");
			out.println("ProductName = [" + tAgentBatchDetail.getProductName() + "],");
			out.println("ProductNum  = [" + tAgentBatchDetail.getProductNum() + "],");
			out.println("OrderStatus  = [" + tAgentBatchDetail.getOrderStatus() + "],");
			out.println("OrderStatusZH  = [" + tAgentBatchDetail.getStatusChinese(tAgentBatchDetail.getOrderStatus()) + "]<br>");
		}

	} else {
		//6、批量结果查询失败
		out.println("ReturnCode   = [" + tResponse.getReturnCode() + "]<br>");
		out.println("ErrorMessage = [" + tResponse.getErrorMessage() + "]<br>");
	}
%> <a href='Merchant.html'>回商户首页</a></CENTER>
</BODY>
</HTML>