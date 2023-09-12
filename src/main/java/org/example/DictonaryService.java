package org.example;

import javax.xml.stream.events.Characters;
import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.regex.Matcher;

public class DictonaryService implements IDictonaryService {
    private final String BASE_PATH = "/src/main/resources/dictionaries/";
    private final String CSV_COLUMNS_DELIMITER = ";";
    private final String CSV_ROWS_DELIMITER = "\n";

    @Override
    public SortedMap<String, String> getAllWords(Language language) {
        var file = getDictianaryFileByLanguage(language);
        if (!file.exists()) {
            System.err.println("Файл не найден");
            return new TreeMap<>();
        }
        var result = new TreeMap<String, String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                var csvColumns = line.split(CSV_COLUMNS_DELIMITER);
                result.put(csvColumns[0], csvColumns[1]);
            }

        } catch (Exception exception) {
            System.err.println("Ошибка чтения файла");
            return new TreeMap<>();
        }
        return result;
    }

    @Override
    public String getTranslation(String word, Language language) {
        var words = getAllWords(language);
        var result = words.get(word);
        return result;
    }

    @Override
    public void deleteFromDictionary(String word, Language language) {
        var words = getAllWords(language);
        if (words.get(word) != null) {
            words.remove(word);
            var file = getDictianaryFileByLanguage(language);
            writeMapToFile(words, file);
        }
    }

    @Override
    public boolean addWordToDictionary(String foreignLanguage, String russianLanguage, Language language) {

        if (!isRespondRestrictions(foreignLanguage, language)) {
            System.err.println("Слово не соответствует ограничениям");
            return false;
        }
        var folderPath = createFolderIfNotExists(BASE_PATH);
        File file = Path.of(folderPath, language.getFileName()).toFile();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception exception) {
            System.err.println("Ошибка создания файла");
        }
        var words = getAllWords(language);
        if ((words.get(foreignLanguage)) != null) {
            words.replace(foreignLanguage, russianLanguage);
        } else {
            words.put(foreignLanguage, russianLanguage);
        }
        return writeMapToFile(words, file);
    }

    private File getDictianaryFileByLanguage(Language language) {
        var filePath = Path.of(new File("").getAbsolutePath(), BASE_PATH, language.getFileName());
        return filePath.toFile();
    }

    private String createFolderIfNotExists(String basePath) {
        var folderPath = Path.of(new File("").getAbsolutePath(), basePath);
        var folder = folderPath.toFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath.toString();
    }


    private boolean writeMapToFile(Map<String, String> map, File file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            map.forEach((k, v) -> {
                var record = String.join(CSV_COLUMNS_DELIMITER, k, v + CSV_ROWS_DELIMITER);
                try {
                    bw.write(record);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            return true;
        } catch (Exception ex) {
            System.err.println("Ошибка записи в файл");
            return false;
        }
    }

    private boolean isRespondRestrictions(String str, Language language) {
        Matcher matcher = language.getPattern().matcher(str);
        return matcher.matches();
    }


}
