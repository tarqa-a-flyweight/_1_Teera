package com.teera.graphics.panes;

import com.teera.debug.Logmas;
import com.teera.graphics.tabs.TextedTab;
import com.teera.graphics.tabs.TabFactory;
import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.Collection;

import static com.teera.debug.Logmas.*;

public class TabZone extends TabPane implements Visited
{
    private Collection<Visitor> visitors = new ArrayList<>();

    /*
        Метод set позволяет другим классам, в частности обработчикам,
        а в общем всем реализующим Visitor классам пользоваться методами TabZone
        (postContents, getContents и др.)
     */

    @Override
    public void addVisitor(Visitor visitor)
    {
        visitors.add(visitor);
        visitors.forEach(v -> v.visit(this));
    }

    public void postContents(String contents)
    {
        LOGGER.fine(contents);

        // format: path@@@content
        String[] s = contents.split("@@@");

        TabFactory factory = TabFactory.createFactory();

        Tab newTab;

        if (s.length < 2)
        {
            newTab = factory.createTab("");
        } else
        {
            newTab = factory.createTab(s[1]);
        }

        if (s.length == 0)
        {
            newTab.setText("<Безымянный>");
        } else
        {
            newTab.setText(s[0]);
        }

        getTabs().add(newTab);
    }

    /*
        В getContents(), а также в других методах происходит обращение к конкретным классам,
        поскольку есть методы, использование которых нельзя предотвратить.

        Напротив, все конкретные классы TabZone, TextedTab, InnerScroll TextZone
        могут быть родителями для других классов с более совершенной реализацией,
        для чего достаточно будет просто заменить конкретные фабрики
        соответствующих классов, поскольку именование родительских классов не изменяется
        и методы реализации остаются.
     */

    public String getContents()
    {
        if (currentTab() instanceof TextedTab textedTab)
        {
            return textedTab.getContents();
        } else
        {
            throw new RuntimeException("Текущая вкладка не является TextedTab!");
        }
    }

    public Tab currentTab()
    {
        Tab currentTab = new Tab();
        for (Tab tab : getTabs())
        {
            if (tab.isSelected())
            {
                currentTab = tab;
                break;
            }
        }
        return currentTab;
    }

}