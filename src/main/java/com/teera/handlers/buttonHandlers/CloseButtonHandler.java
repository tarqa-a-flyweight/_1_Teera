package com.teera.handlers.buttonHandlers;

import com.teera.graphics.dialogs.UnsaveDialogStrategy;
import com.teera.graphics.panes.TabZone;
import com.teera.handlers.FileStore;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static com.teera.debug.Logmas.LOGGER;

public class CloseButtonHandler implements Observer, Visitor
{
    // UnsaveDialog OR TabZone
    private Collection<Visited> visitTargets = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        // Получаем текущее содержание
        Path currentPath = null;
        String currentContent = null;

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                try
                {
                    currentPath = Path.of(tabZone.currentTab().getText());
                } catch (Exception e)
                {
                }

                currentContent = tabZone.getContents();
                break;
            }
        }

        String savedContent = null;

        if (currentPath != null)
        // если путь известен, получаем содержимое последнего сохранения
        {
            // Получаем сохраненные данные
            for (Visited visitTarget : visitTargets)
            {
                if (visitTarget instanceof FileStore fileStore)
                {
                    // Можно получать путь
                    savedContent = fileStore.getContent(currentPath);
                    break;
                }
            }
        }


        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof UnsaveDialogStrategy uds)
            {
                if (currentContent == null) currentContent = "";
                if (savedContent == null) savedContent = "";

                if (currentContent.equals(savedContent))
                {
                    break;
                } else
                {
                    if (!uds.confirm()) return;
                }
            }
        }

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                tabZone.getTabs().remove(tabZone.currentTab());
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
