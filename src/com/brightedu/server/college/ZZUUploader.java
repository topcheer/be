package com.brightedu.server.college;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.rpc.holders.StringHolder;

import org.apache.catalina.util.InstanceSupport;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.server.soap.zzu.Clientexaminee;
import com.brightedu.server.soap.zzu.SOAPSession;
import com.brightedu.server.soap.zzu.holders.ClientexamineeHolder;
/**
 * 郑州大学上传接口
 * 
 * @author junzhang
 *
 */
public class ZZUUploader implements IUploader {

	private static HashMap<String,ZZUUploader> _instances = new HashMap<String,ZZUUploader>();
	private RecruitAgent _agent;
	public static ZZUUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			ZZUUploader s = new ZZUUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private ZZUUploader(RecruitAgent agent)
	{
		_agent = agent;
	}
	@Override
	public boolean upload(StudentInfo student) {
    	SOAPSession session = new SOAPSession();
    	try {
			session.connect(_agent.getUserid_for_college(), _agent.getPassword_for_college());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	/*
    	 *<e><zhuanye>18</zhuanye>
    	 *<xingming>test</xingming>
    	 *<xingbie>1</xingbie>
    	 *<csny>19751215</csny>
    	 *<minzu>01</minzu>
    	 *<zjhm>320623197512156257</zjhm>
    	 *<whcd>30</whcd>
    	 *<zzmm>13</zzmm>
    	 *<gzdw>test</gzdw>
    	 *<txdz>test</txdz>
    	 *<youzhengbianma>100012</youzhengbianma>
    	 *<mobile>0123456789</mobile>
    	 *<phonenumber>1234567890</phonenumber>
    	 *<xueli>2</xueli>
    	 *<jiafenxiang>1</jiafenxiang>
    	 *<acount>hangzhou8</acount>
    	 *<shifoumianshi>0</shifoumianshi>
    	 *<foto>\photo\C0\001\129725755334843750_1.jpg</foto>
    	 *<sfzfoto>\photo\C0\001\129725755346093750_2.jpg</sfzfoto>
    	 *<zsfoto>\photo\C0\001\129725755350000000_3.jpg</zsfoto>
    	 *<kaochang>0</kaochang>
    	 *<kaohao>0</kaohao>
    	 *<byxxdm>10001</byxxdm>
    	 *<byxx>test</byxx>
    	 *<bynf>19991215</bynf>
    	 *<byzfbh>10001123456789</byzfbh>
    	 *<gendu>0</gendu>
    	 *</e><stationstr>001</stationstr><gid>17BB6E2D-9415-4383-BDBD-A237D4B70ECE</gid><msg />
 */
    	
    	
    	Clientexaminee ee = new Clientexaminee();
    	//TODO: populate Examinee with information from StudentInfo (transformation required)
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
    	ee.setTxdz("Test");
    	ee.setYouzhengbianma("100012");
    	
    	StringHolder filename= new StringHolder();
    	FileInputStream fis;
    	byte[] fs;
		try {
			fis = new FileInputStream(new File("/Users/apple/1.jpg"));
	    	fs  = new byte[fis.available()];
	    	fis.read(fs);
	    	fis.close();
	    	
	    	boolean uploaded=session.getCapService().uploadFile(fs, filename, SOAPSession.GID, session.getMsg(), session.getStationno().value, "1");
	    	
	    	System.out.println(session.getMsg().value);
	    	
	    	if(uploaded)
	    	{
	    		System.out.println(filename.value);
	    	}
	    	
	    	ee.setFoto(filename.value);
	    	
	    	uploaded=session.getCapService().uploadFile(fs, filename,SOAPSession.GID, session.getMsg(), session.getStationno().value, "2");
	    	
	    	System.out.println(session.getMsg().value);
	    	
	    	if(uploaded)
	    	{
	    		System.out.println(filename.value);
	    	}
	    	ee.setSfzfoto(filename.value);
	    	
	    	uploaded=session.getCapService().uploadFile(fs, filename, SOAPSession.GID, session.getMsg(), session.getStationno().value, "3");
	    	
	    	System.out.println(session.getMsg().value);
	    	
	    	if(uploaded)
	    	{
	    		System.out.println(filename.value);
	    	}        	
	    	
	    	ee.setZsfoto(filename.value);
	      	ClientexamineeHolder e = new ClientexamineeHolder(ee);
	    	
	    	//if(true)return;
			boolean added = session.getCapService().addExaminee(e, session.getStationno().value, SOAPSession.GID, session.getMsg());
			
			System.out.println(added + ":" + session.getMsg().value);
	    	
	    		    	
	    	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	

		return false;
	}

}
