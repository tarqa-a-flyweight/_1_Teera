package com.teera.handlers.buttonHandlers;

import com.teera.files.IOStrategyFactory;
import com.teera.files.OutputStrategy;
import com.teera.graphics.dialogs.SaveAsDialogStrategy;
import com.teera.graphics.panes.TabZone;
import com.teera.handlers.FileStore;
import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class SaveButtonHandler implements Observer, Visitor
{
    // SaveAsDialogStrategy OR TabZone OR Filestore
    private Collection<Visited> visitTargets = new ArrayList<>();

    @Override
    public void update(Observable observable)
    {
        // Получаем текущее содержание
        String currentContent = null;
        Path currentPath = null;

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                String name = tabZone.currentTab().getText();

                try
                {
                    currentPath = Path.of(name);
                } catch (Exception e)
                {
                }

                currentContent = tabZone.getContents();
                break;
            }
        }

        if (currentContent == null) return;

        if (currentPath == null)
        {
            for (Visited visitTarget : visitTargets)
            {
                if (visitTarget instanceof SaveAsDialogStrategy saveAsDialog)
                {
                    currentPath = saveAsDialog.saveAsDialog();
                    break;
                }
            }

            if (currentPath == null) return;
        } else // если путь известен, получаем содержимое последнего сохранения
        {
            String savedContent = null;

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

            if (currentContent.equals(savedContent)) return;
        }

        // Если есть содержимое, файл и несохраненные изменения, то записываем
        IOStrategyFactory factory = IOStrategyFactory.createFactory();
        OutputStrategy out = factory.createOutputStrategy();

        out.write(currentContent, currentPath);

        for (Visited visitTarget : visitTargets)
        {
            if (visitTarget instanceof TabZone tabZone)
            {
                tabZone.currentTab().setText(currentPath.toString());
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
