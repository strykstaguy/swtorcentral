package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.util.FragmentUtils;

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

        ImageButton imgClass = (ImageButton) findViewById(R.id.iv_image);
        int iColor = ContextCompat.getColor(AchievementActivity.this, R.color.swtor_blue);

        int red = (iColor & 0xFF0000) / 0xFFFF;
        int green = (iColor & 0xFF00) / 0xFF;
        int blue = iColor & 0xFF;

        float[] matrix = { 0, 0, 0, 0, red
                , 0, 0, 0, 0, green
                , 0, 0, 0, 0, blue
                , 0, 0, 0, 1, 0 };

        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
        imgClass.setColorFilter(colorFilter);

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

    public void setCompletedText(String text) {
        TextView achievementProgress = (TextView) findViewById(R.id.achievementProgress);
        achievementProgress.setText(text);
    }

    public void setTotalText(String text) {
        TextView achievementMax = (TextView) findViewById(R.id.achievementMax);
        achievementMax.setText(text);
    }

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 1) {
			getFragmentManager().popBackStack();
	    } else {
	        this.finish();
	    }
    }
}