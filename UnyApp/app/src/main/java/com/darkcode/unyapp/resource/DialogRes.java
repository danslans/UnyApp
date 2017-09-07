package com.darkcode.unyapp.resource;
import android.app.*;
import android.os.*;
import android.view.*;
import android.text.*;
import android.support.v7.cardview.*;

public class DialogRes extends DialogFragment
{
	private String title;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		alert.setTitle("hola");
		alert.setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_res,null));
		return alert.create();
	} 
	
}
