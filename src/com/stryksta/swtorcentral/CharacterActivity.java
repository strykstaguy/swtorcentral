package com.stryksta.swtorcentral;
 
import java.util.ArrayList;
import java.util.HashMap;

import com.stryksta.swtorcentral.data.CharacterItem;
import com.stryksta.swtorcentral.util.AchievementsDatabase;
import com.stryksta.swtorcentral.util.CharacterDatabase;
import com.stryksta.swtorcentral.util.SessionManager;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
 
public class CharacterActivity extends FragmentActivity {
	ArrayList<CharacterItem> characterArray = new ArrayList<CharacterItem>();
	private static final int ADD_PARTICIPANT = 1121;
	ListView characterList;
	SessionManager session;
	CharacterAdapter adapter;
	String mUserCharacter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        //getActionBar().setTitle("Blank");
        
        session = new SessionManager(getApplicationContext());
        
        characterList = (ListView) findViewById(R.id.characterListView);
        registerForContextMenu(characterList);
        
        updateCharacters();
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PARTICIPANT && resultCode == Activity.RESULT_OK) {
        	updateCharacters();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.character_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	        return true;
	    case R.id.character_menu_add:
	    	
	    	Intent addCharacterIntent = new Intent(this, CharacterAddActivity.class);
	    	startActivityForResult(addCharacterIntent, ADD_PARTICIPANT);
	        //startActivity(addCharacterIntent);
	        
            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
          super.onCreateContextMenu(menu, v, menuInfo);
          if (v.getId() == R.id.characterListView) {
              MenuInflater inflater = getMenuInflater();
              inflater.inflate(R.menu.character_options_menu, menu);
          }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
          AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
          switch(item.getItemId()) {
             case R.id.character_menu_edit:
            	 
            	Bundle bundle = new Bundle();
 				bundle.putInt("character_id", adapter.getItem(info.position).getId());
 				
 				Intent addCharacterIntent = new Intent(CharacterActivity.this, CharacterEditActivity.class);
 				addCharacterIntent.putExtras(bundle);
 		    	startActivityForResult(addCharacterIntent, ADD_PARTICIPANT);
 		    	
                return true;
             case R.id.character_menu_delete:
            	 CharacterDatabase db = new CharacterDatabase(this);
            	 int characterID = adapter.getItem(info.position).getId();
            	 String selectedCharacter = adapter.getItem(info.position).getName();
            	 
            	 if (session.isLoggedIn()) {
            		 HashMap<String, String> user = session.getUserDetails();
            		 mUserCharacter = user.get(SessionManager.KEY_NAME);
           		 }
            	 
            	 if (session.isLoggedIn() && mUserCharacter == selectedCharacter) {
            		 Toast.makeText(CharacterActivity.this, "Please logout first, then try again.", Toast.LENGTH_SHORT).show();
            	 } else {
            		 if (db.removeCharacter(characterID)) {
                		 updateCharacters();
                		 Toast.makeText(CharacterActivity.this, "Successfully Removed", Toast.LENGTH_SHORT).show();
                	 } else {
                		 Toast.makeText(CharacterActivity.this, "There was an error, try again.", Toast.LENGTH_SHORT).show();
                	 }
            	 }
                return true;
              default:
                return super.onContextItemSelected(item);
          }
    }
    
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
	
	private void updateCharacters(){
		
		CharacterDatabase db = new CharacterDatabase(this);
		adapter = new CharacterAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, characterArray);
        characterList.setAdapter(adapter);
        
        adapter.setNotifyOnChange(false); 
        characterArray.clear();
        characterArray = db.getCharacters();
        adapter.clear();
        adapter.addAll(characterArray);
        
        adapter.notifyDataSetChanged();
        
		db.close();
	}
}