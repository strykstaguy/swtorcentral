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
    ArrayList<AbilitiesItem> abilitiesItems;
    private RecyclerView aRecyclerView;
    private GridLayoutManager aLayoutManager;
    private AbilityDetailAdapter aRecycleAdapter;

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

        if ( bundle != null ) {
            clsApc = bundle.getString("clsApc");
            clsName = bundle.getString("clsName");
            clsAbility = bundle.getString("clsAbility");
        }
        getSupportActionBar().setTitle(clsName + " Abilities");

        TextView txtViewStory = (TextView) findViewById(R.id.ablSubtitle);
        txtViewStory.setText(clsName);

        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtDescription.setText(Html.fromHtml(clsAbility));

        //Get Base Abilities
        abilitiesItems = new ArrayList<>();
        abilitiesDatabase = new AbilitiesDatabase(AbilitiesActivity.this);
        abilitiesItems = abilitiesDatabase.getAbilities(clsApc);
        abilitiesDatabase.close();

        //Set RecyclerView
        aRecyclerView = (RecyclerView) findViewById(R.id.abilitiesList);

        if (aRecyclerView != null) {
            aLayoutManager = new GridLayoutManager(AbilitiesActivity.this, 1, GridLayoutManager.VERTICAL, false);
            aRecyclerView.setLayoutManager(aLayoutManager);
        }

        //Set Adapter
        aRecycleAdapter = new AbilityDetailAdapter(abilitiesItems);
        aRecyclerView.setAdapter(aRecycleAdapter);
        aRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AbilitiesActivity.this, aRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            public void onItemClick(View view, int position)
            {
                boolean wrapInScrollView = true;
                MaterialDialog dialog = new MaterialDialog.Builder(AbilitiesActivity.this)
                        .title(abilitiesItems.get(position).getAbilityName())
                        .customView(R.layout.ability_dialog, wrapInScrollView)
                        .positiveText(R.string.positive)
                        .show();

                View ablView = dialog.getCustomView();

                TextView ablLevel = (TextView) ablView.findViewById(R.id.ablLevel);
                ablLevel.setText(String.valueOf(abilitiesItems.get(position).getLevelAquired()));

                TextView ablCastingActivation = (TextView) ablView.findViewById(R.id.ablCastingActivation);
                if (abilitiesItems.get(position).getAbilityPassive() == "True") {
                    ablCastingActivation.setText("Passive");
                } else {
                    if (abilitiesItems.get(position).getCastingTime() == 0) {
                        ablCastingActivation.setText("Instant");
                    } else {
                        ablCastingActivation.setText("Activation: " +  String.valueOf(abilitiesItems.get(position).getCastingTime()) + "s");
                    }
                }

                TextView ablChanneled = (TextView) ablView.findViewById(R.id.ablChanneled);
                if (abilitiesItems.get(position).getChannelingTime() == 0) {
                    ablChanneled.setVisibility(View.GONE);
                } else {
                    ablChanneled.setText("Channeled: " +  String.valueOf(abilitiesItems.get(position).getCastingTime()) + "s");
                }

                TextView ablDescription = (TextView) ablView.findViewById(R.id.ablDescription);
                ablDescription.setText(abilitiesItems.get(position).getAbilityDescription());
            }

            public void onItemLongClick(View view, int position)
            {

            }
        }));

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
}