package com.teera.handlers.buttonHandlers;

import com.teera.graphics.panes.TabZone;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.util.ArrayList;
import java.util.Collection;

public class CreateButtonHandler implements Observer, Visitor
{
    // TabZone OR FileStore
    private Collection<Visited> visitTargets = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                tabZone.postContents("@@@");
                break;
            }
        }
    }

    @Override
    public void visit(Visited visited)
    {
        visitTargets.add(visited);
    }
}
