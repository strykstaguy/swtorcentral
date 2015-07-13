package com.stryksta.swtorcentral;
 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.stryksta.swtorcentral.data.AddCharacterItem;
import com.stryksta.swtorcentral.util.database.CharacterDatabase;
import com.stryksta.swtorcentral.util.FloatingActionButton;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
 
public class CharacterAddActivity extends AppCompatActivity implements OnItemSelectedListener {
	private CharacterDatabase db;
	LinkedHashMap<String, Integer> genderItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> raceItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> alignmentItem = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> classItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> advancedclassItem = new LinkedHashMap<String, Integer>();
	LinkedHashMap<String, Integer> crewSkillsclassItem = new LinkedHashMap<String, Integer>();

    private Toolbar mToolbar;

	static EditText characterLevel;
	static EditText characterName;
	static EditText characterLegacy;
	static EditText characterDescription;
	static EditText characterClass;
	static EditText characterAdvancedClass;
	static EditText characterGender;
	static EditText characterRace;
	static EditText characterAlignment;
	static EditText characterCrewSkill1;
	static EditText characterCrewSkill2;
	static EditText characterCrewSkill3;

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
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_add_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        characterLevel = (EditText) findViewById(R.id.characterLevel);
        characterName = (EditText) findViewById(R.id.characterName);
        characterLegacy = (EditText) findViewById(R.id.characterLegacy);
        
        characterDescription = (EditText) findViewById(R.id.characterDescription);
        characterClass = (EditText) findViewById(R.id.characterClass);
        characterAdvancedClass = (EditText) findViewById(R.id.characterAdvancedClass);
        characterGender = (EditText) findViewById(R.id.characterGender);
        characterRace = (EditText) findViewById(R.id.characterRace);
        characterAlignment = (EditText) findViewById(R.id.characterAlignment);
        characterCrewSkill1 = (EditText) findViewById(R.id.characterCrewSkill1);
        characterCrewSkill2 = (EditText) findViewById(R.id.characterCrewSkill2);
        characterCrewSkill3 = (EditText) findViewById(R.id.characterCrewSkill3);

        cClass = 0;
        cAdvanced = 0;
        cRace = 4;
        cGender = 0;
        cAlignment = 0;
        cCrewSkill1 = 0;
        cCrewSkill2 = 0;
        cCrewSkill3 = 0;

        addGenders();
        addRaces();
        addAlignment();
        addCrewSkills();
        addClasses();

        FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_save))
                .withButtonColor(getResources().getColor(R.color.swtor_blue))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (characterLevel.getText().length() == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CharacterAddActivity.this);
                    alert.setTitle("Level is Required");
                    alert.setMessage("Please select a level");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                } else if(characterName.getText().length() == 0) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CharacterAddActivity.this);
                    alert.setTitle("Character Name is Required");
                    alert.setMessage("Please name your character");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                } else {
                    CharacterDatabase db = new CharacterDatabase(CharacterAddActivity.this);

                    cName = characterName.getText().toString();
                    cLegacy = characterLegacy.getText().toString();
                    cLevel = Integer.parseInt(characterLevel.getText().toString());
                    cDescription = characterDescription.getText().toString();

                    db.close();
                    new saveCharacter().execute();
                }
            }
        });

        characterLevel.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
            	setCharacterLevel(CharacterAddActivity.this);
            }
        });


     // Debug the thread name
     	Log.d("SWTORCentral", Thread.currentThread().getName());
        
    }
    
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    	//Log.d("SWTORCentral", );
    	//addAdvancedClasses(classItem.get(parent.getItemAtPosition(pos).toString()));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	    	this.finish();
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
        final List<String> values = new ArrayList<String>(classItem.keySet());
        final String[] list = classItem.keySet().toArray(new String[0]);

        characterClass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CharacterAddActivity.this)
                        .setTitle("Classes")
                        .setItems(list, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CharacterAddActivity.this, list[which], Toast.LENGTH_SHORT).show();
                                //characterClass.setText(text);
                                // = classItem.get(text);

                                //addAdvancedClasses(classItem.get(text));
                            }
                        });

                alertBuilder.create();
                alertBuilder.show();
            }
        });
        db.close();
    }

	public void addAdvancedClasses(int classid) {
		CharacterDatabase db = new CharacterDatabase(this);
		advancedclassItem = db.getAdvancedClasses(classid);
		final List<String> values = new ArrayList<String>(advancedclassItem.keySet());
        final String[] list = advancedclassItem.keySet().toArray(new String[0]);

        characterAdvancedClass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        db.close();
	}

    public void addGenders() {
        CharacterDatabase db = new CharacterDatabase(this);
        genderItem = db.getGenders();
        final List<String> values = new ArrayList<String>(genderItem.keySet());
        final String[] list = genderItem.keySet().toArray(new String[0]);

        characterGender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        db.close();
    }

    public void addRaces() {
        CharacterDatabase db = new CharacterDatabase(this);
        raceItem = db.getRaces();
        final List<String> values = new ArrayList<String>(raceItem.keySet());
        final String[] list = raceItem.keySet().toArray(new String[0]);

        characterRace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        db.close();
    }

    public void addAlignment() {
        CharacterDatabase db = new CharacterDatabase(this);
        alignmentItem = db.getAlignment();
        final List<String> values = new ArrayList<String>(alignmentItem.keySet());
        final String[] list = alignmentItem.keySet().toArray(new String[0]);

        characterAlignment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        db.close();
    }

    public void addCrewSkills() {
        CharacterDatabase db = new CharacterDatabase(this);
        crewSkillsclassItem = db.getCrewSkills();
        final List<String> values = new ArrayList<String>(crewSkillsclassItem.keySet());
        final String[] list = crewSkillsclassItem.keySet().toArray(new String[0]);

        characterCrewSkill1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        characterCrewSkill2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        characterCrewSkill3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        db.close();
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
		CharacterDatabase db = new CharacterDatabase(CharacterAddActivity.this);
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
			db.addCharacter(new AddCharacterItem(cClass, cAdvanced, cRace, cGender, cAlignment, 0, cLevel, cName, cLegacy, cCrewSkill1, cCrewSkill2, cCrewSkill3, cDescription));
			db.close();
			Toast.makeText(CharacterAddActivity.this, "Character Added Successfully", Toast.LENGTH_LONG).show();
			setResult(Activity.RESULT_OK);
			CharacterAddActivity.this.finish();
			
		}
	}
}