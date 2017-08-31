package com.darkcode.unyapp;

import android.app.*;
import android.os.*;
import android.widget.SearchView;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getActionBar().hide();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
