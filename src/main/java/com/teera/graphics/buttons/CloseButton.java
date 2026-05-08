package com.teera.graphics.buttons;

public class CloseButton extends ObservableButton
{
    public CloseButton()
    {
        super("Close");

        setOnAction(actionEvent -> alert());
    }

    @Override
    public void alert()
    {
        observers().forEach(o -> o.update(this));
    }
}