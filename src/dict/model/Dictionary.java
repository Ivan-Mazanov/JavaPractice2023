package dict.model;

import java.util.HashMap;
import java.util.function.Predicate;

//Хранение данных словаря и методы работы с этими данными
public class Dictionary {
    private String dictionaryName;
    private String filePath;
    private HashMap<String, String> content;

    //Валидация новых значений при помощи Predicate
    private Predicate<String> validationFunc;

    public Dictionary(String dictionaryName, String filePath, HashMap<String, String> content,
                      Predicate<String> validationFunc) {
        this.dictionaryName = dictionaryName;
        this.filePath = filePath;
        this.content = content;
        this.validationFunc = validationFunc;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public String getFilePath() {
        return filePath;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public boolean deleteByKey(String key) {
        var result = content.remove(key);

        return result != null;
    }

    public String findByKey(String key) {
        return content.get(key);
    }

    public boolean addPair(String key, String value) {
        if (validationFunc.test(key)) {
            content.put(key, value);
            return true;
        }
        return false;
    }
}
