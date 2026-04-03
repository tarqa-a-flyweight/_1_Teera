package com.teera.chunks;

public class WrapStrategyFactory
{
    public static WrapStrategyFactory createFactory()
    {
        return new WrapStrategyFactory();
    }

    public WrapStrategy createWrapStrategy()
    {
        return new WrapStrategy();
    }
}
