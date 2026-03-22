package com.teera.graphics.components.scrolls;

import com.teera.handlers.patterns.Observable;
import com.teera.handlers.patterns.Observer;
import javafx.scene.control.ScrollPane;

import java.util.Collection;

public class InnerScroll extends ScrollPane implements Observer
{
    public InnerScroll(String contents)
    {
        // Нужен чанкер, чтобы поместить в оболочки текстовые компоненты
    }

    private void setNodes(Collection<String> chunks)
    {
        // каждый узел нужно сделать еще и отслеживаемым
    }

    public String getContents()
    {
        // Нужен объединитель, чтобы скомпоновать текстовые компоненты
        return "";
    }

    @Override
    public void update(Observable observable)
    {
        // getContent + чанкер + setNodes
    }
}