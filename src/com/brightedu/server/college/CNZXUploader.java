package com.brightedu.server.college;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
/**
 * 
 * 川农在线上传接口 
 * @author junzhang
 *
 */
public class CNZXUploader implements IUploader {
	
	private static final String CONNECTION = "Keep-Alive";
	private static final String ACCEPT_ENCODING="gzip, deflate";
	private static final String ACCEPT="*/*";
	private static final String USER_AGENT="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; QQWubi 87; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0)";
	private static final String ACCEPT_LANGUAGE="zh-CN";
	private static final String HOST="www.cnzx.info";
	private static final String LOGIN_URL = "http://www.cnzx.info/ASPdotNET_NEW_publish_2/ZhaoShengBaoMing/default.aspx?xBMD=3604";
	private static final String LOGIN_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
	private static final String REG_URL = "http://www.cnzx.info/ASPdotNET_NEW_publish_2/ZhaoShengBaoMing/BAOMING.ASPX?PageBMD=360401&PagePC=121&PageCONTROL=bm_page";
	private static final String LIST_URL = "http://www.etju.com/netfiles3web/(y5ene1rpiarkyjnd132p4gjv)/CTXLView.aspx";

	private DefaultHttpClient client;
	
	private static HashMap<String,CNZXUploader> _instances = new HashMap<String,CNZXUploader>();
	private RecruitAgent _agent;
	
	
	public static CNZXUploader getInstance(RecruitAgent agent)
	{
		
		if(_instances.containsKey(agent.getUserid_for_college()))
		{
			return _instances.get(agent.getUserid_for_college());
		}
		else
		{
			
			CNZXUploader s = new CNZXUploader(agent);
		
			_instances.put(agent.getUserid_for_college(),s);
			
			return s;
		}

	}
	
	private CNZXUploader(RecruitAgent agent)
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
           
            
            HttpGet req = new HttpGet(REG_URL);
            req.setHeader(HttpHeaders.REFERER,LOGIN_URL);
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
            //System.out.println(content);
            
            HttpPost req2 = new HttpPost(REG_URL);
            req2.setHeader(HttpHeaders.REFERER,REG_URL);
            req2.setHeader(HttpHeaders.ACCEPT, ACCEPT);   
            req2.setHeader(HttpHeaders.ACCEPT_LANGUAGE,ACCEPT_LANGUAGE);   
            req2.setHeader(HttpHeaders.USER_AGENT,USER_AGENT);   
            req2.setHeader(HttpHeaders.ACCEPT_ENCODING,ACCEPT_ENCODING);   
            req2.setHeader(HttpHeaders.HOST,HOST);   
            req2.setHeader(HttpHeaders.CONNECTION,CONNECTION); 
            req2.setHeader(HttpHeaders.CONTENT_TYPE,LOGIN_FORM_CONTENT_TYPE);

            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
            nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
            nvps.add(new BasicNameValuePair("__VIEWSTATE",view_state));
            nvps.add(new BasicNameValuePair("__LASTFOCUS",""));
            nvps.add(new BasicNameValuePair("tbx_XM","测试Test"));
            nvps.add(new BasicNameValuePair("hidnfld_XM",""));
            nvps.add(new BasicNameValuePair("hidnfld_XB",""));
            nvps.add(new BasicNameValuePair("DpDnLst_MZ","01"));
            nvps.add(new BasicNameValuePair("hidnfld_MZ",""));
            nvps.add(new BasicNameValuePair("DpDnLst_ZJLX","01"));
            nvps.add(new BasicNameValuePair("tbx_ZJHM","320623197512156257"));
            nvps.add(new BasicNameValuePair("hidnfld_ZJLX",""));
            nvps.add(new BasicNameValuePair("hidnfld_ZJHM",""));
            nvps.add(new BasicNameValuePair("hidnfld_CSRQ",""));
            nvps.add(new BasicNameValuePair("tbx_LXDH","13900000000"));
            nvps.add(new BasicNameValuePair("DpDnLst_ZZMM","01"));
            nvps.add(new BasicNameValuePair("hidnfld_LXDH",""));
            nvps.add(new BasicNameValuePair("hidnfld_ZZMM",""));
            nvps.add(new BasicNameValuePair("DpDnLst_BKCC","3"));
            nvps.add(new BasicNameValuePair("DpDnLst_WHCD","2"));
            nvps.add(new BasicNameValuePair("hidnfld_BKCC",""));
            nvps.add(new BasicNameValuePair("hidnfld_WHCD",""));
            nvps.add(new BasicNameValuePair("RdoBtnLst_KSLX","1"));
            nvps.add(new BasicNameValuePair("hidnfld_KSLX",""));
            nvps.add(new BasicNameValuePair("DpDnLst_ZY","31"));
            nvps.add(new BasicNameValuePair("hidnfld_ZY",""));
            nvps.add(new BasicNameValuePair("tbx_TXDZ","test"));
            nvps.add(new BasicNameValuePair("DpDnLst_ZYLB","002"));
            nvps.add(new BasicNameValuePair("hidnfld_TXDZ",""));
            nvps.add(new BasicNameValuePair("hidnfld_ZYLB",""));
            nvps.add(new BasicNameValuePair("Btn_Save","保存报名信息"));
            

            req2.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            
            rsp = client.execute(req2);
            entity = rsp.getEntity();

            content = EntityUtils.toString(entity);

            System.out.println(content);
            
            if(content.contains("数据保存成功")) 
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

	public static void main(String[] argv)
	{
		
		CNZXUploader.getInstance(new RecruitAgent()).upload(new StudentInfo());
	}
}
