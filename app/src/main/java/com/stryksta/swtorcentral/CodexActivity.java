package com.stryksta.swtorcentral;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.stryksta.swtorcentral.adapters.CodexAdapter;
import com.stryksta.swtorcentral.data.CodexItem;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;

public class CodexActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<CodexItem> cdxItems;
    private CodexAdapter mRecycleAdapter;
    String cdxCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codex_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Codexes");

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            cdxCategory = bundle.getString("cdxCategory");

        }

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(CodexActivity.this, R.color.colorPrimary));
        }

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.codexList);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(CodexActivity.this, 1, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }


        //Get Codex Categories
        codexDB = new CodexDatabase(CodexActivity.this);
        cdxItems = codexDB.getCodexes(cdxCategory, "Republic");

        //Set Adapter
        mRecycleAdapter = new CodexAdapter(cdxItems);
        mRecyclerView.setAdapter(mRecycleAdapter);

        //Close DB
        codexDB.close();

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
}