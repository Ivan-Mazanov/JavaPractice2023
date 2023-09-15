package org.example;

import org.example.interfaces.IUIService;
import org.example.services.UIService;

/**
 * @author zolobov.ea.kst
 */
public class Main {
    private final static IUIService uiService = new UIService();

    /**
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        uiService.startSession();
    }
}