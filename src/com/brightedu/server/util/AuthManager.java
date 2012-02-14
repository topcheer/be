package com.brightedu.server.util;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.helpers.FileWatchdog;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AuthManager {

	static Document treeMenuDoc;

	static final String FUNCTION_SEP = "|";
	static final String CATEGORY_TAG = "category";
	static final String INTERNAL_AUTH_SEP = " ";

	static URL functionConfigURL = AuthManager.class
			.getResource("/functions.xml");

	static {
		FileWatchdog watchDog = new FileWatchdog(functionConfigURL.getPath()) {

			@Override
			protected void doOnChange() {
				Log.i("Reload AuthManager Configuration");
				treeMenuDoc = Utils.getXMLDocument(functionConfigURL);
			}
		};
		watchDog.setDelay(5000);
		watchDog.setName("AuthManager Watcher");
		watchDog.start();
	}

	public static void load() {

		treeMenuDoc = Utils.getXMLDocument(functionConfigURL);
	}

	public static String getFunctions(String[] functionIds) {
		StringBuilder sb = new StringBuilder();// root separator is '|',
												// function separator is ','
		Set<Node> parentNodes = new HashSet<Node>();
		for (String functionId : functionIds) {
			Element e = getElementById(functionId);
			if (e == null) {
				Log.e(functionId + " is not configured in funcitons.xml");
			} else {
				if (e.getTagName().equals(CATEGORY_TAG)) {// group of functions
					// id+name+parentid
					String cagegoryId = e.getAttribute("id");
					appendParent(e, sb);
					NodeList nodes = e.getChildNodes();
					for (int i = 0; i < nodes.getLength(); i++) {
						if (nodes.item(i) instanceof Element) {
							Element functionEle = (Element) nodes.item(i);
							appendFunction(functionEle, cagegoryId, sb);
						}
					}
				} else { // single function
					Element parentNode = (Element) e.getParentNode();
					if (!parentNodes.contains(parentNode)) {
						parentNodes.add(parentNode);
						appendParent(parentNode, sb);
					}
					appendFunction(e, parentNode.getAttribute("id"), sb);
				}
			}
		}

		return sb.toString();
	}

	private static void appendParent(Element e, StringBuilder sb) {
		String cagegoryId = e.getAttribute("id");
		sb.append(cagegoryId).append(INTERNAL_AUTH_SEP);
		sb.append(e.getAttribute("name")).append(INTERNAL_AUTH_SEP);
		sb.append("root");// root node, not leaf
		sb.append(FUNCTION_SEP);
	}

	private static void appendFunction(Element functionEle, String parentId,
			StringBuilder sb) {
		sb.append(functionEle.getAttribute("id")).append(INTERNAL_AUTH_SEP);
		sb.append(functionEle.getAttribute("name")).append(INTERNAL_AUTH_SEP);
		sb.append(parentId);
		sb.append(FUNCTION_SEP);
	}

	private static Element getElementById(String id) {
		return Utils.findElement("//*[@id = '" + id + "']", treeMenuDoc);
	}

}
