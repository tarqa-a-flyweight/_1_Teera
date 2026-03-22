package com.teera.handlers.buttonHandlers;

import com.teera.graphics.buttons.OpenButton;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

public class OpenButtonHandler implements Observer, Visitor
{
    Visited visited;

    @Override
    public void update(Observable observable)
    {
        if (observable instanceof OpenButton openButton)
        {

        } else
        {
            throw new RuntimeException("Наблюдаемый объект не является OpenButton!");
        }
    }

    @Override
    public void visit(Visited visited)
    {
        this.visited = visited;
    }
}