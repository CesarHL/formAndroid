package mipymex.mcs.com.pruebas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*
        // Creación de la tabla de usuarios para la sesion
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DataDB.TABLE_NAME_USERS + "(" +
                "_id INTEGER PRIMARY KEY, " +
                DataDB.NUM_AGENCIA + " INTEGER, " +
                DataDB.NAME + " TEXT, " +
                DataDB.PASSWORD + " TEXT);"
        );
        System.out.println(DataDB.TABLE_NAME_USERS + " Creada");
        */


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}