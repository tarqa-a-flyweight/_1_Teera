package com.teera.graphics.dialogs;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UnsaveDialogStrategy implements Visited
{
    private Collection<Visitor> visitors = new ArrayList<>();

    public boolean confirm()
    {
        Dialog<Boolean> dialog = new Dialog<>();

        DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.setContentText("Don't save changes?");

        ButtonType confirm = new ButtonType("Ok");
        ButtonType cancel = new ButtonType("Cancel");

        dialogPane.getButtonTypes().addAll(confirm, cancel);
        dialog.setDialogPane(dialogPane);
        dialog.setResultConverter(button -> button == confirm);

        Optional<Boolean> result = dialog.showAndWait();

        return result.orElse(false);
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