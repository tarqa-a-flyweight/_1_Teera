package com.teera.startpoint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Teera
 * @version 0.1 22-03-2026
 * @author tarqa-a-flyweight
 */

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        /*
        Для начала компоновки нужно:
        - Написать абстрактные фабрики для стратегий
        - Написать работу ChunkingStrategy
        - Написать работу UnitStrategy
        - Написать работу WrapStrategy

        - Написать работу Input- и Output- Strategy
        - Написать метод подтверждения UnsaveDialogStrategy

        - Написать взаимодействие InnerScroll и TextZone

        - Написать адресацию запросов от обработчиков

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
        GridPane nodeRoot = new GridPane();

        Scene scene = new Scene(nodeRoot, 300, 300);
        stage.setScene(scene);

        stage.show();
    }
}