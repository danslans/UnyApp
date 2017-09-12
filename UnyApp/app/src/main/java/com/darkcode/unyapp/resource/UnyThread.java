package com.darkcode.unyapp.resource;

import android.content.Context;

public class UnyThread extends Thread{
	private Context context;
	private DialogRes dialog;

	public UnyThread(Context context) {
		this.context = context;
	}

	public void setDialog(DialogRes dialog)
	{
		this.dialog = dialog;
	}

	public DialogRes getDialog()
	{
		return dialog;
	}

	@Override
	public void run()
	{
		Utils utils=new Utils(context);
		boolean stateDb=Utils.validateStringToBoolean(utils.searchPreference(Utils.NAME_SHARED[0],Utils.NAME_SHARED[1]));
		while (!stateDb){
			stateDb=Utils.validateStringToBoolean(utils.searchPreference(Utils.NAME_SHARED[0],Utils.NAME_SHARED[1]));
			System.out.println(""+stateDb);
		}
		 if(stateDb){
			 dialog.dismiss();
		 }
	}

	
}
