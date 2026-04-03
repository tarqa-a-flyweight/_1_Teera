package com.teera.handlers.buttonHandlers;

import com.teera.files.IOStrategyFactory;
import com.teera.files.InputStrategy;
import com.teera.graphics.dialogs.OpenDialogStrategy;
import com.teera.graphics.panes.TabZone;
import com.teera.handlers.FileStore;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class OpenButtonHandler implements Observer, Visitor
{
    // OpenDialogStrategy OR TabZone OR FileStore
    private Collection<Visited> visitTargets = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        Path path = null;
        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof OpenDialogStrategy openDialog)
            {
                path = openDialog.openDialog();
                break;
            }
        }

        if (path == null) return;

        IOStrategyFactory factory =  IOStrategyFactory.createFactory();
        InputStrategy in = factory.createInputStrategy();
        String nextContent = in.read(path);

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                tabZone.postContents(path + "@@@" + nextContent);
                break;
            }
        }

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof FileStore fileStore)
            {
                // Можно получать путь
                fileStore.putContent(path, nextContent);
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