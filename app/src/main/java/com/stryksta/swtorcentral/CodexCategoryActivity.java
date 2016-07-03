package com.stryksta.swtorcentral;

import android.content.Intent;
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

import com.stryksta.swtorcentral.adapters.CodexCategoryAdapter;
import com.stryksta.swtorcentral.data.CodexCategoryItem;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.CodexDatabase;

import java.util.ArrayList;

public class CodexCategoryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;

    private CodexDatabase codexDB;
    ArrayList<CodexCategoryItem> cdxCategoryItems;
    private CodexCategoryAdapter mRecycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codex_category_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Codexes");

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(CodexCategoryActivity.this, R.color.colorPrimary));
        }

        //Set RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.codexList);

        if (mRecyclerView != null) {
            mLayoutManager = new GridLayoutManager(CodexCategoryActivity.this, 2, GridLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }


        //Get Codex Categories
        codexDB = new CodexDatabase(CodexCategoryActivity.this);
        //cdxCategoryItems = codexDB.getCategories();

        //Set Adapter
        mRecycleAdapter = new CodexCategoryAdapter(cdxCategoryItems);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CodexCategoryActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            public void onItemClick(View view, int position)
            {
                Bundle bundle = new Bundle();
                bundle.putString("cdxCategory", cdxCategoryItems.get(position).getCategory());

                Intent addCharacterIntent = new Intent(CodexCategoryActivity.this, CodexActivity.class);
                addCharacterIntent.putExtras(bundle);
                startActivity(addCharacterIntent);
            }

            public void onItemLongClick(View view, int position)
            {

            }
        }));
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