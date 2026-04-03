package com.teera.graphics.tabs;

import com.teera.graphics.components.scrolls.InnerScroll;
import com.teera.graphics.components.scrolls.ScrollFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import static com.teera.debug.Logmas.*;

public class TextedTab extends Tab
{
    ScrollPane scroll;

    public TextedTab(String contents)
    {
        LOGGER.fine(contents);
        ScrollFactory factory = ScrollFactory.createFactory();
        this.scroll = factory.createScroll(contents);
        setContent(scroll);
    }

    public String getContents()
    {
        if (scroll instanceof InnerScroll innerScroll)
        {
            return innerScroll.getContents();
        } else
        {
            throw new RuntimeException("Панель прокрутки не является InnerScroll!");
        }
    }
}