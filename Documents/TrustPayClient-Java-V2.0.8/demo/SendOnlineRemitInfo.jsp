<%@ page contentType="text/html; charset=gb2312" %>
<% response.setHeader("Cache-Control", "no-cache"); %>
<html>
  <head>
    <title>农行网上支付平台-商户接口示例-网上付款信息发送</title>
  </head>
  <BODY BGCOLOR='#FFFFFF' TEXT='#000000' LINK='#0000FF' VLINK='#0000FF' ALINK='#FF0000'>
<CENTER>网上付款信息发送<br>
<%
//1、取得网上付款所需要的信息
        String tSerialNumber = request.getParameter("SerialNumber");
        String tTotalCount = request.getParameter("TotalCount");
        String tTotalAmount = request.getParameter("TotalAmount");
        String tCheckType = request.getParameter("CheckType");
        String tRemark = request.getParameter("Remark");
        
        //2、取得列表项
        String[] NO_arr = null;
        String[] CardNo_arr = null;
        String[] CardName_arr = null;
        String[] RemitAmount_arr = null;
        String[] Purpose_arr = null;
        int iTotalCount = 0;
        double iTotalAmount = 0D;
        
        try
        {
            iTotalCount = Integer.parseInt(tTotalCount);
        }
        catch(Exception ex)
        {
            out.println("ReturnCode   = [1101]<br/>");
            out.println("ErrorMessage = [付款总笔数不合法：" + tTotalCount + "]<br/>");
            return;
        }
        
        try
        {
            iTotalAmount = Double.parseDouble(tTotalAmount);
        }
        catch(Exception ex)
        {
            out.println("ReturnCode   = [1101]<br/>");
            out.println("ErrorMessage = [付款总金额不合法" + tTotalAmount + "]<br/>");
            return;
        }
        
        if (iTotalCount == 1)
        {
            String NO = request.getParameter("NO");
            String CardNo = request.getParameter("CardNo");
            String CardName = request.getParameter("CardName");
            String RemitAmount = request.getParameter("RemitAmount");
            String Purpose = request.getParameter("Purpose");

            NO_arr = new String[] { NO };
            CardNo_arr = new String[] { CardNo };
            CardName_arr = new String[] { CardName };
            RemitAmount_arr = new String[] { RemitAmount };
            Purpose_arr = new String[] { Purpose };
        }
        else
        {
            NO_arr = request.getParameterValues("NO");
            
            CardNo_arr = request.getParameterValues("CardNo");
            CardName_arr = request.getParameterValues("CardName");
            RemitAmount_arr = request.getParameterValues("RemitAmount");
            Purpose_arr = request.getParameterValues("Purpose");
        }
        java.util.ArrayList tRemitList = new java.util.ArrayList(); 
        for (int i = 0; i < CardNo_arr.length; i++)
        {
            String[] tremit = new String[5];
            tremit[0] = NO_arr[i];
            tremit[1] = CardNo_arr[i];
            tremit[2] = CardName_arr[i];
            tremit[3] = RemitAmount_arr[i];
            tremit[4] = Purpose_arr[i];
            tRemitList.add(tremit);
        }
        //2、生成网上付款请求对象
        com.hitrust.trustpay.client.b2c.OnlineRemitRequest tOnlineRemitRequest = new com.hitrust.trustpay.client.b2c.OnlineRemitRequest();
        tOnlineRemitRequest.setTotalCount(iTotalCount);//总笔数  （必要信息）
        tOnlineRemitRequest.setTotalAmount(iTotalAmount); //总金额 （必要信息）  
        tOnlineRemitRequest.setSerialNumber(tSerialNumber);
        //tOnlineRemitRequest.setCheckType(tCheckType);
        tOnlineRemitRequest.setRemark(tRemark);//备注
        tOnlineRemitRequest.setRemitList(tRemitList);

        //3、传送网上付款请求并取得结果
        com.hitrust.trustpay.client.TrxResponse tResponse = tOnlineRemitRequest.postRequest();

        //4、判断网上付款结果状态，进行后续操作
        if (tResponse.isSuccess())
        {
            //5、网上付款发送成功
            out.println("TrxType   = [" + tResponse.getValue("TrxType") + "]<br/>");
            out.println("TotalCount  = [" + tResponse.getValue("TotalCount") + "]<br/>");
            out.println("TotalAmount = [" + tResponse.getValue("TotalAmount") + "]<br/>");
            out.println("SerialNumber  = [" + tResponse.getValue("SerialNumber") + "]<br/>");
            out.println("ResultMessage   = [" + tResponse.getErrorMessage() + "]<br/>");
        }
        else
        {
            //6、网上付款发送失败
            out.println("ReturnCode   = [" + tResponse.getReturnCode() + "]<br/>");
            out.println("ErrorMessage = [" + tResponse.getErrorMessage() + "]<br/>");
        }
        
 %>
<CENTER><a href='Merchant.html'>回商户首页</a></CENTER>
  </body>
</html>
