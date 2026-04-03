package com.teera.files;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class OutputStrategy
{
    public void write(String contents, Path path)
    {
        // Возможна параллельная запись

        try (PrintWriter printWriter =
                     new PrintWriter(new FileOutputStream(path.toString()), true, StandardCharsets.UTF_8))
        {
            printWriter.print(contents);
        } catch (IOException ioe)
        {
            throw new RuntimeException(ioe.getMessage());
        }
    }
}