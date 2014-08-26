package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.stryksta.swtorcentral.data.DatacronItem;
import com.stryksta.swtorcentral.util.DatacronDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;
import com.stryksta.swtorcentral.util.NotifyingScrollView;
import com.stryksta.swtorcentral.util.PlanetDatabase;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
 
public class PlanetActivity extends FragmentActivity {
	private String planetText;
	private String factionText;
	private String typeText;
	private PlanetDatabase dbPlanet;
	private DatacronDatabase dbDatacrons;
	private ArrayList<DatacronItem> datacrons;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_main);
        
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
		String planetDescription = dbPlanet.PlanetDescription(planetText);
		dbPlanet.close();
		
        
        
        FadingActionBarHelper helper = new FadingActionBarHelper()
        	.actionBarBackground(R.drawable.ab_solid_swtor)
        	.headerLayout(R.layout.transparent_header)
        	.parallax(true)
        	.contentLayout(R.layout.planet_main);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
        
        ImageView ll = (ImageView) findViewById(R.id.transparentBackground);
        int resId = getResources().getIdentifier(planetBackground, "drawable", getPackageName());
        ll.setBackgroundResource(resId);
        //ll.getLayoutParams().height = 300;
        //ll.requestLayout();
       
		TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
		txtDescription.setText(planetDescription);
		
		TextView txtPlanetTitle = (TextView) findViewById(R.id.txtPlanetTitle);
		txtPlanetTitle.setText(planetText);
		
		TextView txtDatacronTitle = (TextView) findViewById(R.id.txtDatacronTitle);
		TextView txtDatacronSubTitle = (TextView) findViewById(R.id.txtDatacronSubTitle);
		txtDatacronTitle.setText(planetText);
		
		NonScrollListView datacronItems = (NonScrollListView) findViewById(R.id.lstDatacron);
		
		if (typeText.equals("Bonus Series") || typeText.equals("Flashpoint")) {
			txtDatacronSubTitle.setVisibility(View.GONE);
			txtDatacronTitle.setVisibility(View.GONE);
			datacronItems.setVisibility(View.GONE);
			
		} else {
			dbDatacrons = new DatacronDatabase(this);
			datacrons = dbDatacrons.getDatacronsPerPlanet(planetText, factionText);
			
			PlanetAdapter adapter = new PlanetAdapter(this, R.layout.planet_row, android.R.id.text1, datacrons);
			datacronItems.setAdapter(adapter);
		}
		
		
		
		
		
		
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