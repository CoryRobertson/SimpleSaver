package com.github.coryrobertson.simplesaver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SaverTest
{
    final String[] dataToTest = {"word12", "number1", "g", " ", "61idfkukhdifug"};
    final String TESTFILENAME = "./saves/test.sav";


    @Test
    void saveAndLoadTest()
    {
        SimpleSave simpleSave1 = new SimpleSave(dataToTest);
        Assertions.assertTrue(simpleSave1.writeToSaveFile(new File(TESTFILENAME)));

        SimpleSave simpleSave2 = new SimpleSave();

        String[] data = simpleSave2.readFromSaveFile(new File(TESTFILENAME));

        for(int i = 0; i < dataToTest.length; i++)
        {
            Assertions.assertEquals(dataToTest[i], data[i]);

        }
    }

    @Test
    void READMETest()
    {
        String[] stringSave = new String[] {"123456789", "987654321"};
        SimpleSave simpleSave = new SimpleSave(stringSave);
        simpleSave.writeToSaveFile(new File("./saves/simpleSave.sav"));

        String[] data = simpleSave.readFromSaveFile(new File("./saves/simpleSave.sav"));

        //all above code goes into the readme
        for(int i = 0; i < data.length; i++)
        {
            Assertions.assertEquals(data[i], stringSave[i]);
        }
    }


}
