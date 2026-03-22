package com.teera.graphics.components;

import javafx.scene.control.Tab;

public class TextedTabFactory extends TabFactory
{
    @Override
    public Tab createTab(String contents)
    {
        return new TextedTab(contents);
    }
}