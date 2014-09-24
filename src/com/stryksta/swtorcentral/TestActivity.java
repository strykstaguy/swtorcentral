package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.data.LoreItem;
import com.stryksta.swtorcentral.util.ClassesDatabase;
import com.stryksta.swtorcentral.util.LoreDatabase;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
 
public class TestActivity extends FragmentActivity {
    
	ArrayList<LoreItem> loreItemsArray = new ArrayList<LoreItem>();
	ListView listView;
	TestAdapter adapter;
	private LoreDatabase db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Lore");
        
        listView = (ListView) findViewById(R.id.loreListview);
        
        db = new LoreDatabase(TestActivity.this);

        //loreItemsArray = db.getLocationLore("Tython", "Republic");
        loreItemsArray.add(new LoreItem("Tython", "Locations Lore"));
        loreItemsArray.addAll(db.getLocationLore("Tython", "Republic"));
        
        db.close();
        
        adapter = new TestAdapter(TestActivity.this, loreItemsArray);
		listView.setAdapter(adapter);
		
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