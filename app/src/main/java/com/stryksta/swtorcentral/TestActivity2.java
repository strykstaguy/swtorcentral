package com.stryksta.swtorcentral;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.stryksta.swtorcentral.util.FragmentUtils;

public class TestActivity2 extends ActionBarActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Abilities");

        if (!FragmentUtils.isFragmentPresent(TestActivity2.this, "Test1")) {
            FragmentUtils.switchFragmentsInActivity(TestActivity2.this, R.id.testFrame, new Test1Fragment(), "Test1");
        }

        //Debug the thread name
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
            Fragment trestsdg =  FragmentUtils.getActiveFragment(TestActivity2.this);
        } else {
            this.finish();
        }
    }
}