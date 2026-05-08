package com.teera.graphics.dialogs;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class SaveAsDialogStrategy implements Visited
{
    private Window window;
    private FileChooser fileChooser;

    private Collection<Visitor> visitors = new ArrayList<>();

    public SaveAsDialogStrategy(Window window)
    {
        this.window = window;
        this.fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(".txt","*.txt");
        fileChooser.getExtensionFilters().add(filter);
    }

    public Path saveAsDialog()
    {
        File file = fileChooser.showSaveDialog(window);
        if (file == null)
        {
            return null;
        } else {
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