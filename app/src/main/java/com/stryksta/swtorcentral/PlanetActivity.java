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
import android.widget.TextView;

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

        dbPlanet = new PlanetDatabase(PlanetActivity.this);
        String Description = dbPlanet.PlanetDescription(planetText);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        dbPlanet.close();

        txtDescription.setText(Description);

		TextView pltName = (TextView) findViewById(R.id.pltName);
		pltName.setText(planetText);

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