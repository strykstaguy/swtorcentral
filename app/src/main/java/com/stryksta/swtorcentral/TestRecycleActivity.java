package com.stryksta.swtorcentral;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.stryksta.swtorcentral.adapters.RecycleAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;

import java.util.ArrayList;

import static android.support.v7.widget.LinearLayoutManager.*;

public class TestRecycleActivity extends ActionBarActivity {

    private Toolbar mToolbar;

    private AbilitiesDatabase abilitiesDB;
    ArrayList<AbilitiesItem> playerAbilitiesItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_recycle_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Abilities");

        //Set RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        //Get Abilities
        abilitiesDB = new AbilitiesDatabase(TestRecycleActivity.this);
        playerAbilitiesItems = abilitiesDB.getPlayerAbilities();

        //Set Adapter
        RecycleAdapter adapter = new RecycleAdapter(playerAbilitiesItems);
        recyclerView.setAdapter(adapter);

        //Close DB
        abilitiesDB.close();

     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
}