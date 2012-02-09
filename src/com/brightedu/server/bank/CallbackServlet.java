package com.brightedu.server.bank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hitrust.trustpay.client.TrxException;
import com.hitrust.trustpay.client.b2c.PaymentResult;

public class CallbackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		updateBankTx(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		updateBankTx(req,resp);
		
	}
	
	private void updateBankTx(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			boolean isSuccess= false;
			String  trxNumber ="";
			StringBuffer remark = new StringBuffer();
			long orderNo ;
			
			PaymentResult tResult = new PaymentResult(req.getParameter("MSG"));

			
			isSuccess = tResult.isSuccess();
			
			if(isSuccess){
				orderNo = new Long(tResult.getValue("TrxType")).longValue();
				trxNumber = tResult.getValue("iRspRef" );
				remark.append("TrxType = [" + tResult.getValue("TrxType" ) + "]<br>");
				remark.append("OrderNo = [" + tResult.getValue("OrderNo" ) + "]<br>");
				remark.append("Amount = [" + tResult.getValue("Amount" ) + "]<br>");
				remark.append("BatchNo = [" + tResult.getValue("BatchNo" ) + "]<br>");
				remark.append("VoucherNo = [" + tResult.getValue("VoucherNo" ) + "]<br>");
				remark.append("HostDate = [" + tResult.getValue("HostDate" ) + "]<br>");
				remark.append("HostTime = [" + tResult.getValue("HostTime" ) + "]<br>");
				remark.append("MerchantRemarks = [" + tResult.getValue("MerchantRemarks") + "]<br>");
				remark.append("PayType = [" + tResult.getValue("PayType" ) + "]<br>");
				remark.append("NotifyType = [" + tResult.getValue("NotifyType" ) + "]<br>");
				remark.append("TrnxNo = [" + tResult.getValue("iRspRef" ) + "]<br>");
			}
			else
			{
				remark.append(tResult.getReturnCode() + "-" + tResult.getErrorMessage());
			}
			
			//TODO update bank_order table where the orderNum matches
			
			//TODO insert into charge_admin with related credit 
				
			
		} catch (TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
