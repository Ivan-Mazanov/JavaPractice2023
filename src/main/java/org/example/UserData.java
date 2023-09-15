package org.example;

import org.example.enums.Language;
/**
 * @author zolobov.ea.kst
 */
public class UserData {
    private static Language selectedLanguage = Language.ENGLISH;

    public static Language getSelectedLanguage() {
        return selectedLanguage;
    }

    public static void setSelectedLanguage(Language language) {
        selectedLanguage = language;
    }
}
