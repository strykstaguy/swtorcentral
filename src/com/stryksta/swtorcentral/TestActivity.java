package com.stryksta.swtorcentral;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.CharacterDatabase;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class TestActivity extends FragmentActivity {
    
	ExpandableListView expandableListView;
    TestExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    SessionManager session;
    String mUserCharacter;
    private CharacterDatabase db;
    ArrayList<String> characterArray = new ArrayList<String>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getActionBar().setTitle("Test");
        
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        
        session = new SessionManager(getApplicationContext());
        
        db = new CharacterDatabase(TestActivity.this);
			characterArray = db.CharacterSelectionList();
		db.close();
		
        if (session.isLoggedIn()) {
	        HashMap<String, String> user = session.getUserDetails();
	        mUserCharacter = user.get(SessionManager.KEY_NAME);
        } else {
        	mUserCharacter = "None";
        }
        
        
        expandableListDetail = new HashMap<String, List<String>>();
        expandableListDetail.put(mUserCharacter, characterArray);
        
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new TestExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        
        expandableListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
            		int groupPosition, int childPosition, long id) {
                		//Toast.makeText(getApplicationContext(), expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
            			
                		String characterSelectionText = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                		//expandableListTitle.get(groupPosition);
                		//expandableListTitle.set(groupPosition, "test");
                		if (characterSelectionText.equals("Add Character")) {
                			Toast.makeText(getApplicationContext(), "Add Character!", Toast.LENGTH_SHORT).show();
                		} else if (characterSelectionText.equals(mUserCharacter)) {
                			Toast.makeText(getApplicationContext(), "You are already logged in to this character", Toast.LENGTH_SHORT).show();
                		} else {
                			//characterLogin(characterSelectionText);
                			//Log selected user in
                			int characterID = Integer.parseInt(db.getCharacterID(characterSelectionText));
                	    	session.createLoginSession(characterSelectionText, characterID);
                			Toast.makeText(getApplicationContext(), characterSelectionText + " logged in.", Toast.LENGTH_SHORT).show();
                			expandableListAdapter.notifyDataSetChanged();
                		}
                		
                return false;
            }
        });
        
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