package com.neoflex.demo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataCache {
    private static final String path = "D:/Neoflex/demo/src/main/resources/NamesForBankAccounts/"; //Путь к файлам с ФИО

    public static List<String> listMansNames;
    public static List<String> listMansSurnames;
    public static List<String> listMansPatronymics;
    public static List<String> listWomansNames;
    public static List<String> listWomansSurnames;
    public static List<String> listWomansPatronymics;

    static {
        try {
            listMansNames = Files.readAllLines(Paths.get(path + "MansNames.txt"));
            listMansSurnames = Files.readAllLines(Paths.get(path + "MansSurnames.txt"));
            listMansPatronymics = Files.readAllLines(Paths.get(path + "MansPatronymics.txt"));
            listWomansNames = Files.readAllLines(Paths.get(path + "WomansNames.txt"));
            listWomansSurnames = Files.readAllLines(Paths.get(path + "WomansSurnames.txt"));
            listWomansPatronymics = Files.readAllLines(Paths.get(path + "WomansPatronymics.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
