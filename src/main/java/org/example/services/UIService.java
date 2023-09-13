package org.example.services;

import org.example.enums.Language;
import org.example.UserData;
import org.example.interfaces.IDictonaryService;
import org.example.interfaces.IUIService;

import java.util.Scanner;

public class UIService implements IUIService {
    private final IDictonaryService dictonaryService = new DictonaryService();


    @Override
    public void startSession(){
        boolean programRunning = true;


        while (programRunning) {
            renderMenu();
            int menuItem = Integer.MAX_VALUE;
            try {
                var str = readLine();
                if (str == null) {
                    continue;
                } else {
                    menuItem = Integer.parseInt(str);
                }
            } catch (Exception ex) {
                   System.err.println("Ошибка выбора пункта меню");
                   continue;
            }
            switch (menuItem) {
                case 1:
                    changeLanguage();
                    break;
                case 2:
                    getDictionary();
                    break;
                case 3:
                    getWord();
                    break;
                case 4:
                    addWord();
                    break;
                case 5:
                    deleteWord();
                    break;
                case 0:
                    programRunning = false;
                    break;
                default:
                    System.err.println("Пункт меню не найден");
                    break;

            }


        }
    }
    private  void renderMenu() {

        System.out.println("\nМеню:");
        System.out.println("Текущий выбранный язык - " + UserData.getSelectedLanguage());
        System.out.println("1. Выбор языка словаря");
        System.out.println("2. Просмотр словаря:");
        System.out.println("3. Поиск слова:");
        System.out.println("4. Добавление слова:");
        System.out.println("5. Удаление слова:");
        System.out.println("0. Выход\n");
        System.out.print("Выберите пункт меню: ");

    }

    private   void changeLanguage() {
        System.out.println("\n1. Выбор языка словаря");
        System.out.println("Доступные языки:\n");
        var languages = Language.values();
        for (int i = 0; i < languages.length; i++) {
            System.out.printf("%s. %s\n", i, languages[i].getCyrillicName());
        }
        System.out.print("Введите значение: ");
        int languageNum = Integer.MAX_VALUE;
        try {
            var str = readLine();
            if (str == null) {
                return;
            }
            languageNum = Integer.parseInt(str);
        } catch (Exception ex) {
             System.err.println("Ошибка выбора языка");
             return;
        }
        if(languageNum < languages.length && languageNum >= 0) {
            UserData.setSelectedLanguage(languages[languageNum]);
        }
        else
        {
            System.err.println("Выбран несуществующий язык словаря. Попробуйте ещё раз.\n");
            changeLanguage();
        }
    }

    private   void getDictionary() {
        System.out.println("\n2. Просмотр словаря:");
        var dictionary = dictonaryService.getAllWords(UserData.getSelectedLanguage());
        dictionary.forEach((k, v) -> {
            System.out.printf("%s - %s\n", k, v);
        });
    }

    private   void getWord() {
        System.out.println("\n3. Поиск слова:");
        System.out.print("Введите слово: ");

        var str = readLine();
        if (str == null) {
            return;
        }

        var word = dictonaryService.getTranslation(str, UserData.getSelectedLanguage());
        if (word == null) {
            System.out.println("Слово не найдено");
        } else {
            System.out.printf("Перевод слова %s: %s\n", str, word);
        }

    }

    private   void addWord() {
        System.out.println("\n4. Добавление слова:");
        System.out.print("Введите слово: ");

        var foreignLanguage = readLine();
        if (foreignLanguage == null) {
            return;
        }

        System.out.print("Введите его перевод: ");
        String russianTranslation = readLine();
        if (russianTranslation == null) {
            return;
        }
        dictonaryService.addWordToDictionary(foreignLanguage, russianTranslation, UserData.getSelectedLanguage());

    }

    private   void deleteWord() {
        System.out.println("\n5. Удаление слова:");
        System.out.print("Введите удаляемое слово: ");
        var str = readLine();
        if (str == null) {
           return;
        }
       if(dictonaryService.deleteFromDictionary(str, UserData.getSelectedLanguage())) {
           System.out.println("Слово удалено");
       }
       else {
           System.err.println("Слово не найдено");
       }
    }

    private  String readLine() {
        Scanner scanner = new Scanner(System.in);
        var str = scanner.next();
        if (str != null && !str.isBlank()) {
            return str;
        } else return null;
    }
}
