package com.darkcode.unyapp.resource;

import android.content.Context;

public class UnyThread extends Thread{
	private Context context;

	public UnyThread(Context context) {
		this.context = context;
	}

	@Override
	public void run()
	{
		Utils utils=new Utils(context);
		while (!Utils.validateStringToBoolean(utils.searchPreference(Utils.NAME_SHARED[0],"StateDB"))){

		}
	}

	
}
