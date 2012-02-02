package com.brightedu.client;

import com.brightedu.client.login.LoginDialog;
import com.brightedu.client.nav.CommandTreeNode;
import com.brightedu.client.nav.ExplorerTreeNode;
import com.brightedu.client.nav.FunctionTree;
import com.brightedu.client.panels.PanelData;
import com.brightedu.client.panels.PanelFactory;
import com.brightedu.model.edu.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
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
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.tree.TreeNode;
import com.smartgwt.client.widgets.tree.events.LeafClickEvent;
import com.smartgwt.client.widgets.tree.events.LeafClickHandler;

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

	private static ExplorerTreeNode[] treeNodeData = null;

	public static final String idSuffix = "_bright";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// System.out.println("Auth: " + auth);
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
		topBar.setHeight(62);
		topBar.setWidth100();
		// topBar。
		// topBar.addSpacer(6);
		ImgButton homeBtn = new ImgButton();
		homeBtn.setSrc("jm_toolstrip.png");
		// sgwtHomeButton.setWidth(24);
		// sgwtHomeButton.setHeight(24);
		homeBtn.setPrompt("今明教育在线");
		// sgwtHomeButton.setHoverStyle("interactImageHover");
		homeBtn.setShowRollOver(false);
		homeBtn.setShowDownIcon(false);
		homeBtn.setShowDown(false);
		homeBtn.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(ClickEvent event) {
				com.google.gwt.user.client.Window.open(
						"http://www.jmedu.com.cn/", "今明教育在线", null);
			}
		});
		topBar.addMember(homeBtn);
		topBar.addSpacer(6);

		topBar.addFill();

		ToolStripButton login_logout_Btn = new ToolStripButton();
		login_logout_Btn.setTitle("退出");
		login_logout_Btn.setIcon("logout.gif");
		login_logout_Btn
				.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
					public void onClick(ClickEvent event) {
						login_logout();
					}
				});

		topBar.addButton(login_logout_Btn);

		topBar.addSeparator();

		ToolStripButton aboutBtn = new ToolStripButton();
		aboutBtn.setWidth(18);
		aboutBtn.setHeight(18);
		aboutBtn.setIcon("about.gif");
		aboutBtn.setShowFocused(false);
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
			label.setContents("请对当前站点关闭Firebug");

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
					tab = new Tab();
					tab.setID(factory.getID() + "_tab");
					// store history token on tab so that when an already open
					// is selected, one can retrieve the
					// history token and update the URL
					tab.setAttribute("historyToken",
							explorerTreeNode.getNodeID());
					// tab.setContextMenu(contextMenu);

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

	private void login_logout() {
		SC.say("login or logout!");
	}

	public static ExplorerTreeNode[] getNavTreeData() {
		return treeNodeData;
	}
}
