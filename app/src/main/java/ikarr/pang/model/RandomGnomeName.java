package ikarr.pang.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import ikarr.pang.controller.MainActivity;

public class RandomGnomeName {
    private final static int GENDERLESS = 0;
    private final static int FEMALE = 1;
    private final static int MALE = 2;
    public static final String LAST_NAMES_FILE = "gnomesLastNames.txt";

    private final static String[] femaleFirstNameList = {
            "Adan", "Adva", "Aiko", "Aithne", "Allison", "Amorette", "Armida", "Belita", "Bonita",
            "Brenna", "Brooke", "Carlin", "Charlene", "Darra", "Demi", "Jenna", "Kiara", "Kierna",
            "Koemi", "Maleah", "Miette", "Miki", "Nina", "Piera", "Posy", "Rosine", "Rowan", "Shanna",
            "Solita,", "Tulla", "Viveca", "Zita"
    };
    private final static String[] maleFirstNameList = {
            "Yves", "Tawnie", "Teagan", "Whitley"
    };

    private final static String[] allFirstNameList = Persona.mergeArrays(femaleFirstNameList, maleFirstNameList);

    private final static String[] lastNameList = {
            "Toto"
    };

    public static String[] getAllFirstNameList() {
        return allFirstNameList;
    }
    public static String[] getFemaleFirstNameList() {
        return femaleFirstNameList;
    }
    public static String[] getMaleFirstNameList() {
        return maleFirstNameList;
    }
    public static String[] getLastNameList() {
        return lastNameList;
    }

    public RandomGnomeName(Persona name) {
        if (MainActivity.firstNameLock.isOpen()) {
            switch(name.getGender()) {
                case GENDERLESS:
                    name.setFirstName(Persona.randomFromArray(getAllFirstNameList()));
                    break;
                case FEMALE:
                    name.setFirstName(Persona.randomFromArray(getFemaleFirstNameList()));
                    break;
                case MALE:
                    name.setFirstName(Persona.randomFromArray(getMaleFirstNameList()));
                    break;
                default:
                    break;
            }
        }
        if (MainActivity.lastNameLock.isOpen()) {
            name.setLastName(Persona.randomFromArray(getLastNameList()));
        }
    }

    public static String [] loadArrayFromText(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<String> myList = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            myList.add(line);
        }
        br.close();
        String[] array = new String[myList.size()];
        myList.toArray(array);
        return array;
    }

}

