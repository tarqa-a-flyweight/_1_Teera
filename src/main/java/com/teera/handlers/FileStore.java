package com.teera.handlers;

import com.teera.handlers.patterns.Visited;
import com.teera.handlers.patterns.Visitor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileStore implements Visited
{
    private Collection<Visitor> visitors = new ArrayList<>();

    private Map<Path, String> contents = new HashMap<>();

    @Override
    public void addVisitor(Visitor visitor)
    {
        visitors.add(visitor);
        visitors.forEach(v -> v.visit(this));
    }

    public void putContent(Path path, String content)
    {
        // Возможно открытие одного и того же файла!
        // Можно хранить в виде чанков, а не в строке.
        contents.put(path, content);
    }

    public String getContent(Path path)
    {
        return contents.get(path);
    }

    protected final Collection<Visitor> visitors()
    {
        return visitors;
    }
}
