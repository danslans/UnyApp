package com.darkcode.unyapp;

import android.app.*;
import android.content.Context;
import android.os.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.*;
import android.widget.Toast;

import com.darkcode.unyapp.adapters.UniversityAdapter;
import com.darkcode.unyapp.cad.UniversityCAD;
import com.darkcode.unyapp.model.Department;
import com.darkcode.unyapp.model.University;

import java.util.ArrayList;
import android.widget.*;
import com.darkcode.unyapp.resource.*;
import android.content.res.*;
import android.content.*;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener
{
    private RecyclerView recyclerView;
    private UniversityAdapter adapter;
    private ArrayList<University> arrayList =new ArrayList<>();
    private UniversityCAD universityCAD;
    private DialogRes dialogRes;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        universityCAD= new UniversityCAD(MainActivity.this);
        validateDownloadDb();
		//testInsert();
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        arrayList= universityCAD.selectUniversity();
        adapter=new UniversityAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
    private void loadDialog(){
        dialogRes= new DialogRes();
        dialogRes.setTitle("Download");
        dialogRes.show(getFragmentManager(),null);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        arrayList.clear();
        arrayList= universityCAD.searchUniversity(newText);
        adapter=new UniversityAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        //Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_SHORT).show();
        return false;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView  searchView =null;
        if(search !=null){
            searchView =(SearchView) search.getActionView();
            searchView.setOnQueryTextListener(this);
        }
        if(searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item",Toast.LENGTH_SHORT).show();
        }
		return super.onOptionsItemSelected(item);
	}
	private void testInsert(){
            universityCAD.insertDepartment(new Department(2,"Antioquia"));
        for (int i=0;i<10;i++){
            universityCAD.insertUniversity(new University(i,"Universidad de Antioquia "+i,"this information not is reality"+i,"udea.com"+i,"logo.png","2"));
        }
    }
	private String synchronizedCloud(){
		return universityCAD.downloadDBCloud();
	}

	private void validateDownloadDb(){
		
		SharedPreferences preference=getSharedPreferences(Utils.NAME_SHARED[0],Context.MODE_PRIVATE);
		Toast.makeText(getApplicationContext(),preference.getString("statusDB",""),Toast.LENGTH_SHORT).show();
		if(!Boolean.parseBoolean(preference.getString("statusDB",""))){
			loadDialog();
			synchronizedCloud();
		}else{
			Toast.makeText(getApplicationContext(),"Discharged",Toast.LENGTH_SHORT).show();
        }
    }
	
}
