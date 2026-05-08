package com.teera.graphics.buttons;

public class SaveAsButton extends ObservableButton
{
    public SaveAsButton()
    {
        super("Save As");

        setOnAction(actionEvent -> alert());
    }

    @Override
    public void alert()
    {
        observers().forEach(o -> o.update(this));
    }
}