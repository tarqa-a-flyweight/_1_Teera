package com.teera.graphics.dialogs;

import javafx.stage.Window;

public class DialogStrategyFactory
{
    public static DialogStrategyFactory createFactory()
    {
        return new DialogStrategyFactory();
    }

    public OpenDialogStrategy createOpenDialog(Window window)
    {
        return new OpenDialogStrategy(window);
    }

    public SaveAsDialogStrategy createSaveAsDialog(Window window)
    {
        return new SaveAsDialogStrategy(window);
    }
}
