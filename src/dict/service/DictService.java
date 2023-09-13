package dict.service;

import dict.io.CsvFileIO;
import dict.io.FileIO;
import dict.io.FileType;
import dict.model.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import static dict.service.Converter.getStringFromHashMap;

//Сервис для работы со словарем. К нему обращается UI
public class DictService {
    private FileIO fIleIO;
    private DictContainer dictContainer = new DictContainer();


    public DictService(FileType fileType) {
        switch (fileType) {
            case CSV -> fIleIO = new CsvFileIO();
        }
    }

    public boolean initializeDict(String dictName, String filePath, Predicate<String> validationFunc) {
        HashMap<String, String> hashMap = fIleIO.getDataFromFile(filePath);

        Dictionary dictionary = new Dictionary(dictName, filePath, hashMap, validationFunc);

        return dictContainer.addDict(dictionary);
    }

    public List<String> getNames() {
        List<String> nameList = new ArrayList<>();

        for (Dictionary dictionary : dictContainer.getCopyOfContent()) {

            nameList.add(dictionary.getDictionaryName());
        }
        return nameList;
    }

    public boolean addNewPair(String dictName, String key, String value) {
        if (key == null)
            return false;
        return dictContainer.GetDict(dictName).addPair(key, value);
    }

    public String getDictString(String dictName) {
        var dict = dictContainer.GetDict(dictName).getContent();

        return getStringFromHashMap(dict, ":", System.lineSeparator());
    }

    public String findByKey(String dictName, String key) {
        return dictContainer.GetDict(dictName).findByKey(key);
    }

    public boolean deleteByKey(String dictName, String key) {
        return dictContainer.GetDict(dictName).deleteByKey(key);
    }

    public void saveData(String dictName) {
        fIleIO.loadDataInFile(dictContainer.GetDict(dictName));
    }
}
