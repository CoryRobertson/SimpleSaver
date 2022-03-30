# SimpleSaver
A java tool that helps you simpleSave information quickly and easily, so you can read it again later!
### Contributions
I love when people collaborate with me, feel free to pull request this repo when ever you like, I am open to all feedback as well!
### Use
Simply put this in your gradle file under repositories
```gradle
maven { url 'https://jitpack.io' }
```
and this under your dependencies
```gradle
implementation 'com.github.CoryRobertson:SimpleSaver:v1.4'
```
To use the simple plaintext String saver:
```java
import com.github.coryrobertson.simplesaver.*;

String[] stringSave = new String[] {"123456789", "987654321"};
SimpleSave simpleSave = new SimpleSave(stringSave);
simpleSave.writeToSaveFile(new File("./simpleSave.sav"));

String[] data = simpleSave.readFromSaveFile(new File("./simpleSave.sav"));
```
To use the serialized saver:
```java
import com.github.coryrobertson.simplesaver.*;

Double[] doubles = {1.1, 5.6};
Serializer.save(doubles, "./fileName.ser");

SerializableSave<Double> doubleSave = Serializer.loadSave("./fileName.ser");
Double[] doubleData = doubleSave.getSaveData();
```
