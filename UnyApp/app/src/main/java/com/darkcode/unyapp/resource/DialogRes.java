package com.darkcode.unyapp.resource;
import android.app.*;
import android.os.*;
import android.view.*;

import com.darkcode.unyapp.R;

public class DialogRes extends DialogFragment
{
	private String title;

	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
		alert.setTitle(title);
		alert.setView(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_res,null));
		return alert.create();
	} 
	
}
