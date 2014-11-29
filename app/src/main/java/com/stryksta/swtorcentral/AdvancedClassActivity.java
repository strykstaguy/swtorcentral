package com.stryksta.swtorcentral;
 
import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.stryksta.swtorcentral.adapters.AdvancedClassPagerAdapter;

public class AdvancedClassActivity extends ActionBarActivity {
	public static ArrayList<String> titles = new ArrayList<String>();

    private String AdvancedClassText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_class_main);
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new AdvancedClassPagerAdapter(getSupportFragmentManager()));
        
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
            int advancedPos = bundle.getInt("position");
            String classText = bundle.getString("class");
        	AdvancedClassText = bundle.getString("advancedclass");
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.swtor_blue));
        
        //Get Titles
        getSupportActionBar().setTitle(AdvancedClassText);
        
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