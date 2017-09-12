package com.darkcode.unyapp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

public class InformationUny extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_uny);
        Bundle bundle = getIntent().getExtras();
        bundle.getInt("idUny");
        Toast.makeText(getApplicationContext(),""+bundle.getInt("idUny"),Toast.LENGTH_SHORT).show();
    }

}
