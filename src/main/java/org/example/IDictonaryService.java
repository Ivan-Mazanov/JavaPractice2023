package org.example;

import java.util.SortedMap;

public interface IDictonaryService {
    SortedMap<String, String> getAllWords(Language language);

    String getTranslation(String word, Language language);

    void deleteFromDictionary(String word, Language language);

    boolean addWordToDictionary(String foreignLanguage, String russianLanguage, Language language);
}
