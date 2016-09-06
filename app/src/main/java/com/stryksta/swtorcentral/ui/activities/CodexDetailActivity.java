package com.stryksta.swtorcentral.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.ui.adapters.CodexAdapter;
import com.stryksta.swtorcentral.ui.adapters.PlanetCodexAdapter;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;

public class CodexDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private String cdxCategory;
    private String cdxPlanetID;
    private String cdxPlanetName;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private CodexDatabase codexDatabase;
    private CodexAdapter mRecycleAdapter;
    ArrayList<CodexItem> cdxItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codex_detail_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(CodexDetailActivity.this, R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            cdxCategory = bundle.getString("cdxCategory");
            cdxPlanetID = bundle.getString("cdxPlanetID");
            cdxPlanetName = bundle.getString("cdxPlanetName");
        }

        getSupportActionBar().setTitle(cdxPlanetName + " " + cdxCategory);

        codexDatabase = new CodexDatabase(CodexDetailActivity.this);
        cdxItems = codexDatabase.getCodexesByCategory(cdxCategory, cdxPlanetID);
        codexDatabase.close();

        mRecyclerView = (RecyclerView) findViewById(R.id.codexDetailList);

        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        mRecycleAdapter = new CodexAdapter(cdxItems);
        mRecyclerView.setAdapter(mRecycleAdapter);

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