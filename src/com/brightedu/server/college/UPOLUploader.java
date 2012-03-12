package com.brightedu.server.college;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.brightedu.model.edu.RecruitAgent;
import com.brightedu.model.edu.StudentInfo;
import com.brightedu.shared.IdcardUtils;
import com.brightedu.util.NumberVerCodeIdentifier;
/**
 * 
 * 天津大学上传接口
 * @author junzhang
 *
 */
public class UPOLUploader implements IUploader {
	
	private static final String CONNECTION = "Keep-Alive";
	private static final String ACCEPT_ENCODING="gzip, deflate";
	private static final String ACCEPT="*/*";
	private static final String USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; QQWubi 87; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
	private static final String ACCEPT_LANGUAGE="zh-CN";
	private static final String HOST="xueli.upol.cn";
	private static final String INDEX_URL= "http://xueli.upol.cn/M4/upol/platform/index.jsp";
	private static final String LOGIN_URL = "http://xueli.upol.cn/M4/upol/platform/login.jsp";
	private static final String IMG_URL = "http://xueli.upol.cn/M4/upol/platform/image.jsp";
	private static final String MAIN_URL = "http://xueli.upol.cn/M4/entity/snsenter.jsp?type=SITEMANAGER";
	private static final String LOGIN_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String REG_URL = "http://xueli.upol.cn/M4/entity/submanager/recruit/sign_up.jsp";
	private static final String REG_EXE_URL = "http://xueli.upol.cn/M4/entity/submanager/recruit/sign_upexe.jsp";
	
	private DefaultHttpClient client;
	
	private static HashMap<String,UPOLUploader> _instances = new HashMap<String,UPOLUploader>();
	private RecruitAgent _agent;
	
	
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
		
		client = new DefaultHttpClient();
		
		
		//测试环境要用proxy
		HttpHost proxy = new HttpHost("127.0.0.1", 8080, "http");
		client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		
		//http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/ctlogin.aspx用户名：hzzx密码：zjyd 
        try {
           
            
            HttpGet req = new HttpGet(INDEX_URL);
            req.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req.setHeader(HttpHeaders.HOST,HOST);   
            req.setHeader(HttpHeaders.CONNECTION,CONNECTION);   
            
            HttpResponse rsp = client.execute(req);
            HttpEntity entity = rsp.getEntity();

            String content = EntityUtils.toString(entity);
            
            String cookie=null;
            for (Header h: rsp.getAllHeaders())
            {
            	System.out.println(h.getName() + ":" + h.getValue());
            	if(h.getName().equalsIgnoreCase("Set-Cookie"))
            	{
            		cookie = h.getValue().substring(0,h.getValue().indexOf(";"));
            	}
            	
            }
            System.out.println(cookie);
            
//            
//            String view_state = getViewState(content);
//           
//            System.out.println(view_state);
            String rand = null;
            while(true)
            {
                HttpGet imgreq = new HttpGet(IMG_URL);
                imgreq.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
                imgreq.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
                imgreq.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
                imgreq.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
                imgreq.setHeader(HttpHeaders.HOST,HOST);   
                imgreq.setHeader(HttpHeaders.CONNECTION,CONNECTION);  
                imgreq.setHeader("Cookie",cookie);
                HttpResponse imgrsp = client.execute(imgreq);
                
                HttpEntity img = imgrsp.getEntity();
                
            	rand = NumberVerCodeIdentifier.recognize(ImageIO.read(img.getContent()));
            	if(rand != null && rand.length() == 4)
            	{
            		break;
            	}
            		
            }
            
            System.out.println(rand);
            
            HttpPost req2 = new HttpPost(LOGIN_URL);
            req2.setHeader(HttpHeaders.REFERER,INDEX_URL);
            req2.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req2.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req2.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req2.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req2.setHeader(HttpHeaders.HOST,HOST);   
            req2.setHeader(HttpHeaders.CONNECTION,CONNECTION); 
            req2.setHeader(HttpHeaders.CONTENT_TYPE,LOGIN_FORM_CONTENT_TYPE);
            req2.setHeader("Cookie",cookie);
            
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("login_id", "0485"));
            nvps.add(new BasicNameValuePair("input_password", "zjjmedu"));
            nvps.add(new BasicNameValuePair("type","SITEMANAGER"));
            nvps.add(new BasicNameValuePair("rand",rand));

