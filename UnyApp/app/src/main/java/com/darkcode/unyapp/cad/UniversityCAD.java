package com.darkcode.unyapp.cad;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.darkcode.unyapp.database.UniversityDB;
import com.darkcode.unyapp.model.Department;
import com.darkcode.unyapp.model.University;

import java.util.ArrayList;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import org.json.*;
import android.widget.*;
import com.darkcode.unyapp.resource.*;

/**
 * Created by daniel.gomez on 01/09/2017.
 */

public class UniversityCAD {
    private Context context;
    private UniversityDB db;
    private SQLiteDatabase sqLiteDatabase;
    private ContentValues values;
    private Cursor cursor;
	private RequestQueue request;
	private String msn=",";
	private int[] states;
	private Utils util;


    public UniversityCAD(Context context) {
        this.context = context;
        instanceAll();
    }
    private void instanceAll(){
        db=UniversityDB.getConnection(context);
		request=Volley.newRequestQueue(context);
		util=new Utils(context);
    }
    public ArrayList<University> selectUniversity(){
        ArrayList<University> universities=new ArrayList<>();
        sqLiteDatabase=db.getReadableDatabase();
        String[] columns={
                UniversityDB.StructureDB.COLUMN_ID,
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
                values.put(UniversityDB.StructureDB.COLUMN_ID,university.getId());
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
                UniversityDB.StructureDB.COLUMN_ID,
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
	public String downloadDBCloud(){
		JsonArrayRequest jsonArrayRequest= new JsonArrayRequest("http://localhost:8080/UnyApp/UniversityCAD.php",new Response.Listener<JSONArray>(){
			public void onResponse(JSONArray json){
				states=new int[json.length()];
				for (int i=0;i<json.length();i++){
					try{
					JSONObject obj=json.getJSONObject(i);
						JSONObject objU=(JSONObject) obj.get("Antioquia");
						states[i]=insertUniversity(new University(objU.getInt("id"),objU.getString("name"),objU.getString("information"),objU.getString("url"),objU.getString("logo"),"1"));
					}catch(JSONException e){
						msn+=e;
					}
				}
			}
		},new Response.ErrorListener(){
			public void onErrorResponse(VolleyError e){
				msn+=e;
			}
		});
		request.add(jsonArrayRequest);
		return msn;
	}
	
	private int selectUniversityId(int id){
		sqLiteDatabase=db.getReadableDatabase();
		String[] column={UniversityDB.StructureDB.COLUMN_ID};
		cursor=sqLiteDatabase.query(UniversityDB.StructureDB.NAME_TABLE_UNIVERSITIES,column,UniversityDB.StructureDB.COLUMN_ID+" = "+id,null,null,null,null);
		Toast.makeText(context,""+cursor.getCount(),Toast.LENGTH_LONG).show();
		return cursor.getCount();
	}
	private void validateStateDb(){
		boolean resultState=false;
		for(int i: states){
			if (i>=1){
				resultState=true;
			}
		}
	}
}
