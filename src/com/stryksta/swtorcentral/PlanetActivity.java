package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.util.PlanetDatabase;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class PlanetActivity extends FragmentActivity {
	private String planetText;
	private PlanetDatabase db;
	
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
        }
        
        getActionBar().setTitle(planetText);
        
        db = new PlanetDatabase(this);
		String planetBackground = db.PlanetBackground(planetText);
		
        LinearLayout ll = (LinearLayout) findViewById(R.id.planetBackground);
        int resId = getResources().getIdentifier(planetBackground, "drawable", getPackageName());
        ll.setBackgroundResource(resId);
        
        //TextView txtDescription = (TextView) findViewById(R.id.txtPlanet);
		//txtDescription.setText(planetText);
		
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.planet_title_strip);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.swtor_blue));
        
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