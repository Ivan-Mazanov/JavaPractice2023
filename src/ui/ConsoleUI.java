package ui;

import dict.io.FileType;
import dict.service.DictService;

import java.util.List;

//Работа с DictService и отправка сообщений клиенту
public class ConsoleUI {
    private DictService dictService;
    private String currentDictName;

    public ConsoleUI() {
        dictService = new DictService(FileType.CSV);

        dictService.initializeDict("latin", "src/dicts list/latin-dict.csv",
                k -> k.length() == 4 && k.matches("^[a-zA-Z]*$"));


        dictService.initializeDict("digit", "src/dicts list/digit-dict.csv",
                k -> k.length() == 5 && k.matches("^[0-9]*$"));
    }

    public String getNames() {
        StringBuilder names = new StringBuilder();
        for (String name : dictService.getNames()) {
            names.append(" ").append(name);
        }

        return names.toString();
    }

    public String switchSpecifiedDict(String dictName) {
        List<String> names = dictService.getNames();

        for (String name : names) {
            if (name.equals(dictName)) {
                currentDictName = dictName;
                return "Successfully switched on " + dictName;
            }
        }
        return "No dictionaries with this name";
    }

    public String getCurrentDict() {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        return dictService.getDictString(currentDictName);
    }

    public String deleteFromCurrentDict(String key) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        boolean result = dictService.deleteByKey(currentDictName, key);

        if (result)
            return "Successfully deleted";
        return "There is no such key";
    }

    public String findInCurrentDict(String key) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        String result = dictService.findByKey(currentDictName, key);
        if (result == null)
            return "There is no such key";
        return result;
    }

    public String addInCurrentDict(String key, String value) {
        if (currentDictName == null)
            return "Not switched on a specified dictionary";

        boolean result = dictService.addNewPair(currentDictName, key, value);

        if (result)
            return "Successfully added";
        return "Wrong input data";
    }

    public String saveCurrentDict() {
        dictService.saveData(currentDictName);

        return "Current dictionary was successfully saved";
    }

    public String saveAllData() {
        List<String> names = dictService.getNames();

        for (String name : names) {
            dictService.saveData(name);
        }
        return "All data was successfully saved";
    }
}
