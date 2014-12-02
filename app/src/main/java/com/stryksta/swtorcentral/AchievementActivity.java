package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.util.FragmentUtils;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
 
public class AchievementActivity extends ActionBarActivity {
    String mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        //switchFragment(new Category1Fragment());
        
       // if (savedInstanceState == null) {
          
        if (!FragmentUtils.isFragmentPresent(AchievementActivity.this, "Category1")) {
        	FragmentUtils.switchFragmentsInActivity(AchievementActivity.this, R.id.achievementframe, new Category1Fragment(), "Category1");
        }
        //} else {
        	//FragmentUtils.getFragmentByTag(AchievementActivity.this, "Category1");
        //}
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
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
		if (getFragmentManager().getBackStackEntryCount() > 1) {
			getFragmentManager().popBackStack();
            Fragment trestsdg =  FragmentUtils.getActiveFragment(AchievementActivity.this);
	    } else {
	        this.finish();
	    }
    }
}