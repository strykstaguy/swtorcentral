package com.stryksta.swtorcentral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView.OnChildClickListener;

import com.stryksta.swtorcentral.data.DrawerItem;
import com.stryksta.swtorcentral.util.CharacterDatabase;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

public class MainActivity extends FragmentActivity  {

	// Drawer
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private LinearLayout mDrawerView;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] menuItems;
	private String[] menuItemsIcon;
	private String mUserCharacter;
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

		session = new SessionManager(getApplicationContext());
		
		//Start Drawer
		mTitle = mDrawerTitle = getTitle();
		menuItems = getResources().getStringArray(R.array.drawerItems);
		menuItemsIcon = getResources().getStringArray(R.array.drawerIcons);
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	    mDrawerList = (ListView) findViewById(R.id.drawer);
	    mDrawerView = (LinearLayout) this.findViewById(R.id.drawer_view);
	    mCharacterListView = (ExpandableListView) findViewById(R.id.expandableListView);
	    
	    CharacterSelection();
	    
	 	// set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        DrawerAdapter mAdapter = new DrawerAdapter(this);// Add First Header
        
        //mAdapter.addHeader(R.string.header1);
        
        int res = 0;
		for (String item : menuItems) {

			int id_title = getResources().getIdentifier(item, "string", this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon[res], "drawable", this.getPackageName());

			DrawerItem mItem = new DrawerItem(id_title, id_icon);
			//if (res==0) mItem.counter=10;
			mAdapter.addItem(mItem);
			res++;
		}
		
        // set up the drawer's list view with items and click listener
        /*mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, menuItems));*/
        mDrawerList.setAdapter(mAdapter); 
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
     // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        //getActionBar().setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.action_bar)));
     // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
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
		} else {
			mUserCharacter = "None";
		}
		
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
                		} else if (characterSelectionText.equals(mUserCharacter)) {
                			Toast.makeText(getApplicationContext(), "You are already logged in to this character", Toast.LENGTH_SHORT).show();
                		} else {
                			//Log selected user in
                			int characterID = Integer.parseInt(db.getCharacterID(characterSelectionText));
                			String characterImage = db.getCharacterImage(characterSelectionText);
                	    	session.createLoginSession(characterSelectionText, characterID, characterImage);
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
        mDrawerLayout.isDrawerOpen(mDrawerView);
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
	        case R.id.menu_refresh:
	        	Toast.makeText(getApplicationContext(), "Refresh!", Toast.LENGTH_SHORT).show();
	            break;
	    }
	    return true;
	}
	/* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        
   	 switch(position) {
	    case 0:
	    	//Toast.makeText(getApplicationContext(), "First Fragment", Toast.LENGTH_SHORT).show();
	    	//switchFragment(new ReaderActivity());
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main, new ReaderActivity(), "Reader");
	        break;
	    case 1:
	    	Intent serverIntent = new Intent(this, ServerActivity.class);
	        startActivity(serverIntent);
	        break;
	    case 2:
	    	//switchFragment(new DatacronActivity());
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main, new DatacronActivity(), "Datacron");
	        break;
	    case 3:
	    	//switchFragment(new ClassesActivity());
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main, new ClassesActivity(), "Classes");
	        break;
	    case 4:
	    	Intent settingsIntent = new Intent(this, SettingsActivity.class);
	        startActivity(settingsIntent);
	        break;
	    case 5:
	    	//Intent factionIntent = new Intent(this, FactionActivity.class);
	        //startActivity(factionIntent);
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main, new FactionFragment(), "Faction");
	        break;
	    case 6:
	    	Intent eventIntent = new Intent(this, EventsActivity.class);
	        startActivity(eventIntent);
	        break;
	    case 7:
	    	Intent characterIntent = new Intent(this, CharacterActivity.class);
	        startActivity(characterIntent);
	        break;
	    case 8:
	    	Intent achievementIntent = new Intent(this, AchievementActivity.class);
	        startActivity(achievementIntent);
	        break;
	    case 9:
	    	Intent testIntent = new Intent(this, TutorialActivity.class);
	        startActivity(testIntent);
	        break;
	    default:
	}
        // update selected item and title, then close the drawer
        //mDrawerList.setItemChecked(position, true);
        //setTitle(mGalaxyTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerView);
    }
    
    public void switchFragment(Fragment newFragment) {
    	if (newFragment.isAdded()) {
    		getFragmentManager()
			.beginTransaction()
			.show(newFragment)
			.commit();
    	} else {
    		getFragmentManager()
        	.beginTransaction()
        	.replace(R.id.main, newFragment)
        	.addToBackStack(null)
        	.commit();
    	}
    	
    }
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
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
        mDrawerToggle.syncState();
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