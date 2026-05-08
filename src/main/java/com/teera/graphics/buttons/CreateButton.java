package com.teera.graphics.buttons;

public class CreateButton extends ObservableButton
{
    public CreateButton()
    {
        super("Create");

        setOnAction(actionEvent -> alert());
    }

    @Override
    public void alert()
    {
        observers().forEach(o -> o.update(this));
    }
}