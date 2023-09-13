package dict.io;

import dict.model.Dictionary;

import java.util.HashMap;

public interface FileIO {
    HashMap<String, String> getDataFromFile(String filePath);

    void loadDataInFile(Dictionary dictionary);
}
