package com.stryksta.swtorcentral;
 
import java.util.ArrayList;

import com.stryksta.swtorcentral.data.CharacterItem;
import com.stryksta.swtorcentral.util.CharacterDatabase;
import com.stryksta.swtorcentral.util.CharacterDatabase;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
 
public class CharacterActivity extends FragmentActivity {
	private CharacterDatabase db;
	ArrayList<CharacterItem> characterArray = new ArrayList<CharacterItem>();
	private static final int ADD_PARTICIPANT = 1121;
	CharacterAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        //getActionBar().setTitle("Blank");
        
        updateCharacters();
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PARTICIPANT && resultCode == Activity.RESULT_OK)
        {
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
	
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
	
	private void updateCharacters(){
		CharacterDatabase db = new CharacterDatabase(this);
        characterArray = db.getCharacters();
        
        ListView characterList = (ListView) findViewById(R.id.characterListView);
        adapter = new CharacterAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, characterArray);
        characterList.setAdapter(adapter);
        characterList.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				Toast.makeText(CharacterActivity.this, adapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
				return false;
			}
        }); 
		db.close();
	}
}