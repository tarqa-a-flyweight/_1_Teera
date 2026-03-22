package com.teera.graphics.panes;

import javafx.scene.control.TabPane;

public class TabPaneFactory
{
    public static TabPaneFactory createFactory()
    {
        return new TabZoneFactory();
    }

    public TabPane createTabPane()
    {
        return new TabPane();
    }
}
