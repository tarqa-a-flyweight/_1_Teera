package com.teera.handlers.buttonHandlers;

import com.teera.files.IOStrategyFactory;
import com.teera.files.OutputStrategy;
import com.teera.graphics.dialogs.SaveAsDialogStrategy;
import com.teera.graphics.panes.TabZone;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class SaveAsButtonHandler implements Observer, Visitor
{
    // SaveAsDialogStrategy OR TabZone
    private Collection<Visited> visitTargets = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        // Получаем текущее содержание
        String currentContent = "";

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                currentContent = tabZone.getContents();
                break;
            }
        }

        Path targetPath = null;

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof SaveAsDialogStrategy saveAsDialog)
            {
                targetPath = saveAsDialog.saveAsDialog();
                break;
            }
        }

        if (targetPath == null) return;

        // Если есть содержимое и целевой файл, записываем
        IOStrategyFactory factory = IOStrategyFactory.createFactory();
        OutputStrategy out = factory.createOutputStrategy();

        out.write(currentContent, targetPath);
    }

    @Override
    public void visit(Visited visited)
    {
        visitTargets.add(visited);
    }
}
