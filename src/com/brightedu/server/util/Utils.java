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
	
	public static void main(String[] xx){
		String agreement_filename = "as_234-df.doc.1_2344/FGSSDF";
		System.out.println(agreement_filename
				.replace("/", "#"));
	}
}
