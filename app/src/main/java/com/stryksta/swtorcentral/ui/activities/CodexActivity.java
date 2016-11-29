package com.stryksta.swtorcentral.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.stryksta.swtorcentral.R;
import com.stryksta.swtorcentral.ui.fragments.Category1Fragment;
import com.stryksta.swtorcentral.ui.fragments.CodexCategoryFragment;
import com.stryksta.swtorcentral.ui.fragments.CodexDetailFragment;
import com.stryksta.swtorcentral.util.FragmentUtils;

public class CodexActivity extends AppCompatActivity {
    String mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codex_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (!FragmentUtils.isFragmentPresent(CodexActivity.this, "Categories")) {
        	FragmentUtils.switchFragmentsInActivity(CodexActivity.this, R.id.codexFrame, new CodexCategoryFragment(), "Codex Category");
        }
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
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
		if (getFragmentManager().getBackStackEntryCount() > 1) {
			getFragmentManager().popBackStack();
	    } else {
	        this.finish();
	    }
    }

}