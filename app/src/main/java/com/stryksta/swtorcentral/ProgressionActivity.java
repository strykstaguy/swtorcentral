package com.stryksta.swtorcentral;

import java.util.ArrayList;

import com.stryksta.swtorcentral.adapters.ProgressionAdapter;
import com.stryksta.swtorcentral.data.ProgressionItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.timeline.TimelineType;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


public class ProgressionActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressionAdapter mRecycleAdapter;
    private RecyclerView mRecyclerView;
    ArrayList<ProgressionItem> progressionItems;
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

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            FactionText = bundle.getString("faction");
        }
        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.progressionList);

        mLayoutManager = new GridLayoutManager(ProgressionActivity.this, 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if ( FactionText.equals("Republic")) {
            progressionItems = generateRepublicData();

        } else {
            progressionItems = generateEmpireData();
        }

        mRecycleAdapter = new ProgressionAdapter(progressionItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ProgressionActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            public void onItemClick(View view, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putString("planet", progressionItems.get(position).getPlanet());
                bundle.putString("type", progressionItems.get(position).getLevel());
                bundle.putString("faction", FactionText);
                Intent intent = new Intent(getApplicationContext(), PlanetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            public void onItemLongClick(View view, int position)
            {

            }
        }));
    }

    private ArrayList<ProgressionItem> generateRepublicData(){
        ArrayList<ProgressionItem> planets = new ArrayList<ProgressionItem>();

        planets.add(new ProgressionItem("Prologue", TimelineType.END, ProgressionItem.HEADER));

        planets.add(new ProgressionItem("Tython", "Level 1-10", R.drawable.pl_tython, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Ord Mantell", "Level 1-10", R.drawable.pl_ord_mantell, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Republic Fleet", "Level 10+", R.drawable.republic_logo, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("The Esseles", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Coruscant", "Level 10-16", R.drawable.pl_coruscant, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Kuat Drive Yards", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));

        planets.add(new ProgressionItem("Chapter 1", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Hammer Station", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Taris", "Level 16-19", R.drawable.pl_taris, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Taris", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Tatooine", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPR));

        planets.add(new ProgressionItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 2", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Nar Shaddaa", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Balmorra", "Level 32-36", R.drawable.pl_balmorra, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Taral V", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Maelstrom Prison", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Balmorra", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPR));

        planets.add(new ProgressionItem("Quesh", "Level 36-37", R.drawable.pl_quesh, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Hoth", "Level 37-41", R.drawable.pl_hoth, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 3", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Alderaan", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Voss", "Level 44-47", R.drawable.pl_voss, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Voss", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Hoth Bonus Series", "Flashpoint", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));

        planets.add(new ProgressionItem("Corellia", "Level 47-50", R.drawable.pl_corellia, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Interlude 1", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Belsavis", "Bonus Series", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Ilum", "Level 50-53", R.drawable.pl_ilum, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("The Eternity Vault", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Karagga's Palace", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Explosive Conflict", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("Chapter 4: Rise of the Hutt Cartel", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Makeb", "Level 51-54", R.drawable.pl_makeb, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Interlude 2", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Czerka Corporate Labs", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Czerka Core Meltdown", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Scum and Villainy", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Terror From Beyond", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Oricon", "Flashpoint", R.drawable.pl_oricon, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Forged Alliances", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Incursion on Korriban", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Attach on Tython", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Deapths of Manaan", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Rakatta Prime", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("Chapter 5: Shadow of Revan", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Rishi", "Level 55-58", R.drawable.pl_rishi, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Blood Hunt", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPR));
        planets.add(new ProgressionItem("Battle of Rishi", "Flashpoint", R.drawable.ic_tactical_flashpoint, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("Yavin IV", "Level 58-60", R.drawable.pl_yavin, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("The Ravagers", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));
        planets.add(new ProgressionItem("Temple of Sacrifice", "Operation", R.drawable.ic_operation, TimelineType.LINE, ProgressionItem.FLASHOPL));

        planets.add(new ProgressionItem("Ziost", "Level 60", R.drawable.pl_yavin, TimelineType.LINE, ProgressionItem.PLANET));

        return planets;
    }

    private ArrayList<ProgressionItem> generateEmpireData(){
        ArrayList<ProgressionItem> planets = new ArrayList<ProgressionItem>();

        planets.add(new ProgressionItem("Korriban", "Level 1-10", R.drawable.pl_korriban, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Hutta", "Level 1-10", R.drawable.pl_hutta, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Empire Fleet", "Level 10+", R.drawable.empire_logo, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Prologue", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("The Black Talon", "Flashpoint", R.drawable.ic_bonus, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Dromund Kaas", "Level 11-16", R.drawable.pl_dromundkaas, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Hammer Station", "Flashpoint", R.drawable.ic_operation, TimelineType.END, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Balmorra", "Level 16-19", R.drawable.pl_balmorra, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Balmorra", "Bonus Series", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 1", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Tatooine", "Bonus Series", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, TimelineType.END, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Nar Shaddaa", "Bonus Series", R.drawable.faction_both, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Boarding Party", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Taris", "Level 32-36", R.drawable.pl_taris, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 2", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Taris", "Bonus Series", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("The Foundry", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Quesh", "Level 36-37", R.drawable.pl_quesh, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Hoth", "Level 37-41", R.drawable.pl_hoth, TimelineType.END, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Alderaan", "Bonus Series", R.drawable.pl_alderaan, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Colicoid War Games", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("The Red Reaper", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Voss", "Level 44-47", R.drawable.pl_voss, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 3", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Voss", "Bonus Series 1", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Voss", "Bonus Series 2", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Voss", "Bonus Series 3", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Hoth", "Bonus Series", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Directive 7", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Corellia", "Level 47-50", R.drawable.pl_corellia, TimelineType.END, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Belsavis", "Bonus Series", R.drawable.faction_both, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Ilum", "Level 50-53", R.drawable.pl_ilum, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Battle of Ilum", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Interlude", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("False Emperor", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Lost Island", "Flashpoint", R.drawable.ic_flashpoint, TimelineType.END, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Chapter 4", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Makeb", "Level 53-55", R.drawable.pl_makeb, TimelineType.SINGLE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Oricon", "Flashpoint", R.drawable.pl_oricon, TimelineType.START, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("CZ-198", "Flashpoint", R.drawable.pl_cz198, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Czerka Corporate Labs", "Flashpoint", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Level 55 Content", ProgressionItem.SECTION));

        planets.add(new ProgressionItem("Czerka Core Meltdown", "Flashpoint", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Kuat Drive Yards", "Flashpoint", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));
        planets.add(new ProgressionItem("Assault on Tython", "Flashpoint", R.drawable.faction_both, TimelineType.LINE, ProgressionItem.PLANET));

        planets.add(new ProgressionItem("Korriban Incursion", "Flashpoint", R.drawable.faction_both, TimelineType.END, ProgressionItem.PLANET));
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