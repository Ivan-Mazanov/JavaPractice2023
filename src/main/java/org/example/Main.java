package org.example;

import org.example.interfaces.IUIService;
import org.example.services.UIService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private final static IUIService uiService = new UIService();

    public static void main(String[] args) {
        uiService.startSession();
    }
}