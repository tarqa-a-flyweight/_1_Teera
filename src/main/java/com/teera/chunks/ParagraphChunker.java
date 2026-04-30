package com.teera.chunks;

import java.util.ArrayList;
import java.util.Collection;

import static com.teera.chunks.ChunkingStrategyFactory.CHUNK_SIZE;

public class ParagraphChunker extends ChunkingStrategy
{
    @Override
    public Collection<String> chunking(String contents)
    {
        Collection<String> chunks = new ArrayList<>();

        if (contents.length() <= CHUNK_SIZE) chunks.add(contents);

        /*
            Если абзацы нестандартные - разбиваем на предложения
            Если предложения нестандартные - на слова.
            Если слова все без пробелов - переносим по буквам.
            Если все абзацы обычного размера.
        */

        String[] paras = contents.split("\n");
        String para = "";

        for (int i = 0; i < paras.length; i++)
        {
            para = para.concat(paras[i]);
            para += "\n";

            if (para.length() > CHUNK_SIZE)
            {
                chunks.add(para);
                para = "";
            }

            if (i == paras.length - 1) chunks.add(para);
        }

        return chunks;
    }
}
