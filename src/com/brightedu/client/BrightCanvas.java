package com.brightedu.client;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;

public class BrightCanvas extends Canvas {

	public final static String ID="brightcanvas";

	private Label tipLabel = new Label();

	public BrightCanvas() {
		super(ID);
		initTipsLabel();
	}


	private void initTipsLabel() {
		tipLabel.setShowEdges(true);
		tipLabel.setBackgroundColor("#f0f0f0");
		tipLabel.setPadding(5);
		tipLabel.setWidth(200);
		tipLabel.setHeight(100);
		tipLabel.setTop(-120);
		tipLabel.setValign(VerticalAlignment.CENTER);
		tipLabel.setAlign(Alignment.CENTER);
		tipLabel.setAnimateTime(1200); // milliseconds
		tipLabel.setParentElement(this);
	}

	protected void showTip(String tip) {
		int width = getWidth();
		final int left = width / 2 - tipLabel.getWidth() / 2;
		int height = getHeight();
		tipLabel.setLeft(left);
		tipLabel.setContents(tip);
		tipLabel.setVisible(true);
		tipLabel.animateMove(left, height / 2 - tipLabel.getHeight() / 2);
		new Timer() {
			public void run() {
				tipLabel.animateMove(left, -120, new AnimationCallback() {
					
					@Override
					public void execute(boolean earlyFinish) {
						// TODO Auto-generated method stub
						tipLabel.setVisible(false);
					}
				});
//				tipLabel.animateMove(left, -120);
			}
		}.schedule(1800);
	}

}
