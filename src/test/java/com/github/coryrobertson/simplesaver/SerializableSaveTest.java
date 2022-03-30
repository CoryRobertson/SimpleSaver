package com.github.coryrobertson.simplesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

class SerializableSaveTest
{

    @Test
    @DisplayName("getSaveData")
    void getSaveData()
    {
        Integer[] ints = {5,7,9};
        SerializableSave<Integer> serializableSave = new SerializableSave<>(ints);
        Assertions.assertEquals(serializableSave.getSaveData(),ints);
    }

    @Test
    @DisplayName("getSaveDataObj")
    void getSaveDataObj()
    {
        Integer integer = 5;
        SerializableSave<Integer> serializableSave = new SerializableSave<>(integer);
        Assertions.assertEquals(serializableSave.getSaveDataObj(),5);
    }

    @Test
    @DisplayName("getDate")
    void getDate()
    {
        SerializableSave<String> serializableSave = new SerializableSave<>("this is some data to put into a save");
        Date date = new Date();
        Assertions.assertEquals(serializableSave.getDate(),date);
    }

    @Test
    @DisplayName("resetDate")
    void resetDate()
    {
        SerializableSave<String> serializableSave = new SerializableSave<>("this is some data to put into a save");
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        serializableSave.resetDate();
        Date date = new Date();

        Assertions.assertTrue(serializableSave.getDate().compareTo(date) <= 1);
    }
}