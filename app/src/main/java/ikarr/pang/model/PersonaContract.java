package ikarr.pang.model;

import android.provider.BaseColumns;

public class PersonaContract {

    private PersonaContract() {}

    public static class PersonaTable implements BaseColumns {
        public static final String TABLE_NAME = "persona_storage";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_ANCESTRY = "ancestry";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
    }

}
