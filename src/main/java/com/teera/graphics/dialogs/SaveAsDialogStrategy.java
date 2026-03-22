package com.teera.graphics.dialogs;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class SaveAsDialogStrategy implements Visited
{
    private Window window;
    private FileChooser fileChooser = new FileChooser();

    private Collection<Visitor> visitors = new ArrayList<>();

    public SaveAsDialogStrategy(Window window)
    {
        this.window = window;
    }

    public Path saveAsDialog()
    {
        return fileChooser.showSaveDialog(window).toPath();
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