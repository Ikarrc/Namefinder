package ikarr.pang.model;

import ikarr.pang.controller.MainActivity;

public class RandomElfName {
    private final static int GENDERLESS = 0;
    private final static int FEMALE = 1;
    private final static int MALE = 2;

    private boolean[] locks = {true, true, true};

    private final static String[] femaleFirstNameList = {
            "Agna", "Bodill", "Ingra", "Kazmuk", "Kotri", "Lupp", "Rusilka", "Torra", "Yangrit"
    };
    private final static String[] maleFirstNameList = {
            "Argnar", "Bodill", "Dolgrin", "Edrukk", "Grunyar", "Kazmuk", "Kotri", "Lupp", "Morgrym", "Rogar"
    };
    private final static String[] allFirstNameList = {
            "Agna", "Bodill", "Dolgrin", "Edrukk", "Grunyar", "Ingra", "Kazmuk", "Kotri", "Lupp", "Morgrym", "Rogar", "Rusilka", "Torra", "Yangrit"
    };
    private final static String[] lastNameList = {
            "Aerel", "Amrunelara", "Caladrel", "Dardlara", "Faunra", "Heldalel", "Jathal", "Lanliss", "Oparal", "Seldlon", "Soumral", "Talathel", "Tessara", "Variel", "Yalandlara", "Zordlon"
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

    public RandomElfName(Persona name) {
        name.setFirstName(name.getFirstName());
        name.setLastName(name.getLastName());
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
}
