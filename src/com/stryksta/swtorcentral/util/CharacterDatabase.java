package com.stryksta.swtorcentral.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.stryksta.swtorcentral.data.AddCharacterItem;
import com.stryksta.swtorcentral.data.CharacterItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class CharacterDatabase extends SQLiteAssetHelper {
	
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "swtor";
    
    public CharacterDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
    
	public ArrayList<CharacterItem> getCharacters() {
        ArrayList<CharacterItem> characterItem = new ArrayList<CharacterItem>();
        
        SQLiteDatabase db = this.getReadableDatabase();
        //String sqlSelect = "SELECT * FROM character order by _id asc";
        StringBuilder builder = new StringBuilder();
        String sqlSelect = builder
        	.append("SELECT character._id, character.name, character.level, character.description, race.name race, advanced_classes.class advanced_class, gender.name gender, alignment.name alignment, cs1.name crew_skill_1, cs2.name crew_skill_2, cs3.name crew_skill_3 ")
            .append("FROM character ")
            .append("LEFT JOIN race ")
            .append("ON race._id = character.race_id ")
            .append("LEFT JOIN advanced_classes ")
            .append("ON character.advanced_class_id = advanced_classes._id ")
            .append("LEFT JOIN gender ")
            .append("ON character.gender_id = gender._id ")
            .append("LEFT JOIN alignment ")
            .append("ON character.alignment_id = alignment._id ")
            .append("LEFT JOIN crew_skill cs1 ")
            .append("ON character.crew_skill_id_1 = cs1._id ")
            .append("LEFT JOIN crew_skill cs2 ")
            .append("ON character.crew_skill_id_2 = cs2._id ")
            .append("LEFT JOIN crew_skill cs3 ")
            .append("ON character.crew_skill_id_3 = cs3._id ")
            .append("ORDER BY character._id asc")
        .toString();
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
                int CharacterID = c.getInt(c.getColumnIndex("_id"));
                String advanced_class = c.getString(c.getColumnIndex("advanced_class"));
                String race = c.getString(c.getColumnIndex("race"));
                String gender = c.getString(c.getColumnIndex("gender"));
                String alignment = c.getString(c.getColumnIndex("alignment"));
                String level = c.getString(c.getColumnIndex("level"));
                String name = c.getString(c.getColumnIndex("name"));
                String crew_skill_1 = c.getString(c.getColumnIndex("crew_skill_1"));
                String crew_skill_2 = c.getString(c.getColumnIndex("crew_skill_2"));
                String crew_skill_3 = c.getString(c.getColumnIndex("crew_skill_3"));
                String description = c.getString(c.getColumnIndex("description"));

                characterItem.add(new CharacterItem(CharacterID, advanced_class, race, gender, alignment, level, name, crew_skill_1, crew_skill_2, crew_skill_3, description));
            } while (c.moveToNext());
        }
        
        //c.moveToFirst();
        c.close();
        db.close();
        return characterItem;

    }

    public void addCharacter(AddCharacterItem character){
         
    	SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put("advanced_class_id", character.getAdvancedClassId());
        values.put("race_id", character.getRace());
        values.put("gender_id", character.getGender());
        values.put("alignment_id", character.getAlignment());
        values.put("skill_tree_build_id", character.getSkillTreeBuildId());
        values.put("level", character.getLevel());
        values.put("name", character.getName());
        values.put("crew_skill_id_1", character.getCrewSkillId_1());
        values.put("crew_skill_id_2", character.getCrewSkillId_2());
        values.put("crew_skill_id_3", character.getCrewSkillId_3());
        values.put("description", character.getDescription());
        db.insert("character", null, values);
        /*
       if (!isCharacterExist(db, character.getName())) {
            
        }
        */
    }
    
    public void editCharacter(AddCharacterItem character, int ID){
        
    	SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put("advanced_class_id", character.getAdvancedClassId());
        values.put("race_id", character.getRace());
        values.put("gender_id", character.getGender());
        values.put("alignment_id", character.getAlignment());
        values.put("skill_tree_build_id", character.getSkillTreeBuildId());
        values.put("level", character.getLevel());
        values.put("name", character.getName());
        values.put("crew_skill_id_1", character.getCrewSkillId_1());
        values.put("crew_skill_id_2", character.getCrewSkillId_2());
        values.put("crew_skill_id_3", character.getCrewSkillId_3());
        values.put("description", character.getDescription());
        db.update("character", values, "_id = ? ", new String[] {String.valueOf(ID)});
    }
    
    public HashMap<String, String> getCharacter(int id) {
   	 HashMap<String, String> character = new HashMap<String, String>();
  		SQLiteDatabase db = getReadableDatabase();
  		StringBuilder builder = new StringBuilder();
  		String sqlSelect = builder
  	        	.append("SELECT character._id, character.name, character.level, character.description, race.name race, advanced_classes.class advanced_class, gender.name gender, alignment.name alignment, cs1.name crew_skill_1, cs2.name crew_skill_2, cs3.name crew_skill_3 ")
  	            .append("FROM character ")
  	            .append("LEFT JOIN race ")
  	            .append("ON race._id = character.race_id ")
  	            .append("LEFT JOIN advanced_classes ")
  	            .append("ON character.advanced_class_id = advanced_classes._id ")
  	            .append("LEFT JOIN gender ")
  	            .append("ON character.gender_id = gender._id ")
  	            .append("LEFT JOIN alignment ")
  	            .append("ON character.alignment_id = alignment._id ")
  	            .append("LEFT JOIN crew_skill cs1 ")
  	            .append("ON character.crew_skill_id_1 = cs1._id ")
  	            .append("LEFT JOIN crew_skill cs2 ")
  	            .append("ON character.crew_skill_id_2 = cs2._id ")
  	            .append("LEFT JOIN crew_skill cs3 ")
  	            .append("ON character.crew_skill_id_3 = cs3._id ")
  	            .append("WHERE character._id  = ?")
  	        .toString();
  		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(id)});
  		
  		if (c.moveToFirst()) {
              do {
            	  character.put("name", c.getString(c.getColumnIndex("name")));
            	  character.put("level", c.getString(c.getColumnIndex("level")));
            	  character.put("description", c.getString(c.getColumnIndex("description")));
            	  character.put("race", c.getString(c.getColumnIndex("race")));
            	  character.put("advanced_class", c.getString(c.getColumnIndex("advanced_class")));
            	  character.put("gender", c.getString(c.getColumnIndex("gender")));
            	  character.put("alignment", c.getString(c.getColumnIndex("alignment")));
            	  character.put("crew_skill_1", c.getString(c.getColumnIndex("crew_skill_1")));
            	  character.put("crew_skill_2", c.getString(c.getColumnIndex("crew_skill_2")));
            	  character.put("crew_skill_3", c.getString(c.getColumnIndex("crew_skill_3")));
              } while (c.moveToNext());
          }
  		
  		//c.moveToFirst();
  		c.close();
  		return character;

  	}
    
    public boolean isCharacterExist(SQLiteDatabase db, String character) {
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + "character"
                + " WHERE name = '" + character + "'", new String[] {});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }
    
    public LinkedHashMap<String, Integer> getClasses(){
    	LinkedHashMap<String, Integer> classItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM classes order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
            	classItem.put(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return classItem;
    }
    
    public LinkedHashMap<String, Integer> getAdvancedClasses(int classid){
    	LinkedHashMap<String, Integer> advancedclassItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM advanced_classes where class_id = ? order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(classid)});
        
        if (c.moveToFirst()) {
            do {
            	advancedclassItem.put(c.getString(c.getColumnIndex("class")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return advancedclassItem;
    }
    
    public LinkedHashMap<String, Integer> getGenders(){
    	LinkedHashMap<String, Integer> genderItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM gender order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
               	genderItem.put(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return genderItem;
    }
    
    public LinkedHashMap<String, Integer> getRaces(){
    	LinkedHashMap<String, Integer> raceItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM race order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
            	raceItem.put(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return raceItem;
    }
    
    public LinkedHashMap<String, Integer> getAlignment(){
    	LinkedHashMap<String, Integer> alignmentItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM alignment order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
            	alignmentItem.put(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return alignmentItem;
    }
    
    public LinkedHashMap<String, Integer> getCrewSkills(){
    	LinkedHashMap<String, Integer> crewSkillsItem = new LinkedHashMap<String, Integer>();
    	SQLiteDatabase db = this.getReadableDatabase();
        String sqlSelect = "SELECT * FROM crew_skill order by _id asc";
        Cursor c = db.rawQuery(sqlSelect, null);
        
        if (c.moveToFirst()) {
            do {
            	crewSkillsItem.put(c.getString(c.getColumnIndex("name")), c.getInt(c.getColumnIndex("_id")));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return crewSkillsItem;
    }
    
    public String getClass(String txtAdvancedClass) {
		String txtClassName = null;
		SQLiteDatabase db = getReadableDatabase();
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT classes.name ")
		    .append("FROM classes ")
		    .append("LEFT JOIN advanced_classes ")
		    .append("ON advanced_classes.class_id = classes._id ")
		    .append("WHERE advanced_classes.class = ?")
		.toString();
		
		Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtAdvancedClass)});
		
		if (c.moveToFirst()) {
			txtClassName = c.getString(c.getColumnIndex("name"));
        }
		
		//c.moveToFirst();
		c.close();
		return txtClassName;

	}
    
    public ArrayList<String> CharacterSelectionList() {
		SQLiteDatabase db = getReadableDatabase();
		
		ArrayList<String> characterArray = new ArrayList<String>();
		
		String sqlSelect = "SELECT * FROM character";
        Cursor c = db.rawQuery(sqlSelect, null);
		
        if (c.moveToFirst()) {
            do {
            	String characterName;
    			characterName = c.getString(c.getColumnIndex("name"));
    			characterArray.add(characterName);
            } while (c.moveToNext());
        }
		
        characterArray.add("Add Character");
        characterArray.add("Logout");

		c.close();
		return characterArray;

	}
    
    public String getCharacterID(String txtName) {
		String txtID = null;
		SQLiteDatabase db = getReadableDatabase();
		
		 String sqlSelect = "SELECT _id FROM character WHERE name = ?";
	     Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtName)});
		
		if (c.moveToFirst()) {
			txtID = c.getString(c.getColumnIndex("_id"));
        }
		
		//c.moveToFirst();
		c.close();
		return txtID;
	}
    
    public String getCharacterImage(String txtName) {
		String txtID = "ic_action_user";
		SQLiteDatabase db = getReadableDatabase();
		StringBuilder builder = new StringBuilder();
		String sqlSelect = builder
			.append("SELECT advanced_classes.advanced_class_icon ")
		    .append("FROM character ")
		    .append("LEFT JOIN advanced_classes ")
		    .append("ON character.advanced_class_id = advanced_classes._id ")
		    .append("WHERE character.name = ?")
		.toString();
		
	    Cursor c = db.rawQuery(sqlSelect, new String[]{String.valueOf(txtName)});
	    	 
		if (c.moveToFirst()) {
			txtID = c.getString(c.getColumnIndex("advanced_class_icon")) + "_light";
        }
		
		//c.moveToFirst();
		c.close();
		return txtID;

	}
}