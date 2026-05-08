package com.teera.graphics.buttons;

public class SaveButton extends ObservableButton
{
    public SaveButton()
    {
        super("Save");

        setOnAction(actionEvent -> alert());
    }

    @Override
    public void alert()
    {
        observers().forEach(o -> o.update(this));
    }
}