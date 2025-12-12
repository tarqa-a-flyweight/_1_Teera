package com.teera.filework;

import com.teera.startpoint.InputContentArea;
import com.teera.startpoint.WindowsShowcase;
import java.io.IOException;

public class SaveFile
{

    public static void save() throws IOException, InterruptedException
    {
        // Проверка наличия файла сохранения.
        if (UserFileProcessor.getUserFileName().isEmpty())
        {
            UserFileProcessor.init(OpenFile.chooseFile());
        }

        if (!UserFileProcessor.getUserFileName().isEmpty())
        {
            Appendable content = UserFileProcessor.updateContent(InputContentArea.getText());

            // Сохраняем изменения во внутреннее хранилище и в файл.
            UserFileProcessor.write(content.toString());

            // Убираем значок несохраненных изменений.
            WindowsShowcase.getStage().setTitle(UserFileProcessor.getUserFileName());
        }
    }
}
