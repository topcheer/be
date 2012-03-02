package com.brightedu.client.canvas;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;

public class BrightCanvas extends Canvas {

	public final static String ID="brightcanvas";

	private Label tipLabel = new Label();
	
	private Label busyLabel = new Label();

	public BrightCanvas() {
		super(ID);
		initTipsLabel();
		initBusyLabel();
	}


	private void initTipsLabel() {
		tipLabel.setShowEdges(true);
		tipLabel.setStyleName("tipmessage");
		tipLabel.setPadding(5);
		tipLabel.setWidth(200);
		tipLabel.setHeight(100);
		tipLabel.setTop(-120);
		tipLabel.setValign(VerticalAlignment.CENTER);
		tipLabel.setAlign(Alignment.CENTER);
		tipLabel.setAnimateTime(2000); // milliseconds
		tipLabel.setParentElement(this);
		tipLabel.setKeepInParentRect(true);
	}
	
	private void initBusyLabel(){
		busyLabel.setShowEdges(true);
//		busyLabel.setContents(tip);
		busyLabel.setStyleName("busymessage");
		busyLabel.setPadding(5);
		busyLabel.setWidth(200);
		busyLabel.setHeight(30);
		busyLabel.setValign(VerticalAlignment.CENTER);
		busyLabel.setAlign(Alignment.CENTER);
		busyLabel.setAnimateTime(1200); // milliseconds
		busyLabel.setParentElement(this);
		busyLabel.setKeepInParentRect(true);
	}

	public void showTip(String tip) {
		int width = getWidth();
		final int left = width / 2 - tipLabel.getWidth() / 2;
		int height = getHeight();
		tipLabel.setLeft(left);
		tipLabel.setContents(tip);
		tipLabel.setVisible(true);
		tipLabel.setTop(height / 2 - tipLabel.getHeight() / 2);
		tipLabel.animateHide(AnimationEffect.FADE);
//		tipLabel.animateMove(left, height / 2 - tipLabel.getHeight() / 2);
//		new Timer() {
//			public void run() {
//				tipLabel.animateMove(left, -120, new AnimationCallback() {
//					
//					@Override
//					public void execute(boolean earlyFinish) {
//						// TODO Auto-generated method stub
//						tipLabel.setVisible(false);
//					}
//				});
////				tipLabel.animateMove(left, -120);
//			}
//		}.schedule(1800);
	}
	
	public void showBusyLoading(){ // NOT implemented yet
		int width = getWidth();
		final int left = width / 2 - tipLabel.getWidth() / 2;
		int height = getHeight();
		busyLabel.setLeft(left);
		
		busyLabel.setVisible(true);
		busyLabel.animateMove(left, height / 2 - busyLabel.getHeight() / 2);
//		new Timer() {
//			public void run() {
//				busyLabel.animateMove(left, -120, new AnimationCallback() {
//					
//					@Override
//					public void execute(boolean earlyFinish) {
//						// TODO Auto-generated method stub
//						busyLabel.setVisible(false);
//					}
//				});
////				tipLabel.animateMove(left, -120);
//			}
//		}.schedule(1800);
	}

}
