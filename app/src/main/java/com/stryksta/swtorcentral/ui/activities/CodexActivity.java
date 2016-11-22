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
import com.stryksta.swtorcentral.util.database.CodexDatabase;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

public class CodexActivity extends AppCompatActivity{
    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<String> cdxCategoryItems;
    ArrayList<CodexItem> cdxItems;

    private CodexAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codex_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(CodexActivity.this, R.color.colorPrimary));
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
        }

        getSupportActionBar().setTitle("Codexes");

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.listCodexes);

        //Get Codex Categories
        codexDB = new CodexDatabase(CodexActivity.this);
       // cdxCategoryItems = codexDB.getCategories();
        new CodexActivity.GetCodexes().execute();

        //Close DB
       //codexDB.close();

        //Set Codex Adapter
        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

    }
    public void updateItems(String cdxCategory) {

        //codexDB = new CodexDatabase(CodexActivity.this);

        ArrayList<CodexItem> cdxUpdatedItems;
        cdxUpdatedItems = codexDB.getCodexes(cdxCategory);

        //Refresh RecyclerView
        mRecycleAdapter.updateItems(cdxUpdatedItems);

        //codexDB.close();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //FilterUrl.instance().clear();
    }

    private class GetCodexes extends AsyncTask<String, Void, ArrayList<CodexItem>> {

        protected void onPreExecute() {
            super.onPreExecute();
            cdxItems = new ArrayList<>();
        }

        @Override
        protected ArrayList<CodexItem> doInBackground(String... urls) {

            try {
                //cdxItems = codexDB.getCodexes("Achievement: Datacrons");
                cdxItems = codexDB.getAllCodexes();
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
            mRecyclerView.addItemDecoration(
                    new HorizontalDividerItemDecoration.Builder(CodexActivity.this)
                            .color(ContextCompat.getColor(CodexActivity.this, R.color.backgroundlight))
                            .sizeResId(R.dimen.divider)
                            .marginResId(R.dimen.divider_leftmargin, R.dimen.divider_rightmargin)
                            .build());
            mRecyclerView.setAdapter(mRecycleAdapter);
        }
    }
}