package com.teera.graphics.dialogs;

public class UnsaveDialogStrategyFactory
{
    public static UnsaveDialogStrategyFactory createFactory()
    {
        return new UnsaveDialogStrategyFactory();
    }

    public UnsaveDialogStrategy createUnsaveDialog()
    {
        return new UnsaveDialogStrategy();
    }
}
