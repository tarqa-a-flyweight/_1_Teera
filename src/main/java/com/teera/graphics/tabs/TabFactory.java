package com.teera.graphics.tabs;

import javafx.scene.control.Tab;

public class TabFactory
{
    public static TabFactory createFactory()
    {
        return new TextedTabFactory();
    }

    public Tab createTab(String contents)
    {
        return new Tab();
    }
}