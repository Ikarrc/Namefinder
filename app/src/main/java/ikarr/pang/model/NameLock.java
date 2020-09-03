package ikarr.pang.model;

public class NameLock {
    private final static int FIRSTNAME = 0;
    private final static int LASTNAME = 1;
    private final static int SURNAME = 2;

    private final static boolean OPEN = true;
    private final static boolean CLOSE = false;

    private boolean position = OPEN;
    private int relation;

    public NameLock(int relation) {
        this.relation = relation;
    }

    public boolean isOpen() {
        return position;
    }
    public void setPosition(boolean position) {
        this.position = position;
    }

    public void switchLock() {
        if (this.isOpen()) {
            this.position = CLOSE;
        } else {
            this.position = OPEN;
        }
    }

    public int getRelation() {
        return relation;
    }
    public void setRelation(int relation) {
        this.relation = relation;
    }
}
