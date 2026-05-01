package com.teera.chunks;

public class UnitStrategyFactory
{
    public static UnitStrategyFactory createFactory()
    {
        return new UnitStrategyFactory();
    }

    public UnitStrategy createUnitStrategy()
    {
        return new UnitStrategy();
    }
}