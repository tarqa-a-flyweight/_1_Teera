package com.teera.graphics.components;

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