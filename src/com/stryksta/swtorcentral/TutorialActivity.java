package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.TutorialItem;
import com.stryksta.swtorcentral.util.AutoMeasureGridView;
import com.stryksta.swtorcentral.util.TutorialDatabase;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
 
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
		

		AutoMeasureGridView tutorialList = (AutoMeasureGridView) findViewById(R.id.tutorial_list);
        tutorialAdapter = new TutorialAdapter(TutorialActivity.this, datacrons);
        tutorialList.setAdapter(tutorialAdapter);
		
        tutorialList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
				//Toast.makeText(getApplicationContext(), tutorialAdapter.getCellHeight(position, parent), Toast.LENGTH_SHORT).show();
					Bundle bundle = new Bundle();
					bundle.putString("video_url", tutorialAdapter.getItem(position).getVideoURL());
					bundle.putString("title", tutorialAdapter.getItem(position).getTitle());
					Intent addVideoIntent = new Intent(TutorialActivity.this, VideoPlayerActivity.class);
					addVideoIntent.putExtras(bundle);
					startActivity(addVideoIntent);
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