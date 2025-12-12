package com.teera.filework;

import com.teera.startpoint.InputContentArea;
import com.teera.startpoint.WindowsShowcase;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OpenFile
{
    private static FileChooser fileChooser = new FileChooser();

    public static void open() throws IOException, InterruptedException, ExecutionException
    {
        UnsaveOpen unsaveOpen = new UnsaveOpen();

        // Сначала запускаем диалоговое окно
        CompletableFuture<Boolean> confirmTask = unsaveOpen.confirmUnsave();

        // Только получив положительный ответ, мы можем перейти к выбору файла
        confirmTask.thenAccept(result ->
        {
            if (result)
            {
                // Инициализируем
                File file = chooseFile();

                if (file != null)
                {
                    UserFileProcessor.init(file);
                } else
                {
                     return;
                }

                // Если выбор сделан:
                Appendable userContent;
                if (!UserFileProcessor.getUserFileName().isEmpty())
                {
                    try
                    {
                        // Читаем
                        userContent = UserFileProcessor.read();

                        // Добавляем интервалы
                        InputContentArea.setDefaultStyle();
                    } catch (IOException | InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                    // Устанавливаем на место поля ввода
                    InputContentArea.setAreaText(userContent);

                    // Устанавливаем название
                    WindowsShowcase.getStage().setTitle(UserFileProcessor.getUserFileName());
                }
            }
        });
    }

    public static File chooseFile()
    {
        // Настройки для FileChooser ...

        fileChooser.setInitialDirectory(new File("C:\\Users"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текстовый файл", "*.txt")
        );

        return fileChooser.showOpenDialog(WindowsShowcase.getStage());
    }

}
