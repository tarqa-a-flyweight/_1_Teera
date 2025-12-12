package com.teera.startpoint;

import com.teera.filework.*;
import com.teera.filework.UserFileProcessor;
import com.teera.format.ProgramFormatSetter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Teera - basic text editor (study project).
 *
 * @author tarqa-a-flyweight
 * @version 1.0 12-12-2025
 */

public class WindowsShowcase extends Application
{
    /// Главный шрифт программы (за исключением изменений styledTextArea при помощи CSS)
    public static final Font APTOS_FONT = Font.font("Aptos", 16);

    public static final int AREA_SCENE_WIDTH = 764, AREA_SCENE_HEIGHT = 432;

    /** Площадка программы для отображения окна выбора файла
     * Пример строчки класса OpenFile:
     * <code>
     *     return fileChooser.showOpenDialog(WindowsShowcase.getStage());
     * </code>
     */
    private static Stage mainstage;


    /**
     * Все изменения сохраняются в textArea, а сохранение происходит загрузкой текста в файл?
     * Оценка изменений происходит сравнением содержимого файла (userFileContent) и содержания текста getText()?
     * При сохранении содержание textArea сначала сохраняется в userFileContent, после чего сохраняется в файл.
     *
     * @throws Exception перехватывает возможные ошибки ввода-вывода
     */
    @Override
    public void init() throws Exception
    {
        InputContentArea.init();
        InputContentArea.setDefaultStyle();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        String title = UserFileProcessor.getUserFileName();
        if (title.isEmpty())
        {
            stage.setTitle("Безымянный");
        } else
        {
            stage.setTitle(title);
        }
        stage.setResizable(false);

        GridPane nodeRoot = new GridPane();
        nodeRoot.setHgap(10);
        nodeRoot.setVgap(10);
        Scene areaScene = new Scene(nodeRoot, AREA_SCENE_WIDTH, AREA_SCENE_HEIGHT);
        stage.setScene(areaScene);

        Button openButton = new Button("Открыть");
        Button saveButton = new Button("Сохранить");
        Button closeButton = new Button("Закрыть");

        defaultStyle(openButton, closeButton, saveButton, ProgramFormatSetter.getFormatSetterButton());

        openButton.setOnAction(actionEvent ->
        {
            try
            {
                OpenFile.open();
            } catch (IOException | InterruptedException | ExecutionException e)
            {
                throw new RuntimeException(e);
            }
        });

        closeButton.setOnAction(actionEvent ->
        {
            try
            {
                CloseFile.close();
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });

        saveButton.setOnAction(actionEvent ->
        {
            try
            {
                SaveFile.save();
            } catch (IOException | InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });

        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().addAll(openButton, closeButton, saveButton, ProgramFormatSetter.getFormatSetterButton());

        nodeRoot.add(hBox, 1, 1);
        InputContentArea.setAreaOnGridPane(nodeRoot, 1, 2);

        // Stage нам потребуется для вывода FileChooser
        mainstage = stage;

        stage.show();
    }

    public static Stage getStage()
    {
        return mainstage;
    }

    public static void defaultStyle(Button... buttons)
    {
        List<Button> list = Arrays.asList(buttons);
        Iterator<Button> iterator = list.iterator();

        while (iterator.hasNext())
        {
            Button el = iterator.next();
            el.setFont(APTOS_FONT);
            el.setFocusTraversable(false);
        }
    }
}
