package com.teera.chunks;

public class ChunkingStrategyFactory
{
    public static ChunkingStrategyFactory createFactory()
    {
        return new ChunkingStrategyFactory();
    }

    public ChunkingStrategy createChunkingStrategy()
    {
        return new ChunkingStrategy();
    }
}