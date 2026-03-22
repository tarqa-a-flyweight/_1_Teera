package com.teera.graphics.buttons;

import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collection;

abstract public class ObservableButton extends Button implements Observable
{
    private Collection<Observer> observers = new ArrayList<>();

    public ObservableButton(String s)
    {
        super(s);
    }

    public Collection<Observer> observers()
    {
        return observers;
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
}