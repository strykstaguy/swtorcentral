package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import mobi.parchment.widget.adapterview.listview.ListView;

import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.HorizontalListView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
 
public class FactionActivity extends FragmentActivity {
	private FactionAdapter adapter1;
	private FactionAdapter adapter2;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progression_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        ListView<FactionAdapter> listView1 = (ListView<FactionAdapter>) findViewById(R.id.progressionList1);
        ListView<FactionAdapter> listView2 = (ListView<FactionAdapter>) findViewById(R.id.progressionList2);
        
        adapter1 = new FactionAdapter(this, generateRepublicData());
        adapter2 = new FactionAdapter(this, generateEmpireData());
        
        listView1.setAdapter(adapter1);
        
        listView1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("planet", adapter1.getItem(position).getPlanet());
	            
					Intent intent = new Intent(getApplicationContext(), PlanetActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
			    }});
        
        listView2.setAdapter(adapter2);
        
        listView2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			    {
					Bundle bundle = new Bundle();
					bundle.putString("planet", adapter2.getItem(position).getPlanet());
	            
					Intent intent = new Intent(getApplicationContext(), PlanetActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
			    }});
    }
    
    private ArrayList<ProgressionItem> generateEmpireData(){
		ArrayList<ProgressionItem> models = new ArrayList<ProgressionItem>();
	    
	    models.add(new ProgressionItem("Korriban", R.drawable.pl_korriban));
	    models.add(new ProgressionItem("Hutta", R.drawable.pl_hutta));
	    models.add(new ProgressionItem("Korriban"));
	    models.add(new ProgressionItem("Korriban"));
	    models.add(new ProgressionItem(R.drawable.ic_flashpoint, "The Black Talon", "Flashpoint"));
	    models.add(new ProgressionItem("Dromund Kaas", R.drawable.pl_dromundkaas));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Hammer Station", "Flashpoint"));
	    models.add(new ProgressionItem("Balmorra", R.drawable.pl_balmorra));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Balmorra", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Athiss", "Flashpoint"));
	    models.add(new ProgressionItem("Nar Shaddaa", R.drawable.pl_narshaddaa));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Mandalorian Raiders", "Flashpoint"));
	    models.add(new ProgressionItem("Tatooine", R.drawable.pl_tatooine));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Tatooine", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Cademimu", "Flashpoint"));
	    models.add(new ProgressionItem("Alderaan", R.drawable.pl_alderaan));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Boarding Party", "Flashpoint"));
	    models.add(new ProgressionItem("Taris", R.drawable.pl_taris));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Taris", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "The Foundry", "Flashpoint"));
	    models.add(new ProgressionItem("Quesh", R.drawable.pl_quesh));
	    models.add(new ProgressionItem("Hoth", R.drawable.pl_hoth));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Alderaan", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Colicoid War Games", "Flashpoint"));
	    models.add(new ProgressionItem("Belsavis", R.drawable.pl_belsavis));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "The Red Reaper", "Flashpoint"));
	    models.add(new ProgressionItem("Voss", R.drawable.pl_voss));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 1", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 2", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 3", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Alderaan", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Directive 7", "Flashpoint"));
	    models.add(new ProgressionItem("Corellia", R.drawable.pl_corellia));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Belsavis", "Bonus Series"));
	    models.add(new ProgressionItem("Ilum", R.drawable.pl_ilum));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Battle of Ilum", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "False Emperor", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Kaon Under Seige", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Lost Island", "Flashpoint"));
	    models.add(new ProgressionItem("Makeb", R.drawable.pl_makeb));
	    models.add(new ProgressionItem("Oricon", R.drawable.pl_oricon));
	    models.add(new ProgressionItem("CZ-198", R.drawable.pl_cz198));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Czerka Corporate Labs", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Czerka Core Meltdown", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Kuat Drive Yards", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Assault on Tython", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Korriban Incursion", "Flashpoint"));
	    return models;
	}
    
    private ArrayList<ProgressionItem> generateRepublicData(){
		ArrayList<ProgressionItem> models = new ArrayList<ProgressionItem>();
	    
	    models.add(new ProgressionItem("Tython", R.drawable.pl_tython));
	    models.add(new ProgressionItem("Ord Mantell", R.drawable.pl_ord_mantell));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "The Esseles", "Flashpoint"));
	    models.add(new ProgressionItem("Coruscant", R.drawable.pl_coruscant));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Hammer Station", "Flashpoint"));
	    models.add(new ProgressionItem("Taris", R.drawable.pl_taris));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Taris", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Athiss", "Flashpoint"));
	    models.add(new ProgressionItem("Nar Shaddaa", R.drawable.pl_narshaddaa));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Mandalorian Raiders", "Flashpoint"));
	    models.add(new ProgressionItem("Tatooine", R.drawable.pl_tatooine));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Tatooine", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Cademimu", "Flashpoint"));
	    models.add(new ProgressionItem("Alderaan", R.drawable.pl_alderaan));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Taral V", "Flashpoint"));
	    models.add(new ProgressionItem("Balmorra", R.drawable.pl_balmorra));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Balmorra 1", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Balmorra 2", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Maelstrom Prison", "Flashpoint"));
	    models.add(new ProgressionItem("Quesh", R.drawable.pl_quesh));
	    models.add(new ProgressionItem("Hoth", R.drawable.pl_hoth));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Alderaan", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Colicoid War Games", "Flashpoint"));
	    models.add(new ProgressionItem("Belsavis", R.drawable.pl_belsavis));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "The Red Reaper", "Flashpoint"));
	    models.add(new ProgressionItem("Voss", R.drawable.pl_voss));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 1", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 2", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Voss 3", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Alderaan", "Bonus Series"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Directive 7", "Flashpoint"));
	    models.add(new ProgressionItem("Corellia", R.drawable.pl_corellia));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Belsavis", "Bonus Series"));
	    models.add(new ProgressionItem("Ilum", R.drawable.pl_ilum));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Battle of Ilum", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "False Emperor", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Kaon Under Seige", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Lost Island", "Flashpoint"));
	    models.add(new ProgressionItem("Makeb", R.drawable.pl_makeb));
	    models.add(new ProgressionItem("Oricon", R.drawable.pl_oricon));
	    models.add(new ProgressionItem("CZ-198", R.drawable.pl_cz198));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Czerka Corporate Labs", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Czerka Core Meltdown", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Kuat Drive Yards", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Assault on Tython", "Flashpoint"));
	    models.add(new ProgressionItem(R.drawable.faction_both_black, "Korriban Incursion", "Flashpoint"));
	    return models;
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