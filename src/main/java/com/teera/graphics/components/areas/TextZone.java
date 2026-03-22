package com.teera.graphics.components.areas;

import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Collection;

public class TextZone extends TextArea implements Observable, TextComponent
{
    private Collection<Observer> observers = new ArrayList<>();

    public TextZone(String contents)
    {
        super(contents);

        textProperty().addListener(observable ->
        {
            // если текст больше того-то значения, вызываем alert и проводим
            // повторное разбиение
        });
    }

    // Метод не предназначен для переопределения в потомках
    protected final Collection<Observer> observers()
    {
        return observers;
    }

    @Override
    public String contents()
    {
        return getText();
    }

    @Override
    public void add(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void alert()
    {
        observers.forEach(o -> o.update(this));
    }
}
