package com.example.projetsqllite.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "Machinegestion";

    private static final String CREATE_TABLE_MARQUE = "CREATE TABLE marque(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "code TEXT," +
            "libelle TEXT)";


    private static final String CREATE_TABLE_USER = "CREATE TABLE user(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "firstname TEXT," +
            "lastname TEXT,"+
            "email TEXT,"+
            "password TEXT)";

    private static final String CREATE_TABLE_MACHINE = "CREATE TABLE machine(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "reference TEXT," +
            "prix INTEGER," +
            "date TEXT," +
            "marque_nom TEXT," +
            "FOREIGN KEY(marque_nom) REFERENCES marque(id))";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MARQUE);
        db.execSQL(CREATE_TABLE_MACHINE);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS machine");
        db.execSQL("DROP TABLE IF EXISTS marque");
        db.execSQL("DROP TABLE IF EXISTS user");
        this.onCreate(db);
    }
}
