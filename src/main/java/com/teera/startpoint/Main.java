package com.teera.startpoint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Teera
 * @author tarqa-a-flyweight
 * @version 0.1
 */

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        stage.setTitle("Teera");
        GridPane nodeRoot = new GridPane();

        Scene scene = new Scene(nodeRoot, 300, 300);
        stage.setScene(scene);

        stage.show();
    }
}
