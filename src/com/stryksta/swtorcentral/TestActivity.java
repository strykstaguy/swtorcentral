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
    private ArrayList<TestItem> eventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Timeline");
        
        listView = (HorizontalListView) findViewById(android.R.id.list);

        eventos = new ArrayList<TestItem>();

        TestItem eventoFirst = new TestItem("Starter Planets", TimelineType.START);
        eventos.add(eventoFirst);

        eventos.add(new TestItem("Planet 1", TimelineType.MIDDLE));
        eventos.add(new TestItem("Planet 2", TimelineType.MIDDLE));
        eventos.add(new TestItem("Planet 3", TimelineType.MIDDLE));
        eventos.add(new TestItem("Planet 4", TimelineType.MIDDLE));
        eventos.add(new TestItem("Planet 5", TimelineType.MIDDLE));
        
        TestItem eventoLast = new TestItem("End Game Planets", TimelineType.END);
        eventos.add(eventoLast);

        listView.setAdapter(new TestAdapter(this, eventos));
        
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