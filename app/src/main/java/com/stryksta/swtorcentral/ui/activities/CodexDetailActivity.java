package com.stryksta.swtorcentral.ui.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        getSupportActionBar().setTitle(cdxCategory);

        codexDatabase = new CodexDatabase(CodexDetailActivity.this);
        mRecyclerView = (RecyclerView) findViewById(R.id.codexDetailList);

        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexDetailActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        new GetCodexes().execute(cdxCategory);
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

    private class GetCodexes extends AsyncTask<String, Void, ArrayList<CodexItem>> {

        protected void onPreExecute() {
            super.onPreExecute();
            cdxItems = new ArrayList<>();
        }

        @Override
        protected ArrayList<CodexItem> doInBackground(String... urls) {

            try {
                cdxItems = codexDatabase.getCodexes(urls[0]);
            } catch (Exception e) {
                if(e.getMessage() != null) {
                    Log.e("SWTORCentral", e.getMessage());
                }
            }
            return null;
        }

        protected void onPostExecute(ArrayList<CodexItem> result) {
            //Set Codex Adapter
            mRecycleAdapter = new CodexAdapter(cdxItems);
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setAdapter(mRecycleAdapter);
        }
    }
}