package com.teera.files;

public class IOStrategyFactory
{
    public static IOStrategyFactory createFactory()
    {
        /*
            Важно! Как и в любой абстрактной фабрике,
            возвращаемый объект является объектом конкретной фабрики - потомком
            абстрактной фабрики, переопределяющий фабричные методы так,
            чтобы можно было использовать возможности реализации конкретных классов.

            Продукты фабрики (конкретные классы), а в частности InputStrategy и OutputStrategy,
            в структуре этого редактора должны являться родителями для всех
            конкретных стратегий, создаваемых в будущем.

            Для еще большей гибкости все продукты пользовательского
            интерфейса абстрактной фабрики определяются через предопределенный
            и хорошо известный встроенный класс библиотеки Java.
            К таковым относится например класс TabPane на фабрике TabZoneFactory или
            ScrollPane на фабрике ScrollFactory.
         */
        return new IOStrategyFactory();
    }

    public InputStrategy createInputStrategy()
    {
        return new InputStrategy();
    }

    public OutputStrategy createOutputStrategy()
    {
        return new OutputStrategy();
    }
}