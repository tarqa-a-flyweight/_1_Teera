package com.teera.handlers.buttonHandlers;

import com.teera.graphics.buttons.SaveButton;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.util.ArrayList;
import java.util.Collection;

public class SaveButtonHandler implements Observer, Visitor
{
    private Collection<Visited> visiteds = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        /*
            Не требуется знать состояние наблюдаемого объекта,
            поскольку известно, какие действия от этого обработчика требуются
         */

    }

    @Override
    public void visit(Visited visited)
    {
        visiteds.add(visited);
    }
}
