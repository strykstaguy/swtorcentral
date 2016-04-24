package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.ProgressProfileView;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class AchievementActivity extends AppCompatActivity {
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



        //CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsed_toolbar);
        //collapsingToolbarLayout.setTitle("Achievements");
        //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

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

    public void setTitleText(String text) {
        TextView achievementCat = (TextView) findViewById(R.id.achievementCat);
        achievementCat.setText(text);
    }

    public void setPoints(String text) {
        TextView achievementPoints = (TextView) findViewById(R.id.achievementProgress);
        achievementPoints.setText(text);
    }

    public void setAchievementProgress(int progressValue) {
        ProgressProfileView achievementProgress = (ProgressProfileView) findViewById(R.id.advImage);


        //float total_completed = item.getCompleted();
        //float total = item.getTotal();

        //float progressValue = (float)total_completed/(float)total;
        float newFloat = (float) progressValue;
        achievementProgress.setProgress(newFloat);
    }

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 1) {
			getFragmentManager().popBackStack();
	    } else {
	        this.finish();
	    }
    }
}