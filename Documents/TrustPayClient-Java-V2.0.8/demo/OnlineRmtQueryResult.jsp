<%@ page contentType="text/html; charset=gb2312" %>
<% response.setHeader("Cache-Control", "no-cache"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>农行网上支付平台-商户接口示例-网上付款交易结果查询</title>
  </head>
  
    <BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>网上付款交易结果查询<br>
<%
	StringBuffer strMessage = new StringBuffer("");

        String tSerialNumber = request.getParameter("SerialNumber");
        String tReceiveAccount = request.getParameter("ReceiveAccount");
        
        //1、生成网上付款交易结果查询请求对象
        com.hitrust.trustpay.client.b2c.OnlineRmtQueryResultRequest tRequest = new com.hitrust.trustpay.client.b2c.OnlineRmtQueryResultRequest();
        tRequest.setSerialNumber(tSerialNumber);
        //tRequest.setPayAccount(request.getParameter("PayAccount"));
        tRequest.setReceiveAccount(tReceiveAccount);
        tRequest.setStartTime(request.getParameter("StartTime"));
        tRequest.setEndTime(request.getParameter("EndTime"));
        tRequest.setRemark(request.getParameter("Remark"));
         
        //2、传送网上付款交易结果查询请求并取得结果
        com.hitrust.trustpay.client.TrxResponse tTrxResponse = tRequest.postRequest();
        if (tTrxResponse.isSuccess())
        {
            //生成批量对象
            com.hitrust.trustpay.client.b2c.OnlineRmtBatch tBatch = new com.hitrust.trustpay.client.b2c.OnlineRmtBatch(new com.hitrust.trustpay.client.XMLDocument(tTrxResponse.getValue("ResultSets")));
            //3、输出查询结果
            if (tSerialNumber != "" && tReceiveAccount == "")
            {                
                strMessage.append("SerialNumber   = [" + tTrxResponse.getValue("SerialNumber") + "]<br/>");
                strMessage.append("TrnxTime        = [" + tTrxResponse.getValue("TrnxTime") + "]<br/>");
                strMessage.append("TotalCount     = [" + tTrxResponse.getValue("TotalCount") + "]<br/>");
                strMessage.append("TotalAmount    = [" + tTrxResponse.getValue("TotalAmount") + "]<br/>");
                strMessage.append("Status         = [" + tTrxResponse.getValue("Status") + "]<br/>");
                strMessage.append("SuccessAmount  = [" + tTrxResponse.getValue("SuccessAmount") + "]<br/>");
                strMessage.append("SuccessCount   = [" + tTrxResponse.getValue("SuccessCount") + "]<br/>");
                strMessage.append("FailAmount     = [" + tTrxResponse.getValue("FailAmount") + "]<br/>");
                strMessage.append("FailCount      = [" + tTrxResponse.getValue("FailCount") + "]<br/>");

                //4、取得查询结果明细
                java.util.ArrayList tQueryResults = tBatch.getQueryResults();
                for (int i = 0; i < tQueryResults.size(); i++)
                {
                    com.hitrust.trustpay.client.b2c.QueryResult tQueryResult = (com.hitrust.trustpay.client.b2c.QueryResult)tQueryResults.get(i);
                    strMessage.append("No                   = [" + tQueryResult.getNo() + "]<br/>");
                    strMessage.append("PayAccountNo         = [" + tQueryResult.getPayAccountNo() + "]<br/>");
                    strMessage.append("PayAccountName       = [" + tQueryResult.getPayAccountName() + "]<br/>");
                    strMessage.append("ReceiveAccountNo     = [" + tQueryResult.getReceiveAccountNo() + "]<br/>");
                    strMessage.append("ReceiveAccountName   = [" + tQueryResult.getReceiveAccountName() + "]<br/>");
                    strMessage.append("Purpose              = [" + tQueryResult.getPurpose() + "]<br/>");
                    strMessage.append("PayAmount            = [" + tQueryResult.getPayAmount() + "]<br/>");
                    strMessage.append("Status               = [" + tQueryResult.getStatus() + "]<br/>");
                    strMessage.append("FailReason           = [" + tQueryResult.getFailReason() + "]<br/>");                    
                }
            }
            else if (tSerialNumber == "" && tReceiveAccount !="")
            {
                //5、取得查询结果明细
                java.util.ArrayList tQueryResults = tBatch.getQueryResults();
                for (int i = 0; i < tQueryResults.size(); i++)
                {
                    com.hitrust.trustpay.client.b2c.QueryResult tQueryResult = (com.hitrust.trustpay.client.b2c.QueryResult)tQueryResults.get(i);
                    strMessage.append("No                   = [" + tQueryResult.getNo() + "]<br/>");
                    strMessage.append("SerialNumber         = [" + tQueryResult.getSerialNumber() + "]<br/>");
                    strMessage.append("TrnxTime             = [" + tQueryResult.getTrnxTime() + "]<br/>");
                    strMessage.append("PayAccountNo         = [" + tQueryResult.getPayAccountNo() + "]<br/>");
                    strMessage.append("PayAccountName       = [" + tQueryResult.getPayAccountName()+ "]<br/>");
                    strMessage.append("ReceiveAccountNo     = [" + tQueryResult.getReceiveAccountNo() + "]<br/>");
                    strMessage.append("ReceiveAccountName   = [" + tQueryResult.getReceiveAccountName() + "]<br/>");
                    strMessage.append("Purpose              = [" + tQueryResult.getPurpose() + "]<br/>");
                    strMessage.append("PayAmount            = [" + tQueryResult.getPayAmount() + "]<br/>");
                    strMessage.append("Status               = [" + tQueryResult.getStatus() + "]<br/>");
                    strMessage.append("FailReason           = [" + tQueryResult.getFailReason() + "]<br/>");
                }
            }
            else if (tSerialNumber != "" && tReceiveAccount != "")
            {
                //5、取得查询结果明细
                java.util.ArrayList tQueryResults = tBatch.getQueryResults();
                for (int i = 0; i < tQueryResults.size(); i++)
                {
                    com.hitrust.trustpay.client.b2c.QueryResult tQueryResult = (com.hitrust.trustpay.client.b2c.QueryResult)tQueryResults.get(i);
                    strMessage.append("No                   = [" + tQueryResult.getNo() + "]<br/>");
                    strMessage.append("SerialNumber         = [" + tQueryResult.getSerialNumber() + "]<br/>");
                    strMessage.append("TrnxTime             = [" + tQueryResult.getTrnxTime() + "]<br/>");
                    strMessage.append("PayAccountNo         = [" + tQueryResult.getPayAccountNo() + "]<br/>");
                    strMessage.append("PayAccountName       = [" + tQueryResult.getPayAccountName() + "]<br/>");
                    strMessage.append("ReceiveAccountNo     = [" + tQueryResult.getReceiveAccountNo() + "]<br/>");
                    strMessage.append("ReceiveAccountName   = [" + tQueryResult.getReceiveAccountName() + "]<br/>");
                    strMessage.append("Purpose              = [" + tQueryResult.getPurpose() + "]<br/>");
                    strMessage.append("PayAmount            = [" + tQueryResult.getPayAmount() + "]<br/>");
                    strMessage.append("Status               = [" + tQueryResult.getStatus() + "]<br/>");
                    strMessage.append("FailReason           = [" + tQueryResult.getFailReason() + "]<br/>");
                }
            }                 
        }
        else
        {
            
            strMessage.append("ReturnCode   = [" + tTrxResponse.getReturnCode() + "]<br/>");
            strMessage.append("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br/>");
        }
       	out.println(strMessage.toString());
 %>
    <CENTER><a href='Merchant.html'>回商户首页</a></CENTER>
  </body>
</html>
