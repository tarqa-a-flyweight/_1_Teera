package com.teera.graphics.components.areas;

import javafx.scene.control.TextArea;

public class TextComponentFactory
{
    public static TextComponentFactory createFactory()
    {
        return new TextZoneFactory();
    }

    public TextArea createTextArea(String contents)
    {
        return new TextArea();
    }
}