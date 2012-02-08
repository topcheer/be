package com.brightedu.client;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;

public class BrightCanvas extends Canvas{
	
public static String ID;
	
	private Label tipLabel = new Label();
	
	public BrightCanvas(){
		initTipsLabel();
		ID = getID();
	}
	
	private void initTipsLabel() {

		tipLabel.setParentElement(this);
		tipLabel.setShowEdges(true);
		tipLabel.setBackgroundColor("#f0f0f0");
		tipLabel.setPadding(5);
		tipLabel.setWidth(200);
		tipLabel.setHeight(100);
		tipLabel.setTop(-120);
//		tipLabel.setLeft(-220); // start off screen
		tipLabel.setValign(VerticalAlignment.CENTER);
		tipLabel.setAlign(Alignment.CENTER);
		tipLabel.setAnimateTime(1000); // milliseconds
	}

	public void showTip(String tip) {
		int width = getWidth();
		final int left = width/2-tipLabel.getWidth()/2;
//		Canvas ca = getById(BatchAdmin.ID);
		int height = getHeight();
		tipLabel.setLeft(left);
		tipLabel.setContents(tip);
		tipLabel.animateMove(left, height/2-tipLabel.getHeight()/2);
		new Timer() {
			public void run() {
				tipLabel.animateMove(left, -120);
			}
		}.schedule(2500);
	}


}
