package com.darkcode.unyapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by daniel.gomez on 31/08/2017.
 */

public class UniversityDB extends SQLiteOpenHelper {
    private static final String NAME ="unyapp.db";
    private static final int VERSION =1;

    public UniversityDB(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    class StructureDB implements BaseColumns{

    }
}
