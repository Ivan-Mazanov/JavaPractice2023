package org.example.enums;

import java.util.regex.Pattern;

/**
 * @author zolobov.ea.kst
 */
public enum Language {
    ENGLISH("englishToRussian.csv", "^[a-zA-Z]{4}$", "Англо-русский"),
    NUMERIC("numericToRussian.csv", "^[0-9]{5}$", "Числово-русский");

    private final String fileName;
    private final Pattern pattern;
    private final String cyrillicName;

    Language(String fileName, String regex, String cyrillicName) {
        this.fileName = fileName;
        pattern = Pattern.compile(regex);
        this.cyrillicName = cyrillicName;
    }

    public String getFileName() {
        return fileName;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getCyrillicName() {
        return cyrillicName;
    }

}
