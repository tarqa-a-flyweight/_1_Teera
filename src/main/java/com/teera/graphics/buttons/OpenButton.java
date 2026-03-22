package com.teera.graphics.buttons;

public class OpenButton extends ObservableButton
{
    public OpenButton()
    {
        super("<Открыть>");

        setOnAction(actionEvent -> alert());
    }

    @Override
    public void alert()
    {
        observers().forEach(o -> o.update(this));
    }
}