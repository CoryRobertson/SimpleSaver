import com.github.coryrobertson.simplesaver.Save;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SaverTest
{
    static String[] dataToTest = {"word1", "number1", "g", " ", "61"};
    static final String TESTFILENAME = "./test.sav";
    @Test
    @Order(1)
    void SaveTest()
    {
        Save<String> save = new Save<>(dataToTest);
        Assertions.assertTrue(save.writeToSaveFile(new File(TESTFILENAME)));
    }

    @Test
    @Order(2)
    void LoadTest()
    {
        Save<String> save = new Save<>();
        save = save.readFromSaveFile(new File(TESTFILENAME));
        String[] data = save.getData();
        for(int i = 0; i < dataToTest.length; i++)
        {
            Assertions.assertEquals(dataToTest[i], data[i]);
        }
    }


}
