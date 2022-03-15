package com.github.coryrobertson.simplesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class SerializerTest {

    private final Integer[] NUMS = {1,2,3,4};
    private final String[] STRINGS = {"word 3", "kjhgasd", "__++__", "%%6498dsadsa"};
    private final Double[] DOUBLES = {5.1,6.8,7.2,1.1,9.00001,800081.5687};

    @Test
    void saveAndLoadTestWithIntegers()
    {
        String fileName = "nums.ser";
        Assertions.assertTrue(Serializer.save(NUMS, fileName));


        SerializableSave<Integer> numsSave = Serializer.loadSave(fileName);
        assert numsSave != null;
        Integer[] numsData = numsSave.getSaveData();
        for(int i = 0; i < numsData.length; i++)
        {
            Assertions.assertEquals(numsData[i], NUMS[i]);
        }
    }

    @Test
    void saveAndLoadTestWithStrings()
    {
        String fileName = "strings.ser";
        Assertions.assertTrue(Serializer.save(STRINGS,fileName));

        SerializableSave<String> stringsSave = Serializer.loadSave(fileName);
        assert stringsSave != null;
        String[] stringsData = stringsSave.getSaveData();
        for(int i = 0; i < stringsData.length; i++)
        {
            Assertions.assertEquals(stringsData[i], STRINGS[i]);
        }
    }

    @Test
    void saveAndLoadWithNoFileName()
    {
        Assertions.assertTrue(Serializer.save(DOUBLES));

        SerializableSave<Double> doubleSave = Serializer.loadSave();
        Double[] doubleData = doubleSave.getSaveData();
        for(int i = 0; i < doubleData.length; i++)
        {
            Assertions.assertEquals(doubleData[i],DOUBLES[i]);
        }
    }

    @Test
    void testThrownError()
    {
        Assertions.assertNull(Serializer.loadSave("not existing file save name"));
    }

    @Test
    void saveExists()
    {
        File file = new File("nums.ser");
        if(file.exists())
            Assertions.assertTrue(Serializer.saveExists("nums.ser"));
        else
            Assertions.assertFalse(Serializer.saveExists("nums.ser"));

    }
}