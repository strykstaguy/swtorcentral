package com.stryksta.swtorcentral.ui.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.models.FilterItem;
import com.stryksta.swtorcentral.models.ServerItem;
import com.stryksta.swtorcentral.ui.adapters.CodexAdapter;
import com.stryksta.swtorcentral.ui.adapters.CodexFilterAdapter;
import com.stryksta.swtorcentral.ui.adapters.DropMenuAdapter;
import com.stryksta.swtorcentral.models.CodexItem;
import com.stryksta.swtorcentral.ui.views.ItemOffsetDecoration;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipCloud;
import com.stryksta.swtorcentral.ui.views.chipcloud.ChipListener;
import com.stryksta.swtorcentral.util.database.CodexDatabase;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CodexActivity extends AppCompatActivity{
    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<String> cdxCategoryItems;
    ArrayList<CodexItem> cdxItems;

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
        drpFaction = "All";

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.listCodexes);

        //Get Codex Categories
        codexDB = new CodexDatabase(CodexActivity.this);
        cdxCategoryItems = codexDB.getCategories();
        cdxItems = codexDB.getAllCodexes();
        //Close DB
       //codexDB.close();

        ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);
        for (String mString : cdxCategoryItems) {
            chipCloud.addChip(mString);
        }

        chipCloud.setChipListener(new ChipListener() {
            @Override
            public void chipSelected(int index, String text) {
                //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                updateItems(text);
            }

            @Override
            public void chipDeselected(int index) {

            }
        });

        //Set Codex Adapter
        if (mRecyclerView != null) {
            mLayoutManager = new LinearLayoutManager(CodexActivity.this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }


    }

    public class getCodexes extends AsyncTask<Void, Void, ArrayList<CodexItem>> {

        @Override
        protected ArrayList<CodexItem> doInBackground(Void... voids) {



            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<CodexItem> result) {
            //Set Codex Adapter
            mRecycleAdapter = new CodexAdapter(cdxItems);
            mRecyclerView.setAdapter(mRecycleAdapter);
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
}