package com.github.coryrobertson.simplesaver;

import java.io.Serializable;

/**
 * A save file used only inside the serializer
 * @param <T> type of data to be saved
 */
public class SerializableSave<T> implements Serializable
{
    private static final long serialVersionUID = 1L;
    private T[] data;
    private T dataObj;


    public SerializableSave(T[] data)
    {
        this.data = data;
    }

    public SerializableSave(T data)
    {
        this.dataObj = data;
    }

    /**
     *
     * @return save data array
     */
    public T[] getSaveData()
    {
        return this.data;
    }

    /**
     *
     * @return save data object
     */
    public T getSaveDataObj()
    {
        return this.dataObj;
    }

}
