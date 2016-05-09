package com.stryksta.swtorcentral;

import com.stryksta.swtorcentral.adapters.AbilityPagerAdapter;
import com.stryksta.swtorcentral.adapters.PlanetPagerAdapter;
import com.stryksta.swtorcentral.data.ClassItem;
import com.stryksta.swtorcentral.util.database.ClassesDatabase;
import com.stryksta.swtorcentral.util.database.PlanetDatabase;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

public class AbilitiesActivity extends AppCompatActivity {
    private String planetText;
    private String factionText;
    private String typeText;
    private ClassesDatabase clsDatabase;
    ArrayList<ClassItem> classItems;
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
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();

        if ( bundle != null ) {
            planetText = bundle.getString("planet");
            factionText = bundle.getString("faction");
            typeText = bundle.getString("type");
        }
        getSupportActionBar().setTitle("Abilities");

        //Get Classes Info
        clsDatabase = new ClassesDatabase(AbilitiesActivity.this);
        classItems = clsDatabase.getClasses();
        clsDatabase.close();


        ViewPager viewPager = (ViewPager) findViewById(R.id.ability_pager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Debug the thread name
        Log.d("SWTORCentral", Thread.currentThread().getName());

    }

    private void setupViewPager(ViewPager viewPager) {
        AbilityPagerAdapter adapter = new AbilityPagerAdapter(getSupportFragmentManager());
        //adapter.addFragment(new AbilityFragment(), "Information");
        //adapter.addFragment(new AbilityFragment(), "Datacrons");
        //adapter.addFragment(new AbilityFragment(), "Lore");
        int clsTabCount = 0;
        for (ClassItem listClassName : classItems) {
            //Log.d("SWTORCentral", "Class Name: " + clsTabCount);
            adapter.addFragment(AbilityFragment.newInstance(clsTabCount, listClassName.getClassName(), listClassName.getApc()), listClassName.getClassName());
            clsTabCount++;
        }

        viewPager.setAdapter(adapter);
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