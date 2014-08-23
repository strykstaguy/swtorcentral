package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.TestItem;
import com.stryksta.swtorcentral.util.TimelineType;
import com.stryksta.swtorcentral.util.HorizontalListView;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
 
public class TestActivity extends FragmentActivity {
	private HorizontalListView listView;
    private ArrayList<TestItem> planets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Timeline");
        
        listView = (HorizontalListView) findViewById(android.R.id.list);

        planets = generateEmpireData();
        
        listView.setAdapter(new TestAdapter(this, planets));
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    private ArrayList<TestItem> generateEmpireData(){
		ArrayList<TestItem> planets = new ArrayList<TestItem>();
	    
	    planets.add(new TestItem("Korriban", "Level 1-10", R.drawable.pl_korriban, "", TimelineType.START));
	    planets.add(new TestItem("Hutta", "Level 1-10", R.drawable.pl_hutta, "", TimelineType.LINE));
	    planets.add(new TestItem("The Black Talon", "Flashpoint", R.drawable.ic_flashpoint, "Prologue", TimelineType.LINE));
	    planets.add(new TestItem("Dromund Kaas", "Level 11-16", R.drawable.pl_dromundkaas, "", TimelineType.LINE));
	    planets.add(new TestItem("Hammer Station", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

	    planets.add(new TestItem("Balmorra", "Level 16-19", R.drawable.pl_balmorra, "", TimelineType.START));
	    planets.add(new TestItem("Balmorra", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, "", TimelineType.LINE));
		planets.add(new TestItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, "Chapter 1", TimelineType.LINE));
		planets.add(new TestItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, "", TimelineType.LINE));
		planets.add(new TestItem("Tatooine", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, "", TimelineType.END));

	    planets.add(new TestItem("Nar Shaddaa", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
	    planets.add(new TestItem("Boarding Party", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Taris", "Level 32-36", R.drawable.pl_taris, "", TimelineType.LINE));
		planets.add(new TestItem("Taris", "Bonus Series", R.drawable.faction_both_black, "Chapter 2", TimelineType.LINE));
		planets.add(new TestItem("The Foundry", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Quesh", "Level 36-37", R.drawable.pl_quesh, "", TimelineType.LINE));
		planets.add(new TestItem("Hoth", "Level 37-41", R.drawable.pl_hoth, "", TimelineType.END));

	    planets.add(new TestItem("Alderaan", "Bonus Series", R.drawable.pl_alderaan, "", TimelineType.START));
	    planets.add(new TestItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
	    planets.add(new TestItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, "", TimelineType.LINE));
	    planets.add(new TestItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Level 44-47", R.drawable.pl_voss, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 3", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 3", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Hoth", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Corellia", "Level 47-50", R.drawable.pl_corellia, "", TimelineType.END));

		planets.add(new TestItem("Belsavis", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
		planets.add(new TestItem("Ilum", "Level 50-53", R.drawable.pl_ilum, "", TimelineType.LINE));
		planets.add(new TestItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, "Interlude", TimelineType.LINE));
		planets.add(new TestItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

	    planets.add(new TestItem("Makeb", "Level 53-55", R.drawable.pl_makeb, "Chapter 4", TimelineType.SINGLE));

	    planets.add(new TestItem("Oricon", "Flashpoint", R.drawable.pl_oricon, "", TimelineType.START));
	    planets.add(new TestItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, "", TimelineType.LINE));
	    planets.add(new TestItem("Czerka Corporate Labs", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Czerka Core Meltdown", "Flashpoint", R.drawable.faction_both_black, "Level 55 Content", TimelineType.LINE));
	    planets.add(new TestItem("Kuat Drive Yards", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Assault on Tython", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Korriban Incursion", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.END));
	    return planets;
	}
    
    private ArrayList<TestItem> generateRepublicData(){
		ArrayList<TestItem> planets = new ArrayList<TestItem>();
	    
	    planets.add(new TestItem("Tython", "Level 1-10", R.drawable.pl_tython, "", TimelineType.START));
	    planets.add(new TestItem("Ord Mantell", "Level 1-10", R.drawable.pl_ord_mantell, "", TimelineType.LINE));
	    planets.add(new TestItem("The Esseles", "Flashpoint", R.drawable.ic_flashpoint, "Prologue", TimelineType.LINE));
	    planets.add(new TestItem("Coruscant", "Level 11-16", R.drawable.pl_coruscant, "", TimelineType.LINE));
	    planets.add(new TestItem("Hammer Station", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

	    planets.add(new TestItem("Taris", "Level 16-19", R.drawable.pl_taris, "", TimelineType.START));
	    planets.add(new TestItem("Taris", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, "", TimelineType.LINE));
		planets.add(new TestItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, "Chapter 1", TimelineType.LINE));
		planets.add(new TestItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, "", TimelineType.LINE));
		planets.add(new TestItem("Tatooine", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, "", TimelineType.END));

		planets.add(new TestItem("Nar Shaddaa", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
	    planets.add(new TestItem("Taral V", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Balmorra", "Level 32-36", R.drawable.pl_balmorra, "", TimelineType.LINE));
		planets.add(new TestItem("Balmorra", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 2", TimelineType.LINE));
		planets.add(new TestItem("Balmorra", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Maelstrom Prison", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Quesh", "Level 36-37", R.drawable.pl_quesh, "", TimelineType.LINE));
		planets.add(new TestItem("Hoth", "Level 37-41", R.drawable.pl_hoth, "", TimelineType.END));

	    planets.add(new TestItem("Alderaan", "Bonus Series", R.drawable.pl_alderaan, "", TimelineType.START));
	    planets.add(new TestItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
	    planets.add(new TestItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, "", TimelineType.LINE));
	    planets.add(new TestItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Level 44-47", R.drawable.pl_voss, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 3", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Voss", "Bonus Series 3", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Hoth", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
		planets.add(new TestItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Corellia", "Level 47-50", R.drawable.pl_corellia, "", TimelineType.END));

		planets.add(new TestItem("Belsavis", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
		planets.add(new TestItem("Ilum", "Level 50-53", R.drawable.pl_ilum, "", TimelineType.LINE));
		planets.add(new TestItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, "Interlude", TimelineType.LINE));
		planets.add(new TestItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
		planets.add(new TestItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

	    planets.add(new TestItem("Makeb", "Level 53-55", R.drawable.pl_makeb, "Chapter 4", TimelineType.LINE));

	    planets.add(new TestItem("Oricon", "Flashpoint", R.drawable.pl_oricon, "", TimelineType.START));
	    planets.add(new TestItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, "", TimelineType.LINE));
	    planets.add(new TestItem("Czerka Corporate Labs", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Czerka Core Meltdown", "Flashpoint", R.drawable.faction_both_black, "Level 55 Content", TimelineType.LINE));
	    planets.add(new TestItem("Kuat Drive Yards", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Assault on Tython", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
	    planets.add(new TestItem("Korriban Incursion", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.END));
	    
	    return planets;
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