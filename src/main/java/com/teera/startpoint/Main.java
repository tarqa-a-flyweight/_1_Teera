package com.teera.startpoint;

import com.teera.graphics.buttons.ObservableButton;
import com.teera.graphics.buttons.OpenButton;
import com.teera.graphics.buttons.SaveButton;
import com.teera.graphics.dialogs.*;
import com.teera.graphics.panes.TabZone;
import com.teera.graphics.panes.TabZoneFactory;
import com.teera.handlers.FileStore;
import com.teera.handlers.FileStoreFactory;
import com.teera.handlers.buttonHandlers.OpenButtonHandler;
import com.teera.handlers.buttonHandlers.SaveButtonHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Teera
 * @version 0.2 03-04-2026
 * @author tarqa-a-flyweight
 */

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        /*

        После компоновки:
        - *Отладить программу (должна полностью соответствовать требованиям)
        - Почистить код от сторонних файлов (и неиспользуемых пакетов)
        - Отметить версию как 1.0 (стабильная)

        - Обернуть программу в JAR
        - Запустить редактор как настольное приложение (должна запускаться для Java 17)
        - Повторно отладить как настольное приложение (оптимизация, переносимость)

        - Написать README.md для запуска как настольное приложение
            (пояснение, что учебный проект для закрепления материала книг
            Шилдта, Хорстаммна (1 том, начало второго тома), а также паттернов GoF)
        - Опубликовать проект на GitHub
        - Отредактировать конспект по проекту (особенно о применении паттернов,
        обобщений, потоков ввода-вывода, диалоговых окон и панели вкладок)

        Дополнительно (но не обязательно):
        - Запустить проект для Java 11 и ниже
        - Добавить другие графические компоненты: списки, таблицы, графики
        - Добавить новые оформления редактора
        - Добавить поддержку английского и французского
         */

        stage.setTitle("Teera");
        stage.setResizable(false);
        GridPane nodeRoot = new GridPane(10, 10);

        Scene scene = new Scene(nodeRoot, 700, 450);
        stage.setScene(scene);

        ObservableButton open = new OpenButton();
        ObservableButton save = new SaveButton();

        // Compose
        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(open, save);

        TabZoneFactory factory = TabZoneFactory.createFactory();
        TabZone tabZone = factory.createTabPane();
        tabZone.postContents("@@@");

        // Handlers
        OpenButtonHandler openButtonHandler = new OpenButtonHandler();
        SaveButtonHandler saveButtonHandler = new SaveButtonHandler();
        open.add(openButtonHandler);
        save.add(saveButtonHandler);

        FileStoreFactory fileStoreFactory = FileStoreFactory.createFactory();
        FileStore filestore = fileStoreFactory.createFileStore();

        DialogStrategyFactory dialogFactory = DialogStrategyFactory.createFactory();
        OpenDialogStrategy openDialog = dialogFactory.createOpenDialog(stage);
        SaveAsDialogStrategy saveAsDialog = dialogFactory.createSaveAsDialog(stage);

        openButtonHandler.visit(tabZone);
        openButtonHandler.visit(filestore);
        openButtonHandler.visit(openDialog);

        saveButtonHandler.visit(tabZone);
        saveButtonHandler.visit(filestore);
        saveButtonHandler.visit(saveAsDialog);

        nodeRoot.add(buttonsBox, 0, 0);
        nodeRoot.add(tabZone, 0, 1);
        stage.show();
    }
}