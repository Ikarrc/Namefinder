package ikarr.pang.model;

public class GenderLocks {
    private final static int GENDERLESS = 0;
    private final static int FEMALE = 1;
    private final static int MALE = 2;
    private final static boolean SELECTED = true;

    private boolean[] selection = {SELECTED, false, false};

    public GenderLocks() { }

    public boolean isGenderless() {
        return selection[0] == SELECTED;
    }
    public boolean isFemale() {
        return selection[1] == SELECTED;
    }
    public boolean isMale() {
        return selection[2] == SELECTED;
    }

    public int getSelectedGender() {
        for (int i = 0; i < 3; i++) {
            if (selection[i]) {
                return i;
            }
        }
        return -1;
    }

    public void selectGender(int gender) {
        for (int i = 0; i < 3; i++) {
            if (i == gender) {
                selection[i] = SELECTED;
            } else {
                selection[i] = false;
            }
        }
    }
}
