package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.AbilitiesDatabase;
import com.stryksta.swtorcentral.util.NonScrollListView;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
 
public class AbilityDetailActivity extends FragmentActivity {
	
	private AbilitiesDatabase db;
	ArrayList<AbilitiesItem> ability = new ArrayList<AbilitiesItem>();
	AbilityDetailAdapter abilityAdapter;
	@SuppressWarnings("unused")
	private String ClassName;
	private String AbilityName;
	private String ClassResource;
	private String type;
	private int ClassId;
	private int AdvancedId;
	private int SkillId;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ability_details_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	ClassName = bundle.getString("class");
        	AbilityName = bundle.getString("ability_name");
        	ClassResource = bundle.getString("class_resource");
        	AdvancedId = bundle.getInt("advanced_class_id");
        	ClassId = bundle.getInt("class_id");
        	SkillId = bundle.getInt("skill_id");
        	type = bundle.getString("type");
        }
        
        getActionBar().setTitle(AbilityName);
        
        db = new AbilitiesDatabase(this);
        
        if (type.equals("class")) {
        	ability = db.getAbilityClass(AbilityName, ClassId);
        } else if(type.equals("advanced")) {
        	ability = db.getAbilityAdvanced(AbilityName, AdvancedId);
        } else if(type.equals("skill")) {
        	ability = db.getAbilitySkill(AbilityName, SkillId);
        }
		
		
		NonScrollListView abilitylistview = (NonScrollListView) findViewById(R.id.listView1);
		
		abilityAdapter = new AbilityDetailAdapter(this, android.R.layout.simple_list_item_1, ability, ClassResource, type);
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