package com.brightedu.server.college;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.shared.IdcardUtils;
/**
 * 石油大学上传接口 
 * 
 * @author junzhang
 *
 */
public class UPOLUploader implements IUploader{

	private static final String CONNECTION = "Keep-Alive";
	private static final String ACCEPT_ENCODING="gzip, deflate";
	private static final String ACCEPT="image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, application/x-shockwave-flash, */*";
	private static final String USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; QQWubi 87; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
	private static final String ACCEPT_LANGUAGE="zh-CN";
	private static final String HOST="www.etju.com";
	private static final String LOGIN_URL = "http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/ctlogin.aspx";
	private static final String LOGIN_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String REG_URL = "http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/CTXLSign.aspx";
	private static final String LIST_URL = "http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/CTXLView.aspx";
	
	private static HashMap<String,UPOLUploader> _instances = new HashMap<String,UPOLUploader>();
	private RecruitAgent _agent;
	private DefaultHttpClient client;
	
	public static UPOLUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			UPOLUploader s = new UPOLUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private UPOLUploader(RecruitAgent agent)
	{
		_agent = agent;
	}
	@Override
	public boolean upload(StudentInfo student) {
		// TODO 代码
		
		client = new DefaultHttpClient();
		
		
		//测试环境要用proxy
		HttpHost proxy = new HttpHost("127.0.0.1", 8080, "http");
		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		
		//http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/ctlogin.aspx用户名：hzzx密码：zjyd 
        try {
           
            
            HttpGet req = new HttpGet(LOGIN_URL);
            req.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req.setHeader(HttpHeaders.HOST,HOST);   
            req.setHeader(HttpHeaders.CONNECTION,CONNECTION);   
            
            HttpResponse rsp = client.execute(req);
            HttpEntity entity = rsp.getEntity();

            String content = EntityUtils.toString(entity);

            String view_state = getViewState(content);
           
            System.out.println(view_state);
            
            HttpPost req2 = new HttpPost(LOGIN_URL);
            req2.setHeader(HttpHeaders.REFERER,LOGIN_URL);
            req2.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req2.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req2.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req2.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req2.setHeader(HttpHeaders.HOST,HOST);   
            req2.setHeader(HttpHeaders.CONNECTION,CONNECTION); 
            req2.setHeader(HttpHeaders.CONTENT_TYPE,LOGIN_FORM_CONTENT_TYPE);

            
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("TextBox1", "hzzx"));
            nvps.add(new BasicNameValuePair("TextBox2", "zjyd"));
            nvps.add(new BasicNameValuePair("__VIEWSTATE",view_state));
            nvps.add(new BasicNameValuePair("ImageButton1.x","0"));
            nvps.add(new BasicNameValuePair("ImageButton1.y","0"));

            req2.setEntity(new UrlEncodedFormEntity(nvps, HTTP.ISO_8859_1));
            
            rsp = client.execute(req2);
            entity = rsp.getEntity();

            content = EntityUtils.toString(entity);

            req = new HttpGet(REG_URL);
            req.setHeader(HttpHeaders.REFERER,LIST_URL);
            req.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req.setHeader(HttpHeaders.HOST,HOST);   
            req.setHeader(HttpHeaders.CONNECTION,CONNECTION);   

            rsp = client.execute(req);
            entity = rsp.getEntity();
            content = EntityUtils.toString(entity);

            view_state = getViewState(content);
            

            req2 = new HttpPost(REG_URL);
            
            req2.setHeader(HttpHeaders.REFERER,REG_URL);
            req2.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req2.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req2.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req2.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req2.setHeader(HttpHeaders.HOST,HOST);   
            req2.setHeader(HttpHeaders.CONNECTION,CONNECTION); 
            
            MultipartEntity entity4 = new MultipartEntity(); 

            entity4.addPart("__VIEWSTATE", new StringBody(view_state));
            entity4.addPart("date", new StringBody(""));
            entity4.addPart("month", new StringBody(""));
            entity4.addPart("year", new StringBody(""));
            entity4.addPart("TextBox1", new StringBody("姓名",Charset.forName("GBK")));
            entity4.addPart("TextBox2", new StringBody("320623197512156257"));
            entity4.addPart("DropDownList1", new StringBody(IdcardUtils.getGenderByIdCard("320623197512156257"),Charset.forName("GBK")));
            entity4.addPart("TextBox19", new StringBody(IdcardUtils.getBirthByIdCard("320623197512156257")));
            entity4.addPart("DropDownList3", new StringBody("共产主义青年团",Charset.forName("GBK")));
            entity4.addPart("DropDownList4", new StringBody(IdcardUtils.getProvinceByIdCard("320623197512156257"),Charset.forName("GBK")));
            entity4.addPart("DropDownList5", new StringBody("技校",Charset.forName("GBK")));
            entity4.addPart("DropDownList2", new StringBody("汉族",Charset.forName("GBK")));
            entity4.addPart("TextBox3", new StringBody("1234567890"));
            entity4.addPart("TextBox4", new StringBody(""));
            entity4.addPart("TextBox5", new StringBody("310000"));
            entity4.addPart("TextBox26", new StringBody(""));
            entity4.addPart("TextBox12", new StringBody("1999-07-26"));
            entity4.addPart("TextBox6", new StringBody("浙江大学",Charset.forName("GBK")));
            entity4.addPart("TextBox7", new StringBody(""));
            entity4.addPart("TextBox8", new StringBody("BIYE000012"));
            entity4.addPart("TextBox25", new StringBody("10001"));
            entity4.addPart("TextBox13", new StringBody(""));
            entity4.addPart("TextBox14", new StringBody(""));
            entity4.addPart("TextBox9", new StringBody(""));
            entity4.addPart("TextBox15", new StringBody(""));
            entity4.addPart("TextBox16", new StringBody(""));
            entity4.addPart("TextBox10", new StringBody(""));
            entity4.addPart("TextBox17", new StringBody(""));
            entity4.addPart("TextBox18", new StringBody(""));
            entity4.addPart("TextBox11", new StringBody(""));
            entity4.addPart("DropDownList16", new StringBody("自取",Charset.forName("GBK")));
            entity4.addPart("TextBox20", new StringBody("xxx"));
            
            entity4.addPart("DropDownList7", new StringBody("2"));
            entity4.addPart("DropDownList8", new StringBody("1"));
            entity4.addPart("DropDownList15", new StringBody("Y"));
            entity4.addPart("DropDownList6", new StringBody("参加考试",Charset.forName("GBK")));
            entity4.addPart("DropDownList9", new StringBody("121ZB02Z"));
            entity4.addPart("DropDownList11", new StringBody(""));
            entity4.addPart("DropDownList12", new StringBody(""));
            entity4.addPart("DropDownList10", new StringBody("092"));
            entity4.addPart("DropDownList13", new StringBody(""));
            entity4.addPart("DropDownList14", new StringBody(""));
            entity4.addPart("TextBox21", new StringBody(""));
            entity4.addPart("TextBox22", new StringBody(""));
            entity4.addPart("TextBox23", new StringBody(""));
            entity4.addPart("TextBox24", new StringBody(""));
            entity4.addPart("Button1", new StringBody("确定添加",Charset.forName("GBK")));
//            FileBody fileBody = new FileBody(new File(""),"application/octet-stream"); 
//            entity4.addPart("UploadFile", fileBody);
            
            req2.setEntity(entity4);
            
            rsp = client.execute(req2);
            entity = rsp.getEntity();
            content = EntityUtils.toString(entity);
           
            System.out.println(content);
            
            if(content.contains("请您核对并且打印报名确认表")) 
            {
            	return true;
            }
            else
            {
            	return false;
            }
            
            
            
            

        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            client.getConnectionManager().shutdown();
        }
		
			
		return false;
	}

	private String getViewState(String content) {
		return content.substring(content.indexOf("\"__VIEWSTATE\" value=\"") + 21,
        		content.indexOf("\" />",content.indexOf("\"__VIEWSTATE\" value=\"") + 21)
        		) ;
	}

}
