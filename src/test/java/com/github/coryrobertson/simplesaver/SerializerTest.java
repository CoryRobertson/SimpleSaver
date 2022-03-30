package com.github.coryrobertson.simplesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

class SerializerTest {

    private final Integer[] NUMS = {1,2,3,4};
    private final String[] STRINGS = {"word 3", "kjhgasd", "__++__", "%%6498dsadsa"};
    private final Double[] DOUBLES = {5.1,6.8,7.2,1.1,9.00001,800081.5687};

    public static SerializableSave serializableSave;

    @Test
    @DisplayName("saveAndLoadTestWithIntegers")
    void saveAndLoadTestWithIntegers()
    {
        String fileName = "./saves/nums.ser";
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
    @DisplayName("saveAndLoadTestWithStrings")
    void saveAndLoadTestWithStrings()
    {
        String fileName = "./saves/strings.ser";
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
    @DisplayName("saveAndLoadWithNoFileName")
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
    @DisplayName("testThrownError")
    void testThrownError()
    {
        Assertions.assertNull(Serializer.loadSave("not existing file save name"));
    }

    @Test
    @DisplayName("saveExists")
    void saveExists()
    {
        File file = new File("./saves/nums.ser");
        if(file.exists())
            Assertions.assertTrue(Serializer.saveExists("./saves/nums.ser"));
        else
            Assertions.assertFalse(Serializer.saveExists("./saves/nums.ser"));

    }

    @Test
    @DisplayName("READMETestUsage")
    void READMETestUsage()
    {
        Double[] doubles = {1.1, 5.6};
        Serializer.save(doubles, "./saves/fileName.ser");

        SerializableSave<Double> doubleSave = Serializer.loadSave("./saves/fileName.ser");
        assert doubleSave != null;
        Double[] doubleData = doubleSave.getSaveData();

        //all above code goes into the readme
        for(int i = 0; i < doubleData.length; i++)
        {
            Assertions.assertEquals(doubles[i], doubleData[i]);
        }
    }

    @Test
    @DisplayName("testSaveExists")
    void testSaveExists()
    {
        File file = new File("./saves/data.ser");
        if(file.exists())
            Assertions.assertTrue(Serializer.saveExists());
        else
            Assertions.assertFalse(Serializer.saveExists());
    }


    @Test
    @DisplayName("saveAndLoadSingleObjectTest")
    void saveAndLoadSingleObjectTest()
    {
        String data = "this is a single string object";
        Assertions.assertTrue(Serializer.save(data,"./saves/singleObject.ser"));

        SerializableSave<String> singleObjectSave = Serializer.loadSave("./saves/singleObject.ser");
        assert singleObjectSave != null;
        Assertions.assertEquals(singleObjectSave.getSaveDataObj(),data);
    }

    final String stringData = "this is some test sample data we are going to send over the internet";


    @Test
    @DisplayName("sendAndReceiveSaveSingleObjectOverNetwork")
    void sendAndReceiveSaveSingleObject()
    {
        int port = 5000;
        String host = "localhost";
        SerializableSave save;

        Thread t1 = new Thread(() -> SerializerTest.serializableSave = Serializer.receiveSave(port));
        t1.start();

        Thread t2 = new Thread(() -> Assertions.assertTrue(Serializer.sendSave(stringData,host,port)));
        t2.start();

        try
        {
            t2.join();
            t1.join();
        }
        catch (InterruptedException e)
        {
            Assertions.fail();
        }


        save = SerializerTest.serializableSave;

        Assertions.assertEquals(stringData,save.getSaveDataObj()); // check save data for errors
    }

    final String[] stringDataArray = {"this is an array!", "hopefully all of these tests pass?!?", "especially this one."};

    @Test
    @DisplayName("sendAndReceiveSaveMultiObjectOverNetwork")
    void sendAndReceiveSaveMultiObject()
    {
        int port = 5001;
        String host = "localhost";
        SerializableSave save;

        Thread t3 = new Thread(() -> SerializerTest.serializableSave = Serializer.receiveSave(port));
        t3.start();

        Thread t4 = new Thread(() -> Assertions.assertTrue(Serializer.sendSave(stringDataArray,host,port)));
        t4.start();

        try
        {
            t4.join();
            t3.join();
        }
        catch (InterruptedException e)
        {
            Assertions.fail();
        }


        save = SerializerTest.serializableSave;

        for(int i = 0; i < stringDataArray.length; i++)
        {
            Assertions.assertEquals(stringDataArray[i], save.getSaveData()[i]);
        }
    }
}