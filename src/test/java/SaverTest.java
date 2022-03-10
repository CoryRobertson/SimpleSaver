import com.github.coryrobertson.simplesaver.Save;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SaverTest
{
    static String[] dataToTest = {"word1", "number1", "g", " ", "61idfkukhdifug"};
    static final String TESTFILENAME = "./test.sav";

    @Test
    @Order(1)
    void SaveTest()
    {
        Save save = new Save(dataToTest);
        Assertions.assertTrue(save.writeToSaveFile(new File(TESTFILENAME)));
    }

    @Test
    @Order(2)
    void LoadTest()
    {
        Save save = new Save();
        String[] data = save.readFromSaveFile(new File(TESTFILENAME));

        for(int i = 0; i < dataToTest.length; i++)
        {
            Assertions.assertEquals(dataToTest[i], data[i]);
        }
    }


}
