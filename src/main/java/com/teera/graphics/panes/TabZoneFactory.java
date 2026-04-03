package com.teera.graphics.panes;

public class TabZoneFactory
{
    public static TabZoneFactory createFactory()
    {
        return new TabZoneFactory();
    }

    public TabZone createTabPane()
    {
        return new DecorTabZone();
    }
}