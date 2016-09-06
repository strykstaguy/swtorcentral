package com.stryksta.swtorcentral.ui.activities;

import java.util.ArrayList;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.ProgressionAdapter;
import com.stryksta.swtorcentral.models.ProgressionItem;
import com.stryksta.swtorcentral.ui.views.timeline.TimelineType;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
            getWindow().setStatusBarColor(ContextCompat.getColor(ProgressionActivity.this, R.color.colorPrimary));
        }

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.progressionList);

        mLayoutManager = new GridLayoutManager(ProgressionActivity.this, 1, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressionItems = generateData();

        mRecycleAdapter = new ProgressionAdapter(progressionItems);
        mRecyclerView.setAdapter(mRecycleAdapter);
        /*
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ProgressionActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            public void onItemClick(View view, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putString("planet", progressionItems.get(position).getRepublicFlashpoint());
                bundle.putString("type", progressionItems.get(position).getEmpireLevel());
                Intent intent = new Intent(getApplicationContext(), PlanetActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            public void onItemLongClick(View view, int position)
            {

            }
        }));
*/
    }

    private ArrayList<ProgressionItem> generateData(){
        ArrayList<ProgressionItem> planets = new ArrayList<ProgressionItem>();

        planets.add(new ProgressionItem("Prologue", TimelineType.END, ProgressionItem.HEADER));

        planets.add(new ProgressionItem("Tython", "Level 1-10", R.drawable.pl_tython, "Korriban", "Level 1-10", R.drawable.pl_korriban));
        planets.add(new ProgressionItem("Ord Mantell", "Level 1-10", R.drawable.pl_ord_mantell, "Hutta", "Level 1-10", R.drawable.pl_hutta));

        planets.add(new ProgressionItem("Carrick Station", "Republic Fleet", "Vaiken Spacedock", "Imperial Fleet", R.drawable.empire_logo, ProgressionItem.FLASHOP_DOUBLE));
        planets.add(new ProgressionItem("The Esseles", "Flashpoint","The Black Talon", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_DOUBLE));
        planets.add(new ProgressionItem("Coruscant", "Level 10-16", R.drawable.pl_coruscant, "Dromund Kaas", "Level 11-16", R.drawable.pl_dromundkaas));
        planets.add(new ProgressionItem("Hammer Station", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Kuat Drive Yards", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Chapter 1", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Taris (Republic)", "Level 16-19", R.drawable.pl_taris, "Balmorra (Empire)", "Level 16-19", R.drawable.pl_balmorra));
        planets.add(new ProgressionItem("Athiss", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Nar Shaddaa", "Level 21-24", R.drawable.pl_narshaddaa, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Mandalorian Raiders", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Tatooine", "Level 25-28", R.drawable.pl_tatooine, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Cademimu", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Alderaan", "Level 29-32", R.drawable.pl_alderaan, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Chapter 2", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Balmorra (Republic)", "Level 32-36", R.drawable.pl_balmorra, "Taris (Empire)", "Level 21-24", R.drawable.pl_taris));
        planets.add(new ProgressionItem("Taral V", "Flashpoint","Boarding Party", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_DOUBLE));
        planets.add(new ProgressionItem("Maelstrom Prison", "Flashpoint", "The Foundry", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_DOUBLE));
        planets.add(new ProgressionItem("Quesh", "Level 36-37", R.drawable.pl_quesh, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Hoth", "Level 37-41", R.drawable.pl_hoth, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Colicoid War", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Chapter 3", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Belsavis", "Level 41-44", R.drawable.pl_belsavis, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("The Red Reaper", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Voss", "Level 44-47", R.drawable.pl_voss, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Directive 7", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Corellia", "Level 47-50", R.drawable.pl_corellia, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Interlude 1", ProgressionItem.CHAPTER));


        planets.add(new ProgressionItem("Black Hole", "Daily", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Section X", "Daily", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Ilum", "Level 50-52", R.drawable.pl_ilum, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Battle of Ilum", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("False Emperor", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Kaon Under Seige", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Lost Island", "Flashpoint", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));


        planets.add(new ProgressionItem("The Eternity Vault", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Karagga's Palace", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Explosive Conflict", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Chapter 4: Rise of the Hutt Cartel", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Makeb", "Level 51-54", R.drawable.pl_makeb, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Toborro's Courtyard", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Interlude 2", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("CZ-198", "Daily", R.drawable.pl_cz198, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Czerka Corporate Labs", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Czerka Core Meltdown", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Scum and Villainy", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Terror From Beyond", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Oricon", "Daily", R.drawable.pl_oricon, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Dread Fortress", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Dread Palace", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Forged Alliances", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Incursion on Korriban", "Flashpoint", "Attack on Tython", "Flashpoint", R.drawable.ic_tactical_flashpoint, ProgressionItem.FLASHOP_DOUBLE));
        planets.add(new ProgressionItem("Attack on Tython", "Flashpoint", "Incursion on Korriban", "Flashpoint", R.drawable.ic_tactical_flashpoint, ProgressionItem.FLASHOP_DOUBLE));

        planets.add(new ProgressionItem("Depths of Manaan", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Legacy of the Rakata", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Chapter 5: Shadow of Revan", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Rishi", "Level 55-58", R.drawable.pl_rishi, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Blood Hunt", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Battle of Rishi", "Flashpoint", R.drawable.ic_flashpoint, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Yavin IV", "Level 58-60", R.drawable.pl_yavin, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("The Ravagers", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Temple of Sacrifice", "Operation", R.drawable.ic_operation, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));

        planets.add(new ProgressionItem("Ziost", "Level 60", R.drawable.pl_ziost, ProgressionItem.DIR_LEFT));

        planets.add(new ProgressionItem("Bonus Series", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Balmorra", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Taris", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Nar Shaddaa", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Tatooine", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Alderaan", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Hoth", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Belsavis", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Voss", "Bonus Series", R.drawable.ic_bonus, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));


        planets.add(new ProgressionItem("Knights of the Fallen Empire", ProgressionItem.CHAPTER));

        planets.add(new ProgressionItem("Chapter 1", "The Hunt", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 2", "A Dream of Empire", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 3", "Outlander", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 4", "The Gravestone", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 5", "From the Grave", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 6", "Asylum", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 7", "Lady of Sorrows", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 8", "Taking Flight", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 9", "The Alliance", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 10", "Anarchy in Paradise", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 11", "Disavowed", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 12", "Visions in the Dark", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 13", "Profit and Plunder", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 14", "Mmandalore's Revenge", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));
        planets.add(new ProgressionItem("Chapter 15", "Gemini Deception", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_RIGHT));
        planets.add(new ProgressionItem("Chapter 16", "The Battle of Odessen", R.drawable.chapter, ProgressionItem.FLASHOP_SINGLE, ProgressionItem.DIR_LEFT));

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