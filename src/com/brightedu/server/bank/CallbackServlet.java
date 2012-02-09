package com.brightedu.server.bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.brightedu.dao.edu.BankOrderMapper;
import com.brightedu.dao.edu.ChargeAdminMapper;
import com.brightedu.model.edu.BankOrder;
import com.brightedu.model.edu.BankOrderExample;
import com.brightedu.model.edu.ChargeAdmin;
import com.brightedu.model.edu.ChargeAdminExample;
import com.brightedu.server.util.ConnectionManager;
import com.hitrust.trustpay.client.TrxException;
import com.hitrust.trustpay.client.b2c.PaymentResult;

public class CallbackServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3079744184443202126L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		updateBankTrx(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		updateBankTrx(req,resp);
		
	}
	
	private void updateBankTrx(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			boolean isSuccess= false;
			String  trxNumber ="";
			StringBuffer remark = new StringBuffer();
			Long orderNo = null;
			
			PaymentResult tResult = new PaymentResult(req.getParameter("MSG"));

			
			isSuccess = tResult.isSuccess();
			
			if(isSuccess){
				orderNo = new Long(tResult.getValue("TrxType"));
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
			
			// update bank_order table where the orderNum matches
			
			SqlSession session = ConnectionManager.getSessionFactory().openSession();
			
			BankOrderMapper bom = session.getMapper(BankOrderMapper.class);
			
			BankOrderExample boe = new BankOrderExample();
			
			boe.createCriteria().andOrder_idEqualTo(orderNo);
			
			BankOrder bo = new BankOrder();
			bo.setBank_code(trxNumber);
			bo.setRemark(remark.toString());
			bo.setIs_paid(isSuccess);
			
			bom.updateByExampleSelective(bo, boe);
			
			session.commit();
			
			if(isSuccess){
				
			
				// insert into charge_admin with related credit record (only need to be done when success)
				
				//get charge_id from updated records so we can locate the related record in charge_admin to determine student info etc.
				
				List<BankOrder> bos = bom.selectByExample(boe);
				ArrayList<Integer> charge_ids = new ArrayList<Integer>();
				for (int i=0 ; i<bos.size();i++)
				{
					charge_ids.add((int) bos.get(i).getCharge_id().longValue());
				}
				
				// get origianl records in charge_admin
				ChargeAdminMapper cam = session.getMapper(ChargeAdminMapper.class);
				ChargeAdminExample cae = new ChargeAdminExample ();
				cae.createCriteria().andCharge_idIn(charge_ids);
				List<ChargeAdmin> cal = cam.selectByExample(cae);
				
				ChargeAdmin ca = new ChargeAdmin();
				
				for(int i=0; i< cal.size(); i++)
				{
					ca.setAmount(cal.get(i).getAmount());
					ca.setAmount_flag(false); // credit
					ca.setBank_code(trxNumber);
					ca.setStudent_id(cal.get(i).getStudent_id());
					ca.setFee_id(cal.get(i).getFee_id());
					ca.setCharge_type_id(1); // 网银
					cam.insertSelective(ca);
				}
				
				session.commit();
				session.close();
			}
			
			
		} catch (TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
