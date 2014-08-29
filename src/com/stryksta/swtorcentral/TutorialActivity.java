package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.TutorialItem;
import com.stryksta.swtorcentral.util.ListViewUtil;
import com.stryksta.swtorcentral.util.TutorialAdapter;
import com.stryksta.swtorcentral.util.TutorialDatabase;
import com.stryksta.swtorcentral.util.Utility;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
 
public class TutorialActivity extends FragmentActivity {
	private TutorialDatabase db;
	private ArrayList<TutorialItem> datacrons;
	TutorialAdapter tutorialAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Tutorials");
        
        db = new TutorialDatabase(TutorialActivity.this);
		datacrons = db.getTutorials();
		db.close();
		

		GridView tutorialList = (GridView) findViewById(R.id.tutorial_list);
        tutorialAdapter = new TutorialAdapter(TutorialActivity.this, datacrons);
        tutorialList.setAdapter(tutorialAdapter);
		
        tutorialList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
				Toast.makeText(getApplicationContext(), tutorialAdapter.getCellHeight(position, parent), Toast.LENGTH_SHORT).show();
					Bundle bundle = new Bundle();
					bundle.putString("videourl", tutorialAdapter.getItem(position).getVideoURL());
					bundle.putString("title", tutorialAdapter.getItem(position).getTitle());
					Intent addTestIntent = new Intent(TutorialActivity.this, TestActivity.class);
					addTestIntent.putExtras(bundle);
					startActivity(addTestIntent);
		}});
        
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