package com.teera.startpoint;

import com.teera.debug.ProgramLog;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import org.fxmisc.richtext.InlineCssTextArea;
import com.teera.filework.Pref;
import com.teera.filework.UserFileProcessor;
import com.teera.format.ProgramFormatSetter;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class InputContentArea
{
    private static final int SYMBOLS_IN_LINE = 84;
    private static final int TEXT_BORDER_SIZE = SYMBOLS_IN_LINE * 50;

    private static InlineCssTextArea styledTextArea;

    /// Для малого текста (< TEXT_BORDER_SIZE)
    private static Appendable innerContent;

    /// Для большого текста (> TEXT_BORDER_SIZE)
    private static List<String> chunks;
    private static ListIterator<String> chuncksIterator;


    /**
     * Управление поведением TextArea при изменении содержимого файла
     */
    public static void init()
    {
        styledTextArea = new InlineCssTextArea("");

        styledTextArea.textProperty().addListener(ob ->
                {
                    /// Если работаем с итератором (borderSizeIterator = null), должны примести его к innerContent
                    if (chuncksIterator != null)
                    {
                        ProgramLog.logger.fine("Обновление innerContent...");


                        Appendable currentContent = new StringBuilder(styledTextArea.getText());
                        chuncksIterator.set(currentContent.toString());

                        ProgramLog.logger.fine("previousIndex=" + chuncksIterator.previousIndex()
                                + "\tnextIndex=" + chuncksIterator.nextIndex()
                                + "\tcurrentContent size: " + currentContent.toString().length());

                        innerContent = chunksToBuilder();

                    } else
                    {
                        ProgramLog.logger.fine("-> Запускается обработка малого текста...");
                        innerContent = new StringBuilder(styledTextArea.getText());
                    }

                    ProgramLog.logger.fine("innerContent size: " + innerContent.toString().length());

                    // Изменения обозначаем звездочкой
                    if (WindowsShowcase.getStage() != null
                            && !WindowsShowcase.getStage().getTitle().contains("*")
                            && !getText().toString().equals(UserFileProcessor.getContent().toString())
                    )
                    {
                        WindowsShowcase.getStage().setTitle("*" + WindowsShowcase.getStage().getTitle());
                    }
                }
        );

    }


    /// Ограничение размера текста, выводимого пользователю
    public static void setAreaText(Appendable text)
    {
        innerContent = text;

        String textStr = text.toString();

        ProgramLog.logger.fine("text size: " + textStr.length());

        if (textStr.length() < TEXT_BORDER_SIZE)
        {
            chuncksIterator = null;
            styledTextArea.caretPositionProperty().addListener(cl ->
            {
            });
            styledTextArea.replaceText(innerContent.toString());
        } else
        {
            chunks = new LinkedList<>();

            // Разделяем на кусочки (первые элементы), последний (элементы + 1) и остаток
            for (int i = 1; i < textStr.length() / TEXT_BORDER_SIZE + 2; i++)
            {
                if (i * TEXT_BORDER_SIZE > textStr.length())
                {
                    chunks.add(textStr.substring((i - 1) * TEXT_BORDER_SIZE));
                } else
                {
                    chunks.add(textStr.substring((i - 1) * TEXT_BORDER_SIZE, i * TEXT_BORDER_SIZE));
                }

                ProgramLog.logger.fine("border list size: " + chunks.size());
            }

            chuncksIterator = chunks.listIterator();
            styledTextArea.replaceText(chuncksIterator.next());

            styledTextArea.caretPositionProperty().addListener(cl ->
            {

                int caretPos = styledTextArea.getCaretPosition();
                ProgramLog.logger.fine("caretPos: " + caretPos);

                /// Если пересекает границу, то нас интересует только направление
                if (caretPos == styledTextArea.getText().length() - 1)
                {
                    if (chuncksIterator.hasNext())
                    {
                        styledTextArea.replaceText(chuncksIterator.next());
                    }
                } else if (caretPos == 0)
                {
                    if (chuncksIterator.hasPrevious())
                    {
                        styledTextArea.replaceText(chuncksIterator.previous());
                    }
                }

            });

        }
    }

    /// Большой текст сохраняется другим способом
    public static Appendable getText()
    {
        if (chuncksIterator == null)
        {
            if (innerContent != null)
            {
                return innerContent;
            } else
            {
                return new StringBuilder();
            }
        } else
        {
            return chunksToBuilder();
        }
    }

    ///  Итератор очищается
    public static void clearArea()
    {
        styledTextArea.clear();

        if (chuncksIterator != null)
        {
            chunks = null;
            chuncksIterator = null;
            styledTextArea.caretPositionProperty().addListener(cl -> {});
        }

        innerContent = null;

        if (styledTextArea.getText() != null)
        {
            styledTextArea.clear();
        }

    }

    private static Appendable chunksToBuilder()
    {
        ListIterator<String> builderIterator = chunks.listIterator();

        StringBuilder parentContent = new StringBuilder();

        while (builderIterator.hasNext())
        {
            parentContent.append(builderIterator.next());
        }

        return parentContent;
    }


    public static void setDefaultStyle()
    {
        styledTextArea.setWrapText(true);

        styledTextArea.setPrefHeight(WindowsShowcase.AREA_SCENE_HEIGHT * 0.85);
        styledTextArea.setPrefWidth(WindowsShowcase.AREA_SCENE_WIDTH * 0.97);

        setAreaLeading(Pref.getPreferences().getInt(Pref.LEADING, ProgramFormatSetter.DEFAULT_LEADING));

        styledTextArea.requestLayout();
    }

    public static void setAreaLeading(int size)
    {
        Appendable currentContent = getText();

        String spacingCommand = "-fx-line-spacing: " + size + "px;";
        String fontCommand = "-fx-font-family: Aptos, sans-serif;\n-fx-font-size: 16px;";

        styledTextArea.setPadding(new Insets(10, 0, 0, 10));

        styledTextArea.setParagraphInsertionStyle(spacingCommand);
        styledTextArea.setStyle(fontCommand);

        // Надо подумать, можно ли обойтись без этого
        if (currentContent != null) styledTextArea.replaceText(currentContent.toString());
    }

    public static void setAreaOnGridPane(GridPane nodeRoot, int v, int v1)
    {
        nodeRoot.add(styledTextArea, v, v1);
    }
}
