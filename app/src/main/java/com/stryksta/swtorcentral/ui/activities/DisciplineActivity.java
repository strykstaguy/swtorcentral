package com.stryksta.swtorcentral.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.AbilitiesItem;
import com.stryksta.swtorcentral.models.DisciplineItem;
import com.stryksta.swtorcentral.models.SkillItem;
import com.stryksta.swtorcentral.ui.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.util.DividerItemDecoration;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;

import java.util.ArrayList;

public class DisciplineActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ClassesDatabase classDB;
    ArrayList<SkillItem> skillItems;
    ArrayList<AbilitiesItem> abilityItems;

    private RecyclerView skillsRecyclerView;
    private GridLayoutManager skillsLayoutManager;
    private AbilityDetailAdapter skillsRecycleAdapter;

    String disIcon;
    String advClassName;
    String disName;
    String disDescription;
    String disAPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discipline_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(DisciplineActivity.this, R.color.colorPrimary));
        }

        //get bundle info
        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            advClassName = bundle.getString("advClassName");
            disName = bundle.getString("disName");
            disDescription = bundle.getString("disDescription");
            disIcon = bundle.getString("disIcon");
            disAPC = bundle.getString("disAPC");
        }

        //Set Discipline
        TextView txtDisName = (TextView) findViewById(R.id.advDiscipline);
        txtDisName.setText(disName);

        //Set Advanced Class
        TextView advMainClass = (TextView) findViewById(R.id.advClass);
        advMainClass.setText(advClassName);

        //Set Description
        TextView txtdisDescription = (TextView) findViewById(R.id.txtDisDesc);
        txtdisDescription.setText(disDescription);

        //Set Discipline Image
        Drawable advDisciplineIcon = getDisciplineIcon(disIcon);
        ImageView disImage = (ImageView) findViewById(R.id.advImage);
        disImage.setImageDrawable(advDisciplineIcon);
        //disImage.setImageResource(advDisciplineIcon);

        classDB = new ClassesDatabase(DisciplineActivity.this);
        abilityItems = classDB.getDisciplineAbilities(disAPC);

        //Set Base RecyclerView
        skillsRecyclerView = (RecyclerView) findViewById(R.id.disciplineAbilities);
        skillsRecyclerView.setNestedScrollingEnabled(false);

        if (skillsRecyclerView != null) {
            skillsLayoutManager = new GridLayoutManager(DisciplineActivity.this, 1, GridLayoutManager.VERTICAL, false);
            skillsRecyclerView.setLayoutManager(skillsLayoutManager);
        }

        //Set Base Adapter
        skillsRecycleAdapter = new AbilityDetailAdapter(abilityItems);
        skillsRecyclerView.setNestedScrollingEnabled(false);
        skillsRecyclerView.addItemDecoration(new DividerItemDecoration(DisciplineActivity.this, GridLayoutManager.VERTICAL));
        skillsRecyclerView.setAdapter(skillsRecycleAdapter);

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

    public Drawable getDisciplineIcon(String disType) {
        Drawable disciplineIcon;
        switch(disType) {
            case "Tank":
                disciplineIcon = ContextCompat.getDrawable(DisciplineActivity.this, R.drawable.ic_tank);
                break;
            case "DPS":
                disciplineIcon = ContextCompat.getDrawable(DisciplineActivity.this, R.drawable.ic_damage);
                break;
            case "Healer":
                disciplineIcon = ContextCompat.getDrawable(DisciplineActivity.this, R.drawable.ic_heals);
                break;
            default:
                disciplineIcon = ContextCompat.getDrawable(DisciplineActivity.this, R.drawable.ic_heals);
        }
        return disciplineIcon;
    }
}