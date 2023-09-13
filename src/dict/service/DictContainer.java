package dict.service;

import dict.model.Dictionary;

import java.util.ArrayList;
import java.util.List;

//Контейнер для хранения словарей
class DictContainer {
    private static List<Dictionary> content = new ArrayList<>();

    public boolean addDict(Dictionary dictionary) {
        if (checkIfNameNotExist(dictionary.getDictionaryName())) {
            content.add(dictionary);
            return true;
        }
        return false;
    }

    public Dictionary GetDict(String dictName) {
        for (Dictionary dictionary : content) {

            if (dictName.equals(dictionary.getDictionaryName()))
                return dictionary;

        }
        return null;
    }

    public List<Dictionary> getCopyOfContent() {

        return List.copyOf(content);
    }

    private boolean checkIfNameNotExist(String newName) {
        for (Dictionary dictionary : content) {
            if (newName.equals(dictionary.getDictionaryName()))
                return false;
        }
        return true;
    }
}
