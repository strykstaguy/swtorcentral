package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.ProgressionAdapter;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.TimelineType;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import mobi.parchment.widget.adapterview.listview.ListView;

public class ProgressionActivity extends AppCompatActivity {
    private ProgressionAdapter factionAdapter;
    String FactionText;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progression_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Progression");

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            FactionText = bundle.getString("faction");
        }

        if ( FactionText.equals("Republic")) {
            factionAdapter = new ProgressionAdapter(ProgressionActivity.this, generateRepublicData());
        } else {
            factionAdapter = new ProgressionAdapter(ProgressionActivity.this, generateEmpireData());
        }

        ListView progressionList = (ListView) findViewById(R.id.progressionList);

        progressionList.setAdapter(factionAdapter);
        progressionList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                Bundle bundle = new Bundle();
                bundle.putString("planet", factionAdapter.getItem(position).getPlanet());
                bundle.putString("type", factionAdapter.getItem(position).getLevel());
                bundle.putString("faction", FactionText);
                Intent intent = new Intent(getApplicationContext(), PlanetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }});
    }

    private ArrayList<ProgressionItem> generateRepublicData(){
        ArrayList<ProgressionItem> planets = new ArrayList<ProgressionItem>();

        planets.add(new ProgressionItem("Tython", "Level 1-10", R.drawable.pl_tython, "", TimelineType.START));
        planets.add(new ProgressionItem("Ord Mantell", "Level 1-10", R.drawable.pl_ord_mantell, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Republic Fleet", "Level 10+", R.drawable.ic_republic_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("The Esseles", "Flashpoint", R.drawable.ic_flashpoint, "Prologue", TimelineType.LINE));
        planets.add(new ProgressionItem("Coruscant", "Level 11-16", R.drawable.pl_coruscant, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hammer Station", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

        planets.add(new ProgressionItem("Taris", "Level 16-19", R.drawable.pl_taris, "", TimelineType.START));
        planets.add(new ProgressionItem("Taris", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, "Chapter 1", TimelineType.LINE));
        planets.add(new ProgressionItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Tatooine", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, "", TimelineType.END));

        planets.add(new ProgressionItem("Nar Shaddaa", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
        planets.add(new ProgressionItem("Taral V", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Balmorra", "Level 32-36", R.drawable.pl_balmorra, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Balmorra", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 2", TimelineType.LINE));
        planets.add(new ProgressionItem("Balmorra", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Maelstrom Prison", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Quesh", "Level 36-37", R.drawable.pl_quesh, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hoth", "Level 37-41", R.drawable.pl_hoth, "", TimelineType.END));

        planets.add(new ProgressionItem("Alderaan", "Bonus Series", R.drawable.pl_alderaan, "", TimelineType.START));
        planets.add(new ProgressionItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, "", TimelineType.LINE));
        planets.add(new ProgressionItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Level 44-47", R.drawable.pl_voss, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 3", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 3", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hoth", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Corellia", "Level 47-50", R.drawable.pl_corellia, "", TimelineType.END));

        planets.add(new ProgressionItem("Belsavis", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
        planets.add(new ProgressionItem("Ilum", "Level 50-53", R.drawable.pl_ilum, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, "Interlude", TimelineType.LINE));
        planets.add(new ProgressionItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

        planets.add(new ProgressionItem("Makeb", "Level 53-55", R.drawable.pl_makeb, "Chapter 4", TimelineType.SINGLE));

        planets.add(new ProgressionItem("Oricon", "Flashpoint", R.drawable.pl_oricon, "", TimelineType.START));
        planets.add(new ProgressionItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Czerka Corporate Labs", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Czerka Core Meltdown", "Flashpoint", R.drawable.faction_both_black, "Level 55 Content", TimelineType.LINE));
        planets.add(new ProgressionItem("Kuat Drive Yards", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Assault on Tython", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Korriban Incursion", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Rakatta Prime", "Flashpoint", R.drawable.pl_rakata_prime, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Manaan", "Flashpoint", R.drawable.pl_manaan, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Rishi", "Flashpoint", R.drawable.pl_rishi, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Yavin IV", "Flashpoint", R.drawable.pl_yavin, "", TimelineType.END));
        return planets;
    }

    private ArrayList<ProgressionItem> generateEmpireData(){
        ArrayList<ProgressionItem> planets = new ArrayList<ProgressionItem>();

        planets.add(new ProgressionItem("Korriban", "Level 1-10", R.drawable.pl_korriban, "", TimelineType.START));
        planets.add(new ProgressionItem("Hutta", "Level 1-10", R.drawable.pl_hutta, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Empire Fleet", "Level 10+", R.drawable.ic_empire_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("The Black Talon", "Flashpoint", R.drawable.ic_bonus, "Prologue", TimelineType.LINE));
        planets.add(new ProgressionItem("Dromund Kaas", "Level 11-16", R.drawable.pl_dromundkaas, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hammer Station", "Flashpoint", R.drawable.ic_operation, "", TimelineType.END));

        planets.add(new ProgressionItem("Balmorra", "Level 16-19", R.drawable.pl_balmorra, "", TimelineType.START));
        planets.add(new ProgressionItem("Balmorra", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, "Chapter 1", TimelineType.LINE));
        planets.add(new ProgressionItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Tatooine", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, "", TimelineType.END));

        planets.add(new ProgressionItem("Nar Shaddaa", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
        planets.add(new ProgressionItem("Boarding Party", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Taris", "Level 32-36", R.drawable.pl_taris, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Taris", "Bonus Series", R.drawable.faction_both_black, "Chapter 2", TimelineType.LINE));
        planets.add(new ProgressionItem("The Foundry", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Quesh", "Level 36-37", R.drawable.pl_quesh, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hoth", "Level 37-41", R.drawable.pl_hoth, "", TimelineType.END));

        planets.add(new ProgressionItem("Alderaan", "Bonus Series", R.drawable.pl_alderaan, "", TimelineType.START));
        planets.add(new ProgressionItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, "", TimelineType.LINE));
        planets.add(new ProgressionItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Level 44-47", R.drawable.pl_voss, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 1", R.drawable.faction_both_black, "Chapter 3", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 2", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Voss", "Bonus Series 3", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Hoth", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Corellia", "Level 47-50", R.drawable.pl_corellia, "", TimelineType.END));

        planets.add(new ProgressionItem("Belsavis", "Bonus Series", R.drawable.faction_both_black, "", TimelineType.START));
        planets.add(new ProgressionItem("Ilum", "Level 50-53", R.drawable.pl_ilum, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, "Interlude", TimelineType.LINE));
        planets.add(new ProgressionItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, "", TimelineType.END));

        planets.add(new ProgressionItem("Makeb", "Level 53-55", R.drawable.pl_makeb, "Chapter 4", TimelineType.SINGLE));

        planets.add(new ProgressionItem("Oricon", "Flashpoint", R.drawable.pl_oricon, "", TimelineType.START));
        planets.add(new ProgressionItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Czerka Corporate Labs", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Czerka Core Meltdown", "Flashpoint", R.drawable.faction_both_black, "Level 55 Content", TimelineType.LINE));
        planets.add(new ProgressionItem("Kuat Drive Yards", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));
        planets.add(new ProgressionItem("Assault on Tython", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.LINE));

        planets.add(new ProgressionItem("Korriban Incursion", "Flashpoint", R.drawable.faction_both_black, "", TimelineType.END));
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