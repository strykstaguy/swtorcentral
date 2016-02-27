package com.stryksta.swtorcentral;
 
import com.stryksta.swtorcentral.adapters.PlanetPagerAdapter;
import com.stryksta.swtorcentral.util.database.PlanetDatabase;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

public class PlanetActivity extends AppCompatActivity {
	private String planetText;
	private String factionText;
	private String typeText;
	private PlanetDatabase dbPlanet;
    private Toolbar mToolbar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

		if (Build.VERSION.SDK_INT >= 21) {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
		}

        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	planetText = bundle.getString("planet");
        	factionText = bundle.getString("faction");
        	typeText = bundle.getString("type");
        }

        getSupportActionBar().setTitle(planetText);

		ViewPager viewPager = (ViewPager) findViewById(R.id.planet_pager);
		if (viewPager != null) {
			setupViewPager(viewPager);
		}

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

        //dbPlanet = new PlanetDatabase(this);
		//String planetBackground = dbPlanet.PlanetBackground(planetText);
		//dbPlanet.close();

     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }

	private void setupViewPager(ViewPager viewPager) {
		PlanetPagerAdapter adapter = new PlanetPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new PlanetTab1(), "Information");
		adapter.addFragment(new PlanetTab2(), "Datacrons");
		adapter.addFragment(new PlanetTab3(), "Lore");
		viewPager.setAdapter(adapter);
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