package com.github.coryrobertson.simplesaver;

import java.io.Serializable;
import java.util.Date;

/**
 * A save file used only inside the serializer
 * @param <T> type of data to be saved
 */
public class SerializableSave<T> implements Serializable
{
    private static final long serialVersionUID = 1L;
    private T[] data;
    private T dataObj;
    private Date date;

    public SerializableSave(T[] data)
    {
        this.data = data;
        date = new Date();

    }

    public SerializableSave(T data)
    {
        this.dataObj = data;
        date = new Date();

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

    /**
     *
     * @return the java.util.Date() object created at the time of saving
     */
    public Date getDate()
    {
        return this.date;
    }

    /**
     * Resets the date object attached to this save
     */
    public void resetDate()
    {
        this.date = new Date();
    }

}
