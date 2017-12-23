package com.example.kubra.abonelerlistesi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AboneDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "aboneler.db";

    private static final int DATABASE_VERSION = 1;

    // Constructor
    public AboneDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sorgu = "CREATE TABLE " + AboneContract.AboneEntry.TABLE_NAME + " (" +
                AboneContract.AboneEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AboneContract.AboneEntry.COLUMN_ABONE_NAME + " TEXT NOT NULL, " +
                AboneContract.AboneEntry.COLUMN_EMAIL + " TEXT NOT NULL " +
                "); ";

        db.execSQL(sorgu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AboneContract.AboneEntry.TABLE_NAME);
        onCreate(db);
    }
}
