package org.example.enums;

import java.util.regex.Pattern;

public enum Language {
    English("englishToRussian.csv","^[a-zA-Z]{4}$","Англо-русский"),
    Numeric("numericToRussian.csv","^[0-9]{5}$","Числово-русский");

    private String fileName;
    private Pattern pattern;
    private String cyrillicName;
    Language(String fileName,String regex,String cyrillicName){
        this.fileName = fileName;
        pattern = Pattern.compile(regex);
        this.cyrillicName = cyrillicName;
    }
    public String getFileName(){
        return fileName;
    }
    public Pattern getPattern(){
        return pattern;
    }
    public String getCyrillicName(){
        return cyrillicName;
    }

}
