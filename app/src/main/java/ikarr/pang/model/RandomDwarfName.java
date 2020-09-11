package ikarr.pang.model;

import ikarr.pang.controller.MainActivity;

public class RandomDwarfName {
    private final static int GENDERLESS = 0;
    private final static int FEMALE = 1;
    private final static int MALE = 2;

    private final static String[] femaleFirstNameList = {
            "Agna", "Astri", "Bari", "Beli", "Bodill", "Druria", "Dori", "Dwina", "Elli", "Etri",
            "Farrondi", "Grukka", "Hadria", "Hera", "Haki", "Heid", "Iari", "Idi", "Ingra", "Kazmuk",
            "Kotri", "Ketria", "Lupp", "Moin", "Nali", "Nalria", "Rusilka", "Torra", "Yangrit"
    };
    private final static String[] maleFirstNameList = {
            "Anar", "Andvar",  "Balin", "Bifur", "Bildur", "Bilin", "Blain", "Bogi", "Bofur",
            "Bombur", "Borin", "Bor", "Bori", "Bragi", "Brimir", "Bruni", "Bur", "Buri", "Burin", "Dagir",
            "Dain", "Dufur", "Durin", "Durni", "Dwalin", "Falar", "Farin", "Fili", "Finar", "Floi",
            "Forgun", "Frar", "Frer", "Frerin", "Fundin", "Galar", "Gilin","Gimli",
            "Ginar", "Gladur", "Gloin", "Grani", "Groin", "Gror","Gumir", "Hepti",
            "Himur", "Hodur", "Honir", "Hornbor", "Ilmir", "Kili", "Kona", "Litir", "Lodun", "Lodur",
            "Lofar", "Lofi", "Lofun", "Logi", "Loni","Mani", "Maro", "Narg", "Narvi", "Nidi", "Nipin",
            "Niping","Nodi", "Nodri", "Noi", "Nori", "Nyrad", "Oin", "Onar", "Ori","Otur", "Pori",
            "Regin", "Sigun", "Sindri", "Skadi", "Skirfir", "Sudri", "Sumar", "Thegur", "Thorin",
            "Thrain", "Thror", "Thrumir", "Vali", "Vani", "Vedir", "Veig", "Vestri", "Vidar", "Vili",
            "Vimur", "Virfir", "Vitir", "Ymir", "Yngvi", "Argnar", "Bodill", "Bofin", "Dolgrin", "Drukon",
            "Edrukk", "Fargin", "Grunyar", "Kazmuk", "Kili", "Kotri", "Lupp", "Morgrym", "Nalbr",
            "Ovak", "Ragzad", "", "Simond", "Thorrin"
    };

    private final static String[] allFirstNameList = Persona.mergeArrays(femaleFirstNameList, maleFirstNameList);

    private final static String[] lastNameList = {
            "Bolragnir", "Gildaymin", "Rundalnim", "Frorvarrak", "Rogarnor", "Griturdong", "Hanargrilin"

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

    public RandomDwarfName(Persona name) {
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
