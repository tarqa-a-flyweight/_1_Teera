package com.teera.graphics.components.areas;


public class DecorTextZone extends TextZone
{
    public DecorTextZone(String contents)
    {
        super(contents);
        setWrapText(true);
        setPrefSize(700, 375);
    }
}