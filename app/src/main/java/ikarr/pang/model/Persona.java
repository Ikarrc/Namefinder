package ikarr.pang.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Persona {
    protected int gender;
    protected String ancestry;
    protected String firstName;
    protected String lastName;
    private final static String[] gendersList = { "genderless", "female", "male" };
    private final static String[] ancestriesList = {
            "Dwarf", "Elf", "Gnome", "Goblin", "Halfling"/*, "Hobgoblin", "Human", "Leshee", "Lizardfolk", "Orc"*/
    };
    /* protected String role = "Warrior";
    private final static String[] roleList = {
            "Warrior", "Ranger", "Champion", "Wizard", "Rogue", "Bard", "Cleric", "Druid"
    }; */

    public static String[] getAncestriesList() {
        return ancestriesList;
    }

    public static String randomFromArray(String[] list) {
        int rand = (int) (Math.random() * (list.length));
        return list[rand];
    }

    public static String[] mergeArrays(String[] arrayOne, String[] arrayTwo) {
        List<String> listOne = Arrays.asList(arrayOne);
        List<String> listTwo = Arrays.asList(arrayTwo);
        List<String> mergedList = new ArrayList<>(listTwo);
        mergedList.removeAll(listOne);
        mergedList.addAll(listOne);
        String [] mergedArray = new String[mergedList.size()];
        mergedArray = mergedList.toArray(mergedArray);
        return mergedArray;
    }

    public static String randomAncestry() {
        return randomFromArray(ancestriesList);
    }

    public Persona(int gender, String ancestry) {
        this.gender = gender;
        this.ancestry = ancestry;
    }

    public Persona(int gender, String ancestry, String firstName, String lastName) {
        this.gender = gender;
        this.ancestry = ancestry;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAncestry() {
        return ancestry;
    }
    public void setAncestry(String ancestry) {
        List<String> list = Arrays.asList(ancestriesList);
        if(list.contains(ancestry)){
            this.ancestry = ancestry;
        }
    }

    public int getAncestryIndex() {
        for (int i = 0; i < ancestriesList.length; i++) {
            if (this.ancestry.equals(ancestriesList[i])) {
                return i;
            }
        }
        return -1;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /* public String getRole() { return role; }
    public void setRole(String role) { this.role = role; } */

    public boolean equals(Persona npc) {
        if (this == npc) return true;
        if (npc == null) return false;
        return this.gender == npc.gender &&
               this.ancestry.equals(npc.ancestry) &&
               this.firstName.equals(npc.firstName) &&
               this.lastName.equals(npc.lastName);
    }
}
