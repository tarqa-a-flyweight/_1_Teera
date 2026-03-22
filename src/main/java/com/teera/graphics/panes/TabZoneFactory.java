package com.teera.graphics.panes;

import javafx.scene.control.TabPane;

public class TabZoneFactory extends TabPaneFactory
{
    @Override
    public TabPane createTabPane()
    {
        return new TabZone();
    }
}