package com.teera.startpoint;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import org.fxmisc.richtext.InlineCssTextArea;
import com.teera.filework.Pref;
import com.teera.filework.UserFileProcessor;
import com.teera.format.ProgramFormatSetter;

public class InputContentArea
{
    private static InlineCssTextArea styledTextArea;

    private static Appendable innerContent;

    /**
     * Управление поведением TextArea при изменении содержимого файла
     */
    public static void init()
    {
        styledTextArea = new InlineCssTextArea("");

        styledTextArea.textProperty().addListener(ob ->
                {
                    /// Не полный текст обрабатывается
                    innerContent = new StringBuilder(styledTextArea.getText());

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

        styledTextArea.replaceText(innerContent.toString());
    }

    public static Appendable getText()
    {
        if (innerContent != null)
        {
            return innerContent;
        } else
        {
            return new StringBuilder();
        }
    }

    public static void clearArea()
    {
        styledTextArea.clear();
        innerContent = null;
    }


    public static void setDefaultStyle()
    {
        styledTextArea.setWrapText(true);

        styledTextArea.setPrefHeight(WindowsShowcase.AREA_SCENE_HEIGHT * 0.85);
        styledTextArea.setPrefWidth(WindowsShowcase.AREA_SCENE_WIDTH * 0.97);

        setAreaLeading(Integer.parseInt(Pref.getPreferences().get(Pref.LEADING, String.valueOf(ProgramFormatSetter.DEFAULT_LEADING))));

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

        if (currentContent != null) setAreaText(currentContent);
    }

    public static void setAreaOnGridPane(GridPane nodeRoot, int v, int v1)
    {
        nodeRoot.add(styledTextArea, v, v1);
    }
}
