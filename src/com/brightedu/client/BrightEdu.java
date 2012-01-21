package com.brightedu.client;

import java.util.LinkedHashMap;

import com.brightedu.client.login.LoginDialog;
import com.brightedu.shared.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TabBarControls;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BrightEdu implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private String auth;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// final Button sendButton = new Button("登陆");
		// final TextBox nameField = new TextBox();
		// final PasswordTextBox passwordField = new PasswordTextBox();
		//
		//
		//
		// final Label errorLabel = new Label();
		//
		// // We can add style names to widgets
		// sendButton.addStyleName("sendButton");
		//
		// // Add the nameField and sendButton to the RootPanel
		// // Use RootPanel.get() to get the entire body element
		// RootPanel.get("nameLabelContainer").add(new Label("用户名"));
		// RootPanel.get("nameFieldContainer").add(nameField);
		// RootPanel.get("passwordlabelContainer").add(new Label("密码"));
		// RootPanel.get("passwordFieldContainer").add(passwordField);
		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);
		//
		//
		// nameField.setVisibleLength(15);
		// nameField.setMaxLength(25);
		//
		// passwordField.setVisibleLength(18);
		// passwordField.setMaxLength(25);
		//
		//
		//
		// // Create the popup dialog box
		// final DialogBox dialogBox = new DialogBox();
		// dialogBox.setText("Remote Procedure Call");
		// dialogBox.setAnimationEnabled(true);
		// final Button closeButton = new Button("Close");
		// // We can set the id of a widget by accessing its Element
		// closeButton.getElement().setId("closeButton");
		// final Label textToServerLabel = new Label();
		// final HTML serverResponseLabel = new HTML();
		// VerticalPanel dialogVPanel = new VerticalPanel();
		// dialogVPanel.addStyleName("dialogVPanel");
		// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		// dialogVPanel.add(textToServerLabel);
		// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		// dialogVPanel.add(serverResponseLabel);
		// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		// dialogVPanel.add(closeButton);
		// dialogBox.setWidget(dialogVPanel);
		//
		//
		//
		// // Add a handler to close the DialogBox
		// closeButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// dialogBox.hide();
		// sendButton.setEnabled(true);
		// sendButton.setFocus(true);
		// }
		// });
		//
		// // Create a handler for the sendButton and nameField
		// class MyHandler implements ClickHandler, KeyUpHandler {
		// /**
		// * Fired when the user clicks on the sendButton.
		// */
		// public void onClick(ClickEvent event) {
		// // sendNameToServer();
		// LoginDialog loginDiaog = new LoginDialog();
		// loginDiaog.show();
		// // LoginWindow loginWindow = new LoginWindow();
		// // loginWindow.show();
		// }
		//
		// /**
		// * Fired when the user types in the nameField.
		// */
		// public void onKeyUp(KeyUpEvent event) {
		// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		// sendNameToServer();
		// }
		// }
		//
		// /**
		// * Send the name from the nameField to the server and wait for a
		// response.
		// */
		// private void sendNameToServer() {
		// // First, we validate the input.
		// errorLabel.setText("");
		// String textToServer = nameField.getText();
		// if (!FieldVerifier.isValidName(textToServer)) {
		// errorLabel.setText("请输入用户名和密码");
		// return;
		// }
		//
		// // Then, we send the input to the server.
		// sendButton.setEnabled(false);
		// textToServerLabel.setText(textToServer);
		// serverResponseLabel.setText("");
		// greetingService.greetServer(textToServer,
		// new AsyncCallback<String>() {
		// public void onFailure(Throwable caught) {
		// // Show the RPC error message to the user
		// dialogBox
		// .setText("Remote Procedure Call - Failure");
		// serverResponseLabel
		// .addStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(SERVER_ERROR);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		//
		// public void onSuccess(String result) {
		// dialogBox.setText("Remote Procedure Call");
		// serverResponseLabel
		// .removeStyleName("serverResponseLabelError");
		// serverResponseLabel.setHTML(result);
		// dialogBox.center();
		// closeButton.setFocus(true);
		// }
		// });
		// }
		// }
		//
		// // Add a handler to send the name to the server
		// MyHandler handler = new MyHandler();
		// sendButton.addClickHandler(handler);
		// nameField.addKeyUpHandler(handler);

		final LoginDialog loginDialog = new LoginDialog();
		loginDialog.show();
		loginDialog.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				// notLogin();
			}
		});
		loginDialog.getOkBtn().addClickHandler((new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// okBtn.setDisabled(true);
				// infolable.setVisible(true);
				// infolable.setContents("good");
				// SC.say("失败", "用户名或密码错误");
				login(loginDialog);
			}
		}));
		loginDialog.getForgetPass().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.say("忘记密码" + auth);
			}
		});
	}

	private void login(LoginDialog loginDialog) {
		String username = loginDialog.getUserItem().getValueAsString();
		String password = loginDialog.getPassItem().getValueAsString();
		User user = new User();
		user.setUser_name(username);
		greetingService.login(user, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
				SC.say("登录失败，请重试! " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				System.out.println(result);
				auth = result;
				createUI();
			}
		});
	}

	private void createUI() {
		// remove headers, divs
		RootPanel p = RootPanel.get("loading");
		if (p != null)
			RootPanel.getBodyElement().removeChild(p.getElement());
		createAdminUI();
	}

	private TabSet mainTabSet;
	private void createAdminUI() {
		VLayout main = new VLayout() {
			@Override
			protected void onInit() {
				super.onInit();
				// if (initToken.length() != 0) {
				// onHistoryChanged(initToken);
				// }
			}
		};

		ToolStrip topBar = new ToolStrip();
		topBar.setHeight(33);
		topBar.setWidth100();

		topBar.addSpacer(6);
		ImgButton sgwtHomeButton = new ImgButton();
		sgwtHomeButton.setSrc("pieces/24/cube_green.png");
		sgwtHomeButton.setWidth(24);
		sgwtHomeButton.setHeight(24);
		sgwtHomeButton.setPrompt("Smart GWT Project Page");
		sgwtHomeButton.setHoverStyle("interactImageHover");
		sgwtHomeButton.setShowRollOver(false);
		sgwtHomeButton.setShowDownIcon(false);
		sgwtHomeButton.setShowDown(false);
		sgwtHomeButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						com.google.gwt.user.client.Window.open(
								"http://code.google.com/p/smartgwt/", "sgwt",
								null);
					}
				});
		topBar.addMember(sgwtHomeButton);
		topBar.addSpacer(6);

		Label title = new Label("Smart GWT Showcase");
		title.setStyleName("sgwtTitle");
		title.setWidth(300);
		topBar.addMember(title);

		topBar.addFill();

		ToolStripButton devConsoleButton = new ToolStripButton();
		devConsoleButton.setTitle("Developer Console");
		devConsoleButton.setIcon("silk/bug.png");
		devConsoleButton
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						SC.showConsole();
					}
				});

		topBar.addButton(devConsoleButton);

		topBar.addSeparator();

		ImgButton imgButton = new ImgButton();
		imgButton.setWidth(18);
		imgButton.setHeight(18);
		imgButton.setSrc("silk/emoticon.png");
		imgButton.setShowFocused(false);
		imgButton.setShowFocusedIcon(false);
		imgButton.setPrompt("I'm feeling lucky");
		imgButton.setHoverWidth(110);
		imgButton.setHoverStyle("interactImageHover");

		topBar.addMember(imgButton);

		topBar.addSpacer(6);

		main.addMember(topBar);

		main.setWidth100();
		main.setHeight100();
		main.setStyleName("tabSetContainer");

		HLayout hLayout = new HLayout();
		hLayout.setLayoutMargin(5);
		hLayout.setWidth100();
		hLayout.setHeight100();

		VLayout sideNavLayout = new VLayout();
		sideNavLayout.setHeight100();
		sideNavLayout.setWidth(185);
		sideNavLayout.setShowResizeBar(true);

		// sideNav = new SideNavTree();
		// sideNav.addLeafClickHandler(new LeafClickHandler() {
		// public void onLeafClick(LeafClickEvent event) {
		// TreeNode node = event.getLeaf();
		// showSample(node);
		// }
		// });
		//
		// sideNavLayout.addMember(sideNav);
		hLayout.addMember(sideNavLayout);

		mainTabSet = new TabSet();

		Layout paneContainerProperties = new Layout();
		paneContainerProperties.setLayoutMargin(0);
		paneContainerProperties.setLayoutTopMargin(1);
		mainTabSet.setPaneContainerProperties(paneContainerProperties);

		mainTabSet.setWidth100();
		mainTabSet.setHeight100();
		mainTabSet.addTabSelectedHandler(new TabSelectedHandler() {
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = event.getTab();
				String historyToken = selectedTab.getAttribute("historyToken");
				if (historyToken != null) {
					History.newItem(historyToken, false);
				} else {
					History.newItem("main", false);
				}
			}
		});

		LayoutSpacer layoutSpacer = new LayoutSpacer();
		layoutSpacer.setWidth(5);

		SelectItem selectItem = new SelectItem();
		selectItem.setHeight(21);
		selectItem.setWidth(130);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		valueMap.put("EnterpriseBlue", "Enterprise Blue");
		valueMap.put("Enterprise", "Enterprise Gray");
		valueMap.put("Graphite", "Graphite");
		valueMap.put("Simplicity", "Simplicity");

		selectItem.setValueMap(valueMap);

		final String skinCookieName = "skin_name_2_4";
		String currentSkin = Cookies.getCookie(skinCookieName);
		if (currentSkin == null) {
			currentSkin = "Enterprise";
		}
		selectItem.setDefaultValue(currentSkin);
		selectItem.setShowTitle(false);
		selectItem.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				Cookies.setCookie(skinCookieName, (String) event.getValue());
				com.google.gwt.user.client.Window.Location.reload();
			}
		});

		DynamicForm form = new DynamicForm();
		form.setPadding(0);
		form.setMargin(0);
		form.setCellPadding(1);
		form.setNumCols(1);
		form.setFields(selectItem);

		mainTabSet.setTabBarControls(TabBarControls.TAB_SCROLLER,
				TabBarControls.TAB_PICKER, layoutSpacer, form);

		// contextMenu = createContextMenu();

		Tab tab = new Tab();
		tab.setTitle("Home&nbsp;&nbsp;");
		tab.setIcon("pieces/16/cube_green.png", 16);
		tab.setWidth(80);

		HLayout mainPanel = new HLayout();
		mainPanel.setHeight100();
		mainPanel.setWidth100();

		// TileView tileView = new TileView(mainPanel);
		// mainPanel.addMember(tileView);

		tab.setPane(mainPanel);

		mainTabSet.addTab(tab);

		Canvas canvas = new Canvas();
		canvas.setBackgroundImage("[SKIN]/shared/background.gif");
		canvas.setWidth100();
		canvas.setHeight100();
		canvas.addChild(mainTabSet);

		hLayout.addMember(canvas);
		main.addMember(hLayout);

		if (SC.hasFirebug()) {
			Label label = new Label();
			label.setWidth100();
			label.setHeight(50);
			label.setValign(VerticalAlignment.CENTER);
			label.setAlign(Alignment.CENTER);
			label.setContents("Firebug can make the Showcase run slow. For the best performance, we suggest disabling Firebug for this site.");

			Window fbWindow = new Window();
			fbWindow.setTitle("Firebug Detected");
			fbWindow.setWidth100();
			fbWindow.setHeight(80);
			fbWindow.addItem(label);
			fbWindow.setRedrawOnResize(true);
			main.addMember(fbWindow);
		}
		main.draw();
	}
}