            req2.setEntity(new UrlEncodedFormEntity(nvps, HTTP.ISO_8859_1));
            
            rsp = client.execute(req2);
            entity = rsp.getEntity();

            content = EntityUtils.toString(entity);
            
            if(!content.contains(MAIN_URL))
            {
            	System.out.println("登录失败");
            	return false;
            }

            
            req = new HttpGet(MAIN_URL);
            req.setHeader(HttpHeaders.REFERER,LOGIN_URL);
            req.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req.setHeader(HttpHeaders.HOST,HOST);   
            req.setHeader(HttpHeaders.CONNECTION,CONNECTION);   
            req.setHeader("Cookie",cookie);
            
            rsp = client.execute(req);
            entity = rsp.getEntity();
            content = EntityUtils.toString(entity);

//            view_state = getViewState(content);
            

            req2 = new HttpPost(REG_EXE_URL);
            
            req2.setHeader(HttpHeaders.REFERER,REG_URL);
            req2.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req2.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req2.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req2.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req2.setHeader(HttpHeaders.HOST,HOST);   
            req2.setHeader(HttpHeaders.CONNECTION,CONNECTION); 
            req2.setHeader(HttpHeaders.CONTENT_TYPE,LOGIN_FORM_CONTENT_TYPE);
            req2.setHeader("Cookie",cookie);
            
            		
            List <NameValuePair> nvps2 = new ArrayList <NameValuePair>();
            nvps2.add(new BasicNameValuePair("name", "测试学生"));
            nvps2.add(new BasicNameValuePair("gender", "男"));
            nvps2.add(new BasicNameValuePair("folk","汉族"));
            nvps2.add(new BasicNameValuePair("birthday","1975-12-15"));
            nvps2.add(new BasicNameValuePair("zzmm","共青团员"));
            nvps2.add(new BasicNameValuePair("zylb","专业技术人员"));
            nvps2.add(new BasicNameValuePair("edu","高中毕业"));
            nvps2.add(new BasicNameValuePair("edutype_id","8"));
            nvps2.add(new BasicNameValuePair("premajor_status","1"));
            nvps2.add(new BasicNameValuePair("major_id","06"));
            nvps2.add(new BasicNameValuePair("card_type","身份证"));
            nvps2.add(new BasicNameValuePair("card_no","320623197512156257"));
            nvps2.add(new BasicNameValuePair("postalcode","310000"));
            nvps2.add(new BasicNameValuePair("address","test address"));
            nvps2.add(new BasicNameValuePair("gzdw",""));
            nvps2.add(new BasicNameValuePair("mobilephone","13958222222"));
            nvps2.add(new BasicNameValuePair("phone",""));
            nvps2.add(new BasicNameValuePair("email",""));
            nvps2.add(new BasicNameValuePair("school_name","test school"));
            nvps2.add(new BasicNameValuePair("school_code","10001"));
            nvps2.add(new BasicNameValuePair("nian1","1999"));
            nvps2.add(new BasicNameValuePair("yue1","08"));
            nvps2.add(new BasicNameValuePair("ri1","11"));
            nvps2.add(new BasicNameValuePair("graduate_cardno","xxxxxxx"));
            nvps2.add(new BasicNameValuePair("xxly","他人介绍"));
            nvps2.add(new BasicNameValuePair("considertype","0"));
            

            req2.setEntity(new UrlEncodedFormEntity(nvps2, HTTP.UTF_8));
            
            rsp = client.execute(req2);
            entity = rsp.getEntity();
            content = EntityUtils.toString(entity);
           
            System.out.println(content);
            
            if(content.contains("成功添加")) 
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
	
	public static void main (String[] argv)
	{
		
		 RecruitAgent agent = new RecruitAgent();
		 agent.setUserid_for_college("hzzx");
		 UPOLUploader.getInstance(agent).upload(new StudentInfo());
	}

}
