package com.stryksta.swtorcentral;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.stryksta.swtorcentral.data.AddCharacterItem;
import com.stryksta.swtorcentral.data.CharacterItem;
import com.stryksta.swtorcentral.util.CharacterDatabase;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
 
public class CharacterEditActivity extends FragmentActivity implements OnItemSelectedListener {
	private CharacterDatabase db;
	LinkedHashMap<String, Integer> genderItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> raceItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> alignmentItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> classItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> advancedclassItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> crewSkillsclassItem = new LinkedHashMap<String, Integer>();
	HashMap<String, String> characterHash = new HashMap<String, String>();
	 
	static EditText characterLevel;
	static EditText characterLegacy;
	static EditText characterName;
	static EditText characterDescription;
	static Spinner characterClass;
	static Spinner characterAdvancedClass;
	static Spinner characterGender;
	static Spinner characterRace;
	static Spinner characterAlignment;
	static Spinner characterCrewSkill1;
	static Spinner characterCrewSkill2;
	static Spinner characterCrewSkill3;
	int cLevel;
	String cName;
	String cLegacy;
	String cDescription;
	int cGender;
	int cRace;
	int cAlignment;
	int cCrewSkill1;
	int cCrewSkill2;
	int cCrewSkill3;
	int cClass;
	int cAdvanced;
	int cCharacterID;
	
