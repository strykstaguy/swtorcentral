package com.stryksta.swtorcentral;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.stryksta.swtorcentral.adapters.AbilityDetailAdapter;
import com.stryksta.swtorcentral.adapters.CompanionClassAdapter;
import com.stryksta.swtorcentral.data.AbilitiesItem;
import com.stryksta.swtorcentral.data.CompanionItem;
import com.stryksta.swtorcentral.util.DividerItemDecoration;
import com.stryksta.swtorcentral.util.RecyclerItemClickListener;
import com.stryksta.swtorcentral.util.database.AbilitiesDatabase;
import com.stryksta.swtorcentral.util.database.CompanionDatabase;

import java.util.ArrayList;

public class CompanionsActivity extends AppCompatActivity {

	//Base Companions
	private CompanionDatabase baseCompanionDatabase;
	ArrayList<CompanionItem> baseCompanions;

	private RecyclerView baseRecyclerView;
	private GridLayoutManager baseLayoutManager;
	private CompanionClassAdapter baseRecycleAdapter;

	private String clsApc;
	private String clsName;
	private String txtNode;
	private String clsAbility;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companions_main);

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
		if (bundle != null) {
			clsApc = bundle.getString("clsApc");
			clsName = bundle.getString("clsName");
			clsAbility = bundle.getString("clsAbility");
			txtNode = bundle.getString("txtNode");
		}

        getSupportActionBar().setTitle(clsName + "Companions");

		TextView ablTitle = (TextView) findViewById(R.id.comTitle);
		ablTitle.setText("Companions");

		TextView ablSubtitle = (TextView) findViewById(R.id.comSubtitle);
		ablSubtitle.setText(clsName);

		//Get Base Companions
		baseCompanionDatabase = new CompanionDatabase(CompanionsActivity.this);
		baseCompanions = new ArrayList<>();
		baseCompanions = baseCompanionDatabase.getOriginalCompanions(txtNode);
		baseCompanionDatabase.close();

		//Set Base RecyclerView
		baseRecyclerView = (RecyclerView) findViewById(R.id.companionsList);

		if (baseRecyclerView != null) {
			baseLayoutManager = new GridLayoutManager(CompanionsActivity.this, 1, GridLayoutManager.VERTICAL, false);
			baseRecyclerView.setLayoutManager(baseLayoutManager);
		}

		//Set Base Adapter
		baseRecycleAdapter = new CompanionClassAdapter(baseCompanions);
		baseRecyclerView.addItemDecoration(new DividerItemDecoration(CompanionsActivity.this, GridLayoutManager.VERTICAL));
		baseRecyclerView.setAdapter(baseRecycleAdapter);
		baseRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CompanionsActivity.this, baseRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			public void onItemClick(View view, int position) {
				boolean wrapInScrollView = true;
				MaterialDialog dialog = new MaterialDialog.Builder(CompanionsActivity.this)
						.title(baseCompanions.get(position).getTitle())
						.customView(R.layout.ability_dialog, wrapInScrollView)
						.positiveText(R.string.positive)
						.show();

				View ablView = dialog.getCustomView();
				//showAbilityDetail(ablView, position, baseCompanions);

			}

			public void onItemLongClick(View view, int position) {

			}
		}));
     	//Debug the thread name
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