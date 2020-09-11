package ikarr.pang.model;

import ikarr.pang.model.PersonaContract.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonaDbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PersonaTable.TABLE_NAME + " (" +
                    PersonaTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PersonaTable.COLUMN_GENDER + " INTEGER NOT NULL, " +
                    PersonaTable.COLUMN_ANCESTRY + " TEXT NOT NULL, " +
                    PersonaTable.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    PersonaTable.COLUMN_LAST_NAME + " TEXT NOT NULL" +
                    ")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PersonaTable.TABLE_NAME;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "PersonaSaver.db";

    private SQLiteDatabase db;

    public PersonaDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertPersona(Persona persona) {
        ContentValues cv = new ContentValues();
        cv.put(PersonaTable.COLUMN_GENDER, persona.getGender());
        cv.put(PersonaTable.COLUMN_ANCESTRY, persona.getAncestry());
        cv.put(PersonaTable.COLUMN_FIRST_NAME, persona.getFirstName());
        cv.put(PersonaTable.COLUMN_LAST_NAME, persona.getLastName());
        db.insert(PersonaTable.TABLE_NAME, null, cv);
    }

    public void insertPersonaList(List<Persona> personaList) {
        db = getWritableDatabase();
        int size = personaList.size();
        if (size > 0) {
            Persona current;
            for (int i = 0; i < size; i++) {
                current = personaList.get(i);
                insertPersona(current);
            }
        }
    }

    // Getting DB content in List
    public List<Persona> initiateSavedPersonas() {
        List<Persona> initialSavedPersonas = new ArrayList<Persona>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + PersonaTable.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            Persona npc;
            do {
                int gender = cursor.getInt(cursor.getColumnIndex(PersonaTable.COLUMN_GENDER));
                String ancestry = cursor.getString(cursor.getColumnIndex(PersonaTable.COLUMN_ANCESTRY));
                String fName = cursor.getString(cursor.getColumnIndex(PersonaTable.COLUMN_FIRST_NAME));
                String lName = cursor.getString(cursor.getColumnIndex(PersonaTable.COLUMN_LAST_NAME));
                npc = new Persona(gender, ancestry, fName, lName);
                initialSavedPersonas.add(npc);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return initialSavedPersonas;
    }

    // Empty the DB
    public void emptyPersonaTable() {
        db = getWritableDatabase();
        String query = "DELETE FROM " + PersonaTable.TABLE_NAME;
        db.execSQL(query);
    }

}
