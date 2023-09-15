package org.example.interfaces;

import org.example.enums.Language;

import java.util.SortedMap;

/**
 * @author zolobov.ea.kst
 */
public interface IDictionaryService {
    SortedMap<String, String> getAllWords(Language language);

    String getTranslation(String word, Language language);

    boolean deleteFromDictionary(String word, Language language);

    boolean addWordToDictionary(String foreignLanguage, String russianLanguage, Language language);
}
