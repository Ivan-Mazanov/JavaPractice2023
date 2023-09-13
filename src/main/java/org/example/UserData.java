package org.example;

import org.example.enums.Language;

public class UserData {
    private static Language selectedLanguage = Language.English;

    public static Language getSelectedLanguage(){
        return selectedLanguage;
    }
    public static void setSelectedLanguage(Language language){
        selectedLanguage = language;
    }
}
