package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.util.PlanetDatabase;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class PlanetActivity extends FragmentActivity {
	private String planetText;
	private String factionText;
	private String typeText;
	private PlanetDatabase dbPlanet;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_main);
        
        ViewPager viewPager = (ViewPager) findViewById(R.id.planet_pager);
        viewPager.setAdapter(new PlanetPagerAdapter(getSupportFragmentManager()));
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	planetText = bundle.getString("planet");
        	factionText = bundle.getString("faction");
        	typeText = bundle.getString("type");
        }
        
        getActionBar().setTitle(planetText);
        
        dbPlanet = new PlanetDatabase(this);
		String planetBackground = dbPlanet.PlanetBackground(planetText);
		dbPlanet.close();
		
        LinearLayout ll = (LinearLayout) findViewById(R.id.planetBackground);
        int resId = getResources().getIdentifier(planetBackground, "drawable", getPackageName());
        ll.setBackgroundResource(resId);
        
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