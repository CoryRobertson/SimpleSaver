# SimpleSaver
A java tool that helps you save information quickly and easily, so you can read it again later!
### Contributions
I love when people collaborate with me, feel free to pull request this repo when ever you like, I am open to all feedback as well!
### Use
Simply put this in your gradle file under repositories
```gradle
maven { url 'https://jitpack.io' }
```
and this under your dependencies
```gradle
implementation 'com.github.CoryRobertson:SimpleSaver:v1.1'
```
then in a class
```java
import com.github.coryrobertson.simplesaver.Save;
```
and then somewhere in your code
```java
Save<String> save = new Save<>(new String[] {"123456789", "987654321"});
save.writeToSaveFile(new File("./save.sav"));
```
