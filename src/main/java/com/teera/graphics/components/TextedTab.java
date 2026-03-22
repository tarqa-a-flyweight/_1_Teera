package com.teera.graphics.components;

import com.teera.graphics.components.scrolls.ScrollFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

public class TextedTab extends Tab
{
    ScrollPane scroll;

    public TextedTab(String contents)
    {
        ScrollFactory factory = ScrollFactory.createFactory();
        this.scroll =  factory.createScroll(contents);
    }

    public String getContents()
    {
        return "";
    }
}