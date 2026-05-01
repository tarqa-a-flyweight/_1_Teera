package com.teera.graphics.dialogs;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class OpenDialogStrategy implements Visited
{
    private Window window;
    private FileChooser fileChooser = new FileChooser();

    private Collection<Visitor> visitors = new ArrayList<>();

    public OpenDialogStrategy(Window window)
    {
        this.window = window;
    }

    public Path openDialog()
    {
        File file = fileChooser.showOpenDialog(window);
        if (file == null)
        {
            return null;
        } else
        {
            return file.toPath();
        }
    }

    @Override
    public void addVisitor(Visitor visitor)
    {
        visitors.add(visitor);
        visitors.forEach(v -> v.visit(this));
    }

    protected final Collection<Visitor> visitors()
    {
        return visitors;
    }
}