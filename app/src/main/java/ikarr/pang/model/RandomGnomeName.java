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
            "Adan (little fire)", "Adva (Small wave)", "Aiko (Little Love)", "Aithne (Little fire)",
            "Allison (little truthful)", "Amorette (little love)", "Armida (little armed one)",
            "Belita (Little Beauty)",
            "Bonita", "Brenna", "Brooke", "Carlin",
            "Charlene", "Darra", "Demi", "Jenna",
            "Kiara", "Kierna", "Koemi", "Maleah",
            "Miette", "Miki", "Nina",
            "Piera", "Posy", "Rosine", "Rowan (Little red head)", "Shanna (Small and Wise)"
             /*
             - Pretty little one (spanish) - Little Tears (Irish) - Small stream -little champion (Gaelic)
             - Little Beauty (French) - Small great one (Gaelic) - half, small (french) - little bird (Arabic)
             - small (Celtic) - little dark haired one (Irish) - little smile (Japanese)
             - Unique little girl (Hawaiian) -Small Sweet thing (spanish) - small (Inupiaq)
             - Little girl (spanish) - Small rock (Italian) - Small flower (English) - Little Rose (Latin)
            - Solita - Little Loner (Spanish) - Tulla - A little hill (Irish) - Viveca -little woman (Scandinavian)
            - Zita - little girl (Italian) */
    };
    private final static String[] maleFirstNameList = {
            "Yves (little archer)", "Tawnie (Little One)", "Teagan (Little Poet)",
            "Whitley (Small field)", "Dolgrin", "Edrukk", "Grunyar", "Kazmuk", "Kotri", "Lupp", "Morgrym", "Rogar"
    };
    private final static String[] allFirstNameList = {
            "Agna", "Bodill", "Dolgrin", "Edrukk", "Grunyar", "Ingra", "Kazmuk", "Kotri", "Lupp", "Morgrym", "Rogar", "Rusilka", "Torra", "Yangrit"
    };
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
        while((line = br.readLine()) != null) {
            myList.add(line);
        }
        br.close();
        String[] array = new String[myList.size()];
        myList.toArray(array);
        return array;
    }

}

