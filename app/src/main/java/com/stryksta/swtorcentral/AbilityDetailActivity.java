package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
 
public class AbilityDetailActivity extends AppCompatActivity {

	private AbilitiesDatabase db;
	ArrayList<AbilitiesItem> ability = new ArrayList<AbilitiesItem>();
	AbilityDetailAdapter abilityAdapter;
    private String AbilityName;
	private String ClassResource;
	private String type;
	private int ClassId;
	private int AdvancedId;
	private int SkillId;
    private Toolbar mToolbar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ability_details_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
            String className = bundle.getString("class");
        	AbilityName = bundle.getString("ability_name");
        	ClassResource = bundle.getString("class_resource");
        	AdvancedId = bundle.getInt("advanced_class_id");
        	ClassId = bundle.getInt("class_id");
        	SkillId = bundle.getInt("skill_id");
        	type = bundle.getString("type");
        }

        getSupportActionBar().setTitle(AbilityName);

        db = new AbilitiesDatabase(this);

		NonScrollListView abilitylistview = (NonScrollListView) findViewById(R.id.listView1);
		
		abilityAdapter = new AbilityDetailAdapter(this,ability);
		abilitylistview.setAdapter(abilityAdapter);
		
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