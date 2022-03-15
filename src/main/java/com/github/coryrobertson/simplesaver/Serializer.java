package com.github.coryrobertson.simplesaver;

import java.io.*;

/**
 * A serializable save data wrapper with generic type support
 */
public class Serializer
{
    public Serializer() {}

    /**
     * Saves the data given in an array to file
     * @param data array of data of any time to save
     * @param <T> type of data to save
     * @param fileName name of file to be saved
     * @return true if saved properly
     */
    public static <T> boolean save(T[] data, String fileName)
    {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new SerializableSave<>(data));
            oos.close();
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static <T> boolean save(T[] data)
    {
        return save(data,"data.ser");
    }

    /**
     * Loads save data and returns it
     * @param <T> the type of data being loaded
     * @param fileName name of file to be loaded
     * @return the data in the form of a SerializeableSave
     */
    public static <T> SerializableSave<T> loadSave(String fileName)
    {
        SerializableSave<T> save;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try
        {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            save = (SerializableSave<T>) objectInputStream.readObject();


            objectInputStream.close();
            fileInputStream.close();
            return save;
        }
        catch (IOException | ClassNotFoundException | ClassCastException e)
        {
            return null;
        }

    }

    public static <T> SerializableSave<T> loadSave()
    {
        return loadSave("data.ser");
    }

    public static boolean saveExists(String fileName)
    {
        return loadSave(fileName) != null;
    }


}
