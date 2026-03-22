package com.teera.graphics.dialogs;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.scene.control.DialogPane;

import java.util.ArrayList;
import java.util.Collection;

public class UnsaveDialogStrategy implements Visited
{
    private Collection<Visitor> visitors = new ArrayList<>();

    public boolean confirm()
    {
        DialogPane dialogPane = new DialogPane();

        // ...

        return false;
    }

    @Override
    public void set(Visitor visitor)
    {
        visitors.add(visitor);
        visitors.forEach(v -> v.visit(this));
    }

    protected final Collection<Visitor> visitors()
    {
        return visitors;
    }
}