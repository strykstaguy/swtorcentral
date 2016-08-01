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

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.adapters.CodexAdapter;
import com.stryksta.swtorcentral.ui.adapters.DropMenuAdapter;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;
import java.util.List;

public class CodexActivity extends AppCompatActivity implements OnFilterDoneListener{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DropDownMenu dropDownMenu;
    private CodexDatabase codexDB;
    List<String> cdxCategoryItems;
    ArrayList<CodexItem> cdxItems;
    String drpCategory;
    String drpFaction;

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

        //Default Filters
        drpCategory = "All";
        drpFaction = "All";

        //Set RecyclerView an Dropdown
        dropDownMenu = (DropDownMenu) findViewById(R.id.dropDownMenu);
        mRecyclerView = (RecyclerView) findViewById(R.id.mFilterContentView);

        //Get Codex Categories
        codexDB = new CodexDatabase(CodexActivity.this);
        cdxCategoryItems = codexDB.getCategories();
        cdxItems = codexDB.getAllCodexes();
        //Close DB
        codexDB.close();

        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }

        //Set Dropdown
        String[] titleList = new String[]{"Category", "Faction"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(CodexActivity.this, titleList, CodexActivity.this, cdxCategoryItems));

        //Set Adapter
        mRecycleAdapter = new CodexAdapter(cdxItems);
        mRecyclerView.setAdapter(mRecycleAdapter);

    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        dropDownMenu.setPositionIndicatorText(position, positionTitle);

        if (position == 0) {
            drpCategory = positionTitle;
        } else if (position == 1) {
            drpFaction = positionTitle;
        }

        updateItems(drpCategory, drpFaction);
        dropDownMenu.close();
        //mFilterContentView.setText(FilterUrl.instance().toString());
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