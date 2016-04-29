package com.stryksta.swtorcentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import com.stryksta.swtorcentral.adapters.CharacterDrawerAdapter;
import com.stryksta.swtorcentral.util.database.CharacterDatabase;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

public class MainActivity extends AppCompatActivity {

	// Drawer
	private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
	private CharSequence mDrawerTitle;


	private CharSequence mTitle;
	private String mUserCharacter;
	private String mUserCharacterLegacy;
	private CharacterDatabase db;
	
	private ExpandableListView mCharacterListView;
	CharacterDrawerAdapter mCharacterAdapter;
	ArrayList<String> mCharacterArray = new ArrayList<String>();
	List<String> mCharacterTitles;
    HashMap<String, List<String>> mCharacterDetails;

	SessionManager session;
	
	private static final int ADD_PARTICIPANT = 1120;
	//End Drawer
	
	/**
	 * This method creates main application view
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

	    session = new SessionManager(getApplicationContext());
		 
		//Start Drawer
		mTitle = mDrawerTitle = getTitle();
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);

        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,  /* toolbar */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1: {
                        FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ReaderActivity(), "Reader");
                        break;
                    }
                    case R.id.navigation_item_2: {
                        Intent serverIntent = new Intent(MainActivity.this, ServerActivity.class);
                        startActivity(serverIntent);
                        break;
                    }
                    case R.id.navigation_item_3: {
                        FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ClassesFragment(), "Classes");
                        break;
                    }
                    case R.id.navigation_item_4: {
                        FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new FactionFragment(), "Faction");
                        break;
                    }
                    case R.id.navigation_item_5: {
                        Intent achievementIntent = new Intent(MainActivity.this, AchievementActivity.class);
                        startActivity(achievementIntent);
                        break;
                    }
                    case R.id.navigation_item_6: {
                        FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new DatacronFragment(), "Datacron");
                        break;
                    }
                    case R.id.navigation_item_7: {
                        Intent testIntent = new Intent(MainActivity.this, CodexCategoryActivity.class);
                        startActivity(testIntent);
                        break;
                    }
                    case R.id.navigation_sub_item_1: {
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                        break;
                    }
                }

                return true;
            }
        });

        if (savedInstanceState == null) {
            //selectItem(0);
            FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ReaderActivity(), "Reader");
        }
		//End Drawer
		

		// Debug the thread name
		Log.d("SWTORCentral", Thread.currentThread().getName());
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PARTICIPANT && resultCode == Activity.RESULT_OK) {
        	CharacterSelection();
        }
    }
	
	public void CharacterSelection() {
		//get characters
	    db = new CharacterDatabase(MainActivity.this);
	    mCharacterArray = db.CharacterSelectionList();
		db.close();
		
		//if user is logged in set the user information or set to none
		if (session.isLoggedIn()) {
			HashMap<String, String> user = session.getUserDetails();
			mUserCharacter = user.get(SessionManager.KEY_NAME);
			mUserCharacterLegacy = user.get(SessionManager.KEY_LEGACY);
		} else {
			mUserCharacter = "None";
		}
		
		Toast.makeText(getApplicationContext(), "Legacy: " + mUserCharacterLegacy, Toast.LENGTH_SHORT).show();
		
		mCharacterDetails = new HashMap<String, List<String>>();
		mCharacterDetails.put(mUserCharacter, mCharacterArray);
        
        mCharacterTitles = new ArrayList<String>(mCharacterDetails.keySet());
        mCharacterAdapter = new CharacterDrawerAdapter(this, mCharacterTitles, mCharacterDetails);
        mCharacterListView.setAdapter(mCharacterAdapter);

        mCharacterListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
            		int groupPosition, int childPosition, long id) {
                		String characterSelectionText = mCharacterDetails.get(mCharacterTitles.get(groupPosition)).get(childPosition);
                		if (characterSelectionText.equals("Add Character")) {

                	        Intent characterAddIntent = new Intent(MainActivity.this, CharacterAddActivity.class);
                	    	startActivityForResult(characterAddIntent, ADD_PARTICIPANT);
                		} else if (characterSelectionText.equals("Logout")) {
                			session.logoutUser();
                			Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
                			mCharacterAdapter.notifyDataSetChanged();
                		} else if (characterSelectionText.equals(mUserCharacter)) {
                			Toast.makeText(getApplicationContext(), "You are already logged in to this character", Toast.LENGTH_SHORT).show();
                		} else {
                			//Log selected user in
                			int characterID = Integer.parseInt(db.getCharacterID(characterSelectionText));
                			String characterImage = db.getCharacterImage(characterSelectionText);
                			String characterLegacy = db.getCharacterLegacy(characterSelectionText);

                	    	session.createLoginSession(characterSelectionText, characterID, characterImage, characterLegacy);
                			Toast.makeText(getApplicationContext(), characterSelectionText + " logged in.", Toast.LENGTH_SHORT).show();
                			mCharacterAdapter.notifyDataSetChanged();
                		}

                return false;
            }
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // mDrawerLayout.isDrawerOpen(mDrawerView);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
       if (mDrawerToggle.onOptionsItemSelected(item)) {
           return true;
       }
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	Toast.makeText(getApplicationContext(), "Refresh!", Toast.LENGTH_SHORT).show();
	            break;
	    }
	    return true;
	}

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    
    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager manager = (ConnectivityManager)
                c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        Log.d("SWTORCentral", "Network is " + isAvailable);
        return isAvailable;
    }
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        //mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void exitByBackKey(){
        @SuppressWarnings("unused")
		AlertDialog alertbox = new AlertDialog.Builder(this)
        .setMessage("Do you want to exit application?")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        }).show();
    }

    public void onBackPressed() {
    	if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
    		exitByBackKey();
        } else {
        	mDrawerLayout.openDrawer(Gravity.LEFT);

        }
    }
}