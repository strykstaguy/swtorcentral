package com.stryksta.swtorcentral.ui.activities;
 
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.DatacronClassAdapter;
import com.stryksta.swtorcentral.models.DatacronItem;
import com.stryksta.swtorcentral.util.database.DatacronDatabase;
import com.stryksta.swtorcentral.util.database.PlanetDatabase;

import java.util.ArrayList;

public class PlanetActivity extends AppCompatActivity {
	private String planetText;
	private String factionText;
	private String typeText;
	private PlanetDatabase dbPlanet;
	private DatacronDatabase dbDatacron;
    private ArrayList<DatacronItem> datacronItems;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatacronClassAdapter mRecycleAdapter;

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
			getWindow().setStatusBarColor(ContextCompat.getColor(PlanetActivity.this, R.color.colorPrimary));
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

		TextView pltTitle = (TextView) findViewById(R.id.pltTitle);
        pltTitle.setText(typeText);

		ImageView pltImage = (ImageView) findViewById(R.id.pltImage);
        //pltImage.setBackgroundResource("");

        //Datacrons
        dbDatacron = new DatacronDatabase(PlanetActivity.this);
        datacronItems = dbDatacron.getDatacronsPerPlanet(planetText);
        dbPlanet.close();

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.pltDatacrons);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(PlanetActivity.this, 1, GridLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        //Set Adapter
        mRecycleAdapter = new DatacronClassAdapter(datacronItems);
        mRecyclerView.setAdapter(mRecycleAdapter);


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