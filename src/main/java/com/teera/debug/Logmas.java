package com.teera.debug;

import java.util.logging.*;

public class Logmas extends ConsoleHandler
{
    public static final Logger LOGGER = init();

    private static Logger init()
    {
        Logmas logmas = new Logmas();
        logmas.setLevel(Level.ALL);

        Logger logger = Logger.getLogger(Logmas.class.getName());
        logger.setUseParentHandlers(false);

        logger.setLevel(Level.ALL);
        logger.addHandler(logmas);
        return logger;
    }

    @Override
    public void publish(LogRecord record)
    {
        System.err.println(record.getSourceMethodName() + " \"" + record.getLevel() + "\"" +
                ": " + record.getMessage());
    }
}