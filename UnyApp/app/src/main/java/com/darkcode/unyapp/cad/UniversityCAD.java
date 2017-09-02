package com.darkcode.unyapp.cad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.darkcode.unyapp.database.UniversityDB;
import com.darkcode.unyapp.model.Department;
import com.darkcode.unyapp.model.University;

import java.util.ArrayList;

/**
 * Created by daniel.gomez on 01/09/2017.
 */

public class UniversityCAD {
    private Context context;
    private UniversityDB db;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues values;
    private Cursor cursor;

    public UniversityCAD(Context context) {
        this.context = context;
        connectDB();
    }
    private void connectDB(){
        db=UniversityDB.getConnection(context);
    }
    public ArrayList<University> selectUniversity(){
        ArrayList<University> universities=new ArrayList<>();
        sqLiteDatabase=db.getReadableDatabase();
        String[] columns={
                UniversityDB.StructureDB._ID,
                UniversityDB.StructureDB.COLUMN_NAME,
                UniversityDB.StructureDB.COLUMN_INFO,
                UniversityDB.StructureDB.COLUMN_URL,
                UniversityDB.StructureDB.COLUMN_LOGO,
                UniversityDB.StructureDB.COLUMN_DEPARTMENT};
        cursor=sqLiteDatabase.query(UniversityDB.StructureDB.NAME_TABLE_UNIVERSITIES,columns,null,null,null,null,null);
        while (cursor.moveToNext()){
             universities.add(new University(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return universities;
    }
    public int insertUniversity(University university){
        sqLiteDatabase=db.getWritableDatabase();
        values=new ContentValues();
                values.put(UniversityDB.StructureDB._ID,university.getId());
                values.put(UniversityDB.StructureDB.COLUMN_NAME,university.getName());
                values.put(UniversityDB.StructureDB.COLUMN_INFO,university.getInformation());
                values.put(UniversityDB.StructureDB.COLUMN_URL,university.getUrl());
                values.put(UniversityDB.StructureDB.COLUMN_LOGO,university.getLogo());
                values.put(UniversityDB.StructureDB.COLUMN_DEPARTMENT,university.getDepartment());
        return (int) sqLiteDatabase.insert(UniversityDB.StructureDB.NAME_TABLE_UNIVERSITIES,null,values);
    }
    public int insertDepartment(Department department){
        sqLiteDatabase=db.getWritableDatabase();
        values=new ContentValues();
        values.put(UniversityDB.StructureDB._ID,department.getId());
        values.put(UniversityDB.StructureDB.COLUMN_NAME,department.getName());
        return (int)sqLiteDatabase.insert(UniversityDB.StructureDB.NAME_TABLE_DEPARTMENTS,null,values);
    }
    public ArrayList<University> searchUniversity(String query){
        ArrayList<University> universities =new ArrayList<>() ;
        sqLiteDatabase=db.getReadableDatabase();
        String[] columns={
                UniversityDB.StructureDB._ID,
                UniversityDB.StructureDB.COLUMN_NAME,
                UniversityDB.StructureDB.COLUMN_INFO,
                UniversityDB.StructureDB.COLUMN_URL,
                UniversityDB.StructureDB.COLUMN_LOGO,
                UniversityDB.StructureDB.COLUMN_DEPARTMENT};
        cursor=sqLiteDatabase.query(UniversityDB.StructureDB.NAME_TABLE_UNIVERSITIES,columns,UniversityDB.StructureDB.COLUMN_NAME +" like "+"'%"+query+"%'",null,null,null,null);
        while (cursor.moveToNext()){
            universities.add(new University(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }
        return universities;
    }
}
