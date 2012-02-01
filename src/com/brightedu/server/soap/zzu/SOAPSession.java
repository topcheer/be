package com.brightedu.server.soap.zzu;

import java.net.URL;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.StringHolder;

import com.brightedu.server.soap.zzu.holders.ClientexamineeHolder;

/**
 * This represents a SOAP session with ZZU WebService including that state of being logged in or not
 */
public class SOAPSession
{
	private static String GID="17BB6E2D-9415-4383-BDBD-A237D4B70ECE";
    private CapServiceLocator capServiceLocator;
    private CapServiceSoap_PortType capService;
    private Boolean token;
    private StringHolder stationno = new StringHolder(),msg = new StringHolder(),stationname = new StringHolder();

	public SOAPSession(URL webServicePort)
    {
    	capServiceLocator = new CapServiceLocator();
        try
        {
            if (webServicePort == null)
            {
            	capService = capServiceLocator.getCapServiceSoap12();
            }
            else
            {
            	capService = capServiceLocator.getCapServiceSoap(webServicePort);

            }
        }
        catch (ServiceException e)
        {
            throw new RuntimeException("ServiceException during SOAPClient contruction", e);
        }
    }

    public SOAPSession()
    {
        this(null);
    }

    public void connect(String userName, String password) throws RemoteException
    {
    	
        token = getCapService().autoLogin(userName, password, stationno, GID, msg, stationname);
    
        System.out.println(token + ":" + stationno.value + ":" + msg.value + ":" + stationname.value);
        
        if(token)
        {
        	//check if the registration is currently enabled
        	
        	boolean allowed = getCapService().getStatus(GID);
        	if(allowed)
        	{
        		System.out.println("ÔÊÐí×¢²á");
        	}
        	else
        	{
        		System.out.println("²»ÔÊÐí×¢²á");
        	}
/*
 *        	<e>
 *        <zhuanye>18</zhuanye>
 *        <xingming>test</xingming><
 *        xingbie>2</xingbie>
 *        <csny>19771212</csny>
 *        <minzu>01</minzu>
 *        <zjhm>101223197712121561</zjhm>
 *        <whcd>30</whcd>
 *        <zzmm>13</zzmm>
 *        <gzdw>test</gzdw>
 *        <txdz>test</txdz>
 *        <youzhengbianma>100012</youzhengbianma>
 *        <mobile>123456789</mobile>
 *        <phonenumber>123456789</phonenumber>
 *        <xueli>2</xueli>
 *        <jiafenxiang>1</jiafenxiang>
 *        <acount>hangzhou8</acount>
 *        <shifoumianshi>0</shifoumianshi>
 *        <foto /><sfzfoto /><zsfoto />
 *        <kaochang>0</kaochang>
 *        <kaohao>0</kaohao>
 *        <byxxdm>10001</byxxdm>
 *        <byxx>test</byxx>
 *        <bynf>1999</bynf>
 *        <byzfbh>10001123456789</byzfbh>
 *        <gendu>0</gendu>
 *        </e><stationstr>001</stationstr>
 *        <gid>17BB6E2D-9415-4383-BDBD-A237D4B70ECE</gid>
 */
        	
        	Clientexaminee ee = new Clientexaminee();
        	ee.setFoto("");
        	ee.setSfzfoto("");
        	ee.setZsfoto("");
        	ee.setShifoumianshi("0");
        	ee.setKaochang(0);
        	ee.setKaohao(0);
        	ee.setByxxdm("10001");
        	ee.setByxx("test");
        	ee.setBynf("19990119");
        	ee.setByzfbh("10001123456789");
        	ee.setGendu("0");
        	ee.setMobile("1234567890");
        	ee.setPhonenumber("01234567890");
        	ee.setXueli("2");
        	ee.setJiafenxiang("1");
        	
        	ee.setAcount("hangzhou8");
        	ee.setZhuanye("18");
        	ee.setXingming("test");
        	ee.setXingbie("1");
        	ee.setCsny("19751215");
        	ee.setMinzu("01");
        	ee.setZjhm("320623197512156257");
        	ee.setWhcd("30");
        	ee.setZzmm("13");
        	ee.setGzdw("test");
        	ee.setYouzhengbianma("100012");
        	
        	ClientexamineeHolder e = new ClientexamineeHolder(ee);
        	
			boolean added = getCapService().addExaminee(e, stationno.value, GID, msg);
			
			System.out.println(added + ":" + msg.value);
        	
        }
      
    }
    public StringHolder getStationno() {
		return stationno;
	}

	public StringHolder getMsg() {
		return msg;
	}

	public StringHolder getStationname() {
		return stationname;
	}

    public Boolean getAuthenticationToken()
    {
        return token;
    }

    public CapServiceSoap_PortType getCapService()
    {
        return capService;
    }

    public CapServiceLocator getCapServiceLocator()
    {
        return capServiceLocator;
    }
    
    public static void main(String[] args) throws RemoteException
    {
    	SOAPSession session = new SOAPSession();
    	session.connect("hangzhou8", "zhuyue");
    	
    }
    
    
}
