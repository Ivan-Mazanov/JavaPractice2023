package org.example;

public class UserData {
    private static Language selectedLanguage = Language.English;

    public static Language getSelectedLanguage(){
        return selectedLanguage;
    }
    public static void setSelectedLanguage(Language language){
        selectedLanguage = language;
    }
}
