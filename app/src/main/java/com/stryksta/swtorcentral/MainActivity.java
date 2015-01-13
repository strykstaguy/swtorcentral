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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import com.stryksta.swtorcentral.adapters.CharacterDrawerAdapter;
import com.stryksta.swtorcentral.adapters.DrawerAdapter;
import com.stryksta.swtorcentral.util.database.CharacterDatabase;
import com.stryksta.swtorcentral.util.FragmentUtils;
import com.stryksta.swtorcentral.util.SessionManager;

import com.heinrichreimersoftware.materialdrawer.DrawerFrameLayout;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

public class MainActivity extends ActionBarActivity {

	// Drawer
	private DrawerFrameLayout mDrawerLayout;
	private ListView mDrawerList;
	
	private LinearLayout mDrawerView;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	//private String[] menuItems;
	//private String[] menuItemsIcon;
	private String mUserCharacter;
	private String mUserCharacterLegacy;
	private CharacterDatabase db;
	
	private ExpandableListView mCharacterListView;
	CharacterDrawerAdapter mCharacterAdapter;
	ArrayList<String> mCharacterArray = new ArrayList<String>();
	List<String> mCharacterTitles;
    HashMap<String, List<String>> mCharacterDetails;
    private Toolbar mToolbar;
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
		//menuItems = getResources().getStringArray(R.array.drawerItems);
		//menuItemsIcon = getResources().getStringArray(R.array.drawerIcons);
	    mDrawerLayout = (DrawerFrameLayout) findViewById(R.id.drawer_layout);
	    //mDrawerList = (ListView) findViewById(R.id.drawer);
	    //mDrawerView = (LinearLayout) this.findViewById(R.id.drawer_view);


	 	// set a custom shadow that overlays the main content when the drawer opens
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        //mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.swtor_blue));

        //DrawerAdapter mAdapter = new DrawerAdapter(this);// Add First Header

        /*

        int res = 0;
		for (String item : menuItems) {

			int id_title = getResources().getIdentifier(item, "string", this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon[res], "drawable", this.getPackageName());

			DrawerItem mItem = new DrawerItem(id_title, id_icon);
			//if (res==0) mItem.counter=10;
			mAdapter.addItem(mItem);
			res++;
		}

        */

        //mDrawerList.setAdapter(mAdapter);
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.closeDrawer();
        //Add Header
        //LayoutInflater inflater = getLayoutInflater();
        //final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
               // mDrawerList, false);

        //mDrawerList.addHeaderView(header, null, true); // true = clickable

        //mCharacterListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //CharacterSelection();

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_feed))
                        .setTextPrimary(getString(R.string.draweritem1))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ReaderActivity(), "Reader");
                                mDrawerLayout.closeDrawer();
                            }
                        })
        );
        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_database))
                        .setTextPrimary(getString(R.string.draweritem2))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent serverIntent = new Intent(MainActivity.this, ServerActivity.class);
                                startActivity(serverIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_datacron))
                        .setTextPrimary(getString(R.string.draweritem3))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new DatacronActivity(), "Datacron");
                                mDrawerLayout.closeDrawer();
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_shield))
                        .setTextPrimary(getString(R.string.draweritem4))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ClassesActivity(), "Classes");
                                mDrawerLayout.closeDrawer();
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_progression))
                        .setTextPrimary(getString(R.string.draweritem5))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new FactionFragment(), "Faction");
                                mDrawerLayout.closeDrawer();
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_calendar_month))
                        .setTextPrimary(getString(R.string.draweritem6))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent eventIntent = new Intent(MainActivity.this, EventsActivity.class);
                                startActivity(eventIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_users))
                        .setTextPrimary(getString(R.string.draweritem7))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent characterIntent = new Intent(MainActivity.this, CharacterActivity.class);
                                startActivity(characterIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_achievement))
                        .setTextPrimary(getString(R.string.draweritem8))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent achievementIntent = new Intent(MainActivity.this, AchievementActivity.class);
                                startActivity(achievementIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_book))
                        .setTextPrimary(getString(R.string.draweritem9))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent tutorialIntent = new Intent(MainActivity.this, TutorialActivity.class);
                                startActivity(tutorialIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_gear))
                        .setTextPrimary(getString(R.string.draweritem10))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(settingsIntent);
                            }
                        })
        );

        mDrawerLayout.addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_action_gear))
                        .setTextPrimary(getString(R.string.draweritem11))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            public void onClick(DrawerItem drawerItem, int id, int position) {
                                Intent testIntent = new Intent(MainActivity.this, TestActivity.class);
                                startActivity(testIntent);
                            }
                        })
        );

        mDrawerLayout.setProfile(
                new DrawerProfile()
                        .setAvatar(getResources().getDrawable(R.drawable.ic_action_user))
                        .setBackground(getResources().getDrawable(R.color.gray_23))
                        .setName(getString(R.string.test_profile_name))
                        .setDescription(getString(R.string.test_profile_desc))
                        .setOnProfileClickListener(new DrawerProfile.OnProfileClickListener() {
                            public void onClick(DrawerProfile drawerProfile) {
                                Intent serverIntent = new Intent(MainActivity.this, ServerActivity.class);
                                startActivity(serverIntent);
                            }
                        })
        );

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
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ReaderActivity(), "Reader");
	        break;
	    case 1:
	    	Intent serverIntent = new Intent(this, ServerActivity.class);
	        startActivity(serverIntent);
	        break;
	    case 2:
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new DatacronActivity(), "Datacron");
	        break;
	    case 3:
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new ClassesActivity(), "Classes");
	        break;
	    case 4:
	    	FragmentUtils.switchFragmentsInActivity(MainActivity.this, R.id.main_content, new FactionFragment(), "Faction");
	        break;
	    case 5:
	    	Intent eventIntent = new Intent(this, EventsActivity.class);
	        startActivity(eventIntent);
	        break;
	    case 6:
	    	Intent characterIntent = new Intent(this, CharacterActivity.class);
	        startActivity(characterIntent);
	        break;
	    case 7:
	    	Intent achievementIntent = new Intent(this, AchievementActivity.class);
	        startActivity(achievementIntent);
	        break;
	    case 8:
	    	Intent tutorialIntent = new Intent(this, TutorialActivity.class);
	        startActivity(tutorialIntent);
	        break;
	    case 9:
	    	Intent settingsIntent = new Intent(this, SettingsActivity.class);
	        startActivity(settingsIntent);
	        break;
	    case 10:
	        Intent testIntent = new Intent(this, TestActivity.class);
	        startActivity(testIntent);
	        break;
	    default:
	}
        // update selected item and title, then close the drawer
        //mDrawerList.setItemChecked(position, true);
        //setTitle(mGalaxyTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerView);
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