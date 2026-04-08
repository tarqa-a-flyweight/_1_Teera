package com.teera.chunks;

public class ChunkingStrategyFactory
{
    public final static int CHUNK_SIZE = 1000;
    public final static int RESIZE_VALUE = 2000;

    public static ChunkingStrategyFactory createFactory()
    {
        return new ChunkingStrategyFactory();
    }

    public ChunkingStrategy createChunkingStrategy()
    {
        return new ChunkingStrategy();
    }
}