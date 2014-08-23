package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.TestItem;
import com.stryksta.swtorcentral.util.TimelineType;
import com.stryksta.swtorcentral.util.HorizontalListView;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
 
public class TestActivity extends FragmentActivity {
	private HorizontalListView listView;
    private ArrayList<TestItem> planets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Timeline");
        
        listView = (HorizontalListView) findViewById(android.R.id.list);

        planets = new ArrayList<TestItem>();

        planets.add(new TestItem("Korriban", "Level 1-10", R.drawable.pl_korriban, "", TimelineType.START));
        planets.add(new TestItem("Hutta", "Level 1-10", R.drawable.pl_hutta, "", TimelineType.LINE));
        planets.add(new TestItem("The Black Talon", "Flashpoint", R.drawable.ic_flashpoint, "Prologue", TimelineType.LINE));
        planets.add(new TestItem("Dromund Kaas", "Level 11-16", R.drawable.pl_dromundkaas, "", TimelineType.LINE));
        planets.add(new TestItem("Hammer Station", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.END));
        
        planets.add(new TestItem("Balmorra", "Level 16-19", R.drawable.pl_balmorra, "", TimelineType.START));
        planets.add(new TestItem("Balmorra", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.END));

        listView.setAdapter(new TestAdapter(this, planets));
        
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