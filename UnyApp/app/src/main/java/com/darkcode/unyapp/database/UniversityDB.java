package com.darkcode.unyapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ${DANSLANS} on 31/08/2017.
 */

public class UniversityDB extends SQLiteOpenHelper {
    private static final String NAME ="unyapp.db";
    private static final int VERSION =1;

    private UniversityDB(Context context) {
        super(context, NAME, null, VERSION);
    }

    public static UniversityDB getConnection(Context context){
        return new UniversityDB(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StructureDB.CREATE_TABLE_DEPARTMENTS);
        db.execSQL(StructureDB.CREATE_TABLE_UNIVERSITIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
  public  class StructureDB implements BaseColumns{
        private static final String TYPE_TEXT =" TEXT ";
        private static final String TYPE_INTEGER =" INTEGER ";
        private static final String PRYMARY = " PRIMARY KEY ";
        private static final String COMA = ",";

        public static final String NAME_TABLE_DEPARTMENTS =" DEPARTMENTS";
        public static final String CREATE_TABLE_DEPARTMENTS = "CREATE TABLE"+NAME_TABLE_DEPARTMENTS+"(" +
                StructureDB._ID+TYPE_INTEGER+PRYMARY+COMA+
                StructureDB.COLUMN_NAME+TYPE_TEXT+
                ")";

        public static final String NAME_TABLE_UNIVERSITIES =" UNIVERSITIES";
        public static final String COLUMN_NAME ="NAME ";
        public static final String COLUMN_INFO="INFORMATION ";
        public static final String COLUMN_URL="URL ";
        public static final String COLUMN_LOGO="LOGO ";
        public static final String COLUMN_DEPARTMENT="DEPARTMENT ";
        public static final String CREATE_TABLE_UNIVERSITIES = "CREATE TABLE "+ NAME_TABLE_UNIVERSITIES +"(" +
                StructureDB._ID+TYPE_INTEGER+PRYMARY+COMA+
                COLUMN_NAME+TYPE_TEXT+COMA+
                COLUMN_INFO+TYPE_TEXT+COMA+
                COLUMN_URL+TYPE_TEXT+COMA+
                COLUMN_LOGO+TYPE_TEXT+COMA+
                COLUMN_DEPARTMENT+TYPE_INTEGER+" FOREIGN KEY ("+COLUMN_DEPARTMENT+") REFERENCES "+NAME_TABLE_DEPARTMENTS+"("+StructureDB._ID+") "+
                ")" ;



    }
}
