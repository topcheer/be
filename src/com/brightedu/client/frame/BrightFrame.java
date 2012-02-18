package com.brightedu.client.frame;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Frame;

/**
 * 这个在IE下也能工作，貌似NameFrame在IE下有问题
 * 
 * @author chetwang
 * 
 */
public class BrightFrame extends Frame {

	private ArrayList<LoadHandler> loadHandlers = null;

	private String frameId;

	@SuppressWarnings("unused")
	private static JavaScriptObject PATTERN_NAME;

	static {
		if (GWT.isClient()) {
			initStatics();
		}
	}

	/**
	 * Creates an HTML IFRAME element with a src and name.
	 * 
	 * @param src
	 *            the src of the frame
	 * @param name
	 *            the name of the frame, which must contain at least one
	 *            non-whitespace character and must not contain reserved HTML
	 *            markup characters such as '<code>&lt;</code>', '<code>&gt;</
code>', or '
	 *            <code>&amp;</code>'
	 * @return the newly-created element
	 * @throws IllegalArgumentException
	 *             if the supplied name is not allowed
	 */
	private static IFrameElement createIFrame(String src, String name) {
		if (name == null || !isValidName(name.trim())) {
			throw new IllegalArgumentException(
					"expecting one or more non-whitespace chars with no '<','>', or '&'");
		}

		// Use innerHTML to implicitly create the <iframe>. This is necessary
		// because most browsers will not respect a dynamically-set iframe name.
		Element div = DOM.createDiv();

		String onload = "onload=\"if(!event){event = window.event;}this.myonload(event);\"";

		div.setInnerHTML("<iframe src=\"" + src + "\" name='" + name + "'"
				+ onload + ">");

		return div.getFirstChild().cast();
	}

	private static native void initStatics() /*-{
		@com.google.gwt.user.client.ui.NamedFrame::PATTERN_NAME = /^[^<>&\'\"]+$/;
	}-*/;

	/**
	 * @param name
	 *            the specified frame name to be checked
	 * @return <code>true</code> if the name is valid, <code>false</
code> if not
	 */
	private static native boolean isValidName(String name) /*-{
		return @com.google.gwt.user.client.ui.NamedFrame::PATTERN_NAME
				.test(name);
	}-*/;

	/**
	 * Constructs a frame with the given name.
	 * 
	 * @param name
	 *            the name of the frame, which must contain at least one
	 *            non-whitespace character and must not contain reserved HTML
	 *            markup characters such as '<code>&lt;</code>', '<code>&gt;</
code>', or '
	 *            <code>&amp;</code>'
	 * 
	 * @throws IllegalArgumentException
	 *             if the supplied name is not allowed
	 */
	public BrightFrame(String name) {
		// Setting a src prevents mixed-content warnings.
		super(createIFrame("javascript:''", name));
		setStyleName("gwt-Frame");
		frameId = name;
		setMyOnload(this.getElement().cast(), this);
	}

	/**
	 * Gets the name associated with this frame.
	 * 
	 * @return the frame's name
	 */
	public String getName() {
		return DOM.getElementProperty(getElement(), "name");
	}

	public void addMyLoadHandler(LoadHandler handler) {
		if (loadHandlers == null) {
			loadHandlers = new ArrayList<LoadHandler>();
		}
		loadHandlers.add(handler);
	}

	public interface LoadHandler {
		void onLoad(Event event);
	}

	public native void setMyOnload(JavaScriptObject elm, BrightFrame frame)/*-{

		elm.myonload = function(event) {
			frame.@com.brightedu.client.frame.BrightFrame::onload(Lcom/google/gwt/user/client/Event;)(event);
		};
	}-*/;

	public void onload(Event event) {
		for (LoadHandler handler : loadHandlers) {
			handler.onLoad(event);
		}
	}

	private native JavaScriptObject getFrameDocument()/*-{
		var frameId = this.@com.brightedu.client.frame.BrightFrame::frameId;
		var f = $wnd.frames[frameId];
		try {
			return f.document;
		} catch (e) {
			// produces an access exception when frame has not loaded yet
			return null;
		}
	}-*/;

	public native String getInnerHtmlContent() /*-{
		var f = this.@com.brightedu.client.frame.BrightFrame::getFrameDocument()();
		if (!f)
			return null;
		return f.body.innerHTML;
	}-*/;

	public native String getInnerHtmlTitle()/*-{
		var f = this.@com.brightedu.client.frame.BrightFrame::getFrameDocument()();
		if (!f)
			return null;
		return f.head.innerHTML;
	}-*/;

	public boolean isSuccess() {
		String title = getInnerHtmlTitle();
		return title.contains("success");
	}

	public String getMessage() {
		return getInnerHtmlContent().trim();
	}

}
