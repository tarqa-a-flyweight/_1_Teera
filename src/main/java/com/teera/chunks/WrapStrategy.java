package com.teera.chunks;

import com.teera.graphics.components.areas.TextComponent;

import java.util.ArrayList;
import java.util.Collection;

public class WrapStrategy
{
    public Collection<TextComponent> wrap(Collection<String> chunks)
    {
        return new ArrayList<>();
    }

    public Collection<String> unwrap(Collection<TextComponent> components)
    {
        return new ArrayList<>();
    }
}