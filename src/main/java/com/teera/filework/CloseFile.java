package com.teera.filework;

import com.teera.debug.ProgramLog;
import com.teera.startpoint.InputContentArea;
import com.teera.startpoint.WindowsShowcase;
import java.util.concurrent.CompletableFuture;

public class CloseFile
{
    public static void close() throws InterruptedException
    {
        // Файл должен быть открыт, текущее содержание должно быть инициализировано, но при этом пустое.
        if (UserFileProcessor.getUserFileName().isEmpty() && InputContentArea.getText() == null) return;

        UnsaveOpen unsaveOpen = new UnsaveOpen();

        // Сначала запускаем диалоговое окно.
        CompletableFuture<Boolean> confirmTask = unsaveOpen.confirmUnsave();

        // Только получив положительный ответ, мы можем перейти к закрытию файла.
        confirmTask.thenAccept(result ->
        {
            if (result)
            {
                // Обновляем данные.
                InputContentArea.clearArea();
                UserFileProcessor.init(null);

                UserFileProcessor.updateContent(new StringBuilder());

                //ProgramLog.logger.fine("Закрываем файл, меняем название...");
                // Устанавливаем название.
                WindowsShowcase.getStage().setTitle("Безымянный");
            }
        });
    }
}
