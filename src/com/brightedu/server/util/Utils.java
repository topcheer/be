package com.brightedu.server.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Utils {

	public static Document getXMLDocument(URL url) {
		return getXMLDocument(url.toString());
	}

	public static Document getXMLDocument(String url) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder bulider = factory.newDocumentBuilder();
			Document doc = bulider.parse(url);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static synchronized Element findElement(String xpathExpr,
			Node sourceNode) {
		try {
			Object result = XPathFactory.newInstance().newXPath()
					.evaluate(xpathExpr, sourceNode, XPathConstants.NODESET);
			for (int i = 0; i < ((NodeList) result).getLength(); i++) {
				Node child = ((NodeList) result).item(i);
				// 由于是唯一的属性，查询到值就返回
				return (Element) child;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static synchronized NodeList findNodes(String xpathExpr,
			Node sourceNode) {
		try {
			Object result = XPathFactory.newInstance().newXPath()
					.evaluate(xpathExpr, sourceNode, XPathConstants.NODESET);
			return (NodeList) result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String printXML(Node doc) {
		try {
			Source source = new DOMSource(doc);
			Transformer xformer = TransformerFactory.newInstance()
					.newTransformer();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			Result result = new StreamResult(out);
			xformer.transform(source, result);
			out.close();
			return new String(baos.toByteArray(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}
		try {
			MessageDigest digester = MessageDigest.getInstance("MD5");
			digester.update(str.getBytes());
			byte[] hash = digester.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0"
							+ Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String md52(String str) {
		if (str == null || str.length() == 0) {
			throw new IllegalArgumentException(
					"String to encript cannot be null or zero length");
		}
		try {
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] b = str.getBytes();
			digester.update(b, 0, b.length);
			byte[] buff = new byte[128];
			int len = digester.digest(buff, 0, buff.length);
			byte[] hash = new byte[len];
			System.arraycopy(buff, 0, hash, 0, len);
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0"
							+ Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static synchronized Object deepClone(Object oldObj) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(oldObj);
			out.flush();
			out.close();
			ByteArrayInputStream bis = new ByteArrayInputStream(
					bos.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bis);
			return in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String base64Encode(String s) {
		BASE64Encoder en = new BASE64Encoder();
		return en.encode(s.getBytes());
	}

	public static String base64Decode(String s) {
		BASE64Decoder de = new BASE64Decoder();
		try {
			return new String(de.decodeBuffer(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStandardGetMethodName(String fieldName) {
		char c = fieldName.charAt(0);
		return "get" + String.valueOf(c).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 获取指定表的指定字段的下一个id值
	 * 
	 * @param session
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public static Integer getNextId(SqlSession session, String tableName,
			String columnName) {
		synchronized (columnName) {
			boolean emptySession = session == null;
			if (emptySession)
				session = ConnectionManager.sessionFactory.openSession();
			Integer id = null;
			try {
				String sql = new StringBuilder("select fun_table_seq('")
						.append(tableName).append("','").append(columnName)
						.append("','next')").toString();
				PreparedStatement prep = session.getConnection()
						.prepareStatement(sql);
				ResultSet rs = prep.executeQuery();
				while (rs.next()) {
					id = rs.getInt(1);
					break;
				}
			} catch (SQLException e) {
				Log.e("", e);
			} finally {
				if (emptySession) {
					session.close();
				}
			}
			return id;
		}
	}
	
	public static Integer getNextId(SqlSession session, String tableName,
			String columnName,int offset) {
		synchronized (columnName) {
			boolean emptySession = session == null;
			if (emptySession)
				session = ConnectionManager.sessionFactory.openSession();
			Integer id = null;
			try {
				String sql = new StringBuilder("select fun_increase_seq('")
						.append(tableName).append("','").append(columnName)
						.append("',").append(offset).append(")").toString();
				PreparedStatement prep = session.getConnection()
						.prepareStatement(sql);
				ResultSet rs = prep.executeQuery();
				while (rs.next()) {
					id = rs.getInt(1);
					break;
				}
			} catch (SQLException e) {
				Log.e("", e);
			} finally {
				if (emptySession) {
					session.close();
				}
			}
			return id;
		}
	}
	
	public static void main(String[] xxx){
		ConnectionManager.loadMybatis();
		System.out.println(getNextId(null, "user_admin", "user_id", 5));
	}
}
