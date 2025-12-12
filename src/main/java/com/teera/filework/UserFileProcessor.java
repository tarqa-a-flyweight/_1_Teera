package com.teera.filework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * UserFileProcessor выполняет доступ к файлу пользователя, производит чтение и запись.
 */
public class UserFileProcessor
{
    private static File userFile;
    private static Appendable userFileContent;


    public static void init(File file)
    {
        userFile = file;
    }

    /**
     * Чтение происходит после открытия файла.
     *
     * @return userFileContent возвращает объект для обработки содержания файла.
     *
     */
    public static Appendable read() throws IOException, InterruptedException
    {
        userFileContent = new StringBuilder();

        Thread thread = new Thread(() ->
        {
            try (Scanner scanner = new Scanner(userFile, StandardCharsets.UTF_8))
            {
                while (scanner.hasNext()) userFileContent.append(scanner.nextLine() + "\n");

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        thread.join();

        return userFileContent;
    }

    /**
     * Запись в файл является частью процесса сохранения файла.
     * Если файл не будет заранее открыт, потребуется его сначала открыть (или создать).
     *
     * @param content является строчным представлением нового содержания файла,
     *                то есть измененный userFileContent.
     *
     */
    public static void write(String content) throws InterruptedException
    {
        Thread thread = new Thread(() ->
        {
            try (FileWriter fileWriter = new FileWriter(userFile))
            {
                fileWriter.write(content + "\n");

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });

        thread.start();
        thread.join();
    }

    /**
     * @return userFileContent последнее сохраненное содержание файла.
     * Нужно для сравнения с текущим содержанием.
     */
    public static Appendable getContent()
    {
        if (userFileContent != null)
        {
            return userFileContent;
        } else
        {
            return new StringBuilder();
        }
    }

    /**
     * Данный метод обновляет текущее содержание файла без записи
     * (потенциальное содержание);
     *
     * @param str новое содержание файла.
     *            Требуется для обновления содержания при сохранении.
     */
    public static Appendable updateContent(Appendable str)
    {
        userFileContent = str;

        return userFileContent;
    }

    /**
     * @return currentFileName возвращает имя текущего файла, либо ничего не возвращает.
     * Нужно использовать для обновления заголовка окна и быстрой проверки наличия файла.
     */
    public static String getUserFileName()
    {
        if (userFile != null)
        {
            return userFile.getName();
        } else
        {
            return "";
        }
    }
}
