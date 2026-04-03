package com.teera.chunks;

import java.util.ArrayList;
import java.util.Collection;
import static com.teera.debug.Logmas.*;

public class ChunkingStrategy
{
    private final static int CHUNK_SIZE = 500;

    public Collection<String> chunking(String contents)
    {
        LOGGER.fine(contents);
        Collection<String> chunks = new ArrayList<>();

        if (contents.length() <= CHUNK_SIZE) chunks.add(contents);

        LOGGER.fine(chunks.size() + "");

        // Можно разбивать текст по предложениям. Так будет понятнее.
        // + 500 мало, лучше 1000-1500 символов на область ввода.

        for (int start = 0; start < contents.length() - CHUNK_SIZE; start += CHUNK_SIZE)
        {
            chunks.add(contents.substring(start, start + CHUNK_SIZE));
            LOGGER.fine("Разбиваем: " + contents.substring(start, start + CHUNK_SIZE));
        }

        return chunks;
    }
}