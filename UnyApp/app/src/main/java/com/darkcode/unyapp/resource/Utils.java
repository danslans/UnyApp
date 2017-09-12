package com.darkcode.unyapp.resource;
import android.content.*;
import java.util.*;

public class Utils
{
	public static final String[] NAME_SHARED={"StateDB","valueDB"};
	private SharedPreferences preference;
	private Context context;

	public Utils(Context context)
	{
		this.context = context;
	}

	public boolean savePreferenceString(String key,String value,String nameShared){
		preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
		SharedPreferences.Editor edit=preference.edit();
		edit.putString(key,value);
		return edit.commit();
	}

    public boolean savePreferenceBoolean(String key,boolean value,String nameShared){
        preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
        SharedPreferences.Editor edit=preference.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }

	public String searchPreference(String nameShared,String key){
		preference=context.getSharedPreferences(nameShared,context.MODE_PRIVATE);
		return preference.getString(key,"");
	}
	public static boolean validateStringToBoolean(String key){
		return Boolean.parseBoolean(key);
	}
}