	String hName;
	String hLegacy;
	String hClass;
    String hLevel;
    String hDescription;
    String hRace;
    String hAdvanced_class;
    String hGender;
    String hAlignment;
    String hCrew_skill_1;
    String hCrew_skill_2;
    String hCrew_skill_3;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_add_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);
        
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        Bundle bundle = getIntent().getExtras();
		
        if ( bundle != null ) {
        	cCharacterID = bundle.getInt("character_id");
        	
        }
        
        characterLevel = (EditText) findViewById(R.id.characterLevel);
        characterName = (EditText) findViewById(R.id.characterName);
        characterLegacy = (EditText) findViewById(R.id.characterLegacy);
        characterDescription = (EditText) findViewById(R.id.characterDescription);
        characterClass = (Spinner) findViewById(R.id.characterClass);
        characterAdvancedClass = (Spinner) findViewById(R.id.characterAdvancedClass);
        characterGender = (Spinner) findViewById(R.id.characterGender);
        characterRace = (Spinner) findViewById(R.id.characterRace);
        characterAlignment = (Spinner) findViewById(R.id.characterAlignment);
        characterCrewSkill1 = (Spinner) findViewById(R.id.characterCrewSkill1);
        characterCrewSkill2 = (Spinner) findViewById(R.id.characterCrewSkill2);
        characterCrewSkill3 = (Spinner) findViewById(R.id.characterCrewSkill3);
        
        CharacterDatabase db = new CharacterDatabase(this);
        characterHash = db.getCharacter(cCharacterID);
        
        
        
        //Set character values
        hName = characterHash.get("name");
        hLegacy = characterHash.get("legacy");
        hLevel = characterHash.get("level");
        hDescription = characterHash.get("description");
        hRace = characterHash.get("race");
        hAdvanced_class = characterHash.get("advanced_class");
        hGender = characterHash.get("gender");
        hAlignment = characterHash.get("alignment");
        hCrew_skill_1 = characterHash.get("crew_skill_1");
        hCrew_skill_2 = characterHash.get("crew_skill_2");
        hCrew_skill_3 = characterHash.get("crew_skill_3");
        hClass = db.getClass(hAdvanced_class);
        
        characterName.setText(hName);
        characterLevel.setText(hLevel);
        characterDescription.setText(hDescription);
        
        addGenders();
        addRaces();
        addAlignment();
        addCrewSkills();
        addClasses();
        
        
        
        characterLevel.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
            	setCharacterLevel(CharacterEditActivity.this);
            }
        });
        
        characterClass.setOnItemSelectedListener(this);
        
     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    	//Log.d("SWTORCentral", );
    	addAdvancedClasses(classItem.get(parent.getItemAtPosition(pos).toString()));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.character_edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
	    	break;
	    case R.id.character_menu_save:
	    	if (characterLevel.getText().length() == 0) {
	    		AlertDialog.Builder alert = new AlertDialog.Builder(CharacterEditActivity.this);
         		alert.setTitle("Name is Required");
         		alert.setMessage("A Character Name is Required");
         		alert.setPositiveButton("OK", null);
         		alert.show();
	    	} else if(characterName.getText().length() == 0) {
	    		
	    	} else {
	    		CharacterDatabase db = new CharacterDatabase(this);
	    		
	    		cName = characterName.getText().toString();
	    		cLegacy = characterLegacy.getText().toString();
	    		cLevel = Integer.parseInt(characterLevel.getText().toString());
				cDescription = characterDescription.getText().toString();
				cGender = genderItem.get(characterGender.getSelectedItem().toString());
				cRace = raceItem.get(characterRace.getSelectedItem().toString());
				cAlignment = alignmentItem.get(characterAlignment.getSelectedItem().toString());
				cCrewSkill1 = crewSkillsclassItem.get(characterCrewSkill1.getSelectedItem().toString());
				cCrewSkill2 = crewSkillsclassItem.get(characterCrewSkill2.getSelectedItem().toString());
				cCrewSkill3 = crewSkillsclassItem.get(characterCrewSkill3.getSelectedItem().toString());
				//cClass = classItem.get(characterClass.getSelectedItem().toString());
				cAdvanced = advancedclassItem.get(characterAdvancedClass.getSelectedItem().toString());
	    		
	    		db.close();
	    		new saveCharacter().execute();
	    	}
	    	break;
	    }
	    return true;
	}
    
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
	        this.finish();
	    } else {
	        getFragmentManager().popBackStack();
	    }
    }
	
	public void addClasses() {
		CharacterDatabase db = new CharacterDatabase(this);
		classItem = db.getClasses();
		List<String> values = new ArrayList<String>(classItem.keySet());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterClass.setAdapter(dataAdapter);
		
		characterClass.setSelection(dataAdapter.getPosition(hClass));
	}
	
	public void addAdvancedClasses(int classid) {
		CharacterDatabase db = new CharacterDatabase(this);
		advancedclassItem = db.getAdvancedClasses(classid);
		List<String> values = new ArrayList<String>(advancedclassItem.keySet());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterAdvancedClass.setAdapter(dataAdapter);
		
		characterAdvancedClass.setSelection(dataAdapter.getPosition(hAdvanced_class));
	}
	
	public void addGenders() {
		CharacterDatabase db = new CharacterDatabase(this);
		genderItem = db.getGenders();
		List<String> values = new ArrayList<String>(genderItem.keySet());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterGender.setAdapter(dataAdapter);
		
		characterGender.setSelection(dataAdapter.getPosition(hGender));
	}
	
	public void addRaces() {
		CharacterDatabase db = new CharacterDatabase(this);
		raceItem = db.getRaces();
		List<String> values = new ArrayList<String>(raceItem.keySet());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterRace.setAdapter(dataAdapter);
		
		characterRace.setSelection(dataAdapter.getPosition(hRace));
	}
	
	public void addAlignment() {
		CharacterDatabase db = new CharacterDatabase(this);
		alignmentItem = db.getAlignment();
		List<String> values = new ArrayList<String>(alignmentItem.keySet());
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterAlignment.setAdapter(dataAdapter);
		
		characterAlignment.setSelection(dataAdapter.getPosition(hAlignment));
	}
	
	public void addCrewSkills() {
		CharacterDatabase db = new CharacterDatabase(this);
		crewSkillsclassItem = db.getCrewSkills();
		List<String> values = new ArrayList<String>(crewSkillsclassItem.keySet());
		
		ArrayAdapter<String> CrewSkill1Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		CrewSkill1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterCrewSkill1.setAdapter(CrewSkill1Adapter);
		
		ArrayAdapter<String> CrewSkill2Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		CrewSkill2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterCrewSkill2.setAdapter(CrewSkill2Adapter);
		
		ArrayAdapter<String> CrewSkill3Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
		CrewSkill3Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		characterCrewSkill3.setAdapter(CrewSkill3Adapter);
		db.close();
		
		characterCrewSkill1.setSelection(CrewSkill1Adapter.getPosition(hCrew_skill_1));
        characterCrewSkill2.setSelection(CrewSkill2Adapter.getPosition(hCrew_skill_2));
        characterCrewSkill3.setSelection(CrewSkill3Adapter.getPosition(hCrew_skill_3));
	}
	
	public static void setCharacterLevel(final Context context){
		AlertDialog.Builder	builder = new AlertDialog.Builder(context);
		builder.setTitle("Character Level");
		builder.setMessage("Please select the level of your character");
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rootView = inflater.inflate(R.layout.character_level_dialog, null);
		
		final NumberPicker CharacterLevel = (NumberPicker)rootView.findViewById(R.id.character_level);
 
		CharacterLevel.setMaxValue(55);
		CharacterLevel.setMinValue(1);
		CharacterLevel.setWrapSelectorWheel(true);
		builder.setView(rootView);
 
		
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {			
				characterLevel.setText(String.valueOf(CharacterLevel.getValue()));
			}
		});
		
		builder.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				characterLevel.setText("");
			}
		});
		builder.show();
	}

	private class saveCharacter extends AsyncTask<Void, Void, Void> {
		CharacterDatabase db = new CharacterDatabase(CharacterEditActivity.this);
		@Override
		protected Void doInBackground(Void... params) {

			try {
				//Log.e("SWTORCentral", "Clicked #2");
				
			} catch (Exception e) {
				if(e.getMessage() != null) {
				    Log.e("SWTORCentral", e.getMessage());
				}
			}
			
			// Debug the task thread name
			Log.d("SWTORCentral", Thread.currentThread().getName());
			return null;
		}

		protected void onPostExecute(Void result) {
			db.editCharacter(new AddCharacterItem(cAdvanced, cRace, cGender, cAlignment, 0, cLevel, cName, cLegacy, cCrewSkill1, cCrewSkill2, cCrewSkill3, cDescription), cCharacterID);
			db.close();
			Toast.makeText(CharacterEditActivity.this, "Character Edited Successfully", Toast.LENGTH_LONG).show();
			setResult(Activity.RESULT_OK);
			CharacterEditActivity.this.finish();
			
		}
	}
}