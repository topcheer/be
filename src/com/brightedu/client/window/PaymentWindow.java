package com.brightedu.client.window;


import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VStack;


public class PaymentWindow extends Window {
	
	IButton finishBtn = new IButton("完成支付");
	String _bankPaymentURL="";
	public PaymentWindow(String bankPaymentURL) {
		_bankPaymentURL = bankPaymentURL;
	}
	
	protected void init() {
		setEdgeMarginSize(4);
		setEdgeOffset(5);
		setAutoCenter(true);
		setTitle("支付页面");
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(false);
		setOverflow(Overflow.VISIBLE);
		setWidth(900);
		setHeight(700);
		
		
		finishBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				destroy();
				 
			}
		});
		
		
		HTMLPane bankPage = new HTMLPane();
		bankPage.setContentsType(ContentsType.PAGE);
		bankPage.setContentsURL(_bankPaymentURL);
		bankPage.setHeight(650);
		
        VStack vStack = new VStack();   
        vStack.setShowEdges(false);   
        vStack.setWidth100();   
        vStack.setMembersMargin(5);   
        vStack.setLayoutMargin(10);
        
        vStack.addMember(bankPage);  
        
        vStack.addMember(finishBtn);
 
		addItem(vStack);
		
		setAutoSize(true);
		setCanDragResize(true);

	}
	
	public void show()
	{
		init();
		super.show();
	}

}
