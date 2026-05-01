package com.teera.graphics.components.areas;

import com.teera.graphics.Resizable;
import javafx.application.Platform;
import javafx.scene.text.Text;

public class VFillTextZone extends TextZone implements Resizable
{
    public VFillTextZone(String contents)
    {
        super(contents);
        setWrapText(true);

        textProperty().addListener(observable ->
        {
            Text text = new Text(getText());
            text.setFont(getFont());
            text.setWrappingWidth(getWidth() - 20);

            double textH = text.getBoundsInLocal().getHeight();

            if (textH + 100 > getHeight()) setPrefHeight(textH + 100);
            if (textH < 400 && getHeight() > 500) setPrefHeight(500);
        });
    }

    @Override
    public void design(double width, double height)
    {
        setPrefWidth(width);

        Text text = new Text(getText());
        text.setFont(getFont());
        text.setWrappingWidth(width - 20);

        double textH = text.getBoundsInLocal().getHeight();

        if (textH + 100 > height)
        {
            setPrefHeight(textH + 100);
        } else
        {
            setPrefHeight(height);
        }
    }

}