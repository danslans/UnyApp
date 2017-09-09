package com.darkcode.unyapp.resource;
import android.content.*;
import java.util.*;

public class Utils
{
	private SharedPreferences preference;
	private Context context;

	public Utils(Context context)
	{
		this.context = context;
	}
	
	public boolean savePreferenceString(String[][] data,String nameShared){
		preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
		SharedPreferences.Editor edit=preference.edit();
		edit.putString(data[0][0],data[0][1]);
		return edit.commit();
	}

    public boolean savePreferenceBoolean(String key,boolean value,String nameShared){
        preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
        SharedPreferences.Editor edit=preference.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }
	
	public ArrayList<String> searchPreference(String nameShared,String[] key){
		ArrayList<String> list=new ArrayList<>();
		preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
		for(String i:key){
			list.add(preference.getString(i,""));
		}
		return list;
	}
}
