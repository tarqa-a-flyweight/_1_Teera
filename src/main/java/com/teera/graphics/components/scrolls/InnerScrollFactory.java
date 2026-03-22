package com.teera.graphics.components.scrolls;

import javafx.scene.control.ScrollPane;

public class InnerScrollFactory extends ScrollFactory
{
    @Override
    public ScrollPane createScroll(String contents)
    {
        return new InnerScroll(contents);
    }
}
