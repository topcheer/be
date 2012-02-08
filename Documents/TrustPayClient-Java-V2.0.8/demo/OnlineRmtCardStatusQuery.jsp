<%@ page contentType="text/html; charset=gb2312" %>
<% response.setHeader("Cache-Control", "no-cache"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <title>农行网上支付平台-商户接口范例-网上付款银行卡状态验证</title>
  </head>
  
   <BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>网上付款银行卡状态验证<br>
<%
		//1、取得身份验证请求所需要的信息
        //2、生成身份验证请求对象
        com.hitrust.trustpay.client.b2c.OnlineRmtCardVerifyRequest tRequest = new com.hitrust.trustpay.client.b2c.OnlineRmtCardVerifyRequest();               
        String iBankCardNo = request.getParameter("txtBankCardNo");
        String iAccountName = new String(request.getParameter("txtAccountName").getBytes("8859_1"),"gb2312");
        
        tRequest.setBankCardNo(iBankCardNo);  //银行帐号       （必要信息）            
		tRequest.setAccountName(iAccountName);  //持卡人姓名       （必要信息）
		
        //3、传送身份验证请求并取得支付网址
        com.hitrust.trustpay.client.TrxResponse tTrxResponse = tRequest.postRequest();
        StringBuffer strMessage = new StringBuffer("");
        if (tTrxResponse.isSuccess())
        {
            //4、身份验证请求提交成功，返回结果。
            String tCardVerifyResult = tTrxResponse.getValue("Status").toString();
            strMessage.append(tCardVerifyResult);
        }
        else
        {
            //5、身份验证请求提交失败，商户自定后续动作
            strMessage.append("ReturnCode   = [" + tTrxResponse.getReturnCode() + "]<br/>");
            strMessage.append("ErrorMessage = [" + tTrxResponse.getErrorMessage() + "]<br/>");
        }

        out.println(strMessage.toString());
 %>
<CENTER><a href='Merchant.html'>回商户首页</a></CENTER>
 
  </body>
</html>
