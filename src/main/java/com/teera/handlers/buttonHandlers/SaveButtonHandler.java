package com.teera.handlers.buttonHandlers;

import com.teera.graphics.buttons.SaveButton;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

public class SaveButtonHandler implements Observer, Visitor
{
    Visited visited;

    @Override
    public void update(Observable observable)
    {
        if (observable instanceof SaveButton saveButton)
        {

        } else
        {
            throw new RuntimeException("Наблюдаемый объект не является SaveButton!");
        }
    }

    @Override
    public void visit(Visited visited)
    {
        this.visited = visited;
    }
}
