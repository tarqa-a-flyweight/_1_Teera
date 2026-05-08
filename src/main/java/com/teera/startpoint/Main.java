package com.teera.startpoint;

import com.teera.graphics.buttons.*;
import com.teera.graphics.dialogs.*;
import com.teera.graphics.panes.TabZone;
import com.teera.graphics.panes.TabZoneFactory;
import com.teera.handlers.FileStore;
import com.teera.handlers.FileStoreFactory;
import com.teera.handlers.buttonHandlers.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Teera
 * @version 0.4 08-05-2026
 * @author tarqa-a-flyweight
 */

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        stage.setTitle("Teera");
        GridPane nodeRoot = new GridPane(10, 10);

        Scene scene = new Scene(nodeRoot, 700,
                                          450);
        stage.setScene(scene);

        ObservableButton open = new OpenButton();
        ObservableButton save = new SaveButton();
        CreateButton create = new CreateButton();
        CloseButton close = new CloseButton();
        SaveAsButton saveAs = new SaveAsButton();

        // Compose
        HBox buttonsBox = new HBox(10);
        Label sidePlaceLabel = new Label();
        buttonsBox.getChildren().addAll(sidePlaceLabel, create, open, close, save, saveAs);

        TabZoneFactory factory = TabZoneFactory.createFactory();
        TabZone tabZone = factory.createTabPane();
        tabZone.postContents("@@@");

        tabZone.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Handlers
        OpenButtonHandler openButtonHandler = new OpenButtonHandler();
        SaveButtonHandler saveButtonHandler = new SaveButtonHandler();
        CreateButtonHandler createButtonHandler = new CreateButtonHandler();
        CloseButtonHandler closeButtonHandler = new CloseButtonHandler();
        SaveAsButtonHandler saveAsButtonHandler = new SaveAsButtonHandler();

        open.add(openButtonHandler);
        save.add(saveButtonHandler);
        create.add(createButtonHandler);
        close.add(closeButtonHandler);
        saveAs.add(saveAsButtonHandler);

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

        createButtonHandler.visit(tabZone);

        closeButtonHandler.visit(tabZone);
        closeButtonHandler.visit(new UnsaveDialogStrategyFactory().createUnsaveDialog());
        closeButtonHandler.visit(filestore);

        saveAsButtonHandler.visit(saveAsDialog);
        saveAsButtonHandler.visit(tabZone);

        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);

        nodeRoot.getColumnConstraints().add(column);

        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);

        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);

        nodeRoot.getRowConstraints().addAll(new RowConstraints(), row1, row2);

        nodeRoot.add(buttonsBox, 0, 1);
        nodeRoot.add(tabZone, 0, 2);
        stage.show();
    }
}