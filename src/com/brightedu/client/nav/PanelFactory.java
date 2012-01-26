package com.brightedu.client.nav;

import com.smartgwt.client.widgets.Canvas;

public interface PanelFactory {

    Canvas create();

    String getID();

    String getDescription();
}
