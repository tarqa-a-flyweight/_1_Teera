package com.teera.graphics.components.areas;

import javafx.scene.control.TextArea;

public class TextZoneFactory extends TextComponentFactory
{
    @Override
    public TextArea createTextArea(String contents)
    {
        return new TextZone(contents);
    }
}