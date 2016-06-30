package com.stryksta.swtorcentral;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.clans.fab.FloatingActionMenu;
import com.stryksta.swtorcentral.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.util.DividerItemDecoration;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AbilitiesActivity extends AppCompatActivity {
    //Abilities
    private AbilitiesDatabase abilitiesDatabase;
    ArrayList<AbilitiesItem> baseAbilities;
    ArrayList<AbilitiesItem> playerAbilitiesItems;

    private RecyclerView baseRecyclerView;
    private RecyclerView playerRecyclerView;

    private GridLayoutManager aLayoutManager;
    private GridLayoutManager aPlayerLayoutManager;


    private AbilityDetailAdapter playerRecycleAdapter;
    private AbilityDetailAdapter baseRecycleAdapter;

    private String clsApc;
    private String clsName;
    private String clsAbility;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ability_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(AbilitiesActivity.this, R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            clsApc = bundle.getString("clsApc");
            clsName = bundle.getString("clsName");
            clsAbility = bundle.getString("clsAbility");
        }
        getSupportActionBar().setTitle(clsName + " Abilities");

        TextView ablTitle = (TextView) findViewById(R.id.ablTitle);
        ablTitle.setText("Abilities");

        TextView ablSubtitle = (TextView) findViewById(R.id.ablSubtitle);
        ablSubtitle.setText(clsName);

        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.setText(Html.fromHtml(clsAbility));

        //Get Abilities
        baseAbilities = new ArrayList<>();
        playerAbilitiesItems = new ArrayList<>();

        abilitiesDatabase = new AbilitiesDatabase(AbilitiesActivity.this);
        baseAbilities = abilitiesDatabase.getAbilities(clsApc);
        playerAbilitiesItems = abilitiesDatabase.getAbilities("apc.pc_default");
        Log.d("SWTORCentral", "Player Abilities Count: " + playerAbilitiesItems.size());
        abilitiesDatabase.close();

        //Set Base RecyclerView
        baseRecyclerView = (RecyclerView) findViewById(R.id.abilitiesBaseList);
        baseRecyclerView.setNestedScrollingEnabled(false);

        if (baseRecyclerView != null) {
            aLayoutManager = new GridLayoutManager(AbilitiesActivity.this, 1, GridLayoutManager.VERTICAL, false);
            baseRecyclerView.setLayoutManager(aLayoutManager);

            /*
            baseRecyclerView.setLayoutManager(new GridLayoutManager(AbilitiesActivity.this, 1, GridLayoutManager.VERTICAL, false){
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            */
        }

        //Set Base Adapter
        baseRecycleAdapter = new AbilityDetailAdapter(baseAbilities);
        baseRecyclerView.addItemDecoration(new DividerItemDecoration(AbilitiesActivity.this, GridLayoutManager.VERTICAL));
        baseRecyclerView.setAdapter(baseRecycleAdapter);
        baseRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AbilitiesActivity.this, baseRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                boolean wrapInScrollView = true;
                MaterialDialog dialog = new MaterialDialog.Builder(AbilitiesActivity.this)
                        .title(baseAbilities.get(position).getAbilityName())
                        .customView(R.layout.ability_dialog, wrapInScrollView)
                        .positiveText(R.string.positive)
                        .show();

                View ablView = dialog.getCustomView();
                showAbilityDetail(ablView, position, baseAbilities);

            }

            public void onItemLongClick(View view, int position) {

            }
        }));

        //Set Base RecyclerView
        playerRecyclerView = (RecyclerView) findViewById(R.id.abilitiesPlayerList);
        playerRecyclerView.setNestedScrollingEnabled(false);

        if (playerRecyclerView != null) {
            aPlayerLayoutManager = new GridLayoutManager(AbilitiesActivity.this, 1, GridLayoutManager.VERTICAL, false);
            playerRecyclerView.setLayoutManager(aPlayerLayoutManager);
            /*
            playerRecyclerView.setLayoutManager(new GridLayoutManager(AbilitiesActivity.this, 1, GridLayoutManager.VERTICAL, false){
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            */
        }

        //Set Base Adapter
        playerRecycleAdapter = new AbilityDetailAdapter(playerAbilitiesItems);
        playerRecyclerView.addItemDecoration(new DividerItemDecoration(AbilitiesActivity.this, GridLayoutManager.VERTICAL));
        playerRecyclerView.setAdapter(playerRecycleAdapter);
        playerRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AbilitiesActivity.this, playerRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
                boolean wrapInScrollView = true;
                MaterialDialog dialog = new MaterialDialog.Builder(AbilitiesActivity.this)
                        .title(playerAbilitiesItems.get(position).getAbilityName())
                        .customView(R.layout.ability_dialog, wrapInScrollView)
                        .positiveText(R.string.positive)
                        .show();

                View ablView = dialog.getCustomView();
                showAbilityDetail(ablView, position, playerAbilitiesItems);

            }

            public void onItemLongClick(View view, int position) {

            }
        }));

        // Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

    }

    public void showAbilityDetail (View ablView, int position, ArrayList<AbilitiesItem> abilities)

    {

        TextView ablLevel = (TextView) ablView.findViewById(R.id.ablLevel);
        ablLevel.setText("Level Acquired: " + String.valueOf(abilities.get(position).getLevelAquired()));

        TextView ablCastingActivation = (TextView) ablView.findViewById(R.id.ablCastingActivation);
        if (abilities.get(position).getAbilityPassive() == "True") {
            ablCastingActivation.setText("Passive");
        } else {
            if (abilities.get(position).getCastingTime() == 0) {
                ablCastingActivation.setText("Instant");
            } else {
                ablCastingActivation.setText("Activation: " + String.valueOf(abilities.get(position).getCastingTime()) + "s");
            }
        }

        TextView ablChanneled = (TextView) ablView.findViewById(R.id.ablChanneled);
        if (abilities.get(position).getChannelingTime() == 0) {
            ablChanneled.setVisibility(View.GONE);
        } else {
            ablChanneled.setText("Channeled: " + String.valueOf(abilities.get(position).getCastingTime()) + "s");
        }

        TextView ablCooldown = (TextView) ablView.findViewById(R.id.ablCooldown);
        if (abilities.get(position).getCooldownTime() == 0) {
            ablCooldown.setVisibility(View.GONE);
        } else {
            ablCooldown.setText("Cooldown: " + String.valueOf(abilities.get(position).getCooldownTime()) + "s");
        }

        TextView ablRange = (TextView) ablView.findViewById(R.id.ablRange);
        if (abilities.get(position).getMaxRange() == 0) {
            ablRange.setVisibility(View.GONE);
        } else {
            int maxRange = abilities.get(position).getCooldownTime() * 10;
            ablRange.setText("Range: " + String.valueOf(maxRange) + "m");
        }

        TextView ablResource = (TextView) ablView.findViewById(R.id.ablResource);
        if (abilities.get(position).getEnergyCost() != 0) {
            ablResource.setText(abilities.get(position).getClassResource() + " " + String.valueOf(abilities.get(position).getEnergyCost()));
        } else if (abilities.get(position).getForceCost() != 0) {
            ablResource.setText(abilities.get(position).getClassResource() + " " + String.valueOf(abilities.get(position).getForceCost()));
        } else {
            ablResource.setVisibility(View.GONE);
        }

        TextView ablDescription = (TextView) ablView.findViewById(R.id.ablDescription);
        ablDescription.setText(abilities.get(position).getAbilityDescription());
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
}