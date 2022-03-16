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
     * @param data array of data of any type to save
     * @param <T> type of data to save
     * @param fileName name of file or path to be saved
     * @return true if saved properly
     */
    public static <T> boolean save(T[] data, String fileName)
    {
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new SerializableSave<>(data));
            oos.close();
            fos.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            File file = new File(fileName);
            if (file.getParentFile().mkdirs())
            {
                return save(data,fileName);
            }
            else {return false;}
        }
        catch (IOException e) {return false;}
    }

    public static <T> boolean save(T[] data)
    {
        return save(data,"saves/data.ser");
    }

    /**
     * Saves the data as a single object to a file
     * @param data object of data of any type to save
     * @param fileName name of file or path to file to save
     * @param <T> type of data to save
     * @return true if saved properly
     */
    public static <T> boolean save(T data, String fileName)
    {
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new SerializableSave<>(data));
            oos.close();
            fos.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            File file = new File(fileName);
            if (file.mkdirs())
            {
                return save(data,fileName);
            }
            else {return false;}
        }
        catch (IOException e) {return false;}
    }

    /**
     * Loads save data and returns it
     * @param <T> the type of data being loaded
     * @param fileName name of file to be loaded
     * @return the data in the form of a SerializeableSave, null if file does not exist, use saveExists() to check first!
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
        return loadSave("saves/data.ser");
    }

    /**
     *
     * @param fileName path to file
     * @return true if the save exists and can be loaded properly
     */
    public static boolean saveExists(String fileName)
    {
        return loadSave(fileName) != null;
    }

    public static boolean saveExists()
    {
        return loadSave() != null;
    }


}
