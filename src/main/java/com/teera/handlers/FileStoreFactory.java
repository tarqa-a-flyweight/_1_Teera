package com.teera.handlers;

public class FileStoreFactory
{
    public static FileStoreFactory createFactory()
    {
        return new FileStoreFactory();
    }

    public FileStore createFileStore()
    {
        return new FileStore();
    }
}
