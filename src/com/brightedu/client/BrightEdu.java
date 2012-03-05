package com.brightedu.client;

import java.io.Serializable;

import com.brightedu.client.canvas.BrightCanvas;
import com.brightedu.client.label.MessageLabel;
import com.brightedu.client.message.event.MessageEventListener;
import com.brightedu.client.message.event.MessageRealEvent;
import com.brightedu.client.nav.CommandTreeNode;
import com.brightedu.client.nav.ExplorerTreeNode;
import com.brightedu.client.nav.FunctionTree;
import com.brightedu.client.panels.PanelData;
import com.brightedu.client.panels.PanelFactory;
import com.brightedu.client.window.IMWindow;
import com.brightedu.client.window.LoginDialog;
import com.brightedu.model.edu.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.core.KeyIdentifier;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemIfFunction;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.plugins.Flashlet;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;

import de.novanic.eventservice.client.event.RemoteEventService;
import de.novanic.eventservice.client.event.RemoteEventServiceFactory;
import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BrightEdu implements EntryPoint {

	private static final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	private static final DataBaseRPCAsync dbService = GWT
			.create(DataBaseRPC.class);

	private String auth;

	private static ExplorerTreeNode[] treeNodeData = null;

	public static final String idSuffix = "_bright";

	private static User user = null;
	final MessageLabel mess = new MessageLabel();

	// MainTimer mainTimer = new MainTimer();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		initLogin();	
//		createAdminUI();
	}
	
	private void initLogin(){
		final LoginDialog loginDialog = new LoginDialog();
		loginDialog.show();

		// loginDialog.getUserItem().getf
		loginDialog.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				// notLogin();
			}
		});
		loginDialog.getOkBtn().addClickHandler((new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				login(loginDialog);
			}
		}));

		loginDialog.addFiledsKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName() != null) {
					if (event.getKeyName().toLowerCase().equals("enter")) {
						login(loginDialog);
					}
				}
			}
		});
		loginDialog.getForgetPass().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.say("忘记密码" + auth);
			}
		});
	}

	private void login(final LoginDialog loginDialog) {
		String username = loginDialog.getUserItem().getValueAsString();
		@SuppressWarnings("unused")
		String password = loginDialog.getPassItem().getValueAsString();
		User user = new User();
		user.setUser_name(username);

		greetingService.login(user, new AsyncCallback<Serializable[]>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				SC.say("登录失败，请重试! " + caught.getMessage());
			}

			@Override
			public void onSuccess(Serializable[] result) {
				setUser((User) result[0]);
				auth = (String) result[1];
				String[] nodes = auth.split("\\|");
				treeNodeData = new ExplorerTreeNode[nodes.length];
				for (int i = 0; i < nodes.length; i++) {
					String[] props = nodes[i].split(" ");// id+name+parentid+factory
					String id = props[0];
					String name = props[1];
					String parentId = props[2];
					PanelFactory f = PanelData.getPanelFactory(id);
					treeNodeData[i] = new ExplorerTreeNode(name, id, parentId,
							id + ".png", f, true, idSuffix);

				}
				createUI();
				loginDialog.destroy();
				showTip("已登录！");
			}
		});
	}

	private static void setUser(User newUser) {
		user = newUser;
	}

	public static User getUser() {
		return user;
	}

	private void createUI() {
		createAdminUI();
		// remove headers, divs
//		RootPanel p = RootPanel.get("loading");
//		if (p != null)
//			RootPanel.getBodyElement().removeChild(p.getElement());
	}

	private TabSet mainTabSet;

	private void createAdminUI() {
		VLayout main = new VLayout();

		ToolStrip topBar = new ToolStrip();
		topBar.setHeight(62);
		topBar.setWidth100();
		// topBar。
		// topBar.addSpacer(6);
		Img c = new Img();
		c.setSrc("jm_banner.png");
		c.setPrompt("今明教育在线");
		c.setWidth(375);
		c.setHeight(56);
		c.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(ClickEvent event) {
				com.google.gwt.user.client.Window.open(
						"http://www.jmedu.com.cn/", "今明教育在线", null);
			}
		});
		topBar.addMember(c);
		topBar.addSpacer(6);

		topBar.addFill();

		mess.setAnimateFadeTime(1000);
		mess.setWidth(200);
		mess.setHeight(20);
		mess.setAlign(Alignment.RIGHT);
		mess.setOpacity(50);


		// final MessTimer mt = new MessTimer();
		// new Timer(){
		//
		// @Override
		// public void run() {
		//
		//
		// dbService.checkNewMessages(user, new CommonAsyncCall<Boolean>(){
		//
		// @Override
		// public void onSuccess(Boolean result) {
		// if(result)
		// {
		//
		// mess.setOpacity(100);
		// mess.setContents("<b><font color=red>你有新短消息</font></b>");
		// mt.scheduleRepeating(500);
		//
		// }
		// else
		// {
		// mess.setOpacity(50);
		// mess.setContents("没有新短消息");
		// mess.setVisible(true);
		// mt.cancel();
		//
		// }
		//
		// }});
		//
		// }}.scheduleRepeating(20000);

		//
		// mainTimer.run();
		// mainTimer.scheduleRepeating(20000);

		topBar.addMember(mess);
		topBar.addSeparator();

		ToolStripButton login_logout_Btn = new ToolStripButton();
		login_logout_Btn.setTitle("退出");
		login_logout_Btn.setPrompt("退出");
		login_logout_Btn.setIcon("logout.gif");
		login_logout_Btn
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						login_logout();
					}
				});
		login_logout_Btn.setShowFocused(false);
		login_logout_Btn.setShowFocusedAsOver(false);
		topBar.addButton(login_logout_Btn);

		topBar.addSeparator();

		ToolStripButton aboutBtn = new ToolStripButton();
		aboutBtn.setWidth(18);
		aboutBtn.setHeight(18);
		aboutBtn.setIcon("about.gif");
		aboutBtn.setShowFocused(false);
		aboutBtn.setShowFocusedAsOver(false);
		aboutBtn.setPrompt("帮助");
		aboutBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				SC.say("help content");
			}
		});
		topBar.addMember(aboutBtn);

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
		sideNavLayout.setStyleName("test");
		sideNavLayout.setHeight100();
		sideNavLayout.setWidth(200);
		sideNavLayout.setShowResizeBar(true);
		FunctionTree sideNav = new FunctionTree();

		sideNav.addLeafClickHandler(new LeafClickHandler() {
			public void onLeafClick(LeafClickEvent event) {
				TreeNode node = event.getLeaf();
				showFunction(node);
			}
		});
		sideNavLayout.addMember(sideNav);
		hLayout.addMember(sideNavLayout);
		// sideNav.setStyleName("test");
		// sideNav.setWidth(200);
		// sideNav.setShowResizeBar(true);
		// hLayout.addMember(sideNav);

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
		Tab homeTab = new Tab();
		homeTab.setTitle("Home&nbsp;&nbsp;");
		homeTab.setIcon("pieces/16/cube_green.png", 16);
		homeTab.setWidth(80);

		HLayout mainPanel = new HLayout();
		mainPanel.setHeight100();
		mainPanel.setWidth100();

		// Calendar ca = new Calendar();

		// mainPanel.addMember(ca);

		Flashlet g = new Flashlet();
		g.setSrc("http://player.youku.com/player.php/Type/Folder/Fid/17079166/Ob/1/Pt/0/sid/XMzU4MDI1ODA4/v.swf");
		g.setWidth(600);
		g.setHeight(400);
		// mainPanel.addMember(g);

		// TileView tileView = new TileView(mainPanel);
		// mainPanel.addMember(tileView);

		homeTab.setPane(mainPanel);

		mainTabSet.addTab(homeTab);

		BrightCanvas canvas = new BrightCanvas();
		// canvas.setBackgroundImage("[SKIN]/shared/background.gif");
		canvas.setWidth100();
		canvas.setHeight100();
		canvas.addChild(mainTabSet);

		hLayout.addMember(canvas);
		main.addMember(hLayout);

		// if (SC.hasFirebug()) {
		// Label label = new Label();
		// label.setWidth100();
		// label.setHeight(50);
		// label.setValign(VerticalAlignment.CENTER);
		// label.setAlign(Alignment.CENTER);
		// label.setContents("请对当前站点关闭Firebug");
		//
		// Window fbWindow = new Window();
		// fbWindow.setTitle("Firebug Detected");
		// fbWindow.setWidth100();
		// fbWindow.setHeight(80);
		// fbWindow.addItem(label);
		// fbWindow.setRedrawOnResize(true);
		// main.addMember(fbWindow);
		// }
		main.draw();
		initMessageContext();
	}

	public static void showTip(String tips) {
		BrightCanvas ca = (BrightCanvas) BrightCanvas.getById(BrightCanvas.ID);
		ca.showTip(tips);
	}

	protected void showFunction(TreeNode node) {
		if (node instanceof ExplorerTreeNode) {
			ExplorerTreeNode explorerTreeNode = (ExplorerTreeNode) node;
			PanelFactory factory = explorerTreeNode.getFactory();
			if (factory != null) {
				String panelID = factory.getID();
				Tab tab = null;
				if (panelID != null) {
					String tabID = panelID + "_tab";
					tab = mainTabSet.getTab(tabID);
				}
				if (tab == null) {
					Canvas panel = factory.create();
					panel.setHeight100();
					tab = new Tab();
					tab.setID(factory.getID() + "_tab");
					// store history token on tab so that when an already open
					// is selected, one can retrieve the
					// history token and update the URL
					tab.setAttribute("historyToken",
							explorerTreeNode.getNodeID());
					Menu contextMenu = createContextMenu();
					tab.setContextMenu(contextMenu);

					String sampleName = explorerTreeNode.getName();

					String icon = explorerTreeNode.getIcon();
					if (icon == null) {
						icon = "silk/plugin.png";
					}
					String imgHTML = Canvas.imgHTML(icon, 16, 16);
					tab.setTitle("<span>" + imgHTML + "&nbsp;" + sampleName
							+ "</span>");
					tab.setPane(panel);
					tab.setCanClose(true);
					mainTabSet.addTab(tab);
					mainTabSet.selectTab(tab);
				} else {
					mainTabSet.selectTab(tab);
				}
			}
		} else if (node instanceof CommandTreeNode) {
			SC.say("not implemented for commandtreenode");
		}
	}

	private Menu createContextMenu() {
		Menu menu = new Menu();
		menu.setWidth(140);

		MenuItemIfFunction enableCondition = new MenuItemIfFunction() {
			public boolean execute(Canvas target, Menu menu, MenuItem item) {
				int selectedTab = mainTabSet.getSelectedTabNumber();
				return selectedTab != 0;
			}
		};

		MenuItem closeItem = new MenuItem("关闭(<u>C</u>)");
		closeItem.setEnableIfCondition(enableCondition);
		closeItem.setKeyTitle("Alt+C");
		KeyIdentifier closeKey = new KeyIdentifier();
		closeKey.setAltKey(true);
		closeKey.setKeyName("C");
		closeItem.setKeys(closeKey);
		closeItem
				.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {

					@Override
					public void onClick(MenuItemClickEvent event) {
						int selectedTab = mainTabSet.getSelectedTabNumber();
						mainTabSet.removeTab(selectedTab);
						mainTabSet.selectTab(selectedTab - 1);
					}
				});

		MenuItem closeAllButCurrent = new MenuItem("关闭其他");
		closeAllButCurrent.setEnableIfCondition(enableCondition);
		closeAllButCurrent
				.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
					public void onClick(MenuItemClickEvent event) {
						int selected = mainTabSet.getSelectedTabNumber();
						Tab[] tabs = mainTabSet.getTabs();
						int[] tabsToRemove = new int[tabs.length - 2];
						int cnt = 0;
						for (int i = 1; i < tabs.length; i++) {
							if (i != selected) {
								tabsToRemove[cnt] = i;
								cnt++;
							}
						}
						mainTabSet.removeTabs(tabsToRemove);
					}
				});

		MenuItem closeAll = new MenuItem("关闭所有");
		closeAll.setEnableIfCondition(enableCondition);
		closeAll.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				Tab[] tabs = mainTabSet.getTabs();
				int[] tabsToRemove = new int[tabs.length - 1];

				for (int i = 1; i < tabs.length; i++) {
					tabsToRemove[i - 1] = i;
				}
				mainTabSet.removeTabs(tabsToRemove);
				mainTabSet.selectTab(0);
			}
		});

		menu.setItems(closeItem, closeAllButCurrent, closeAll);
		return menu;
	}

	private void login_logout() {
		SC.say("login or logout!");
	}

	public static ExplorerTreeNode[] getNavTreeData() {
		return treeNodeData;
	}

	public static DataBaseRPCAsync createDataBaseRPC() {
		return dbService;
	}

	public static GreetingServiceAsync createGreetingRPC() {
		return greetingService;
	}

	private void initMessageContext() {
		checkNewMessageOnLogin();
		/*******************************************/
		MessageServiceRPCAsync messageService = GWT
				.create(MessageServiceRPC.class);
		messageService.startMessageSession(new CommonAsyncCall<Void>() {

			@Override
			public void onSuccess(Void result) {
				// 这里只是建立一个连接，成功后不做任何业务操作
			}
		});
		RemoteEventServiceFactory theEventServiceFactory = RemoteEventServiceFactory
				.getInstance();
		RemoteEventService theEventService = theEventServiceFactory
				.getRemoteEventService();
		Domain messageDomain = DomainFactory.getDomain("Message_User_"
				+ user.getUser_id());
		theEventService.addListener(messageDomain, new MessageEventListener() {

			@Override
			public void onMessage(MessageRealEvent event) {
				mess.showNewMessageTip();
			}

		});
		/*********************************************/
	}

	private void checkNewMessageOnLogin() {
		dbService.checkNewMessages(user, new CommonAsyncCall<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					mess.showNewMessageTip();
				} else {
					mess.dismissNewMessageTip();
				}

			}
		});
	}

	// private class MainTimer extends Timer {
	//
	// @Override
	// public void run() {
	//
	// dbService.checkNewMessages(user, new CommonAsyncCall<Boolean>() {
	//
	// @Override
	// public void onSuccess(Boolean result) {
	// if (result) {
	//
	// mess.setOpacity(100);
	// mess.setContents("<b><font color=red>你有新短消息</font></b>");
	// mt.scheduleRepeating(1000);
	//
	// } else {
	// mess.setOpacity(50);
	// mess.setContents("没有新短消息");
	// mess.setVisible(true);
	// mt.cancel();
	//
	// }
	//
	// }
	// });
	// }
	//
	// }

	
}
