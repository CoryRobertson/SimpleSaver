package com.github.coryrobertson.simplesaver;

import java.io.Serializable;

/**
 * A save file used only inside the serializer
 * @param <T>
 */
public class SerializableSave<T> implements Serializable
{
    private static final long serialVersionUID = 1L;
    private T[] data;

    public SerializableSave(T[] data)
    {
        this.data = data;
    }

    public T[] getSaveData()
    {
        return this.data;
    }
}