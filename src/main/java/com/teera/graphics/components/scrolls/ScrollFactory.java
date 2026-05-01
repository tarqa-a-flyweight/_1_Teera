package com.teera.graphics.components.scrolls;

import javafx.scene.control.ScrollPane;

public class ScrollFactory
{
    public static ScrollFactory createFactory()
    {
        return new InnerScrollFactory();
    }

    public ScrollPane createScroll(String contents)
    {
        return new ScrollPane();
    }
}