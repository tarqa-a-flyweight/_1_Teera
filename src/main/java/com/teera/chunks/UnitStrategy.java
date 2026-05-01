package com.teera.chunks;

import java.util.Collection;

public class UnitStrategy
{
    public String unit(Collection<String> chunks)
    {
        StringBuilder result = new StringBuilder();

        for (String chunk : chunks)
        {
            result.append(chunk);
        }

        return result.toString();
    }
}
