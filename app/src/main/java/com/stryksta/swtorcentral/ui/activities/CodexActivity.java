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
import android.widget.Toast;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.FilterItem;
import com.stryksta.swtorcentral.ui.adapters.CodexAdapter;
import com.stryksta.swtorcentral.ui.adapters.CodexFilterAdapter;
import com.stryksta.swtorcentral.ui.adapters.DropMenuAdapter;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;
import java.util.List;

public class CodexActivity extends AppCompatActivity implements OnFilterDoneListener{
    private Toolbar mToolbar;

    private RecyclerView mFilterRecyclerView;
    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private LinearLayoutManager mFilterLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<FilterItem> cdxCategoryItems;
    ArrayList<CodexItem> cdxItems;

    String filterCategory;
    String drpFaction;

    private CodexAdapter mRecycleAdapter;
    private CodexFilterAdapter mFilterRecycleAdapter;

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

        //Default Filters
        drpFaction = "All";

        //Set RecyclerViews
        mFilterRecyclerView = (RecyclerView) findViewById(R.id.listFilter);
        mRecyclerView = (RecyclerView) findViewById(R.id.listCodexes);

        //Get Codex Categories
        codexDB = new CodexDatabase(CodexActivity.this);
        cdxCategoryItems = codexDB.getCategories();
        cdxItems = codexDB.getAllCodexes();
        //Close DB
        codexDB.close();

        //Set Codex Adapter
        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        //Set Filter Adapter
        if (mFilterRecyclerView != null) {
            mFilterLayoutManager = new LinearLayoutManager(CodexActivity.this, LinearLayoutManager.HORIZONTAL, false);
            mFilterRecyclerView.setLayoutManager(mFilterLayoutManager);
        }

        //Set Codex Adapter
        mRecycleAdapter = new CodexAdapter(cdxItems);
        mRecyclerView.setAdapter(mRecycleAdapter);

        //Set Filter Adapter
        mFilterRecycleAdapter = new CodexFilterAdapter(cdxCategoryItems, CodexActivity.this);
        mFilterRecyclerView.setAdapter(mFilterRecycleAdapter);
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {

        Toast.makeText(getApplicationContext(), "Postion: " + position + ", Title: " + positionTitle, Toast.LENGTH_SHORT).show();
        //updateItems(drpCategory, drpFaction);
    }

    public void updateItems(String cdxCategory, String cdxFaction) {

        codexDB = new CodexDatabase(CodexActivity.this);

        //Update List to reflect new completed/incomplete items
        cdxItems.clear();
        cdxItems = codexDB.getCodexes(cdxCategory, cdxFaction);

        //Refresh RecyclerView
        mRecycleAdapter.updateItems(cdxItems);
        mRecycleAdapter.notifyDataSetChanged();

        codexDB.close();
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
}