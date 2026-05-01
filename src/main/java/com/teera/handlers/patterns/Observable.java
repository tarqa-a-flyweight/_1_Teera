package com.teera.handlers.patterns;

public interface Observable
{
    void add(Observer observer);
    void remove(Observer observer);
    void alert();
}