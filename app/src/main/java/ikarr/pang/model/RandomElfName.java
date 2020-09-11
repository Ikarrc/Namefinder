package ikarr.pang.model;

import ikarr.pang.controller.MainActivity;

public class RandomElfName {
    private final static int GENDERLESS = 0;
    private final static int FEMALE = 1;
    private final static int MALE = 2;

    private boolean[] locks = {true, true, true};

    private final static String[] femaleFirstNameList = {
            "Aglarond", "Aeluin", "Araval", "Arnor", "Balan", "Bril", "Carnen", "Cinodrel", "Cirdan",
            "Danbeth", "Dineth", "Elanor", "Elleth", "Elros", "Eryn", "Felagund", "Finglas", "Faunra",
            "Galadriel", "Gilthoniel", "Glanhir", "Hurin", "Harnen", "Handir", "Iorhael", "Iarwen",
            "Laegel", "Linaewen", "Minas", "Morben", "Mirin", "Nes", "Niniel", "Panthael", "Ramdal",
            "Sarnia", "Tessara", "Tyrn", "Uriel", "Variel"
    };
    private final static String[] maleFirstNameList = {
            "Amon", "Anfalas", "Angmar", "Aradan", "Barthan", "Cair", "Calen", "Celeborn", "Dimbar",
            "Emyl", "Elwyng", "Erebor", "Elrohir", "Faenor", "Fangorn", "Fingolfin", "Firion", "Galvorn",
            "Gildor", "Glorfindel", "Gorthol", "Haelevorn", "Haerast", "Huor", "Ithildin", "Legolas",
            "Laerin", "Mallorn", "Mirion", "Morgai", "Mithrim", "Nevrast", "Nogrod", "Onodio", "Orul",
            "Pelennor", "Rian", "Rauros", "Sarn", "Turin", "Uldor", "Zirion"
    };

    private final static String[] allFirstNameList = Persona.mergeArrays(femaleFirstNameList, maleFirstNameList);

    private final static String[] lastNameList = {
            "Adnair", "Aeglos", "Aegnor", "Aerandir", "Aerel", "Agarwaen", "Amunelara", "Baragud",
            "Barthan", "Borlas", "Bruinen", "Caladrel", "Calben", "Camlost", "Caradhras", "Cuthalion",
            "Dagnir", "Dagor", "Dardlara", "Duinhir", "Dunedhel", "Edegil", "Egladhrim", "Egnor", "Elfaron",
            "Finrod", "Forlindon", "Fornost", "Galadrhim", "Glamdring", "Gloredhel", "Harlindon",
            "Heldalel", "Helluin", "Himladrin", "Imladrin", "Jathal", "Lanliss", "Lomendor", "Lamthanc",
            "Mablung", "Merethrond", "Mindolluin", "Nardol", "Nivrost", "Oparal", "Osgiliath", "Parthgallen",
            "Rhudaur", "Rathdinen", "Seldlon", "Soumral", "Sirannon", "Talathel", "Thingol", "Ulfast",
            "Xanast", "Yalandlara", "Zordlon"
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

    public RandomElfName(Persona persona) {
        persona.setFirstName(persona.getFirstName());
        persona.setLastName(persona.getLastName());
        if (MainActivity.firstNameLock.isOpen()) {
            switch(persona.getGender()) {
                case GENDERLESS:
                    persona.setFirstName(Persona.randomFromArray(getAllFirstNameList()));
                    break;
                case FEMALE:
                    persona.setFirstName(Persona.randomFromArray(getFemaleFirstNameList()));
                    break;
                case MALE:
                    persona.setFirstName(Persona.randomFromArray(getMaleFirstNameList()));
                    break;
                default:
                    break;
            }
        }
        if (MainActivity.lastNameLock.isOpen()) {
            persona.setLastName(Persona.randomFromArray(getLastNameList()));
        }
    }
}
