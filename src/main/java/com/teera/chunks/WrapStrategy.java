package com.teera.chunks;

import com.teera.graphics.components.areas.TextComponent;
import com.teera.graphics.components.areas.TextComponentFactory;

import java.util.ArrayList;
import java.util.Collection;

public class WrapStrategy
{
    public Collection<TextComponent> wrap(Collection<String> chunks)
    {
        Collection<TextComponent> components = new ArrayList<>();

        TextComponentFactory factory = TextComponentFactory.createFactory();

        for (String chunk : chunks)
        {
            if (factory.createTextArea(chunk) instanceof TextComponent textComponent)
            {
                components.add(textComponent);
            } else
            {
                throw new RuntimeException("Компонент не является TextComponent!");
            }
        }

        return components;
    }

    public Collection<String> unwrap(Collection<TextComponent> components)
    {
        Collection<String> result = new ArrayList<>();

        for (TextComponent component : components)
        {
            result.add(component.contents());
        }

        return result;
    }
}