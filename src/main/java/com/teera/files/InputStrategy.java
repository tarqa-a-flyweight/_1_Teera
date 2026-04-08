package com.teera.files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class InputStrategy
{
    public String read(Path path)
    {
        StringBuilder result = new StringBuilder();

        // Возможно параллельное чтение

        try (Scanner scanner = new Scanner(path, StandardCharsets.UTF_8))
        {
            while (scanner.hasNext())
            {
                result.append(scanner.nextLine());
                if (scanner.hasNext()) result.append("\n");
            }
        } catch (IOException ioe)
        {
            throw  new RuntimeException(ioe.getMessage());
        }

        return result.toString();
    }
}